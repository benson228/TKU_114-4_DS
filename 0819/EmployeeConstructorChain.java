public class EmployeeConstructorChain {

    static abstract class EmployeeBase {
        protected String id;
        protected String name;

        public EmployeeBase(String id, String name) {
            System.out.println("EmployeeBase constructor");
            this.id = id;
            this.name = name;
        }

        public abstract double calculatePay();
    }

    static class FullTimeEmployee extends EmployeeBase {
        private double monthlySalary;

        public FullTimeEmployee(String id, String name, double monthlySalary) {
            super(id, name);
            System.out.println("FullTimeEmployee constructor");
            this.monthlySalary = Math.max(0, monthlySalary);
        }

        @Override
        public double calculatePay() {
            return monthlySalary;
        }
    }

    static class PartTimeEmployee extends EmployeeBase {
        private double hourlyRate;
        private double hours;

        public PartTimeEmployee(String id, String name,
                                double hourlyRate, double hours) {
            super(id, name);
            System.out.println("PartTimeEmployee constructor");
            this.hourlyRate = Math.max(0, hourlyRate);
            this.hours = Math.max(0, hours);
        }

        @Override
        public double calculatePay() {
            return hourlyRate * hours;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 建立正職員工 ===");
        FullTimeEmployee fullTime =
                new FullTimeEmployee("E001", "Amy", 50000);

        System.out.println("薪資：" + fullTime.calculatePay());

        System.out.println("\n=== 建立兼職員工 ===");
        PartTimeEmployee partTime =
                new PartTimeEmployee("E002", "Bob", 200, 80);

        System.out.println("薪資：" + partTime.calculatePay());

        System.out.println("\n=== 負數測試 ===");
        PartTimeEmployee invalid =
                new PartTimeEmployee("E003", "Tom", -100, -5);

        System.out.println("薪資：" + invalid.calculatePay());
    }
}