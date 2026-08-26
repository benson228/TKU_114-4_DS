public class Q03_EmployeePayroll {

    public static abstract class Employee {
        private final String id;
        private final String name;

        protected Employee(String id, String name) {
            if (id == null || id.trim().isEmpty()
                    || name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.id = id.trim();
            this.name = name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract int monthlyPay();

        public String summary() {
            return id + "|" + name + "|" + monthlyPay();
        }
    }

    public static class SalariedEmployee extends Employee {
        private final int salary;

        public SalariedEmployee(
                String id,
                String name,
                int salary) {
            super(id, name);
            this.salary = Math.max(0, salary);
        }

        @Override
        public int monthlyPay() {
            return salary;
        }
    }

    public static class HourlyEmployee extends Employee {
        private final int hours;
        private final int hourlyRate;

        public HourlyEmployee(
                String id,
                String name,
                int hours,
                int hourlyRate) {
            super(id, name);
            this.hours = Math.max(0, hours);
            this.hourlyRate = Math.max(0, hourlyRate);
        }

        @Override
        public int monthlyPay() {
            int regularHours = Math.min(hours, 160);
            int overtimeHours = Math.max(0, hours - 160);

            double pay =
                    regularHours * hourlyRate
                    + overtimeHours * hourlyRate * 1.5;

            return (int) pay;
        }
    }

    public static int totalPayroll(
            java.util.List<Employee> employees) {

        if (employees == null) {
            return 0;
        }

        int total = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                total += employee.monthlyPay();
            }
        }

        return total;
    }
}
