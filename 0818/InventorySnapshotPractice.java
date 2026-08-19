import java.util.Arrays;

public class InventorySnapshotPractice {
    static class InventorySnapshot {
        private final String warehouseId;
        private final int[] quantities;

        public InventorySnapshot(String warehouseId, int[] quantities) {
            this.warehouseId = warehouseId;
            this.quantities = quantities == null
                    ? new int[0]
                    : Arrays.copyOf(quantities, quantities.length);
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public int[] getQuantities() {
            return Arrays.copyOf(quantities, quantities.length);
        }

        public int totalQuantity() {
            int total = 0;

            for (int quantity : quantities) {
                total += quantity;
            }

            return total;
        }

        public int outOfStockCount() {
            int count = 0;

            for (int quantity : quantities) {
                if (quantity == 0) {
                    count++;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {
        int[] quantities = {5, 0, 3, 0};

        InventorySnapshot snapshot =
                new InventorySnapshot("W001", quantities);

        System.out.println("倉庫：" + snapshot.getWarehouseId());
        System.out.println("數量：" +
                Arrays.toString(snapshot.getQuantities()));
        System.out.println("總數量：" +
                snapshot.totalQuantity());
        System.out.println("缺貨品項：" +
                snapshot.outOfStockCount());

        int[] returnedQuantities = snapshot.getQuantities();
        returnedQuantities[0] = 999;

        System.out.println("\n修改 getter 回傳陣列後：");
        System.out.println("原始快照數量：" +
                Arrays.toString(snapshot.getQuantities()));

        InventorySnapshot emptySnapshot =
                new InventorySnapshot("W002", null);

        System.out.println("\nnull 陣列測試：");
        System.out.println("數量長度：" +
                emptySnapshot.getQuantities().length);
    }
}