public class DeliveryStrategySystem {

    interface DeliveryMethod {
        int calculateFee(int orderAmount);

        String estimate();
    }

    static class HomeDelivery implements DeliveryMethod {
        @Override
        public int calculateFee(int orderAmount) {
            return orderAmount >= 1000 ? 0 : 100;
        }

        @Override
        public String estimate() {
            return "宅配約 1-3 個工作天";
        }
    }

    static class ConvenienceStoreDelivery implements DeliveryMethod {
        @Override
        public int calculateFee(int orderAmount) {
            return 60;
        }

        @Override
        public String estimate() {
            return "超商取貨約 2-4 個工作天";
        }
    }

    static class SelfPickup implements DeliveryMethod {
        @Override
        public int calculateFee(int orderAmount) {
            return 0;
        }

        @Override
        public String estimate() {
            return "門市自取";
        }
    }

    static class OrderService {
        private final DeliveryMethod deliveryMethod;

        public OrderService(DeliveryMethod deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
        }

        public void showOrder(int orderAmount) {
            int safeAmount = Math.max(0, orderAmount);

            System.out.println("訂單金額：" + safeAmount);
            System.out.println("運費：" +
                    deliveryMethod.calculateFee(safeAmount));
            System.out.println("配送說明：" +
                    deliveryMethod.estimate());
        }
    }

    public static void main(String[] args) {
        OrderService home =
                new OrderService(new HomeDelivery());

        OrderService store =
                new OrderService(new ConvenienceStoreDelivery());

        OrderService pickup =
                new OrderService(new SelfPickup());

        System.out.println("=== 宅配 ===");
        home.showOrder(800);

        System.out.println("\n=== 超商取貨 ===");
        store.showOrder(800);

        System.out.println("\n=== 自取 ===");
        pickup.showOrder(800);
    }
}