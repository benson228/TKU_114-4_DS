public class DeviceInspectionSystem {

    static class Device {
        protected String deviceId;

        public Device(String deviceId) {
            this.deviceId = deviceId;
        }

        public void runDiagnostic() {
            System.out.println(deviceId + "：一般設備診斷完成");
        }
    }

    static class Laptop extends Device {
        public Laptop(String deviceId) {
            super(deviceId);
        }

        @Override
        public void runDiagnostic() {
            System.out.println(deviceId + "：Laptop 診斷完成");
        }
    }

    static class Printer extends Device {
        public Printer(String deviceId) {
            super(deviceId);
        }

        @Override
        public void runDiagnostic() {
            System.out.println(deviceId + "：Printer 診斷完成");
        }

        public void cleanPrintHead() {
            System.out.println(deviceId + "：清潔列印頭完成");
        }
    }

    static class Router extends Device {
        public Router(String deviceId) {
            super(deviceId);
        }

        @Override
        public void runDiagnostic() {
            System.out.println(deviceId + "：Router 診斷完成");
        }
    }

    public static void main(String[] args) {
        Device[] devices = {
            new Laptop("L001"),
            new Printer("P001"),
            new Router("R001"),
            new Printer("P002")
        };

        for (Device device : devices) {
            device.runDiagnostic();

            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}