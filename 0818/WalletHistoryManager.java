final class WalletTransaction {
    private final int sequence;
    private final String type;
    private final int amount;
    private final int balanceAfter;

    WalletTransaction(int sequence, String type, int amount, int balanceAfter) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    int getSequence() {
        return sequence;
    }

    String getType() {
        return type;
    }

    int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return sequence + " " + type + " " + amount
                + " balance=" + balanceAfter;
    }
}

class DigitalWallet {
    private final String walletId;
    private final String owner;
    private int balance;
    private final WalletTransaction[] transactions;
    private int transactionCount;

    DigitalWallet(String walletId, String owner, int historyCapacity) {
        this.walletId = walletId == null || walletId.isBlank()
                ? "UNKNOWN" : walletId;
        this.owner = owner == null || owner.isBlank()
                ? "Unknown" : owner;
        this.balance = 0;
        this.transactions = new WalletTransaction[Math.max(1, historyCapacity)];
        this.transactionCount = 0;
    }

    boolean deposit(int amount) {
        if (amount <= 0 || transactionCount >= transactions.length) {
            return false;
        }

        balance += amount;
        record("DEPOSIT", amount);
        return true;
    }

    boolean pay(int amount) {
        if (amount <= 0 || amount > balance
                || transactionCount >= transactions.length) {
            return false;
        }

        balance -= amount;
        record("PAY", amount);
        return true;
    }

    boolean refund(int amount) {
        if (amount <= 0 || transactionCount >= transactions.length) {
            return false;
        }

        balance += amount;
        record("REFUND", amount);
        return true;
    }

    boolean transferTo(DigitalWallet target, int amount) {
        if (target == null || target == this || amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        if (transactionCount >= transactions.length
                || target.transactionCount >= target.transactions.length) {
            return false;
        }

        balance -= amount;
        target.balance += amount;

        record("TRANSFER_OUT", amount);
        target.record("TRANSFER_IN", amount);

        return true;
    }

    WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }

        return null;
    }

    int totalByType(String type) {
        if (type == null) {
            return 0;
        }

        int total = 0;

        for (int i = 0; i < transactionCount; i++) {
            if (type.equals(transactions[i].getType())) {
                total += transactions[i].getAmount();
            }
        }

        return total;
    }

    private void record(String type, int amount) {
        transactions[transactionCount] = new WalletTransaction(
                transactionCount + 1,
                type,
                amount,
                balance
        );
        transactionCount++;
    }

    void printStatement() {
        System.out.println(walletId + " owner=" + owner
                + " balance=" + balance);

        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactions[i]);
        }
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        DigitalWallet wallet1 =
                new DigitalWallet("W001", "Amy", 5);

        DigitalWallet wallet2 =
                new DigitalWallet("W002", "Bob", 5);

        System.out.println("=== 儲值與付款 ===");

        System.out.println("wallet1 deposit="
                + wallet1.deposit(1000));

        System.out.println("wallet1 pay 250="
                + wallet1.pay(250));

        System.out.println("wallet1 pay 900="
                + wallet1.pay(900));

        System.out.println("wallet1 refund="
                + wallet1.refund(50));

        System.out.println("\n=== 轉帳 ===");

        System.out.println("wallet1 transfer 300 to wallet2="
                + wallet1.transferTo(wallet2, 300));

        System.out.println("\n=== 查詢交易 ===");

        System.out.println("wallet1 transaction 1="
                + wallet1.findTransaction(1));

        System.out.println("wallet1 transaction 99="
                + wallet1.findTransaction(99));

        System.out.println("\n=== 類型總額 ===");

        System.out.println("wallet1 PAY total="
                + wallet1.totalByType("PAY"));

        System.out.println("wallet1 REFUND total="
                + wallet1.totalByType("REFUND"));

        System.out.println("\n=== Wallet 1 Statement ===");
        wallet1.printStatement();

        System.out.println("\n=== Wallet 2 Statement ===");
        wallet2.printStatement();
    }
}