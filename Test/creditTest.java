import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class creditTest {
    credit creditAccount = new credit(123,-42.3,0);

    @Test
    public void TestWithdrawMoneyMethod_checkNull() {
        assertNull(creditAccount);
    }

    @Test
    public void TestWithdrawMoneyMethod_checkNotNull() {
        assertNotNull(creditAccount);
    }

    @Test
    public void testDeposit_checkEquals() throws IOException {
        double balance = 22.3;
        creditAccount.depositMoney(20);
        assertEquals(balance,creditAccount.getBalance(),0.001);
    }

    @Test
    public void testWithdraw_checkNotEquals() throws IOException {
        double balance1 = -42.3;
        creditAccount.depositMoney(20);
        assertEquals(balance1,creditAccount.getBalance(),0.001);

    }

}