import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class BankStatement implements Printable {
    customer customer1;
    double checkStart;
    double savingsStart;
    double creditStart;

    public customer getCustomer1() {
        return customer1;
    }

    public void setCustomer1(customer customer1) {
        this.customer1 = customer1;
    }

    public double getCheckStart() {
        return checkStart;
    }

    public void setCheckStart(double checkStart) {
        this.checkStart = checkStart;
    }

    public double getSavingsStart() {
        return savingsStart;
    }

    public void setSavingsStart(double savingsStart) {
        this.savingsStart = savingsStart;
    }

    public double getCreditStart() {
        return creditStart;
    }

    public void setCreditStart(double creditStart) {
        this.creditStart = creditStart;
    }

    public BankStatement(){}

    public BankStatement(customer customer1) {
        this.customer1 = customer1;
    }




    /**
     * @param printed
     */
    public void print(String printed) {

    }


    /**
     * @param transactionList
     */
    public void printTransactionListToFileWriter(customer c,List<String> transactionList, FileWriter fw) throws IOException {
        for (int i = 0; i < c.getTransactionLog().size();i++) {
            fw.write(c.getTransactionLog().get(i));
        }


    }

    /**
     * @param customerList
     */
    public void printCustomerList(List<customer> customerList) {

    }

    /**
     * @param c
     */
    public void printBankStatementToFile(customer c) throws IOException {
        FileWriter fw = new FileWriter("Bank Statement for "+ c.getFirstName() + " " + c.getLastName()+".txt");
        fw.write("Bank Statement\n");
        fw.write("Customer Name: " + c.getFirstName() + " " + c.getLastName()+"\n");
        fw.write("Customer Date of Birth: " + c.getDateOfBirth()+"\n");
        fw.write("Customer ID Number: " + c.getIdentificationNumber()+"\n");
        fw.write("Customer Address: " + c.getAddress()+"\n");
        fw.write("Customer Phone Number: " + c.getPhoneNumber()+"\n");
        fw.write("Customer starting balance for checking: " + c.getCheckingaccount().getRealStart()+"\n");
        fw.write("Customer starting balance for savings: " + c.getSavingsAccount().getRealStart()+"\n");
        fw.write("Customer starting balance for credit: " + c.getCreditAccount().getRealStart()+"\n");
        fw.write("Customer end balance for checking: " + c.getCheckingaccount().startingBalance+"\n");
        fw.write("Customer end balance for savings: " + c.getSavingsAccount().startingBalance+"\n");
        fw.write("Customer end balance for credit: " + c.getCreditAccount().startingBalance+"\n");
        fw.write("List of transactions\n");
        printTransactionListToFileWriter(c,c.getTransactionLog(),fw);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        fw.write("Date and time of generated statement: ");
        fw.write(dtf.format(now));
        fw.flush();
        fw.close();
    }
}
