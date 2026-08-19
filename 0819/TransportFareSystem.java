public class TransportFareSystem {

    static abstract class Transport {
        protected String routeName;

        public Transport(String routeName) {
            this.routeName = routeName;
        }

        public abstract int calculateFare(int distance);
    }

    static class Bus extends Transport {
        public Bus(String routeName) {
            super(routeName);
        }

        @Override
        public int calculateFare(int distance) {
            if (distance <= 0) {
                return 0;
            }
            return 30 + distance * 2;
        }
    }

    static class Taxi extends Transport {
        public Taxi(String routeName) {
            super(routeName);
        }

        @Override
        public int calculateFare(int distance) {
            if (distance <= 0) {
                return 0;
            }
            return 85 + Math.max(0, distance - 2) * 20;
        }
    }

    public static void main(String[] args) {
        Transport[] transports = {
            new Bus("台北－新北"),
            new Bus("台中－彰化"),
            new Taxi("台北車站－信義區"),
            new Taxi("台中車站－逢甲")
        };

        int[] distances = {10, 15, 5, 8};

        for (int i = 0; i < transports.length; i++) {
            System.out.println(
                    transports[i].routeName
                    + "，距離：" + distances[i] + " 公里"
                    + "，票價：" + transports[i].calculateFare(distances[i])
                    + " 元"
            );
        }
    }
}