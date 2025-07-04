import java.util.*;

public class ShippingService {

    public static void printShipmentNotice(List<Shippable> itemsToShip) {
        if (itemsToShip == null || itemsToShip.isEmpty()) {
            System.out.println("** No items to ship **");
            return;
        }

        System.out.println("** Shipment notice **");

        Map<String, Double> groupedWeights = new LinkedHashMap<>();
        for (Shippable item : itemsToShip) {
            groupedWeights.merge(item.getName(), item.getWeight(), Double::sum);
        }

        for (Map.Entry<String, Double> entry : groupedWeights.entrySet()) {
            System.out.printf("%s\t%.0fg\n", entry.getKey(), entry.getValue() * 1000);
        }

        double totalWeight = groupedWeights.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }
}

