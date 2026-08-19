public class AccountTransferService {
    static class Account {
        private String accountId;
        private String owner;
        private int balance;

        public Account(String accountId, String owner, int balance) {
            this.accountId = accountId;
            this.owner = owner;
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            balance += amount;
        }

        public boolean withdraw(int amount) {
            if (amount <= 0 || amount > balance) {
                return false;
            }

            balance -= amount;
            return true;
        }

        @Override
        public String toString() {
            return "Account ID: " + accountId
                    + ", Owner: " + owner
                    + ", Balance: " + balance;
        }
    }

    static class TransferService {
        public static boolean transfer(Account source, Account target, int amount) {
            if (source == null || target == null) {
                return false;
            }

            if (source == target) {
                return false;
            }

            if (amount <= 0) {
                return false;
            }

            if (source.getBalance() < amount) {
                return false;
            }

            source.withdraw(amount);
            target.deposit(amount);

            return true;
        }
    }

    public static void main(String[] args) {
        Account account1 = new Account("A001", "王小明", 1000);
        Account account2 = new Account("A002", "陳小華", 500);

        System.out.println("初始狀態");
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n正常轉帳 300");
        System.out.println("轉帳成功：" +
                TransferService.transfer(account1, account2, 300));
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n餘額不足轉帳 2000");
        System.out.println("轉帳成功：" +
                TransferService.transfer(account1, account2, 2000));
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n同帳戶轉帳");
        System.out.println("轉帳成功：" +
                TransferService.transfer(account1, account1, 100));
        System.out.println(account1);

        System.out.println("\nnull 目標帳戶");
        System.out.println("轉帳成功：" +
                TransferService.transfer(account1, null, 100));
        System.out.println(account1);
    }
}