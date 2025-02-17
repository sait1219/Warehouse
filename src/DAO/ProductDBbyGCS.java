package DAO;

import com.google.cloud.storage.*;
import com.google.gson.Gson;
import Model.Product;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
set GOOGLE_APPLICATION_CREDENTIALS="C:\Users\rnsai\Downloads\acs-course-stuttgart-449514-623d764adf18.json"

public class ProductDBbyGCS implements ProductDAO {
    private final Storage storage;
    private final String bucketName = "product_db";
    private final String jdbcUrl = "private final String jdbcUrl = "jdbc:mysql://google/customerdb?cloudSqlInstance=my-project:us-central1:customer-instance&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=acs@2025";

    public ProductDBbyGCS() {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    @Override
public boolean insertProduct(Product product) {
    try {
        String objectName = "products/" + product.getId() + ".json";
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/json").build();
        String json = new Gson().toJson(product);
        storage.create(blobInfo, json.getBytes(StandardCharsets.UTF_8));
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    @Override
public List<Product> getAllProducts() {
    List<Product> productList = new ArrayList<>();
    try {
        // List all objects in the "products/" directory of the GCS bucket
        Page<Blob> blobs = storage.list(bucketName, Storage.BlobListOption.prefix("products/"));
        for (Blob blob : blobs.iterateAll()) {
            String json = new String(blob.getContent(), StandardCharsets.UTF_8);
            Product product = new Gson().fromJson(json, Product.class);
            productList.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return productList;
}


    @Override
public Product getProductByID(int id) {
    try {
        String objectName = "products/" + id + ".json";
        BlobId blobId = BlobId.of(bucketName, objectName);
        Blob blob = storage.get(blobId);
        if (blob == null) {
            return null; // Product not found
        }
        String json = new String(blob.getContent());
        return new Gson().fromJson(json, Product.class);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


    @Override
public boolean updateProduct(int id, String name, double quantity) {
    try {
        // Define the object name in GCS
        String objectName = "products/" + id + ".json";
        BlobId blobId = BlobId.of(bucketName, objectName);
        Blob blob = storage.get(blobId);

        // If the product does not exist, return false
        if (blob == null) {
            System.out.println("Product not found: " + id);
            return false;
        }

        // Create a new Product object with updated values
        Product updatedProduct = new Product(id, name, quantity);

        // Convert updated product to JSON
        String json = new Gson().toJson(updatedProduct);

        // Update the blob content
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/json").build();
        storage.create(blobInfo, json.getBytes(StandardCharsets.UTF_8));

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    @Override
public boolean removeProduct(int id) {
    try {
        // Define the object name in GCS
        String objectName = "products/" + id + ".json";
        BlobId blobId = BlobId.of(bucketName, objectName);
        
        // Delete the object from GCS
        boolean deleted = storage.delete(blobId);
        
        if (deleted) {
            System.out.println("Product deleted successfully: " + id);
        } else {
            System.out.println("Product not found: " + id);
        }

        return deleted;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    @Override
public boolean changeProduct(int id, Product product) {
    try {
        // Define the object name in GCS
        String objectName = "products/" + id + ".json";
        BlobId blobId = BlobId.of(bucketName, objectName);
        Blob blob = storage.get(blobId);

        // If the product does not exist, return false
        if (blob == null) {
            System.out.println("Product not found: " + id);
            return false;
        }

        // Convert the updated product to JSON
        String json = new Gson().toJson(product);

        // Overwrite the existing product data in GCS
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/json").build();
        storage.create(blobInfo, json.getBytes(StandardCharsets.UTF_8));

        System.out.println("Product updated successfully: " + id);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    
    @Override
    // Dispatch product from GCS to Google Cloud SQL
    public boolean dispatchProduct(int productId) {
        try {
            // Step 1: Retrieve the product from Google Cloud Storage
            String objectName = "products/" + productId + ".json";
            BlobId blobId = BlobId.of(bucketName, objectName);
            Blob blob = storage.get(blobId);

            if (blob == null) {
                System.out.println("Product not found in GCS: " + productId);
                return false;
            }

            // Convert JSON to Product object
            String json = new String(blob.getContent(), StandardCharsets.UTF_8);
            Product product = new Gson().fromJson(json, Product.class);

            // Step 2: Insert the product into Google Cloud SQL
            String insertQuery = "INSERT INTO dispatched_products (id, name, quantity) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(jdbcUrl);
                 PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

                stmt.setInt(1, product.getId());
                stmt.setString(2, product.getName());
                stmt.setDouble(3, product.getQuantity());

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted == 1) {
                    System.out.println("Product dispatched to Cloud SQL: " + productId);

                    // Step 3: (Optional) Remove the product from Google Cloud Storage
                    boolean deleted = storage.delete(blobId);
                    if (deleted) {
                        System.out.println("Product removed from GCS: " + productId);
                    } else {
                        System.out.println("Failed to remove product from GCS: " + productId);
                    }
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

}
