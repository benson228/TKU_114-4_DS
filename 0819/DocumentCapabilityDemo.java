public class DocumentCapabilityDemo {

    interface Exportable {
        void export();
    }

    interface Compressible {
        void compress();
    }

    static class BackupDocument implements Exportable, Compressible {

        @Override
        public void export() {
            System.out.println("文件匯出完成");
        }

        @Override
        public void compress() {
            System.out.println("文件壓縮完成");
        }
    }

    public static void main(String[] args) {
        BackupDocument document = new BackupDocument();

        Exportable exportable = document;
        Compressible compressible = document;

        System.out.println("=== Exportable ===");
        exportable.export();

        System.out.println("\n=== Compressible ===");
        compressible.compress();

        System.out.println("\n兩個 reference 是否指向同一物件："
                + (exportable == compressible));
    }
}