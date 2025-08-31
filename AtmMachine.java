import java.util.Scanner;


class UserAccount {
    private double balance;
    private int pin;

    public UserAccount(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Amount deposited successfully!");
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrawal successful!");
        } else if (amount > balance) {
            System.out.println(" Insufficient balance.");
        } else {
            System.out.println(" Invalid withdrawal amount.");
        }
    }

    public void changePin(int newPin) {
        this.pin = newPin;
        System.out.println(" PIN changed successfully!");
    }
}

class ATM {
    private UserAccount account;
    private Scanner sc = new Scanner(System.in);

    public ATM(UserAccount account) {
        this.account = account;
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter PIN: ");
        int enteredPin = sc.nextInt();

        if (account.validatePin(enteredPin)) {
            showMenu();
        } else {
            System.out.println(" Invalid PIN. Access Denied!");
            start();
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1️. Check Balance");
            System.out.println("2️. Deposit");
            System.out.println("3️. Withdraw");
            System.out.println("4️. Change PIN");
            System.out.println("5️. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    account.deposit(sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    account.changePin(sc.nextInt());
                    break;
                case 5:
                    System.out.println(" Exiting... Thank you for using ATM!");
                    return;
                default:
                    System.out.println("! Invalid option, try again.");
            }
        }
    }
}

// Main Class
public class AtmMachine {
    public static void main(String[] args) {
        UserAccount user = new UserAccount(10000.0, 1234);
        ATM atm = new ATM(user);
        atm.start();
    }
}
