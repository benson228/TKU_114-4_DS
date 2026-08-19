public class EquipmentInventory {
    static class Equipment {
        private String id;
        private String name;
        private int availableCount;

        public Equipment(String id, String name, int availableCount) {
            this.id = id == null || id.isBlank() ? "Unknown" : id;
            this.name = name == null || name.isBlank() ? "Unknown" : name;
            this.availableCount = Math.max(0, availableCount);
        }

        public boolean borrowOne() {
            if (availableCount > 0) {
                availableCount--;
                return true;
            }
            return false;
        }

        public void returnItems(int quantity) {
            if (quantity > 0) {
                availableCount += quantity;
            }
        }

        @Override
        public String toString() {
            return "設備編號：" + id
                    + "，名稱：" + name
                    + "，可借數量：" + availableCount;
        }
    }

    public static void main(String[] args) {
        Equipment laptop = new Equipment("E001", "筆記型電腦", 2);
        Equipment projector = new Equipment("E002", "投影機", 0);

        System.out.println(laptop);
        System.out.println(projector);

        System.out.println("\n筆電借用：" + laptop.borrowOne());
        System.out.println(laptop);

        System.out.println("\n投影機借用：" + projector.borrowOne());
        System.out.println(projector);

        System.out.println("\n筆電歸還 2 台");
        laptop.returnItems(2);
        System.out.println(laptop);
    }
}