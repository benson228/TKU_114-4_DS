public class FlexibleCheckoutSystem {

    interface PricingPolicy {
        int finalPrice(int originalPrice);
    }

    static class StandardPricing implements PricingPolicy {
        @Override
        public int finalPrice(int originalPrice) {
            return Math.max(0, originalPrice);
        }
    }

    static class VipPricing implements PricingPolicy {
        @Override
        public int finalPrice(int originalPrice) {
            return Math.max(0, originalPrice) * 85 / 100;
        }
    }

    static class DiscountPricing implements PricingPolicy {
        @Override
        public int finalPrice(int originalPrice) {
            int price = Math.max(0, originalPrice);

            if (price >= 2000) {
                return Math.max(0, price - 300);
            }

            return price;
        }
    }

    interface NotificationChannel {
        boolean send(String receiver, String message);
    }

    static class EmailChannel implements NotificationChannel {
        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || !receiver.contains("@")) {
                return false;
            }

            System.out.println(
                    "EMAIL " + receiver + " -> " + message
            );

            return true;
        }
    }

    static class SmsChannel implements NotificationChannel {
        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || receiver.isBlank()) {
                return false;
            }

            System.out.println(
                    "SMS " + receiver + " -> " + message
            );

            return true;
        }
    }

    static class ConsoleChannel implements NotificationChannel {
        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || receiver.isBlank()) {
                return false;
            }

            System.out.println(
                    "CONSOLE " + receiver + " -> " + message
            );

            return true;
        }
    }

    static class CheckoutResult {
        private final String orderId;
        private final int originalPrice;
        private final int finalPrice;
        private final boolean notificationStatus;

        public CheckoutResult(
                String orderId,
                int originalPrice,
                int finalPrice,
                boolean notificationStatus) {

            this.orderId = orderId;
            this.originalPrice = originalPrice;
            this.finalPrice = finalPrice;
            this.notificationStatus = notificationStatus;
        }

        @Override
        public String toString() {
            return "orderId=" + orderId
                    + ", originalPrice=" + originalPrice
                    + ", finalPrice=" + finalPrice
                    + ", notificationStatus=" + notificationStatus;
        }
    }

    static class CheckoutService {
        private final PricingPolicy pricing;
        private final NotificationChannel channel;

        public CheckoutService(
                PricingPolicy pricing,
                NotificationChannel channel) {

            this.pricing = pricing;
            this.channel = channel;
        }

        public CheckoutResult checkout(
                String orderId,
                int originalPrice,
                String receiver) {

            if (orderId == null
                    || orderId.isBlank()
                    || originalPrice < 0) {

                return new CheckoutResult(
                        orderId,
                        Math.max(0, originalPrice),
                        0,
                        false
                );
            }

            int amount = pricing.finalPrice(originalPrice);

            boolean sent = channel.send(
                    receiver,
                    "order=" + orderId + ", amount=" + amount
            );

            return new CheckoutResult(
                    orderId,
                    originalPrice,
                    amount,
                    sent
            );
        }
    }

    public static void main(String[] args) {

        CheckoutService standardEmail =
                new CheckoutService(
                        new StandardPricing(),
                        new EmailChannel());

        CheckoutService standardSms =
                new CheckoutService(
                        new StandardPricing(),
                        new SmsChannel());

        CheckoutService vipEmail =
                new CheckoutService(
                        new VipPricing(),
                        new EmailChannel());

        CheckoutService vipConsole =
                new CheckoutService(
                        new VipPricing(),
                        new ConsoleChannel());

        CheckoutService discountSms =
                new CheckoutService(
                        new DiscountPricing(),
                        new SmsChannel());

        CheckoutService discountConsole =
                new CheckoutService(
                        new DiscountPricing(),
                        new ConsoleChannel());

        System.out.println("=== 1 Standard + Email ===");
        System.out.println(
                standardEmail.checkout(
                        "O100",
                        2000,
                        "amy@example.com"));

        System.out.println("\n=== 2 Standard + SMS ===");
        System.out.println(
                standardSms.checkout(
                        "O101",
                        800,
                        "0912345678"));

        System.out.println("\n=== 3 VIP + Email ===");
        System.out.println(
                vipEmail.checkout(
                        "O102",
                        2000,
                        "bob@example.com"));

        System.out.println("\n=== 4 VIP + Console ===");
        System.out.println(
                vipConsole.checkout(
                        "O103",
                        3000,
                        "ConsoleUser"));

        System.out.println("\n=== 5 Discount + SMS ===");
        System.out.println(
                discountSms.checkout(
                        "O104",
                        2500,
                        "0987654321"));

        System.out.println("\n=== 6 Discount + Console ===");
        System.out.println(
                discountConsole.checkout(
                        "O105",
                        1500,
                        "ConsoleUser"));

        System.out.println("\n=== Email 失敗測試 ===");
        System.out.println(
                vipEmail.checkout(
                        "O106",
                        2000,
                        "invalid"));
    }
}