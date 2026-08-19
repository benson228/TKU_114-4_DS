public class DigitalWalletSystem {
    static class DigitalWallet {
        private String walletId;
        private String owner;
        private double balance;
        private int transactionCount;

        public DigitalWallet(String walletId, String owner, double balance) {
            this.walletId = walletId;
            this.owner = owner;
            this.balance = Math.max(0, balance);
            this.transactionCount = 0;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                transactionCount++;
            }
        }

        public boolean pay(double amount) {
            if (amount <= 0 || amount > balance) {
                return false;
            }
            balance -= amount;
            transactionCount++;
            return true;
        }

        public void refund(double amount) {
            if (amount > 0) {
                balance += amount;
                transactionCount++;
            }
        }

        public String getWalletId() {
            return walletId;
        }

        public String getOwner() {
            return owner;
        }

        public double getBalance() {
            return balance;
        }

        public int getTransactionCount() {
            return transactionCount;
        }

        @Override
        public String toString() {
            return String.format(
                "Wallet ID: %s, Owner: %s, Balance: %.2f, Transactions: %d",
                walletId, owner, balance, transactionCount
            );
        }
    }

    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("W001", "Alice", 1000);

        System.out.println("初始狀態");
        System.out.println(wallet);

        System.out.println("\n正常儲值 500");
        wallet.deposit(500);
        System.out.println(wallet);

        System.out.println("\n正常付款 300");
        System.out.println("付款成功：" + wallet.pay(300));
        System.out.println(wallet);

        System.out.println("\n餘額不足付款 1500");
        System.out.println("付款成功：" + wallet.pay(1500));
        System.out.println(wallet);

        System.out.println("\n負數金額儲值 -100");
        wallet.deposit(-100);
        System.out.println(wallet);

        System.out.println("\n負數金額付款 -50");
        System.out.println("付款成功：" + wallet.pay(-50));
        System.out.println(wallet);

        System.out.println("\n退款 200");
        wallet.refund(200);
        System.out.println(wallet);
    }
}