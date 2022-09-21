import java.io.IOException;

/**
 * @author Andrew Rodriguez
 * @version 1.0
 * @since February 9 2020
 * <p>
 * The credit class creates an instance of a credit account for a customer and contains their account number and
 * account balance.
 */

public class credit extends account {
    double maxCredit;

    /**
     * Constructors used for credit class. Tried to incorporate all cases including the default.
     */
    public credit() {
    }

    public credit(int accountNumber, double accountBalance, double maxCredit) {
        super(accountNumber, accountBalance);
        this.maxCredit = maxCredit;
    }

    /**
     * @param
     * @return double
     * @throws IOException
     * Method to get the balance of the user who is signed in
     */
    public double getBalance() throws IOException {
        System.out.println(startingBalance);
        return startingBalance;
    }

    public double start(){
        return startingBalance;
    }

    public credit(int accountNumber, double startingBalance, double realStart, double maxCredit) {
        super(accountNumber, startingBalance, realStart);
        this.maxCredit = maxCredit;
    }

    public double getMaxCredit() {
        return maxCredit;
    }

    public void setMaxCredit(double maxCredit) {
        this.maxCredit = maxCredit;
    }

    /**
     * @param amount
     * @return none
     * @throws IOException
     * The method used if the user wants to deposit money into their account
     */
    public void depositMoney(double amount) throws IOException {
        if (amount > 0 && amount <= maxCredit) {
            startingBalance += amount;
            System.out.println("You have deposited $" + amount + " into credit account");
        } else {
            System.err.println("Amount deposited has to be positive, be less then what you currently owe, and you cannot pay more then your max credit");
        }
    }

    /**
     * @param amount
     * @return none
     * The method used to withdraw money from the users bank account
     * @throws IOException
     */
    public void withdrawMoney(double amount) throws IOException {
        if (amount >= startingBalance) {
            startingBalance -= amount;
            System.out.println("You have withdrawn $" + amount + " from credit account");
        } else {
            System.err.println("You have insufficient funds. Your transaction cannot be completed at the moment.");
        }
    }

    /**
     * @param amount
     * @param account
     * @return
     * Method used if user wants to pay someone a speicifc amount and chooses which account they
     * would like to enter amount into
     */
    @Override
    public void paySomeone(double amount, account account) {
    }

    public void transfer(double amount, account account){

    }
}