package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;

import static edu.ithaca.dturnbull.bank.BankAccount.isAmountValid;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001); //valid balance
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", 0)) ; //starting balance is 0
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -1)) ; //starting balance negative
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001); //valid withdrawal

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300)); //withdraws more than present
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100)); //withdraws negative amount

        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(100.123)); //withdraws more than 2 decimals
        bankAccount.withdraw(50.12);

        assertEquals(49.88, bankAccount.getBalance(), 0.001); //valid withdrawal
    }

    @Test
    void depositTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 100);
        bankAccount.deposit(100);
        assertEquals(200, bankAccount.getBalance(), 0.001); //valid deposit
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-100)); //deposit negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(100.123)); //deposit more than 2 decimals
        bankAccount.deposit(50.12);
        assertEquals(250.12, bankAccount.getBalance(), 0.001); //valid deposit
    }

    @Test
    void transferTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        BankAccount bankAccount2 = new BankAccount("a@b.com", 100);
        bankAccount.transfer(bankAccount2, 50);
        assertEquals(150, bankAccount.getBalance(), 0.001); //valid transfer
        assertEquals(150, bankAccount2.getBalance(), 0.001); //valid transfer
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(bankAccount2, -100)); //transfer negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(bankAccount2, 100.123)); //transfer more than 2 decimals
        bankAccount.transfer(bankAccount2, 50.12);
        assertEquals(200.12, bankAccount2.getBalance(), 0.001); //valid transfer
        assertEquals(99.88, bankAccount.getBalance(), 0.001); //valid transfer
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));// empty string
        assertFalse( BankAccount.isEmailValid("ab.com"));   // missing @
        assertFalse( BankAccount.isEmailValid("a@b"));      // missing .com
        assertFalse( BankAccount.isEmailValid(".a@b.com")); // starts with .
        assertFalse( BankAccount.isEmailValid("a@b.c")); // missing a character after the period
        assertFalse( BankAccount.isEmailValid("a..@b.com")); // double .
        assertFalse( BankAccount.isEmailValid("a\"@b.com")); // invalid character "

        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100)); //Invalid negative balance
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 0)); //Invalid 0 balance
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200.1);
        assertEquals(200.1, bankAccount2.getBalance(), 0.001); //Positive, one decimal
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200.12);
        assertEquals(200.12, bankAccount3.getBalance(), 0.001); //Positive, two decimal
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 200.123)); //Invalid 3 decimals
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 200.1234567)); //Invalid number of decimals
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -200.12)); //Negative but good decimal
    }

    @Test
    void isAmountValidTest() {
        assertTrue(isAmountValid(100)); //Positive balance
        assertTrue(isAmountValid(1)); //Positive balance edge case
        assertFalse(isAmountValid(0)); // Non positive
        assertFalse(isAmountValid(-1)); // Negative
        assertTrue(isAmountValid(100.1)); // One decimal
        assertTrue(isAmountValid(100.12)); // Two decimal
        assertFalse(isAmountValid(100.123)); //Three decimal, invalid
        assertFalse(isAmountValid(100.1234567)); //More than three decimals, invalid
        assertFalse(isAmountValid(-10.12)); //negative decimal
    }

}