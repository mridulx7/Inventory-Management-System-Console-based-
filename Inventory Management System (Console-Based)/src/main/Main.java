package main;

import dao.ProductDAO;
import java.util.List;
import java.util.Scanner;
import model.Product;
import util.FileUtil;

public class Main {
    public static void main(String[] args) {

        FileUtil.initializeFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Inventory Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();

                    if (id < 0 || qty < 0 || price < 0 || name.trim().isEmpty()) {
                    System.out.println("Invalid input. ID, quantity, and price must be non-negative, and name must not be empty.");
                    break;
                }

                List<Product> existingProducts = ProductDAO.getAllProducts();
                boolean duplicateId = existingProducts.stream().anyMatch(p -> p.getId() == id);
                if (duplicateId) {
                    System.out.println("Product with this ID already exists.");
                    break;
                }

                    Product p = new Product(id, name, qty, price);
                    ProductDAO.addProduct(p);
                }

                  case 2 -> {
                    System.out.println("Attempting to load products...");
                    List<Product> productsList = ProductDAO.getAllProducts();
                    
                    if (productsList.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        System.out.println("\nID\tName\tQty\tPrice");
                        for (Product p : productsList) {
                            System.out.printf("%d\t%s\t%d\t%.2f\n",
                                    p.getId(), p.getName(), p.getQuantity(), p.getPrice());
                        }
                    }
                    }



                    case 3 -> {
                    System.out.print("Enter product ID to update: ");
                    int id = scanner.nextInt();

                    List<Product> existingProducts = ProductDAO.getAllProducts();
                    boolean exists = existingProducts.stream().anyMatch(p -> p.getId() == id);
                    if (!exists) {
                        System.out.println("Product ID not found.");
                        break;
                    }

                    System.out.print("Enter new quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    double price = scanner.nextDouble();

                    if (qty < 0 || price < 0) {
                        System.out.println("Quantity and price must be non-negative.");
                        break;
                    }

                    ProductDAO.updateProduct(id, qty, price);
                }


                case 4 -> {
                System.out.print("Enter product ID to delete: ");
                int id = scanner.nextInt();

                List<Product> existingProducts = ProductDAO.getAllProducts();
                boolean exists = existingProducts.stream().anyMatch(p -> p.getId() == id);
                if (!exists) {
                    System.out.println("Product ID not found.");
                    break;
                }

                ProductDAO.deleteProduct(id);
            }


                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
