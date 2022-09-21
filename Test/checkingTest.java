import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class checkingTest {
    checking checkAccount = new checking(1234,960.53);

    @Test
    public void testGetBalanceMethod_checkEquals() throws IOException {
        double balance = 960.53;
        assertEquals(balance,checkAccount.getBalance(),0.001);
    }
    @Test
    public void testGetBalanceMethod_checkNotEquals() throws IOException {
        double balance1 = 960.00;
        assertNotEquals(balance1,checkAccount.getBalance(),0.001);
    }

    @Test
    public void testWithdraw_checkEquals() throws IOException {
        double balance2 = 360.53;
        checkAccount.withdrawMoney(600);
        assertEquals(balance2,checkAccount.getBalance(),0.001);
    }

    @Test
    public void testWithdraw_checkNotEquals() throws IOException {
        double balance2 = 435.22;
        checkAccount.withdrawMoney(600);
        assertEquals(balance2,checkAccount.getBalance(),0.001);

    }
}