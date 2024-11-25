public class JointBankAccount {
    private int balance = 50000;    
    public synchronized void withdraw(String user, int amount) {
        if (balance >= amount) {
            System.out.println(user + " is trying to withdraw " + amount);
            balance -= amount;
            System.out.println(user + " successfully withdrew " + amount);
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println(user + " attempted to withdraw " + amount + " but insufficient funds.");
        }
    }
    public static void main(String[] args) {
        JointBankAccount account = new JointBankAccount();
        Thread userA = new Thread(new Runnable() {
            @Override
            public void run() {
                account.withdraw("User A", 45000);
            }
        });
        Thread userB = new Thread(new Runnable() {
            @Override
            public void run() {
                account.withdraw("User B", 20000);
            }
        });

        userA.start();
        userB.start();

        try {
            
            userA.join();
            userB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}