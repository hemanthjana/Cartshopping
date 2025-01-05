import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<String, Integer> cart = new HashMap<>();
    private Map<String, Integer> stock = new HashMap<>();

    public ShoppingCart() {
        // Initialize stock with items and quantities
        stock.put("Laptop", 5);
        stock.put("Phone", 10);
        stock.put("Headphones", 15);
    }

    // Add item to the cart
    public void addItem(String item, int quantity) throws OutOfStockException {
        if (!stock.containsKey(item)) {
            throw new OutOfStockException("Item not available in stock: " + item);
        }

        int availableStock = stock.get(item);
        if (quantity > availableStock) {
            throw new OutOfStockException("Only " + availableStock + " units of " + item + " are available.");
        }

        cart.put(item, cart.getOrDefault(item, 0) + quantity);
        stock.put(item, availableStock - quantity);
        System.out.println(quantity + " units of " + item + " added to the cart.");
    }

    // Remove item from the cart
    public void removeItem(String item) throws ItemNotFoundException {
        if (!cart.containsKey(item)) {
            throw new ItemNotFoundException("Item not found in cart: " + item);
        }

        int removedQuantity = cart.remove(item);
        stock.put(item, stock.get(item) + removedQuantity);
        System.out.println(item + " removed from the cart.");
    }

    // View cart
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " units");
            }
        }
    }

    // Checkout
    public void checkout() throws EmptyCartException {
        if (cart.isEmpty()) {
            throw new EmptyCartException("Cannot checkout with an empty cart!");
        }

        System.out.println("Checkout successful! Items purchased:");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " units");
        }
        cart.clear();
    }
}
