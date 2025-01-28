package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", 0)) ;
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-100));
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
    }

}