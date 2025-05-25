package dao;

import java.util.List;
import model.Product;
import util.FileUtil;

public class ProductDAO {

    // Add a product
    public static void addProduct(Product product) {
        List<Product> products = FileUtil.loadFromFile();
        products.add(product);
        FileUtil.saveToFile(products);
        System.out.println("Product added successfully!");
    }

    // Get all products
    public static List<Product> getAllProducts() {
    List<Product> products = FileUtil.loadFromFile();
    System.out.println("DEBUG: Loaded " + products.size() + " products.");
    return products;
}


    // Update product by ID
    public static boolean updateProduct(int id, int newQty, double newPrice) {
        List<Product> products = FileUtil.loadFromFile();
        boolean found = false;

        for (Product p : products) {
            if (p.getId() == id) {
                p.setQuantity(newQty);
                p.setPrice(newPrice);
                found = true;
                break;
            }
        }

        if (found) {
            FileUtil.saveToFile(products);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product ID not found.");
        }

        return found;
    }

    // Delete product by ID
    public static boolean deleteProduct(int id) {
        List<Product> products = FileUtil.loadFromFile();
        boolean found = products.removeIf(p -> p.getId() == id);

        if (found) {
            FileUtil.saveToFile(products);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product ID not found.");
        }

        return found;
    }
}

