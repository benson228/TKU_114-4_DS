public class PayrollPolymorphismSystem {

    static abstract class Employee {
        protected String id;
        protected String name;

        public Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public abstract int calculatePay();
    }

    static class MonthlyEmployee extends Employee {
        private int monthlySalary;

        public MonthlyEmployee(String id, String name, int monthlySalary) {
            super(id, name);
            this.monthlySalary = Math.max(0, monthlySalary);
        }

        @Override
        public int calculatePay() {
            return monthlySalary;
        }
    }

    static class HourlyEmployee extends Employee {
        private int hourlyRate;
        private int hours;

        public HourlyEmployee(String id, String name, int hourlyRate, int hours) {
            super(id, name);
            this.hourlyRate = Math.max(0, hourlyRate);
            this.hours = Math.max(0, hours);
        }

        @Override
        public int calculatePay() {
            return hourlyRate * hours;
        }
    }

    static class SalesEmployee extends Employee {
        private int baseSalary;
        private int salesAmount;
        private int bonusRate;

        public SalesEmployee(String id, String name,
                             int baseSalary, int salesAmount, int bonusRate) {
            super(id, name);
            this.baseSalary = Math.max(0, baseSalary);
            this.salesAmount = Math.max(0, salesAmount);
            this.bonusRate = Math.max(0, bonusRate);
        }

        @Override
        public int calculatePay() {
            return baseSalary + salesAmount * bonusRate / 100;
        }
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new MonthlyEmployee("E001", "Amy", 50000),
            new HourlyEmployee("E002", "Bob", 200, 160),
            new SalesEmployee("E003", "Charlie", 30000, 100000, 5),
            new MonthlyEmployee("E004", "David", 45000)
        };

        int totalPay = 0;
        int highestPay = 0;

        for (Employee employee : employees) {
            int pay = employee.calculatePay();

            System.out.println(
                    employee.name + " 薪資：" + pay
            );

            totalPay += pay;

            if (pay > highestPay) {
                highestPay = pay;
            }
        }

        System.out.println("\n薪資總額：" + totalPay);
        System.out.println("最高薪資：" + highestPay);
    }
}