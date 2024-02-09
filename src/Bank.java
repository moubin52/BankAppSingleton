import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private static Bank instance;
    private List<Account> accounts;
    private Scanner scanner;

    private Bank() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    //singleton
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }
    //adding a new account
    public void addAccount() {
        System.out.println("Enter account number:");
        int accountNumber = scanner.nextInt();
        System.out.println("Enter account name:");
        String accountName = scanner.next();
        System.out.println("Enter initial balance:");
        double initialBalance = scanner.nextDouble();

        Account account = new Account(accountNumber, accountName, initialBalance);
        accounts.add(account);
        System.out.println("Account added successfully.");
    }
    //viewing all the accounts
    public void viewAllAccounts() {
        System.out.println("All Accounts:");
        for (Account account : accounts) {
            System.out.println("-".repeat(20));
            System.out.println(account);
            System.out.println("-".repeat(20)); 
        }
    }
    //transaction menu
    public void operateOnAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter withdrawal amount:");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    System.out.println("Withdrawal successful.");
                    break;
                case 2:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
    //account finder for transaction menu
    private Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Bank bank = Bank.getInstance();
        Scanner scanner = new Scanner(System.in);
        //main menu
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add new account");
            System.out.println("2. View all accounts");
            System.out.println("3. Operate on account (Withdraw/Deposit)");
            System.out.println("4. Exit program");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bank.addAccount();
                    break;
                case 2:
                    bank.viewAllAccounts();
                    break;
                case 3:
                    System.out.println("Enter account number:");
                    int accountNumber = scanner.nextInt();
                    bank.operateOnAccount(accountNumber);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Account {
    private int accountNumber;
    private String accountName;
    private double balance;
    // constructor
    public Account(int accountNumber, String accountName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = initialBalance;
    }
    // account number
    public int getAccountNumber() {
        return accountNumber;
    }
    // account name
    public String getAccountName() {
        return accountName;
    }
    // account starting balance 
    public double getBalance() {
        return balance;
    }
    // withdrawing money 
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    } 
    // depositing money
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. Remaining balance: " + balance);
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\n" +
                "Name: '" + accountName + "'\n" +
                "Balance: " + balance;
    }
    
}
