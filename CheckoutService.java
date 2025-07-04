import java.util.List;
import java.util.Map;

public class CheckoutService {

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Checkout failed: Cart is empty!");
        }

        for (Map.Entry<BaseProduct, Integer> entry : cart.getItems().entrySet()) {
            BaseProduct product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired()) {
                throw new IllegalStateException(product.getProductName() + " is expired.");
            }

            if (product.getProductQuantity() < quantity) {
                throw new IllegalStateException(product.getProductName() + " is out of stock.");
            }
        }

        double subtotal = cart.calculateSubtotal();
        double shippingFees = cart.getShippableItems().stream()
                .mapToDouble(Shippable::getWeight)
                .sum() * 10;

        double totalprice = subtotal + shippingFees;

        if (customer.getBalance() < totalprice) {
            System.out.println("Checkout failed: Insufficient funds");
            return;
        }

        List<Shippable> shippables = cart.getShippableItems();

        if (!shippables.isEmpty()) {
            ShippingService.printShipmentNotice(shippables);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<BaseProduct, Integer> entry : cart.getItems().entrySet()) {
            BaseProduct product = entry.getKey();
            int qty = entry.getValue();
            double totalProductPrice = product.getProductPrice() * qty;
            System.out.printf("%dx %-12s %.0f\n", qty, product.getProductName(), totalProductPrice);
            product.reduceProductQantity(qty);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal\t%.0f\n", subtotal);
        System.out.printf("Shipping\t%.0f\n", shippingFees);
        System.out.printf("Amount\t\t%.0f\n", totalprice);

        customer.withdraw(totalprice);
        System.out.printf("Remaining balance: %.2f\n", customer.getBalance());

        cart.clear(); // Clear the cart after checkout
    }
}


  
