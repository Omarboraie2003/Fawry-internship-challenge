import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("======== Test 1: Basic Successful Checkout ========");
        testBasicCheckout();

        System.out.println("\n======== Test 2: Add Quantity Greater Than Stock ========");
        testAddMoreThanStock();

        System.out.println("\n======== Test 3: Add Zero and Negative Quantity ========");
        testInvalidQuantities();

        System.out.println("\n======== Test 4: Add Expired Product ========");
        testAddExpiredProduct();

        System.out.println("\n======== Test 5: Checkout with Empty Cart ========");
        testEmptyCartCheckout();

        System.out.println("\n======== Test 6: Insufficient Customer Balance ========");
        testInsufficientBalance();

        System.out.println("\n======== Test 7: Checkout with Exact Balance ========");
        testExactBalance();

        System.out.println("\n======== Test 8: Non-Shippable Only Checkout ========");
        testNonShippableOnly();

        System.out.println("\n======== Test 9: Same Product Added Twice ========");
        testDuplicateProductAdd();

        System.out.println("\n======== Test 10: High Shipping Weight Calculation ========");
        testHeavyShipping();

        System.out.println("\n======== Test 11: Create Expirable + Shippable Product with NULL Expiry ========");
        testExpirableShippableDate();
    }

    static void testBasicCheckout() {
        Customer customer = new Customer("Omar", 1000.0);
        Cart cart = new Cart();

        BaseProduct cheese = new ShippableProducts("Cheese", 100.0, 5, 0.4) {
            public boolean isExpired() { return false; }
        };
        BaseProduct tv = new ShippableProducts("TV", 200.0, 3, 0.7) {
            public boolean isExpired() { return false; }
        };
        BaseProduct scratch = new BaseProduct("Scratch", 50.0, 10) {
            public boolean isShippable() { return false; }
            public boolean isExpired() { return false; }
        };

        cart.addProduct(cheese, 2);
        cart.addProduct(tv, 1);
        cart.addProduct(scratch, 1);
        CheckoutService.checkout(customer, cart);
    }

    static void testAddMoreThanStock() {
        BaseProduct cheese = new ShippableProducts("Cheese", 100.0, 2, 0.4) {
            public boolean isExpired() { return false; }
        };
        Cart cart = new Cart();
        cart.addProduct(cheese, 3);
    }

    static void testInvalidQuantities() {
        BaseProduct item = new BaseProduct("Item", 10.0, 10) {
            public boolean isExpired() { return false; }
        };
        Cart cart = new Cart();

        cart.addProduct(item, 0);   
        cart.addProduct(item, -2);  
    }

    static void testAddExpiredProduct() {
        BaseProduct expired = new ExpirableProduct("Old Cheese", 80.0, 5, LocalDate.of(2022, 1, 1));
        Cart cart = new Cart();
        cart.addProduct(expired, 1);
    }

    static void testEmptyCartCheckout() {
        Customer customer = new Customer("Omar", 1000.0);
        Cart cart = new Cart();
        CheckoutService.checkout(customer, cart);
    }

    static void testInsufficientBalance() {
        Customer customer = new Customer("Rana", 100.0);
        Cart cart = new Cart();
        BaseProduct tv = new ShippableProducts("TV", 200.0, 2, 0.7) {
            public boolean isExpired() { return false; }
        };
        cart.addProduct(tv, 1);
        CheckoutService.checkout(customer, cart);
    }

    static void testExactBalance() {
        Customer customer = new Customer("Exact Pay", 215.0);
        Cart cart = new Cart();
        BaseProduct tv = new ShippableProducts("TV", 200.0, 2, 0.7) {
            public boolean isExpired() { return false; }
        };
        cart.addProduct(tv, 1); 
        CheckoutService.checkout(customer, cart);
    }

    static void testNonShippableOnly() {
        Customer customer = new Customer("NoShip", 1000.0);
        Cart cart = new Cart();
        BaseProduct code = new BaseProduct("E-Card", 100.0, 3) {
            public boolean isShippable() { return false; }
            public boolean isExpired() { return false; }
        };
        cart.addProduct(code, 1);
        CheckoutService.checkout(customer, cart);
    }

    static void testDuplicateProductAdd() {
        Customer customer = new Customer("Doubler", 1000.0);
        Cart cart = new Cart();
        BaseProduct item = new BaseProduct("USB", 50.0, 10) {
            public boolean isExpired() { return false; }
        };
        cart.addProduct(item, 2);
        cart.addProduct(item, 3); 
        CheckoutService.checkout(customer, cart);
    }

    static void testHeavyShipping() {
        Customer customer = new Customer("Heavy", 10000.0);
        Cart cart = new Cart();
        BaseProduct fridge = new ShippableProducts("Fridge", 2000.0, 3, 25.0) {
            public boolean isExpired() { return false; }
        };
        cart.addProduct(fridge, 2); 
        CheckoutService.checkout(customer, cart);
    }

    static void testExpirableShippableDate() {
    
    LocalDate expiredDate = LocalDate.of(2022, 1, 1);
    BaseProduct expiredYogurt = new ExpirableShippableProduct("Yogurt", 50.0, 5, 0.4, expiredDate);

   
}
}






