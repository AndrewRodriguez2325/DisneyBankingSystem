/**
 * Name: Andrew Rodriguez
 * Date: 02/23/21
 * Course: CS 3331 – Advanced Object-Oriented Programming – Spring 2021
 * Instructor: Dr. Daniel Mejia
 * Assignment: Programming Assignment 2
 * <p>
 * I confirm that the work of this assignment is completely my own.
 * By turning in this assignment, I declare that I did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code,
 * lab report and output files were written and produced by me alone.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Andrew Rodriguez
 * @version 1.0
 * @since February 9 2020
 * <p>
 * The runBank class runs the main method and allows for user interaction so that they can access their accounts and modify them as needed.
 * ASSUME THAT EXCEL FILES DO NOT HAVE COMMAS IN THE ADDRESS AND THAT THE ACTION LOG HAS NULL IN EMPTY SPACES.C:\Users\andre\Desktop\CS\PATwo\src\CS 3331 - Bank Users 3.csv
 */


public class runBank implements Printable {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, PasswordIncorrectException {
        // Make the list of customers based on CSV input
        List<customer> customerList = makeCustomerList();
        // Runs the interactive User Interface
        runUI(customerList);
    }

    /**
     * @param customerList
     * @throws IOException
     */
    static void runUI(List<customer> customerList) throws IOException, PasswordIncorrectException {
        runBank rb = new runBank();
        Scanner fileScanner = new Scanner(System.in);
        int selection = 0;
        // The file writer that will be used to create the transaction log each time you run the program.
        FileWriter fw = new FileWriter("transactionLog.txt");
        Scanner scanner = new Scanner(System.in);
        //This would be the best question to ask prior to anything because there is no user interaction with action log
        rb.print("Are you reading from TransactionActions?");
        rb.print("0: No");
        rb.print("1: Yes");
        int isActionLog = scanner.nextInt();
        if (isActionLog == 0) {
            //The first question is if person is a bank manager
            do {
                rb.print("Are you a bank manager?");
                rb.print("0: No");
                rb.print("1: Yes");
                int isBankManger = scanner.nextInt();
                if (isBankManger == 0) {
                    //The First question a customer is asked to make sure we know if they need to create an account
                    rb.print("Existing or new customer?");
                    rb.print("0: New");
                    rb.print("1: Existing");
                    int isExisting = scanner.nextInt();
                    if (isExisting == 0) {
                        rb.print("Thank you for choosing us as your bank, I will just need some basic info from you");
                        customer newCustomer = new customer();
                        rb.print("What is your first name");
                        newCustomer.setFirstName(scanner.next());
                        scanner.nextLine();
                        rb.print("What is your last name");
                        newCustomer.setLastName(scanner.next());
                        scanner.nextLine();
                        rb.print("What is your DOB *mm/dd/yyyy*");
                        newCustomer.setDateOfBirth(scanner.next());
                        scanner.nextLine();
                        int nextIDNumber = customerList.size() + 1;
                        String stringNextID = String.valueOf(nextIDNumber);
                        newCustomer.setIdentificationNumber(stringNextID);
                        rb.print("What is your address");
                        String addy = scanner.nextLine();
                        newCustomer.setAddress(addy);
                        rb.print("What is your phone number *(xxx) xxx-xxxx*");
                        String phoneNum = scanner.nextLine();
                        newCustomer.setPhoneNumber(phoneNum);
                        rb.print("What is your email address");
                        String emailaddress = scanner.nextLine();
                        newCustomer.setEmail(emailaddress);
                        rb.print("Please create a strong password");
                        String password = scanner.nextLine();
                        newCustomer.setPassword(password);
                        rb.print("In order for us to successfully complete your account we must create at least a savings account");
                        rb.print("How much would you like to deposit into your new savings account");
                        double savingStart = scanner.nextDouble();
                        String savAccNum = ("2" + customerList.size() + "");
                        int savingAccNum = Integer.parseInt(savAccNum);
                        savings newCusSaving = new savings(savingAccNum, savingStart);
                        newCustomer.setSavingsAccount(newCusSaving);
                        rb.print("Would you like to create a checking account");
                        rb.print("0: No");
                        rb.print("1: Yes");
                        int createChecking = scanner.nextInt();
                        if (createChecking == 0) {
                            rb.print("No worries. We understand");
                        }
                        if (createChecking == 1) {
                            rb.print("How much would you like to deposit into your new checkings account");
                            double checkingStart = scanner.nextDouble();
                            String cheAccNum = ("1" + customerList.size() + "");
                            int checkingAccNum = Integer.parseInt(cheAccNum);
                            checking newCusChecking = new checking(checkingAccNum, checkingStart);
                            newCustomer.setCheckingaccount(newCusChecking);
                        }
                        rb.print("Would you like to create a credit account");
                        rb.print("0: No");
                        rb.print("1: Yes");
                        int createCredit = scanner.nextInt();
                        if (createCredit == 0) {
                            rb.print("No worries. We understand");
                        }
                        if (createCredit == 1) {
                            rb.print("We offer first time account holders a limit of 1000");
                            String creAccNum = ("3" + customerList.size() + "");
                            int creditAccNum = Integer.parseInt(creAccNum);
                            credit newCusCredit = new credit(creditAccNum, 0, 1000);
                            newCustomer.setCreditAccount(newCusCredit);
                        }
                        rb.print("Thank you again and we hope to offer the best banking experience");
                        customerList.add(newCustomer);
                    }
                    if (isExisting == 1) {
                        rb.print("Welcome!");
                    }
                    do {
                        rb.print("Hey there! Would you like to find your account using your name or account number?");
                        rb.print("A. Inquire account by name");
                        rb.print("B. Inquire account by type/number");
                        String inquireBy = scanner.next();
                        if (inquireBy.equals("A")) {
                            rb.print("PLEASE CAPITALIZE FIRST LETTER");
                            rb.print("Enter first name");
                            String firstName = scanner.next();
                            rb.print("Enter last name");
                            String lastName = scanner.next();
                            for (int i = 0; i < customerList.size(); i++) {
                                if (firstName.equals(customerList.get(i).getFirstName()) && lastName.equals(customerList.get(i).getLastName())) {
                                    rb.print("Account found");
                                    int accountIndex = i;

                                    boolean correctLogin = false;
                                    String passwordAttempt;
                                    String password = customerList.get(accountIndex).getPassword();
                                    while (correctLogin != true) {
                                        rb.print("Please enter your password");
                                        passwordAttempt = scanner.nextLine();
                                        if (passwordAttempt.equals(password)) {
                                            rb.print("Your password is correct");
                                            correctLogin = true;
                                            break;
                                        }
                                        try {
                                            if (!passwordAttempt.equals(password))
                                                throw new PasswordIncorrectException();
                                        } catch (PasswordIncorrectException e) {
                                            System.out.println("Password Incorrect please try again...");
                                        }
                                    }
                                        rb.print("Account information for " + firstName + " " + lastName + "");
                                        rb.print(customerList.get(accountIndex).getDateOfBirth() + " ID Number:" + customerList.get(accountIndex).getIdentificationNumber()
                                                + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                        if (customerList.get(accountIndex).getCheckingaccount() == null)
                                            rb.print("You do not have a checking account with us");
                                        else
                                            rb.print("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                        rb.print("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                        if (customerList.get(accountIndex).getCreditAccount() == null)
                                            rb.print("You do not have a credit account with us");
                                        else
                                            rb.print("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                        rb.print("Which Account would you like to modify");
                                        rb.print("0: Checking Account");
                                        rb.print("1: Savings Account");
                                        rb.print("2: Credit Account");
                                        int modifyType = scanner.nextInt();
                                        if (modifyType == 0) {
                                            do {
                                                // The list of actions the user can perform
                                                rb.print("1: Check Balance");
                                                rb.print("2: Pay Someone");
                                                rb.print("3: Deposit Money");
                                                rb.print("4: Withdraw Balance");
                                                rb.print("5: Cancel");
                                                int action = scanner.nextInt();
                                                PrintStream console = System.out;
                                                switch (action) {
                                                    //The case where the user wants to see it's current balance
                                                    case 1:
                                                        fw.write(firstName + " " + lastName + " made a balance inquiry on checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " made a balance inquiry on checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).getCheckingaccount().getBalance();
                                                        break;
                                                    // The case where the user would like to pay someone else
                                                    case 2:
                                                        rb.print("Who would you like to pay?");
                                                        rb.print("PLEASE CAPITALIZE FIRST LETTER");
                                                        rb.print("Enter first name");
                                                        String payeefirstName = scanner.next();
                                                        rb.print("Enter last name");
                                                        String payeelastName = scanner.next();
                                                        for (int j = 0; j < customerList.size(); j++) {
                                                            if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                                int payeeAccountIndex = j;
                                                                rb.print("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                                double amount = scanner.nextDouble();
                                                                customerList.get(accountIndex).getCheckingaccount().paySomeone(amount, customerList.get(payeeAccountIndex).getCheckingaccount());
                                                                if (amount > 0 || amount <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                                    fw.write(firstName + " " + lastName + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                            firstName + lastName + "'s balance for checkings-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                                    customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                            firstName + lastName + "'s balance for checkings-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                                } else
                                                                    fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                                break;
                                                            } else
                                                                rb.print("Could not find user");
                                                        }
                                                        break;
                                                    // The case where the user wants to deposit money
                                                    case 3:
                                                        rb.print("How much would you like to deposit?");
                                                        double deposit = scanner.nextDouble();
                                                        customerList.get(accountIndex).getCheckingaccount().depositMoney(deposit);
                                                        if (deposit > 0) {
                                                            fw.write(firstName + " " + lastName + " deposited $" + deposit + " into checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " deposited $" + deposit + " into checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the user wants to withdraw
                                                    case 4:
                                                        rb.print("How much would you like to withdraw?");
                                                        double withdraw = scanner.nextDouble();
                                                        customerList.get(accountIndex).getCheckingaccount().withdrawMoney(withdraw);
                                                        if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                            fw.write(firstName + " " + lastName + " withdrew $" + withdraw + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " withdrew $" + withdraw + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the use does not want to continue and chooses to exit
                                                    case 5:
                                                        rb.print("Thank you!");
                                                        break;
                                                    // Default to ensure that the user makes a valid decision.
                                                    default:
                                                        rb.print("Make a valid decision");
                                                        break;

                                                }
                                                rb.print("Would you like to do anything else today?");
                                                rb.print("1: yes");
                                                rb.print("2: no");
                                                selection = scanner.nextInt();

                                            }
                                            while (selection != 2);
                                            rb.print("Thank you. You are now logged out");
                                            break;
                                        }
                                        if (modifyType == 1) {
                                            do {
                                                // The list of actions the user can perform
                                                rb.print("1: Check Balance");
                                                rb.print("2: Pay Someone");
                                                rb.print("3: Deposit Money");
                                                rb.print("4: Withdraw Balance");
                                                rb.print("5: Cancel");
                                                int action = scanner.nextInt();
                                                PrintStream console = System.out;
                                                switch (action) {
                                                    //The case where the user wants to see it's current balance
                                                    case 1:
                                                        fw.write(firstName + " " + lastName + " made a balance inquiry on savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " made a balance inquiry on savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).getSavingsAccount().getBalance();
                                                        break;
                                                    // The case where the user would like to pay someone else
                                                    case 2:
                                                        rb.print("Who would you like to pay?");
                                                        rb.print("PLEASE CAPITALIZE FIRST LETTER");
                                                        rb.print("Enter first name");
                                                        String payeefirstName = scanner.next();
                                                        rb.print("Enter last name");
                                                        String payeelastName = scanner.next();
                                                        for (int j = 0; j < customerList.size(); j++) {
                                                            if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                                int payeeAccountIndex = j;
                                                                rb.print("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                                double amount = scanner.nextDouble();
                                                                customerList.get(accountIndex).getSavingsAccount().paySomeone(amount, customerList.get(payeeAccountIndex).getSavingsAccount());
                                                                if (amount > 0 || amount <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                                    fw.write(firstName + " " + lastName + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                            firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                                    customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                            firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                                } else
                                                                    fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                                break;
                                                            } else
                                                                rb.print("Could not find user");
                                                        }
                                                        break;
                                                    // The case where the user wants to deposit money
                                                    case 3:
                                                        rb.print("How much would you like to deposit?");
                                                        double deposit = scanner.nextDouble();
                                                        customerList.get(accountIndex).getSavingsAccount().depositMoney(deposit);
                                                        if (deposit > 0) {
                                                            fw.write(firstName + " " + lastName + " deposited $" + deposit + " into savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " deposited $" + deposit + " into savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the user wants to withdraw
                                                    case 4:
                                                        rb.print("How much would you like to withdraw?");
                                                        double withdraw = scanner.nextDouble();
                                                        customerList.get(accountIndex).getSavingsAccount().withdrawMoney(withdraw);
                                                        if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                            fw.write(firstName + " " + lastName + " withdrew $" + withdraw + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " withdrew $" + withdraw + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the use does not want to continue and chooses to exit
                                                    case 5:
                                                        rb.print("Thank you!");
                                                        break;
                                                    // Default to ensure that the user makes a valid decision.
                                                    default:
                                                        rb.print("Make a valid decision");
                                                        break;

                                                }
                                                rb.print("Would you like to do anything else today?");
                                                rb.print("1: yes");
                                                rb.print("2: no");
                                                selection = scanner.nextInt();

                                            }
                                            while (selection != 2);
                                            rb.print("Thank you. You are now logged out");
                                            break;
                                        } else
                                            rb.print("Account not found");
                                        if (modifyType == 2) {
                                            do {
                                                // The list of actions the user can perform
                                                rb.print("1: Check Balance");
                                                rb.print("2: Deposit Money(Pay Bill)");
                                                rb.print("3: Cancel");
                                                int action = scanner.nextInt();
                                                PrintStream console = System.out;
                                                switch (action) {
                                                    //The case where the user wants to see it's current balance
                                                    case 1:
                                                        fw.write(firstName + " " + lastName + " made a balance inquiry on credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                firstName + " " + lastName + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " made a balance inquiry on credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                firstName + " " + lastName + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).getCreditAccount().getBalance();
                                                        break;
                                                    // The case where the user wants to deposit money
                                                    case 2:
                                                        rb.print("How much would you like to pay towards your credit?");
                                                        double deposit = scanner.nextDouble();
                                                        customerList.get(accountIndex).getCreditAccount().depositMoney(deposit);
                                                        if (deposit > 0) {
                                                            fw.write(firstName + " " + lastName + " paid $" + deposit + " into credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                    firstName + " " + lastName + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(firstName + " " + lastName + " paid $" + deposit + " into credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                    firstName + " " + lastName + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the user wants to withdraw
                                                    case 3:
                                                        rb.print("Thank you!");
                                                        break;
                                                    // Default to ensure that the user makes a valid decision.
                                                    default:
                                                        rb.print("Make a valid decision");
                                                        break;

                                                }
                                                rb.print("Would you like to do anything else today?");
                                                rb.print("1: yes");
                                                rb.print("2: no");
                                                selection = scanner.nextInt();

                                            }
                                            while (selection != 2);
                                            rb.print("Thank you. You are now logged out");
                                            break;
                                        } else
                                            rb.print("Account not found");
                                    }

                                }

                            }
                            if (inquireBy.equals("B")) {
                                rb.print("What account type?");
                                rb.print("0: Checking Account");
                                rb.print("1: Savings Account");
                                rb.print("2: Credit Account");
                                int accountType = scanner.nextInt();
                                rb.print("What is the account number?");
                                int accountNumberInput = scanner.nextInt();
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (accountNumberInput == customerList.get(i).getCheckingaccount().accountNumber ||
                                            accountNumberInput == customerList.get(i).getSavingsAccount().accountNumber ||
                                            accountNumberInput == customerList.get(i).getCreditAccount().accountNumber) {
                                        rb.print("Account found");
                                        int accountIndex = i;
                                        boolean correctLogin = false;
                                        String passwordAttempt;
                                        String password = customerList.get(accountIndex).getPassword();
                                        while (correctLogin != true) {
                                            rb.print("Please enter your password");
                                            passwordAttempt = scanner.nextLine();
                                            if (passwordAttempt.equals(password)) {
                                                rb.print("Your password is correct");
                                                correctLogin = true;
                                                break;
                                            }
                                        }
                                        rb.print("Account information for " + customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + "");
                                        rb.print(customerList.get(accountIndex).getDateOfBirth() + " " + customerList.get(accountIndex).getIdentificationNumber()
                                                + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                        rb.print("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                        rb.print("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                        rb.print("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                        if (accountType == 0) {
                                            do {
                                                // The list of actions the user can perform
                                                rb.print("1: Check Balance");
                                                rb.print("2: Pay Someone");
                                                rb.print("3: Deposit Money");
                                                rb.print("4: Withdraw Balance");
                                                rb.print("5: Cancel");
                                                int action = scanner.nextInt();
                                                PrintStream console = System.out;
                                                switch (action) {
                                                    //The case where the user wants to see it's current balance
                                                    case 1:
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).getCheckingaccount().getBalance();
                                                        break;
                                                    // The case where the user would like to pay someone else
                                                    case 2:
                                                        rb.print("Who would you like to pay?");
                                                        rb.print("PLEASE CAPITALIZE FIRST LETTER");
                                                        rb.print("Enter first name");
                                                        String payeefirstName = scanner.next();
                                                        rb.print("Enter last name");
                                                        String payeelastName = scanner.next();
                                                        for (int j = 0; j < customerList.size(); j++) {
                                                            if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                                int payeeAccountIndex = j;
                                                                rb.print("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                                double amount = scanner.nextDouble();
                                                                customerList.get(accountIndex).getCheckingaccount().paySomeone(amount, customerList.get(payeeAccountIndex).getCheckingaccount());
                                                                if (amount > 0 && amount <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                                    fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                            customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                                    customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                            customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                                } else
                                                                    fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                                break;
                                                            } else
                                                                rb.print("Could not find user");
                                                        }
                                                        break;
                                                    // The case where the user wants to deposit money
                                                    case 3:
                                                        rb.print("How much would you like to deposit?");
                                                        double deposit = scanner.nextDouble();
                                                        customerList.get(accountIndex).getCheckingaccount().depositMoney(deposit);
                                                        if (deposit > 0) {
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " deposited $" + deposit + " into checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " deposited $" + deposit + " into checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the user wants to withdraw
                                                    case 4:
                                                        rb.print("How much would you like to withdraw?");
                                                        double withdraw = scanner.nextDouble();
                                                        customerList.get(accountIndex).getCheckingaccount().withdrawMoney(withdraw);
                                                        if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the use does not want to continue and chooses to exit
                                                    case 5:
                                                        rb.print("Thank you!");
                                                        break;
                                                    // Default to ensure that the user makes a valid decision.
                                                    default:
                                                        rb.print("Make a valid decision");
                                                        break;

                                                }
                                                rb.print("Would you like to do anything else today?");
                                                rb.print("1: yes");
                                                rb.print("2: no");
                                                selection = scanner.nextInt();

                                            }
                                            while (selection != 2);
                                            rb.print("Thank you. You are now logged out");
                                            break;
                                        }
                                        if (accountType == 1) {
                                            do {
                                                // The list of actions the user can perform
                                                rb.print("1: Check Balance");
                                                rb.print("2: Pay Someone");
                                                rb.print("3: Deposit Money");
                                                rb.print("4: Withdraw Balance");
                                                rb.print("5: Cancel");
                                                int action = scanner.nextInt();
                                                PrintStream console = System.out;
                                                switch (action) {
                                                    //The case where the user wants to see it's current balance
                                                    case 1:
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).getSavingsAccount().getBalance();
                                                        break;
                                                    // The case where the user would like to pay someone else
                                                    case 2:
                                                        rb.print("Who would you like to pay?");
                                                        rb.print("PLEASE CAPITALIZE FIRST LETTER");
                                                        rb.print("Enter first name");
                                                        String payeefirstName = scanner.next();
                                                        rb.print("Enter last name");
                                                        String payeelastName = scanner.next();
                                                        for (int j = 0; j < customerList.size(); j++) {
                                                            if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                                int payeeAccountIndex = j;
                                                                rb.print("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                                double amount = scanner.nextDouble();
                                                                customerList.get(accountIndex).getSavingsAccount().paySomeone(amount, customerList.get(payeeAccountIndex).getSavingsAccount());
                                                                if (amount > 0 && amount <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                                    fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                            customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                                    customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                            customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                            ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                                } else
                                                                    fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                                break;
                                                            } else
                                                                rb.print("Could not find user");
                                                        }
                                                        break;
                                                    // The case where the user wants to deposit money
                                                    case 3:
                                                        rb.print("How much would you like to deposit?");
                                                        double deposit = scanner.nextDouble();
                                                        customerList.get(accountIndex).getSavingsAccount().depositMoney(deposit);
                                                        if (deposit > 0) {
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " deposited $" + deposit + " into savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " deposited $" + deposit + " into savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the user wants to withdraw
                                                    case 4:
                                                        rb.print("How much would you like to withdraw?");
                                                        double withdraw = scanner.nextDouble();
                                                        customerList.get(accountIndex).getSavingsAccount().withdrawMoney(withdraw);
                                                        if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the use does not want to continue and chooses to exit
                                                    case 5:
                                                        rb.print("Thank you!");
                                                        break;
                                                    // Default to ensure that the user makes a valid decision.
                                                    default:
                                                        rb.print("Make a valid decision");
                                                        break;

                                                }
                                                rb.print("Would you like to do anything else today?");
                                                rb.print("1: yes");
                                                rb.print("2: no");
                                                selection = scanner.nextInt();

                                            }
                                            while (selection != 2);
                                            rb.print("Thank you. You are now logged out");
                                            break;
                                        } else
                                            rb.print("Account not found");
                                        if (accountType == 2) {
                                            do {
                                                // The list of actions the user can perform
                                                rb.print("1: Check Balance");
                                                rb.print("2: Deposit Money(Pay Bill)");
                                                rb.print("3: Withdraw Balance(Add Credit) ");
                                                rb.print("4: Cancel");
                                                int action = scanner.nextInt();
                                                PrintStream console = System.out;
                                                switch (action) {
                                                    //The case where the user wants to see it's current balance
                                                    case 1:
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                        customerList.get(accountIndex).getCreditAccount().getBalance();
                                                        break;
                                                    // The case where the user wants to deposit money
                                                    case 2:
                                                        rb.print("How much would you like to pay towards your credit?");
                                                        double deposit = scanner.nextDouble();
                                                        customerList.get(accountIndex).getCreditAccount().depositMoney(deposit);
                                                        if (deposit > 0) {
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid $" + deposit + " into credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid $" + deposit + " into credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the user wants to withdraw
                                                    case 3:
                                                        rb.print("How much would you like to withdraw?");
                                                        double withdraw = scanner.nextDouble();
                                                        customerList.get(accountIndex).getCreditAccount().withdrawMoney(withdraw);
                                                        if (withdraw >= customerList.get(accountIndex).getCreditAccount().getBalance()) {
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                            customerList.get(accountIndex).addTransaction(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                    customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                    ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                        } else
                                                            fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                        break;
                                                    // The case where the use does not want to continue and chooses to exit
                                                    case 4:
                                                        rb.print("Thank you!");
                                                        break;
                                                    // Default to ensure that the user makes a valid decision.
                                                    default:
                                                        rb.print("Make a valid decision");
                                                        break;

                                                }
                                                rb.print("Would you like to do anything else today?");
                                                rb.print("1: yes");
                                                rb.print("2: no");
                                                selection = scanner.nextInt();

                                            }
                                            while (selection != 2);
                                            rb.print("Thank you. You are now logged out");
                                            break;
                                        } else
                                            rb.print("Account not found");
                                    }

                                }

                            } else {
                                rb.print("Invalid Input");
                            }
                            rb.print("Log into account?");
                            rb.print("1: yes");
                            rb.print("2: no");
                            selection = scanner.nextInt();


                        }
                        while (selection != 2) ;
                        rb.print("Thank you for Banking with us today!");

                    }
                    if (isBankManger == 1) {
                        do {
                            rb.print("Hey boss! Would you like to find your customers account their name or account number?");
                            rb.print("A. Inquire account by name");
                            rb.print("B. Inquire account by type/number");
                            rb.print("C. Generate Bank Statement");
                            String inquireBy = scanner.next();
                            if (inquireBy.equals("A")) {
                                rb.print("PLEASE CAPITALIZE FIRST LETTER");
                                rb.print("Enter first name");
                                String firstName = scanner.next();
                                rb.print("Enter last name");
                                String lastName = scanner.next();
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (firstName.equals(customerList.get(i).getFirstName()) && lastName.equals(customerList.get(i).getLastName())) {
                                        rb.print("Account found");
                                        int accountIndex = i;
                                        rb.print("Account information for " + firstName + " " + lastName + "");
                                        rb.print(customerList.get(accountIndex).getDateOfBirth() + " " + customerList.get(accountIndex).getIdentificationNumber()
                                                + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                        rb.print("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                        rb.print("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                        rb.print("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                    }
                                }
                            }
                            if (inquireBy.equals("B")) {
                                rb.print("What account type?");
                                rb.print("0: Checking Account");
                                rb.print("1: Savings Account");
                                rb.print("2: Credit Account");
                                int accountType = scanner.nextInt();
                                rb.print("What is the account number?");
                                int accountNumberInput = scanner.nextInt();
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (accountNumberInput == customerList.get(i).getCheckingaccount().accountNumber ||
                                            accountNumberInput == customerList.get(i).getSavingsAccount().accountNumber ||
                                            accountNumberInput == customerList.get(i).getCreditAccount().accountNumber) {
                                        rb.print("Account found");
                                        int accountIndex = i;
                                        rb.print("Account information for " + customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + "");
                                        rb.print(customerList.get(accountIndex).getDateOfBirth() + " " + customerList.get(accountIndex).getIdentificationNumber()
                                                + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                        rb.print("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                        rb.print("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                        rb.print("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                        break;
                                    } else
                                        System.err.println("Account not found");
                                }
                            }
                            if (inquireBy.equals("C")) {
                                rb.print("PLEASE CAPITALIZE FIRST LETTER");
                                rb.print("Enter first name");
                                String firstName = scanner.next();
                                rb.print("Enter last name");
                                String lastName = scanner.next();
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (firstName.equals(customerList.get(i).getFirstName()) && lastName.equals(customerList.get(i).getLastName())) {
                                        rb.print("Account found");
                                        int accountIndex = i;
                                        BankStatement generate = new BankStatement();
                                        generate.printBankStatementToFile(customerList.get(i));
                                    }
                                }
                            }
                            rb.print("Would you like to do anything else today?");
                            rb.print("1: yes");
                            rb.print("2: no");
                            selection = scanner.nextInt();

                        } while (selection != 2);
                        rb.print("Thank you boss!");
                    }
                    rb.print("Log into Bank again?");
                    rb.print("1: yes");
                    rb.print("2: no");
                    selection = scanner.nextInt();


                } while (selection != 2) ;
                rb.print("Thank you for Banking with us today!");


                File csvFile = new File("Updated Bank Users.csv");
                PrintWriter out = new PrintWriter(csvFile);
                for (int i = 0; i < customerList.size(); i++) {
                    out.println(customerList.get(i).getIdentificationNumber() + "," + customerList.get(i).getFirstName() + "," + customerList.get(i).getLastName() + "," + customerList.get(i).getDateOfBirth()
                            + "," + customerList.get(i).getAddress() + "," + customerList.get(i).getPhoneNumber()
                            + "," + customerList.get(i).getCheckingaccount().accountNumber + "," + customerList.get(i).getCheckingaccount().getBalance() + "," + customerList.get(i).getSavingsAccount().accountNumber
                            + "," + customerList.get(i).getSavingsAccount().getBalance() + "," + customerList.get(i).getCreditAccount().accountNumber + "," + customerList.get(i).getCreditAccount().getBalance());
                }
                fw.close();
                out.close();
            }

            if (isActionLog == 1) {
                //The path that the excel file containing all the information is located
                Scanner ActionFileScanner = new Scanner(System.in);
                rb.print("Where is your action log located");
                String actionExcelFile = fileScanner.nextLine();
                String actionPath = (actionExcelFile);
                //Empty line to ignore the first line
                String lines = "";
                //A buffered reader that will read the excel file
                BufferedReader abr = new BufferedReader(new FileReader(actionPath));
                abr.readLine();
                while ((lines = abr.readLine()) != null) {
                    if (!lines.isEmpty()) {
                        String[] values = lines.split(",");
                        String fromFirstName = values[0];
                        String fromLastName = values[1];
                        String fromWhere = values[2];
                        String action = values[3];
                        String toFirstName = values[4];
                        String toLastName = values[5];
                        String toWhere = values[6];
                        double actionAmount = Double.parseDouble(values[7]);
                        if (action.equals("pays")) {
                            if (fromWhere.equals("Checking")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        for (int j = 0; j < customerList.size(); j++) {
                                            if (toFirstName.equals(customerList.get(j).getFirstName()) && toLastName.equals(customerList.get(j).getLastName())) {
                                                int toIndex = j;
                                                customerList.get(i).getCheckingaccount().paySomeone(actionAmount, customerList.get(j).getCheckingaccount());
                                                if (actionAmount > 0 && actionAmount <= customerList.get(i).getCheckingaccount().getBalance()) {
                                                    rb.print(fromFirstName + " " + fromLastName + " paid " + toFirstName + " " + toLastName + " $" + actionAmount + " from checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                            fromFirstName + fromLastName + "'s balance for checking-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                            ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                                } else
                                                    rb.print(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (fromWhere.equals("Savings")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        for (int j = 0; j < customerList.size(); j++) {
                                            if (toFirstName.equals(customerList.get(j).getFirstName()) && toLastName.equals(customerList.get(j).getLastName())) {
                                                int toIndex = j;
                                                customerList.get(i).getSavingsAccount().paySomeone(actionAmount, customerList.get(j).getCheckingaccount());
                                                if (actionAmount > 0 && actionAmount <= customerList.get(i).getCheckingaccount().getBalance()) {
                                                    rb.print(fromFirstName + " " + fromLastName + " paid " + toFirstName + " " + toLastName + " $" + actionAmount + " from checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                            fromFirstName + fromLastName + "'s balance for checking-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                            ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                                } else
                                                    rb.print(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (action.equals("transfers")) {
                            if (toWhere.equals("Checking")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        customerList.get(i).getSavingsAccount().transfer(actionAmount, customerList.get(i).getCheckingaccount());
                                        if (actionAmount > 0 && actionAmount <= customerList.get(i).getCheckingaccount().getBalance()) {
                                            rb.print(fromFirstName + " " + fromLastName + " transferred  $" + actionAmount + " from savings- " + customerList.get(i).getSavingsAccount().accountNumber + "to Checking " +
                                                    fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                    ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                        } else
                                            rb.print(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                    }
                                }
                            }
                            if (toWhere.equals("Credit")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        customerList.get(i).getSavingsAccount().transfer(actionAmount, customerList.get(i).getCreditAccount());
                                        if (actionAmount > 0 && actionAmount <= customerList.get(i).getCheckingaccount().getBalance()) {
                                            rb.print(fromFirstName + " " + fromLastName + " transferred  $" + actionAmount + " from savings- " + customerList.get(i).getSavingsAccount().accountNumber + "to Credit " +
                                                    fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                    ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                        } else
                                            rb.print(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                    }
                                }
                            }
                        }

                        if (action.equals("inquires")) {
                            for (int i = 0; i < customerList.size(); i++) {
                                if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                    int fromIndex = i;
                                    customerList.get(i).getCheckingaccount().getBalance();
                                }
                            }
                        }

                        if (action.equals("withdraws")) {
                            if (fromWhere.equals("Checking")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        customerList.get(i).getCheckingaccount().withdrawMoney(actionAmount);
                                        if (actionAmount > 0 && actionAmount <= customerList.get(i).getCheckingaccount().getBalance()) {
                                            rb.print(fromFirstName + " " + fromLastName + " withdrew $" + actionAmount + " from checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                    fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                    ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                        } else
                                            rb.print(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                    }
                                }
                            }
                            if (fromWhere.equals("Saving")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        customerList.get(i).getSavingsAccount().withdrawMoney(actionAmount);
                                        if (actionAmount > 0 && actionAmount <= customerList.get(i).getSavingsAccount().getBalance()) {
                                            rb.print(fromFirstName + " " + fromLastName + " withdrew $" + actionAmount + " from savings- " + customerList.get(i).getSavingsAccount().accountNumber + ". " +
                                                    fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                    ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                        } else
                                            rb.print(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                    }
                                }
                            }
                        }

                        if (action.equals("deposits")) {
                            if (toWhere.equals("Checking")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (toFirstName.equals(customerList.get(i).getFirstName()) && toLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        customerList.get(i).getCheckingaccount().depositMoney(actionAmount);
                                        if (actionAmount > 0) {
                                            rb.print(toFirstName + " " + toLastName + " deposited $" + actionAmount + " into checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                    toFirstName + toLastName + "'s balance for checking-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                    ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                        } else
                                            rb.print(toFirstName + " " + toLastName + ", your transaction was unsuccessful" + "\n");
                                    }
                                }
                            }

                            if (toWhere.equals("Savings")) {
                                for (int i = 0; i < customerList.size(); i++) {
                                    if (toFirstName.equals(customerList.get(i).getFirstName()) && toLastName.equals(customerList.get(i).getLastName())) {
                                        int fromIndex = i;
                                        customerList.get(i).getSavingsAccount().depositMoney(actionAmount);
                                        if (actionAmount > 0) {
                                            rb.print(toFirstName + " " + toLastName + " deposited $" + actionAmount + " into savings- " + customerList.get(i).getSavingsAccount().accountNumber + ". " +
                                                    toFirstName + toLastName + "'s balance for checking-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                    ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                        } else
                                            rb.print(toFirstName + " " + toLastName + ", your transaction was unsuccessful" + "\n");
                                    }
                                }
                            }
                        }
                    } else
                        rb.print("The action log has been completed");
                }
            }
        }

        /**
         * @return List of Customers
         * @throws IOException
         */
        static List<customer> makeCustomerList () throws IOException {
            runBank rb = new runBank();
            //Creates the list of Customers that are located in CSV
            List<customer> customerList = new ArrayList<customer>(50);
            Scanner fileScanner = new Scanner(System.in);
            rb.print("Where is your database located");
            String excelfile = fileScanner.nextLine();
            String path = (excelfile);
            //Empty line to ignore the first line
            String line = "";
            //A buffered reader that will read the excel file
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            //Go through each line of the csv and fill in the information for each character with the corresponding attributes with the values array
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] values = line.split(",");
                    int checkingAccNum = Integer.parseInt(values[4]);
                    int savingsAccNum = Integer.parseInt(values[10]);
                    int creditAccNum = Integer.parseInt(values[5]);
                    double checkingStart = Double.parseDouble(values[1]);
                    double savingsStart = Double.parseDouble(values[3]);
                    double creditStart = Double.parseDouble(values[9]);
                    double creditMax = Double.parseDouble(values[12]);
                    checking checkings = new checking(checkingAccNum, checkingStart);
                    checkings.setRealStart(checkingStart);
                    savings saving = new savings(savingsAccNum, savingsStart);
                    saving.setRealStart(savingsStart);
                    credit credit = new credit(creditAccNum, creditStart, creditMax);
                    credit.setRealStart(creditStart);
                    customer c = new customer(values[7], values[0], values[13], values[6], values[2], values[11], values[8], values[14], checkings, saving, credit) {
                    };
                    customerList.add(c);
                }
            }
            return customerList;
        }

        /**
         * @param printed
         */
        @Override
        public void print (String printed){
            System.out.println(printed);
        }

        @Override
        public void printTransactionListToFileWriter (customer c, List < String > transactionList, FileWriter fw) throws
        IOException {

        }


        /**
         * @param customerList
         */
        @Override
        public void printCustomerList (List < customer > customerList) {
            for (int i = 0; i < customerList.size(); i++) {
                System.out.println(customerList.get(i));
            }
        }

        /**
         * @param c
         */
        @Override
        public void printBankStatementToFile (customer c){

        }

        public boolean isCorrect (String attempt, String password){
            if (attempt == password)
                return true;
            else
                return false;
        }
    }

