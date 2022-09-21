import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BankStatement {
    customer customer1;


    public BankStatement(){}

    public BankStatement(customer customer1) {
        this.customer1 = customer1;
    }

    public void writeBankStatement(customer c) throws IOException {
        System.out.println("Bank Statement for:" + c.getFirstName() + " " + c.getLastName());
        System.out.println("Customer Name:" + c.getFirstName() + " " + c.getLastName());
        System.out.println("Customer Date of Birth:" + c.getDateOfBirth());
        System.out.println("Customer ID Number" + c.getIdentificationNumber());
        System.out.println("Customer Address:" + c.getAddress());
        System.out.println("Customer Phone Number:" + c.getPhoneNumber());
        System.out.println("Customer starting balance for checking:" + c.getCheckingaccount().startingBalance);
        System.out.println("Customer starting balance for savings:" + c.getSavingsAccount().startingBalance);
        System.out.println("Customer starting balance for credit:" + c.getCreditAccount().startingBalance);
        System.out.println("Customer end balance for checking:" + c.getCheckingaccount().getBalance());
        System.out.println("Customer end balance for savings:" + c.getSavingsAccount().getBalance());
        System.out.println("Customer end balance for credit:" + c.getCreditAccount().getBalance());
        // This is space saved for when I figure out how to obtain transaction for a single person.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));





    }
}
