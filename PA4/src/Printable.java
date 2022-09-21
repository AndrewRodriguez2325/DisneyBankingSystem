import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface Printable {
    void print(String printed) throws IOException;
    void printTransactionListToFileWriter(customer c,List<String> transactionList, FileWriter fw) throws IOException;
    void printCustomerList(List<customer> customerList) throws IOException;
    void printBankStatementToFile(customer c) throws IOException;
}
