import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Main class for inventory management system
public class inventory {
    // Static scanner for user input
    static Scanner scan = new Scanner(System.in);
    // Arrays to store product names and quantities
    static String[] productName;
    // StringBuilder to build the output text
    static StringBuilder textPrint = new StringBuilder();
    static int[] productNum;
    // Total quantity counter
    static int total = 0;

    // Main method to run the program
    public static void main(String[] args) {
        // Ask for the number of products to initialize
        int num = askProductNum();
        productName = new String[num];
        productNum = new int[num];

        // Loop through the array while asking for each product name and quantity
        inputProducts(num);

        // Build the string to display
        textPrint.append("Products and Quantities:");
        for (int i = 0; i < num; i++) {
            displayAll(productName[i], productNum[i]);
        }
        int out = numProductsOutStock(productNum);
        int in = numProductsInStock(productNum);
        displayQuantity(productNum);
        textPrint.append("\nIn Stock: ").append(in);
        textPrint.append("\nOut of Stock: ").append(out);

        // Write the string into the file
        try (
                FileWriter writer = new FileWriter("Inventory.txt");
                BufferedWriter buffer = new BufferedWriter(writer);
        ) {
            buffer.write(textPrint.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to ask for the number of products with input validation
    static int askProductNum() {
        int num = 0;
        while (true) {
            try {
                print("Enter number of products: ");
                num = scan.nextInt();
                if (num < 0) throw new RuntimeException();
                scan.nextLine();
                return num;
            } catch (Exception e) {
                scan.nextLine();
                print("Invalid input. Please try again\n");
            }
        }
    }

    // Method to input product names and quantities
    static void inputProducts(int num) {
        for (int i = 0; i < num; i++) {
            print("Enter name of product " + (i + 1) + ": ");
            productName[i] = scan.nextLine();
            while (true) {
                try {
                    print("Enter quantity of " + productName[i] + ": ");
                    productNum[i] = scan.nextInt();
                    if (productNum[i] < 0) throw new RuntimeException();
                    scan.nextLine();
                    break;
                } catch (Exception e) {
                    scan.nextLine();
                    print("Invalid quantity. Please try again.\n");
                }
            }
        }
    }

    // Method to append product and quantity to the display string
    static void displayAll(String products, int quantity) {
        textPrint.append("\n").append(products).append(" - ").append(quantity);
    }

    // Method to count products out of stock (quantity == 0)
    static int numProductsOutStock(int[] quantity) {
        int outStock = 0;
        for (int num : quantity) {
            if (num == 0) outStock++;
        }
        return outStock;
    }

    // Method to count products in stock (quantity > 0)
    static int numProductsInStock(int[] quantity) {
        int inStock = 0;
        for (int num : quantity) {
            if (num > 0) inStock++;
        }
        return inStock;
    }

    // Method to calculate and append total quantity
    static void displayQuantity(int[] quantity) {
        for (int num : quantity) {
            total += num;
        }
        textPrint.append("\n\nTotal Quantity: ").append(total);
    }

    // Utility method to print text
    static void print(Object txt) {
        System.out.print(txt);
    }
}