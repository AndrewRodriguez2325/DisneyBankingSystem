import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class savingsTest {
    checking checking = new checking(122, 400);
    savings saving = new savings(123, 50.00);

    @Test
    public void testDepositMoneyMethod_isTrue() throws IOException {
        double deposit = 50.00;
        double newBalance = 100.00;
        saving.depositMoney(50);
        assertTrue(saving.getBalance() == newBalance);
    }

    @Test
    public void testDepositMoneyMethod_isFalse() throws IOException {
        double deposit = 50.00;
        double newBalance = 100.00;
        saving.depositMoney(50);
        assertFalse(saving.getBalance() == newBalance);
    }

    @Test
    public void testTransfer_isEqual() throws IOException {
        double balance = 25;
        saving.transfer(25,checking);
        assertEquals(balance,saving.getBalance(),0.001);
    }

    @Test
    public void testWithdraw_checkisEqualTheOtherWay() throws IOException {
        double balance1 = 100 ;
        checking.transfer(50,saving);
        assertEquals(balance1,saving.getBalance(),0.001);

    }


}