import java.io.IOException;

/**
 * @author Andrew Rodriguez
 * @version 1.0
 * @since February 9 2020
 * <p>
 * The checking class creates an instance of a checking account for a customer and contains their account number and
 * account balance.
 */
public class checking extends account {

    /**
     * Constructors used for checking class. Tried to incorporate all cases including the default.
     */
    public checking() {
    }

    public checking(int accountNumber, double accountBalance) {
        super(accountNumber, accountBalance);
    }

    /**
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




    /**
     * @param amount
     * @param payee
     * @return none
     * The method used if a user wants to pay another user a speicic amount and chooses which account
     */

    public void transfer(double amount, account payee) {
        if (amount > 0 && amount <= startingBalance) {
            startingBalance -= amount;
            payee.startingBalance += amount;
        } else {
            System.err.println("You have insufficient funds. Your transaction cannot be completed at the moment.");
        }
    }

    public void paySomeone(double amount, account payee) {
        if (amount > 0 && amount <= startingBalance) {
            startingBalance -= amount;
            payee.startingBalance += amount;
        } else {
            System.err.println("You have insufficient funds. Your transaction cannot be completed at the moment.");
        }
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
            System.out.println("You have deposited $" + amount + " into your checkings account");
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
            System.out.println("You have withdrawn $" + amount + " from checkings account");
        } else {
            System.err.println("You have insufficient funds. Your transaction cannot be completed at the moment.");
        }
    }


}
