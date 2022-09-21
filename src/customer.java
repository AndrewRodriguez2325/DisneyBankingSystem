import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Rodriguez
 * @version 1.0
 * @since February 9 2020
 * <p>
 * The customer class creates an instance of a customer from csv input and contains their name, date of birth, address,
 * identification number, and phone number.
 */

public class customer extends person {

    /**
     * Attributes that only person has. This is indentification number for security
     * pin for security as well
     * and all three accounts that a customer can have
     */
    private String identificationNumber;
    private account checkingaccount;
    private account savingsAccount;
    private account creditAccount;
    private List<String> transactionLog = new ArrayList<>(1000);

    /**
     * Constructors used for customer class. Tried to incorporate all cases including the default.
     */

    public customer() {
    }

    /**
     * @param firstName
     * @param lastName
     */
    public customer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    /**
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param address
     * @param phoneNumber
     * @param identificationNumber
     * @param pin
     */
    public customer(String firstName, String lastName, String dateOfBirth, String address, String phoneNumber, String identificationNumber, int pin) {
        super(firstName, lastName, dateOfBirth, address, phoneNumber);
        this.identificationNumber = identificationNumber;
    }

    /**
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param address
     * @param phoneNumber
     * @param identificationNumber
     * @param pin
     * @param checkingaccount
     * @param savingsAccount
     * @param creditAccount
     */
    public customer(String firstName, String lastName, String dateOfBirth, String address, String phoneNumber, String identificationNumber, int pin, account checkingaccount, account savingsAccount, account creditAccount) {
        super(firstName, lastName, dateOfBirth, address, phoneNumber);
        this.identificationNumber = identificationNumber;
        this.checkingaccount = checkingaccount;
        this.savingsAccount = savingsAccount;
        this.creditAccount = creditAccount;
    }

    /**
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param address
     * @param phoneNumber
     * @param identificationNumber
     * @param checkingaccount
     * @param savingsAccount
     * @param creditAccount
     */
    public customer(String firstName, String lastName, String dateOfBirth, String address, String phoneNumber, String identificationNumber, account checkingaccount, account savingsAccount, account creditAccount) {
        super(firstName, lastName, dateOfBirth, address, phoneNumber);
        this.identificationNumber = identificationNumber;
        this.checkingaccount = checkingaccount;
        this.savingsAccount = savingsAccount;
        this.creditAccount = creditAccount;
    }

    /**
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param address
     * @param phoneNumber
     * @param identificationNumber
     */
    public customer(String firstName, String lastName, String dateOfBirth, String address, String phoneNumber, String identificationNumber) {
        super(firstName, lastName, dateOfBirth, address, phoneNumber);
        this.identificationNumber = identificationNumber;

    }

    /**
     * Getters and setters used for account class.
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * @param identificationNumber
     */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     * @return
     */

    public List<String> getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(List<String> transactionLog) {
        this.transactionLog = transactionLog;
    }

    /**
     * @param
     */
    public customer(String firstName, String lastName, String dateOfBirth, String address, String phoneNumber, String email, String password, String identificationNumber, account checkingaccount, account savingsAccount, account creditAccount) {
        super(firstName, lastName, dateOfBirth, address, phoneNumber, email, password);
        this.identificationNumber = identificationNumber;
        this.checkingaccount = checkingaccount;
        this.savingsAccount = savingsAccount;
        this.creditAccount = creditAccount;
    }

    /**
     * @return
     */
    public account getCheckingaccount() {
        return checkingaccount;
    }

    /**
     * @param checkingaccount
     */
    public void setCheckingaccount(account checkingaccount) {
        this.checkingaccount = checkingaccount;
    }

    /**
     * @return
     */
    public account getSavingsAccount() {
        return savingsAccount;
    }

    /**
     * @param savingsAccount
     */
    public void setSavingsAccount(account savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    /**
     * @return
     */
    public account getCreditAccount() {
        return creditAccount;
    }

    /**
     * @param creditAccount
     */
    public void setCreditAccount(account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public void addTransaction(String transaction){
        transactionLog.add(transaction);
    }

}
