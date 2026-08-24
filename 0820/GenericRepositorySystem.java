import java.util.ArrayList;
import java.util.List;

public class GenericRepositorySystem {

    static class Repository<T> {
        private final List<T> items = new ArrayList<>();

        public void add(T item) {
            items.add(item);
        }

        public T get(int index) {
            if (index < 0 || index >= items.size()) {
                return null;
            }

            return items.get(index);
        }

        public boolean remove(int index) {
            if (index < 0 || index >= items.size()) {
                return false;
            }

            items.remove(index);
            return true;
        }

        public int size() {
            return items.size();
        }

        public void printAll() {
            for (T item : items) {
                System.out.println(item);
            }
        }
    }

    static class Product {
        private String id;
        private String name;
        private double price;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{id='" + id
                    + "', name='" + name
                    + "', price=" + price + "}";
        }
    }

    public static void main(String[] args) {
        Repository<String> stringRepository =
                new Repository<>();

        stringRepository.add("Java");
        stringRepository.add("Data Structure");
        stringRepository.add("Git");

        System.out.println("=== String Repository ===");
        stringRepository.printAll();

        System.out.println("Size：" +
                stringRepository.size());

        System.out.println("Get index 1：" +
                stringRepository.get(1));

        System.out.println("Remove index 1：" +
                stringRepository.remove(1));

        System.out.println("Remove 後：");
        stringRepository.printAll();

        Repository<Product> productRepository =
                new Repository<>();

        productRepository.add(
                new Product("P001", "Keyboard", 1200));

        productRepository.add(
                new Product("P002", "Mouse", 800));

        productRepository.add(
                new Product("P003", "Headset", 1500));

        System.out.println("\n=== Product Repository ===");
        productRepository.printAll();

        System.out.println("Size：" +
                productRepository.size());

        System.out.println("Get index 0：" +
                productRepository.get(0));

        System.out.println("Remove index 1：" +
                productRepository.remove(1));

        System.out.println("Remove 後：");
        productRepository.printAll();
    }
}