import java.util.Scanner;

// Bank Account class
class BankAccount {
    private double balance;
    // Constructor
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    // Method to check balance
    public double checkBalance() {
        return balance;
    }
}

// ATM class
 class ATM {
    private BankAccount account;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display menu
    public void displayMenu() {
        System.out.println("Welcome to the ATM");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    // Method to process user choice
    public void processChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                boolean withdrawn = account.withdraw(withdrawAmount);
                if (withdrawn) {
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient balance.");
                }
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                System.out.println("Deposit successful.");
                break;
            case 3:
                System.out.println("Your balance is: " + account.checkBalance());
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize bank account with initial balance
        BankAccount account = new BankAccount(1000); // Initial balance of 1000
        
        // Create ATM object
        ATM atm = new ATM(account);

        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            atm.processChoice(choice, scanner);
        }
    }
}
