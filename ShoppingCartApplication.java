import java.util.Scanner;

public class ShoppingCartApplication {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Online Shopping Cart ---");
                System.out.println("1. Add Item to Cart");
                System.out.println("2. Remove Item from Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter item name: ");
                            String addItem = scanner.nextLine();
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            cart.addItem(addItem, quantity);
                            break;

                        case 2:
                            System.out.print("Enter item name to remove: ");
                            String removeItem = scanner.nextLine();
                            cart.removeItem(removeItem);
                            break;

                        case 3:
                            cart.viewCart();
                            break;

                        case 4:
                            cart.checkout();
                            break;

                        case 5:
                            System.out.println("Exiting... Thank you for shopping!");
                            return;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (ItemNotFoundException | OutOfStockException | EmptyCartException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            }
        }
    }
}