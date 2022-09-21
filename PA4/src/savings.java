import java.io.IOException;

/**
 * @author Andrew Rodriguez
 * @version 1.0
 * @since February 9 2020
 * <p>
 * The savings class creates an instance of a savings account for a customer and contains their account number and
 * account balance.
 */

public class savings extends account {

    /**
     * Constructors used for savings class. Tried to incorporate all cases including the default.
     */

    public savings() {
    }

    public savings(int accountNumber, double accountBalance) {
        super(accountNumber, accountBalance);
    }

    /**
     * @param
     * @return double
     * @throws IOException Method to get the balance of the user who is signed in
     */
    public double getBalance() throws IOException {
        System.out.println(startingBalance);
        return startingBalance;
    }

    public double start(){
        return startingBalance;
    }

    public savings(int accountNumber, double startingBalance, double realStart) {
        super(accountNumber, startingBalance, realStart);
    }

    /**
     * @param amount
     * @return none
     * The method used if the user wants to deposit money into their account
     * @throws IOException
     */
    public void depositMoney(double amount) throws IOException {
        if (amount > 0) {
            startingBalance += amount;
            System.out.println("You have deposited $" + amount + " into savings account");
        } else {
            System.err.println("Amount deposited has to be positive.");
        }
    }

    /**
     * @param amount
     * @return none
     * The method used to withdraw money from the users bank account
     * @throws IOException
     */

    public void withdrawMoney(double amount) throws IOException {
        if (amount > 0 && amount <= startingBalance) {
            startingBalance -= amount;
            System.out.println("You have withdrawn $" + amount + " from savings account");
        } else {
            System.err.println("You have insufficient funds. Your transaction cannot be completed at the moment.");
        }
    }

    /**
     * @param amount
     * @param account
     * @return Method used if user wants to pay someone a speicifc amount and chooses which account they
     * would like to enter amount into
     */
    @Override
    public void paySomeone(double amount, account account) {
    }

    public void transfer(double amount, account payee) {
        if (amount > 0 && amount <= startingBalance) {
            startingBalance -= amount;
            payee.startingBalance += amount;
        } else {
            System.err.println("You have insufficient funds. Your transaction cannot be completed at the moment.");
        }
    }


}


