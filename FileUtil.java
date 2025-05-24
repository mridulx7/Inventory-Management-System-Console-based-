package util;

import java.io.*;
import java.util.*;
import model.Product;

public class FileUtil {
    private static final String FILE_PATH = "inventory.txt";
   
    public static void initializeFile() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("inventory.txt created successfully.");
                } else {
                    System.out.println("Failed to create inventory.txt.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while creating the file: " + e.getMessage());
        }
    }

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static List<Product> loadFromFile() {
        List<Product> products = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Debug: Show absolute file path
        System.out.println("🔍 Looking for file at: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("File not found.");
            return products;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 4) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        int quantity = Integer.parseInt(parts[2].trim());
                        double price = Double.parseDouble(parts[3].trim());
                        products.add(new Product(id, name, quantity, price));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping malformed line: " + line);
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return products;
    }

    public static void saveToFile(List<Product> products) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Product p : products) {
                pw.println(p.getId() + "," + p.getName() + "," + p.getQuantity() + "," + p.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
