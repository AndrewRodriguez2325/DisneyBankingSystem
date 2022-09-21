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


public class runBank {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //Creates the list of Checking Accounts for each disney character
        List<account> accountList = new ArrayList<account>(50);
        List<checking> checkingAccountList = new ArrayList<checking>(50);
        List<savings> savingsAccountList = new ArrayList<savings>(50);
        List<credit> creditAccountList = new ArrayList<credit>(50);
        List<customer> customerList = new ArrayList<customer>(50);

        //The path that the excel file containing all the information is located
        Scanner fileScanner = new Scanner(System.in);
        System.out.println("Where is your database located");
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
                int savingsAccNum = Integer.parseInt(values[0]);
                int creditAccNum = Integer.parseInt(values[5]);
                double checkingStart = Double.parseDouble(values[7]);
                double savingsStart = Double.parseDouble(values[8]);
                double creditStart = Double.parseDouble(values[10]);
                double creditMax = Double.parseDouble(values[9]);
                checking checkings = new checking(checkingAccNum, checkingStart);
                savings saving = new savings(savingsAccNum, savingsStart);
                credit credit = new credit(creditAccNum, creditStart, creditMax);
                customer c = new customer(values[12], values[2], values[3], values[11], values[6], values[1], checkings, saving, credit) {
                };
                customerList.add(c);
            }
        }
        int selection = 0;
        // The file writer that will be used to create the transaction log each time you run the program.
        FileWriter fw = new FileWriter("transactionLog.txt");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        //This would be the best question to ask prior to anything becuase there is no user interaction with action log
        System.out.println("Are you reading from TransactionActions?");
        System.out.println("0: No");
        System.out.println("1: Yes");
        int isActionLog = scanner.nextInt();
        if (isActionLog == 0) {
            //The first question is if person is a bank manager
            do {
                System.out.println("Are you a bank manager?");
                System.out.println("0: No");
                System.out.println("1: Yes");
                int isBankManger = scanner.nextInt();
                if (isBankManger == 0) {
                    //The First question a customer is asked to make sure we know if they need to create an account
                    System.out.println("Existing or new customer?");
                    System.out.println("0: New");
                    System.out.println("1: Existing");
                    int isExisting = scanner.nextInt();
                    if (isExisting == 0) {
                        System.out.println("Thank you for choosing us as your bank, I will just need some basic info from you");
                        customer newCustomer = new customer();
                        System.out.println("What is your first name");
                        newCustomer.setFirstName(scanner.next());
                        scanner.nextLine();
                        System.out.println("What is your last name");
                        newCustomer.setLastName(scanner.next());
                        scanner.nextLine();
                        System.out.println("What is your DOB *mm/dd/yyyy*");
                        newCustomer.setDateOfBirth(scanner.next());
                        scanner.nextLine();
                        int nextIDNumber = customerList.size() + 1;
                        String stringNextID = String.valueOf(nextIDNumber);
                        newCustomer.setIdentificationNumber("000-00-" + stringNextID + "");
                        System.out.println("What is your address");
                        String addy = scanner.nextLine();
                        newCustomer.setAddress(addy);
                        System.out.println("What is your phone number *(xxx) xxx-xxxx*");
                        String phoneNum = scanner.nextLine();
                        newCustomer.setPhoneNumber(phoneNum);
                        System.out.println("In order for us to successfully complete your account we must create at least a savings account");
                        System.out.println("How much would you like to deposit into your new savings account");
                        double savingStart = scanner.nextDouble();
                        savings newCusSaving = new savings(customerList.size(), savingStart);
                        newCustomer.setSavingsAccount(newCusSaving);
                        System.out.println("Would you like to create a checking account");
                        System.out.println("0: No");
                        System.out.println("1: Yes");
                        int createChecking = scanner.nextInt();
                        if (createChecking == 0) {
                            System.out.println("No worries. We understand");
                        }
                        if (createChecking == 1) {
                            System.out.println("How much would you like to deposit into your new checkings account");
                            double checkingStart = scanner.nextDouble();
                            checking newCusChecking = new checking(customerList.size(), checkingStart);
                            newCustomer.setCheckingaccount(newCusChecking);
                        }
                        System.out.println("Would you like to create a credit account");
                        System.out.println("0: No");
                        System.out.println("1: Yes");
                        int createCredit = scanner.nextInt();
                        if (createCredit == 0) {
                            System.out.println("No worries. We understand");
                        }
                        if (createCredit == 1) {
                            System.out.println("We offer first time account holders a limit of 1000");
                            credit newCusCredit = new credit(customerList.size(), 0, 1000);
                            newCustomer.setCreditAccount(newCusCredit);
                        }
                        System.out.println("Thank you again and we hope to offer the best banking experience");
                        customerList.add(newCustomer);
                    }
                    if (isExisting == 1) {
                        System.out.println("Welcome!");
                    }
                    do {
                        System.out.println("Hey there! Would you like to find your account using your name or account number?");
                        System.out.println("A. Inquire account by name");
                        System.out.println("B. Inquire account by type/number");
                        String inquireBy = scanner.next();
                        if (inquireBy.equals("A")) {
                            System.out.println("PLEASE CAPITALIZE FIRST LETTER");
                            System.out.println("Enter first name");
                            String firstName = scanner.next();
                            System.out.println("Enter last name");
                            String lastName = scanner.next();
                            for (int i = 0; i < customerList.size(); i++) {
                                if (firstName.equals(customerList.get(i).getFirstName()) && lastName.equals(customerList.get(i).getLastName())) {
                                    System.out.println("Account found");
                                    int accountIndex = i;
                                    System.out.println("Account information for " + firstName + " " + lastName + "");
                                    System.out.println(customerList.get(accountIndex).getDateOfBirth() + " " + customerList.get(accountIndex).getIdentificationNumber()
                                            + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                    if (customerList.get(accountIndex).getCheckingaccount() == null)
                                        System.out.println("You do not have a checking account with us");
                                    else
                                        System.out.println("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                    System.out.println("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                    if (customerList.get(accountIndex).getCreditAccount() == null)
                                        System.out.println("You do not have a credit account with us");
                                    else
                                        System.out.println("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                    System.out.println("Which Account would you like to modify");
                                    System.out.println("0: Checking Account");
                                    System.out.println("1: Savings Account");
                                    System.out.println("2: Credit Account");
                                    int modifyType = scanner.nextInt();
                                    if (modifyType == 0) {
                                        do {
                                            // The list of actions the user can perform
                                            System.out.println("1: Check Balance");
                                            System.out.println("2: Pay Someone");
                                            System.out.println("3: Deposit Money");
                                            System.out.println("4: Withdraw Balance");
                                            System.out.println("5: Cancel");
                                            int action = scanner.nextInt();
                                            PrintStream console = System.out;
                                            switch (action) {
                                                //The case where the user wants to see it's current balance
                                                case 1:
                                                    fw.write(firstName + " " + lastName + " made a balance inquiry on checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                            firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                            ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                    customerList.get(accountIndex).getCheckingaccount().getBalance();
                                                    break;
                                                // The case where the user would like to pay someone else
                                                case 2:
                                                    System.out.println("Who would you like to pay?");
                                                    System.out.println("PLEASE CAPITALIZE FIRST LETTER");
                                                    System.out.println("Enter first name");
                                                    String payeefirstName = scanner.next();
                                                    System.out.println("Enter last name");
                                                    String payeelastName = scanner.next();
                                                    for (int j = 0; j < customerList.size(); j++) {
                                                        if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                            int payeeAccountIndex = j;
                                                            System.out.println("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                            double amount = scanner.nextDouble();
                                                            customerList.get(accountIndex).getCheckingaccount().paySomeone(amount, customerList.get(payeeAccountIndex).getCheckingaccount());
                                                            if (amount > 0 && amount <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                                fw.write(firstName + " " + lastName + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                        firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                        ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                            } else
                                                                fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                            break;
                                                        } else
                                                            System.out.println("Could not find user");
                                                    }
                                                    break;
                                                // The case where the user wants to deposit money
                                                case 3:
                                                    System.out.println("How much would you like to deposit?");
                                                    double deposit = scanner.nextDouble();
                                                    customerList.get(accountIndex).getCheckingaccount().depositMoney(deposit);
                                                    if (deposit > 0)
                                                        fw.write(firstName + " " + lastName + " deposited $" + deposit + " into checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                    else
                                                        fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the user wants to withdraw
                                                case 4:
                                                    System.out.println("How much would you like to withdraw?");
                                                    double withdraw = scanner.nextDouble();
                                                    customerList.get(accountIndex).getCheckingaccount().withdrawMoney(withdraw);
                                                    if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                        fw.write(firstName + " " + lastName + " withdrew $" + withdraw + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                    } else
                                                        fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the use does not want to continue and chooses to exit
                                                case 5:
                                                    System.out.println("Thank you!");
                                                    break;
                                                // Default to ensure that the user makes a valid decision.
                                                default:
                                                    System.out.println("Make a valid decision");
                                                    break;

                                            }
                                            System.out.println("Would you like to do anything else today?");
                                            System.out.println("1: yes");
                                            System.out.println("2: no");
                                            selection = scanner.nextInt();

                                        }
                                        while (selection != 2);
                                        System.out.println("Thank you. You are now logged out");
                                        break;
                                    }
                                    if (modifyType == 1) {
                                        do {
                                            // The list of actions the user can perform
                                            System.out.println("1: Check Balance");
                                            System.out.println("2: Pay Someone");
                                            System.out.println("3: Deposit Money");
                                            System.out.println("4: Withdraw Balance");
                                            System.out.println("5: Cancel");
                                            int action = scanner.nextInt();
                                            PrintStream console = System.out;
                                            switch (action) {
                                                //The case where the user wants to see it's current balance
                                                case 1:
                                                    fw.write(firstName + " " + lastName + " made a balance inquiry on savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                            firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                            ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                    customerList.get(accountIndex).getSavingsAccount().getBalance();
                                                    break;
                                                // The case where the user would like to pay someone else
                                                case 2:
                                                    System.out.println("Who would you like to pay?");
                                                    System.out.println("PLEASE CAPITALIZE FIRST LETTER");
                                                    System.out.println("Enter first name");
                                                    String payeefirstName = scanner.next();
                                                    System.out.println("Enter last name");
                                                    String payeelastName = scanner.next();
                                                    for (int j = 0; j < customerList.size(); j++) {
                                                        if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                            int payeeAccountIndex = j;
                                                            System.out.println("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                            double amount = scanner.nextDouble();
                                                            customerList.get(accountIndex).getSavingsAccount().paySomeone(amount, customerList.get(payeeAccountIndex).getSavingsAccount());
                                                            if (amount > 0 && amount <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                                fw.write(firstName + " " + lastName + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                        firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                        ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                            } else
                                                                fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                            break;
                                                        } else
                                                            System.out.println("Could not find user");
                                                    }
                                                    break;
                                                // The case where the user wants to deposit money
                                                case 3:
                                                    System.out.println("How much would you like to deposit?");
                                                    double deposit = scanner.nextDouble();
                                                    customerList.get(accountIndex).getSavingsAccount().depositMoney(deposit);
                                                    if (deposit > 0)
                                                        fw.write(firstName + " " + lastName + " deposited $" + deposit + " into savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                    else
                                                        fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the user wants to withdraw
                                                case 4:
                                                    System.out.println("How much would you like to withdraw?");
                                                    double withdraw = scanner.nextDouble();
                                                    customerList.get(accountIndex).getSavingsAccount().withdrawMoney(withdraw);
                                                    if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                        fw.write(firstName + " " + lastName + " withdrew $" + withdraw + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                    } else
                                                        fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the use does not want to continue and chooses to exit
                                                case 5:
                                                    System.out.println("Thank you!");
                                                    break;
                                                // Default to ensure that the user makes a valid decision.
                                                default:
                                                    System.out.println("Make a valid decision");
                                                    break;

                                            }
                                            System.out.println("Would you like to do anything else today?");
                                            System.out.println("1: yes");
                                            System.out.println("2: no");
                                            selection = scanner.nextInt();

                                        }
                                        while (selection != 2);
                                        System.out.println("Thank you. You are now logged out");
                                        break;
                                    } else
                                        System.out.println("Account not found");
                                    if (modifyType == 2) {
                                        do {
                                            // The list of actions the user can perform
                                            System.out.println("1: Check Balance");
                                            System.out.println("2: Deposit Money(Pay Bill)");
                                            System.out.println("3: Cancel");
                                            int action = scanner.nextInt();
                                            PrintStream console = System.out;
                                            switch (action) {
                                                //The case where the user wants to see it's current balance
                                                case 1:
                                                    fw.write(firstName + " " + lastName + " made a balance inquiry on credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                            firstName + lastName + "'s balance for checking-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                            ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                    customerList.get(accountIndex).getCreditAccount().getBalance();
                                                    break;
                                                // The case where the user wants to deposit money
                                                case 2:
                                                    System.out.println("How much would you like to pay towards your credit?");
                                                    double deposit = scanner.nextDouble();
                                                    customerList.get(accountIndex).getCreditAccount().depositMoney(deposit);
                                                    if (deposit > 0)
                                                        fw.write(firstName + " " + lastName + " paid $" + deposit + " into credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                firstName + lastName + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                    else
                                                        fw.write(firstName + " " + lastName + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the user wants to withdraw
                                                case 3:
                                                    System.out.println("Thank you!");
                                                    break;
                                                // Default to ensure that the user makes a valid decision.
                                                default:
                                                    System.out.println("Make a valid decision");
                                                    break;

                                            }
                                            System.out.println("Would you like to do anything else today?");
                                            System.out.println("1: yes");
                                            System.out.println("2: no");
                                            selection = scanner.nextInt();

                                        }
                                        while (selection != 2);
                                        System.out.println("Thank you. You are now logged out");
                                        break;
                                    } else
                                        System.out.println("Account not found");
                                }

                            }

                        }
                        if (inquireBy.equals("B")) {
                            System.out.println("What account type?");
                            System.out.println("0: Checking Account");
                            System.out.println("1: Savings Account");
                            System.out.println("2: Credit Account");
                            int accountType = scanner.nextInt();
                            System.out.println("What is the account number?");
                            int accountNumberInput = scanner.nextInt();
                            for (int i = 0; i < customerList.size(); i++) {
                                if (accountNumberInput == customerList.get(i).getCheckingaccount().accountNumber ||
                                        accountNumberInput == customerList.get(i).getSavingsAccount().accountNumber ||
                                        accountNumberInput == customerList.get(i).getCreditAccount().accountNumber) {
                                    System.out.println("Account found");
                                    int accountIndex = i;
                                    System.out.println("Account information for " + customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + "");
                                    System.out.println(customerList.get(accountIndex).getDateOfBirth() + " " + customerList.get(accountIndex).getIdentificationNumber()
                                            + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                    System.out.println("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                    System.out.println("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                    System.out.println("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                    if (accountType == 0) {
                                        do {
                                            // The list of actions the user can perform
                                            System.out.println("1: Check Balance");
                                            System.out.println("2: Pay Someone");
                                            System.out.println("3: Deposit Money");
                                            System.out.println("4: Withdraw Balance");
                                            System.out.println("5: Cancel");
                                            int action = scanner.nextInt();
                                            PrintStream console = System.out;
                                            switch (action) {
                                                //The case where the user wants to see it's current balance
                                                case 1:
                                                    fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                            customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                            ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                    customerList.get(accountIndex).getCheckingaccount().getBalance();
                                                    break;
                                                // The case where the user would like to pay someone else
                                                case 2:
                                                    System.out.println("Who would you like to pay?");
                                                    System.out.println("PLEASE CAPITALIZE FIRST LETTER");
                                                    System.out.println("Enter first name");
                                                    String payeefirstName = scanner.next();
                                                    System.out.println("Enter last name");
                                                    String payeelastName = scanner.next();
                                                    for (int j = 0; j < customerList.size(); j++) {
                                                        if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                            int payeeAccountIndex = j;
                                                            System.out.println("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                            double amount = scanner.nextDouble();
                                                            customerList.get(accountIndex).getCheckingaccount().paySomeone(amount, customerList.get(payeeAccountIndex).getCheckingaccount());
                                                            if (amount > 0 && amount <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                                fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                        customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                        ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                            } else
                                                                fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                            break;
                                                        } else
                                                            System.out.println("Could not find user");
                                                    }
                                                    break;
                                                // The case where the user wants to deposit money
                                                case 3:
                                                    System.out.println("How much would you like to deposit?");
                                                    double deposit = scanner.nextDouble();
                                                    customerList.get(accountIndex).getCheckingaccount().depositMoney(deposit);
                                                    if (deposit > 0)
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " deposited $" + deposit + " into checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                    else
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the user wants to withdraw
                                                case 4:
                                                    System.out.println("How much would you like to withdraw?");
                                                    double withdraw = scanner.nextDouble();
                                                    customerList.get(accountIndex).getCheckingaccount().withdrawMoney(withdraw);
                                                    if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getCheckingaccount().getBalance()) {
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from checking- " + customerList.get(accountIndex).getCheckingaccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for checking-" + customerList.get(accountIndex).getCheckingaccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCheckingaccount().getBalance() + "\n");
                                                    } else
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the use does not want to continue and chooses to exit
                                                case 5:
                                                    System.out.println("Thank you!");
                                                    break;
                                                // Default to ensure that the user makes a valid decision.
                                                default:
                                                    System.out.println("Make a valid decision");
                                                    break;

                                            }
                                            System.out.println("Would you like to do anything else today?");
                                            System.out.println("1: yes");
                                            System.out.println("2: no");
                                            selection = scanner.nextInt();

                                        }
                                        while (selection != 2);
                                        System.out.println("Thank you. You are now logged out");
                                        break;
                                    }
                                    if (accountType == 1) {
                                        do {
                                            // The list of actions the user can perform
                                            System.out.println("1: Check Balance");
                                            System.out.println("2: Pay Someone");
                                            System.out.println("3: Deposit Money");
                                            System.out.println("4: Withdraw Balance");
                                            System.out.println("5: Cancel");
                                            int action = scanner.nextInt();
                                            PrintStream console = System.out;
                                            switch (action) {
                                                //The case where the user wants to see it's current balance
                                                case 1:
                                                    fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                            customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                            ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                    customerList.get(accountIndex).getSavingsAccount().getBalance();
                                                    break;
                                                // The case where the user would like to pay someone else
                                                case 2:
                                                    System.out.println("Who would you like to pay?");
                                                    System.out.println("PLEASE CAPITALIZE FIRST LETTER");
                                                    System.out.println("Enter first name");
                                                    String payeefirstName = scanner.next();
                                                    System.out.println("Enter last name");
                                                    String payeelastName = scanner.next();
                                                    for (int j = 0; j < customerList.size(); j++) {
                                                        if (payeefirstName.equals(customerList.get(j).getFirstName()) && payeelastName.equals(customerList.get(j).getLastName())) {
                                                            int payeeAccountIndex = j;
                                                            System.out.println("How much would you to pay " + payeefirstName + " " + payeelastName);
                                                            double amount = scanner.nextDouble();
                                                            customerList.get(accountIndex).getSavingsAccount().paySomeone(amount, customerList.get(payeeAccountIndex).getSavingsAccount());
                                                            if (amount > 0 && amount <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                                fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid " + payeefirstName + " " + payeelastName + " $" + amount + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                        customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                        ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                            } else
                                                                fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                            break;
                                                        } else
                                                            System.out.println("Could not find user");
                                                    }
                                                    break;
                                                // The case where the user wants to deposit money
                                                case 3:
                                                    System.out.println("How much would you like to deposit?");
                                                    double deposit = scanner.nextDouble();
                                                    customerList.get(accountIndex).getSavingsAccount().depositMoney(deposit);
                                                    if (deposit > 0)
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " deposited $" + deposit + " into savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                    else
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the user wants to withdraw
                                                case 4:
                                                    System.out.println("How much would you like to withdraw?");
                                                    double withdraw = scanner.nextDouble();
                                                    customerList.get(accountIndex).getSavingsAccount().withdrawMoney(withdraw);
                                                    if (withdraw > 0 && withdraw <= customerList.get(accountIndex).getSavingsAccount().getBalance()) {
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from savings- " + customerList.get(accountIndex).getSavingsAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for savings-" + customerList.get(accountIndex).getSavingsAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getSavingsAccount().getBalance() + "\n");
                                                    } else
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the use does not want to continue and chooses to exit
                                                case 5:
                                                    System.out.println("Thank you!");
                                                    break;
                                                // Default to ensure that the user makes a valid decision.
                                                default:
                                                    System.out.println("Make a valid decision");
                                                    break;

                                            }
                                            System.out.println("Would you like to do anything else today?");
                                            System.out.println("1: yes");
                                            System.out.println("2: no");
                                            selection = scanner.nextInt();

                                        }
                                        while (selection != 2);
                                        System.out.println("Thank you. You are now logged out");
                                        break;
                                    } else
                                        System.out.println("Account not found");
                                    if (accountType == 2) {
                                        do {
                                            // The list of actions the user can perform
                                            System.out.println("1: Check Balance");
                                            System.out.println("2: Deposit Money(Pay Bill)");
                                            System.out.println("3: Withdraw Balance(Add Credit) ");
                                            System.out.println("4: Cancel");
                                            int action = scanner.nextInt();
                                            PrintStream console = System.out;
                                            switch (action) {
                                                //The case where the user wants to see it's current balance
                                                case 1:
                                                    fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " made a balance inquiry on credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                            customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                            ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                    customerList.get(accountIndex).getCreditAccount().getBalance();
                                                    break;
                                                // The case where the user wants to deposit money
                                                case 2:
                                                    System.out.println("How much would you like to pay towards your credit?");
                                                    double deposit = scanner.nextDouble();
                                                    customerList.get(accountIndex).getCreditAccount().depositMoney(deposit);
                                                    if (deposit > 0)
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " paid $" + deposit + " into credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                    else
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the user wants to withdraw
                                                case 3:
                                                    System.out.println("How much would you like to withdraw?");
                                                    double withdraw = scanner.nextDouble();
                                                    customerList.get(accountIndex).getCreditAccount().withdrawMoney(withdraw);
                                                    if (withdraw >= customerList.get(accountIndex).getCreditAccount().getBalance()) {
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + " withdrew $" + withdraw + " from credit- " + customerList.get(accountIndex).getCreditAccount().accountNumber + ". " +
                                                                customerList.get(accountIndex).getFirstName() + customerList.get(accountIndex).getLastName() + "'s balance for credit-" + customerList.get(accountIndex).getCreditAccount().accountNumber +
                                                                ": $" + customerList.get(accountIndex).getCreditAccount().getBalance() + "\n");
                                                    } else
                                                        fw.write(customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + ", your transaction was unsuccessful" + "\n");
                                                    break;
                                                // The case where the use does not want to continue and chooses to exit
                                                case 4:
                                                    System.out.println("Thank you!");
                                                    break;
                                                // Default to ensure that the user makes a valid decision.
                                                default:
                                                    System.out.println("Make a valid decision");
                                                    break;

                                            }
                                            System.out.println("Would you like to do anything else today?");
                                            System.out.println("1: yes");
                                            System.out.println("2: no");
                                            selection = scanner.nextInt();

                                        }
                                        while (selection != 2);
                                        System.out.println("Thank you. You are now logged out");
                                        break;
                                    } else
                                        System.out.println("Account not found");
                                }

                            }

                        } else {
                            System.out.println("Invalid Input");
                        }
                        System.out.println("Log into account?");
                        System.out.println("1: yes");
                        System.out.println("2: no");
                        selection = scanner.nextInt();


                    } while (selection != 2);
                    fw.close();
                    System.out.println("Thank you for Banking with us today!");

                }
                if (isBankManger == 1) {
                    do {
                        System.out.println("Hey boss! Would you like to find your customers account their name or account number?");
                        System.out.println("A. Inquire account by name");
                        System.out.println("B. Inquire account by type/number");
                        System.out.println("C. Generate Bank Statement");
                        String inquireBy = scanner.next();
                        if (inquireBy.equals("A")) {
                            System.out.println("PLEASE CAPITALIZE FIRST LETTER");
                            System.out.println("Enter first name");
                            String firstName = scanner.next();
                            System.out.println("Enter last name");
                            String lastName = scanner.next();
                            for (int i = 0; i < customerList.size(); i++) {
                                if (firstName.equals(customerList.get(i).getFirstName()) && lastName.equals(customerList.get(i).getLastName())) {
                                    System.out.println("Account found");
                                    int accountIndex = i;
                                    System.out.println("Account information for " + firstName + " " + lastName + "");
                                    System.out.println(customerList.get(accountIndex).getDateOfBirth() + " " + customerList.get(accountIndex).getIdentificationNumber()
                                            + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                    System.out.println("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                    System.out.println("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                    System.out.println("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                }
                            }
                        }
                        if (inquireBy.equals("B")) {
                            System.out.println("What account type?");
                            System.out.println("0: Checking Account");
                            System.out.println("1: Savings Account");
                            System.out.println("2: Credit Account");
                            int accountType = scanner.nextInt();
                            System.out.println("What is the account number?");
                            int accountNumberInput = scanner.nextInt();
                            for (int i = 0; i < customerList.size(); i++) {
                                if (accountNumberInput == customerList.get(i).getCheckingaccount().accountNumber ||
                                        accountNumberInput == customerList.get(i).getSavingsAccount().accountNumber ||
                                        accountNumberInput == customerList.get(i).getCreditAccount().accountNumber) {
                                    System.out.println("Account found");
                                    int accountIndex = i;
                                    System.out.println("Account information for " + customerList.get(accountIndex).getFirstName() + " " + customerList.get(accountIndex).getLastName() + "");
                                    System.out.println(customerList.get(accountIndex).getDateOfBirth() + " " + customerList.get(accountIndex).getIdentificationNumber()
                                            + " " + customerList.get(accountIndex).getAddress() + " " + customerList.get(accountIndex).getPhoneNumber());
                                    System.out.println("Checking Account Balence: " + customerList.get(accountIndex).getCheckingaccount().startingBalance);
                                    System.out.println("Savings Account Balence: " + customerList.get(accountIndex).getSavingsAccount().startingBalance);
                                    System.out.println("Credit Account Balence: " + customerList.get(accountIndex).getCreditAccount().startingBalance);
                                    break;
                                } else
                                    System.err.println("Account not found");
                            }
                        }
                        if (inquireBy.equals("C")) {
                            System.out.println("PLEASE CAPITALIZE FIRST LETTER");
                            System.out.println("Enter first name");
                            String firstName = scanner.next();
                            System.out.println("Enter last name");
                            String lastName = scanner.next();
                            for (int i = 0; i < customerList.size(); i++) {
                                if (firstName.equals(customerList.get(i).getFirstName()) && lastName.equals(customerList.get(i).getLastName())) {
                                    System.out.println("Account found");
                                    int accountIndex = i;
                                    BankStatement generate = new BankStatement();
                                    generate.writeBankStatement(customerList.get(i));
                                }
                            }
                        }
                        System.out.println("Would you like to do anything else today?");
                        System.out.println("1: yes");
                        System.out.println("2: no");
                        selection = scanner.nextInt();

                    } while (selection != 2);
                    fw.close();
                    System.out.println("Thank you boss!");
                    selection = scanner.nextInt();
                }
                System.out.println("Log into Bank as manager?");
                System.out.println("1: yes");
                System.out.println("2: no");
                selection = scanner.nextInt();


            } while (selection != 2);
            fw.close();
            System.out.println("Thank you for Banking with us today!");


            File csvFile = new File("Updated Bank Users.csv");
            PrintWriter out = new PrintWriter(csvFile);
            for (int i = 0; i < customerList.size(); i++) {
                out.println(customerList.get(i).getFirstName() + "," + customerList.get(i).getLastName() + "," + customerList.get(i).getDateOfBirth()
                        + "," + customerList.get(i).getIdentificationNumber() + "," + customerList.get(i).getAddress() + "," + customerList.get(i).getPhoneNumber()
                        + "," + customerList.get(i).getCheckingaccount().accountNumber + "," + customerList.get(i).getSavingsAccount().accountNumber + "," + customerList.get(i).getCreditAccount().accountNumber
                        + "," + customerList.get(i).getCheckingaccount().getBalance() + "," + customerList.get(i).getSavingsAccount().getBalance() + "," + customerList.get(i).getCreditAccount().getBalance());
            }
            out.close();
        }

        if (isActionLog == 1) {
            //The path that the excel file containing all the information is located
            Scanner ActionFileScanner = new Scanner(System.in);
            System.out.println("Where is your action log located");
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
                                                System.out.println(fromFirstName + " " + fromLastName + " paid " + toFirstName + " " + toLastName + " $" + actionAmount + " from checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                        fromFirstName + fromLastName + "'s balance for checking-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                        ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                            } else
                                                System.out.println(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
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
                                                System.out.println(fromFirstName + " " + fromLastName + " paid " + toFirstName + " " + toLastName + " $" + actionAmount + " from checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                        fromFirstName + fromLastName + "'s balance for checking-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                        ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                            } else
                                                System.out.println(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
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
                                        System.out.println(fromFirstName + " " + fromLastName + " transferred  $" + actionAmount + " from savings- " + customerList.get(i).getSavingsAccount().accountNumber + "to Checking " +
                                                fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                    } else
                                        System.out.println(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                }
                            }
                        }
                        if (toWhere.equals("Credit")) {
                            for (int i = 0; i < customerList.size(); i++) {
                                if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                    int fromIndex = i;
                                    customerList.get(i).getSavingsAccount().transfer(actionAmount, customerList.get(i).getCreditAccount());
                                    if (actionAmount > 0 && actionAmount <= customerList.get(i).getCheckingaccount().getBalance()) {
                                        System.out.println(fromFirstName + " " + fromLastName + " transferred  $" + actionAmount + " from savings- " + customerList.get(i).getSavingsAccount().accountNumber + "to Credit " +
                                                fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                    } else
                                        System.out.println(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
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
                                        System.out.println(fromFirstName + " " + fromLastName + " withdrew $" + actionAmount + " from checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                    } else
                                        System.out.println(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
                                }
                            }
                        }
                        if (fromWhere.equals("Saving")) {
                            for (int i = 0; i < customerList.size(); i++) {
                                if (fromFirstName.equals(customerList.get(i).getFirstName()) && fromLastName.equals(customerList.get(i).getLastName())) {
                                    int fromIndex = i;
                                    customerList.get(i).getSavingsAccount().withdrawMoney(actionAmount);
                                    if (actionAmount > 0 && actionAmount <= customerList.get(i).getSavingsAccount().getBalance()) {
                                        System.out.println(fromFirstName + " " + fromLastName + " withdrew $" + actionAmount + " from savings- " + customerList.get(i).getSavingsAccount().accountNumber + ". " +
                                                fromFirstName + fromLastName + "'s balance for savings-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                    } else
                                        System.out.println(fromFirstName + " " + fromLastName + ", your transaction was unsuccessful" + "\n");
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
                                        System.out.println(toFirstName + " " + toLastName + " deposited $" + actionAmount + " into checking- " + customerList.get(i).getCheckingaccount().accountNumber + ". " +
                                                toFirstName + toLastName + "'s balance for checking-" + customerList.get(i).getCheckingaccount().accountNumber +
                                                ": $" + customerList.get(i).getCheckingaccount().getBalance() + "\n");
                                    } else
                                        System.out.println(toFirstName + " " + toLastName + ", your transaction was unsuccessful" + "\n");
                                }
                            }
                        }

                        if (toWhere.equals("Savings")) {
                            for (int i = 0; i < customerList.size(); i++) {
                                if (toFirstName.equals(customerList.get(i).getFirstName()) && toLastName.equals(customerList.get(i).getLastName())) {
                                    int fromIndex = i;
                                    customerList.get(i).getSavingsAccount().depositMoney(actionAmount);
                                    if (actionAmount > 0) {
                                        System.out.println(toFirstName + " " + toLastName + " deposited $" + actionAmount + " into savings- " + customerList.get(i).getSavingsAccount().accountNumber + ". " +
                                                toFirstName + toLastName + "'s balance for checking-" + customerList.get(i).getSavingsAccount().accountNumber +
                                                ": $" + customerList.get(i).getSavingsAccount().getBalance() + "\n");
                                    } else
                                        System.out.println(toFirstName + " " + toLastName + ", your transaction was unsuccessful" + "\n");
                                }
                            }
                        }
                    }
                } else
                    System.out.println("The action log has been completed");
            }
        }
    }
}

