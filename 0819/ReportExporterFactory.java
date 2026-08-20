public class ReportExporterFactory {

    interface ReportExporter {
        void export(String title, int[] values);
    }

    static class CsvExporter implements ReportExporter {
        @Override
        public void export(String title, int[] values) {
            System.out.println("CSV：" + title);

            if (values == null) {
                System.out.println("資料為空");
                return;
            }

            for (int value : values) {
                System.out.print(value + ",");
            }

            System.out.println();
        }
    }

    static class JsonExporter implements ReportExporter {
        @Override
        public void export(String title, int[] values) {
            System.out.print("JSON：{\"title\":\"" + title + "\",\"values\":[");

            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    if (i > 0) {
                        System.out.print(",");
                    }

                    System.out.print(values[i]);
                }
            }

            System.out.println("]}");
        }
    }

    static class TextExporter implements ReportExporter {
        @Override
        public void export(String title, int[] values) {
            System.out.println("TEXT：" + title);

            if (values == null) {
                System.out.println("資料為空");
                return;
            }

            for (int value : values) {
                System.out.println("數值：" + value);
            }
        }
    }

    static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }

        switch (format.toLowerCase()) {
            case "csv":
                return new CsvExporter();

            case "json":
                return new JsonExporter();

            case "text":
                return new TextExporter();

            default:
                return new TextExporter();
        }
    }

    static void exportReport(
            ReportExporter exporter,
            String title,
            int[] values) {

        if (exporter == null) {
            return;
        }

        exporter.export(title, values);
    }

    public static void main(String[] args) {
        int[] values = {10, 20, 30};

        ReportExporter csv = createExporter("csv");
        ReportExporter json = createExporter("json");
        ReportExporter text = createExporter("text");
        ReportExporter unknown = createExporter("xml");

        System.out.println("=== CSV ===");
        exportReport(csv, "Sales Report", values);

        System.out.println("\n=== JSON ===");
        exportReport(json, "Sales Report", values);

        System.out.println("\n=== TEXT ===");
        exportReport(text, "Sales Report", values);

        System.out.println("\n=== 不支援格式 ===");
        exportReport(unknown, "Sales Report", values);

        System.out.println("\n=== null 測試 ===");
        exportReport(createExporter("csv"), "Empty Report", null);
    }
}