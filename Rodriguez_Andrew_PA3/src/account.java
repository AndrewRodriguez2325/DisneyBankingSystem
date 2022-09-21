
import java.io.IOException;

/**
 * @author Andrew Rodriguez
 * @version 1.0
 * @since February 9 2020
 * The account class is an abstract class containing the attributes that need to be carried across all three types of accounts
 * that is checking, savings, and credit.
 */
public abstract class account {
    /**
     * Attributes that carry for all different classes of type account.
     * The attributes consist of basic information that every account should have.
     */
    protected int accountNumber;
    protected double startingBalance;

    /**
     * Constructors used for account class. Tried to incorporate all cases including the default.
     */
    public account() {
    }

    /**
     * @param accountNumber
     * @param accountBalance
     */
    public account(int accountNumber, double accountBalance) {
        this.accountNumber = accountNumber;
        this.startingBalance = accountBalance;
    }

    /**
     * Getters and setters used for account class.
     */

    /**
     * @return
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return
     */

    public double getStartingBalance() {
        return startingBalance;
    }

    /**
     * @param startingBalance
     */
    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    /**
     * @return none
     * @throws IOException Method to get the balance of the user who is signed in
     */
    public abstract double getBalance() throws IOException;

    /**
     * @param amount
     * @return none
     * The method used if the user wants to deposit money into their account
     * @throws IOException
     */

    public abstract void depositMoney(double amount) throws IOException;

    /**
     * @param amount
     * @return none
     * The method used to withdraw money from the users bank account
     * @throws IOException
     */
    public abstract void withdrawMoney(double amount) throws IOException;

    /**
     * @param amount
     * @param account
     * @return none
     * The method used to pay someone a specific amount to the specified account
     */
    public abstract void paySomeone(double amount, account account);



    public abstract void transfer(double amount, account account);




}
