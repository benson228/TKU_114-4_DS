import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductComparatorPractice {

    static class StoreProduct implements Comparable<StoreProduct> {
        private String id;
        private String name;
        private double price;
        private int stock;

        public StoreProduct(
                String id,
                String name,
                double price,
                int stock) {

            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        @Override
        public int compareTo(StoreProduct other) {
            return this.id.compareTo(other.id);
        }

        @Override
        public String toString() {
            return "id=" + id
                    + ", name=" + name
                    + ", price=" + price
                    + ", stock=" + stock;
        }
    }

    public static void main(String[] args) {
        List<StoreProduct> products = Arrays.asList(
                new StoreProduct("P003", "Keyboard", 1200, 5),
                new StoreProduct("P001", "Mouse", 800, 10),
                new StoreProduct("P005", "Monitor", 5000, 3),
                new StoreProduct("P002", "Headset", 1200, 3),
                new StoreProduct("P004", "Webcam", 800, 5)
        );

        System.out.println("=== 原始順序 ===");
        for (StoreProduct product : products) {
            System.out.println(product);
        }

        List<StoreProduct> byId =
                new ArrayList<>(products);

        Collections.sort(byId);

        System.out.println("\n=== Natural Order：id 升冪 ===");
        for (StoreProduct product : byId) {
            System.out.println(product);
        }

        List<StoreProduct> byPrice =
                new ArrayList<>(products);

        Comparator<StoreProduct> priceComparator =
                Comparator.comparingDouble(
                        (StoreProduct product) -> product.price)
                .thenComparing(product -> product.name);

        byPrice.sort(priceComparator);

        System.out.println("\n=== Comparator 一：price 升冪，同價依 name ===");
        for (StoreProduct product : byPrice) {
            System.out.println(product);
        }

        List<StoreProduct> byStock =
                new ArrayList<>(products);

        Comparator<StoreProduct> stockComparator =
                Comparator.comparingInt(
                        (StoreProduct product) -> product.stock)
                .reversed()
                .thenComparing(product -> product.id);

        byStock.sort(stockComparator);

        System.out.println("\n=== Comparator 二：stock 降冪，同庫存依 id ===");
        for (StoreProduct product : byStock) {
            System.out.println(product);
        }
    }
}