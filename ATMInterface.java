package intern;
import java.util.Scanner;

class Account {
    private double currentBalance;

    public Account(double openingBalance) {
        this.currentBalance = openingBalance;
    }

    public double fetchBalance() {
        return currentBalance;
    }

    public boolean makeWithdrawal(double withdrawalAmount) {
        if (withdrawalAmount > 0 && currentBalance >= withdrawalAmount) {
            currentBalance -= withdrawalAmount;
            return true;
        }
        return false;
    }

    public void makeDeposit(double depositAmount) {
        if (depositAmount > 0) {
            currentBalance += depositAmount;
        }
    }
}

class ATMSystem {
    private Account userAccount;
    private Scanner inputScanner;

    public ATMSystem(Account userAccount) {
        this.userAccount = userAccount;
        this.inputScanner = new Scanner(System.in);
    }

    public void runATM() {
        int userChoice;
        do {
            System.out.println("\n=== ATM MACHINE ===");
            System.out.println("1. View Balance");
            System.out.println("2. Add Money");
            System.out.println("3. Take Money");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            userChoice = inputScanner.nextInt();

            switch (userChoice) {
                case 1:
                    showBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    System.out.println("Thank you for visiting!");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }

        } while (userChoice != 4);
    }

    private void showBalance() {
        System.out.println("Available Balance: ₹" + userAccount.fetchBalance());
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ₹");
        double depositAmount = inputScanner.nextDouble();
        if (depositAmount > 0) {
            userAccount.makeDeposit(depositAmount);
            System.out.println("₹" + depositAmount + " added successfully.");
            showBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: ₹");
        double withdrawalAmount = inputScanner.nextDouble();
        if (userAccount.makeWithdrawal(withdrawalAmount)) {
            System.out.println("Withdrawal successful.");
            showBalance();
        } else {
            System.out.println("Transaction failed. Check your balance or enter a valid amount.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Account myAccount = new Account(10000.0);
        ATMSystem atmMachine = new ATMSystem(myAccount);
        atmMachine.runATM();
    }
}
