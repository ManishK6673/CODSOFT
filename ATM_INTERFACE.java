import java.util.Scanner;


  

// Class representing the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for the withdrawal.");
            return false;
        } else {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayOptions() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performAction(int option) {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                account.withdraw(withdrawAmount);
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                break;
            case 3:
                System.out.println("Your current balance is: $" + account.getBalance());
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        scanner.close();
            }
    }
}

// Main class to run the ATM program
public class ATM_INTERFACE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000); // Initial balance
        ATM atm = new ATM(account);

        int option = 0;
        while (option != 4) {
            atm.displayOptions();
            System.out.print("Select an option: ");
            option = scanner.nextInt();
            atm.performAction(option);
            scanner.close();
        }
    }
}


