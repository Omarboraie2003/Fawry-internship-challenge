import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<BaseProduct, Integer> orderedItems = new LinkedHashMap<>();

    public Map<BaseProduct, Integer> getItems() {
    return orderedItems;
}


    public void addProduct(BaseProduct product, int quantity) {
        if (quantity <= 0) {
            System.out.println("please add a valid quantity");
        }

        if (product.isExpired()) {
            System.out.println("Sorry! The item is expired");
        }

        if (product.getProductQuantity() < quantity) {
            System.out.println("Not enough stock for " +  product.getProductName());
        }

        orderedItems.merge(product, quantity, Integer::sum);
    }

      public Boolean isEmpty(){
         return orderedItems.isEmpty();

    }

       public double calculateSubtotal() {
        double subtotal = 0;
        for (Map.Entry<BaseProduct, Integer> entry : orderedItems.entrySet()) {
            subtotal += entry.getKey().getProductPrice() * entry.getValue();
        }
        return subtotal;
    }
        //this list will be sent to ShippingService 
       public List<Shippable> getShippableItems() {
        List<Shippable> shippingList = new ArrayList<>();
        for (Map.Entry<BaseProduct, Integer> entry : orderedItems.entrySet()) {
            BaseProduct product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isShippable() && product instanceof Shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippingList.add((Shippable) product);
                }
            }
        }
        return shippingList;
    }


       public void clear() {
        orderedItems.clear();
       }
}
    

