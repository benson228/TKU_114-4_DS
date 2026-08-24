public class GenericResultDemo {

    static class Result<T> {
        private final boolean success;
        private final String message;
        private final T data;

        Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return "success=" + success
                    + ", message=" + message
                    + ", data=" + data;
        }
    }

    static Result<String> getNameResult(boolean success) {
        if (success) {
            return new Result<>(true, "成功", "Alice");
        }

        return new Result<>(false, "查詢失敗", null);
    }

    static Result<Integer> getScoreResult(boolean success) {
        if (success) {
            return new Result<>(true, "成功", 95);
        }

        return new Result<>(false, "查詢失敗", null);
    }

    public static void main(String[] args) {
        Result<String> nameResult = getNameResult(true);
        Result<Integer> scoreResult = getScoreResult(true);
        Result<String> failedResult = getNameResult(false);

        System.out.println("=== String Result ===");
        System.out.println(nameResult);
        System.out.println("資料：" + nameResult.getData());

        System.out.println("\n=== Integer Result ===");
        System.out.println(scoreResult);
        System.out.println("資料：" + scoreResult.getData());

        System.out.println("\n=== Failed Result ===");
        System.out.println(failedResult);
        System.out.println("資料：" + failedResult.getData());
    }
}