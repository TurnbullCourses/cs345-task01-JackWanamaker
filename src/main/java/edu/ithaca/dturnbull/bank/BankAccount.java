package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    /**
     * @throws IllegalArgumentException if balance is <= 0
     */
    /**
     * @throws IllegalArgumentException if email is invalid
     */
    /**
     * @throws IllegalArgumentException if balance is <= 0
     */
    public BankAccount(String email, double startingBalance){
        if (!isEmailValid(email)){
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        else if (!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Invalid balance. Must be greater than 0 and have two or less decimals");
        }
        else {
            this.email = email;
            this.balance = startingBalance;
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if amount is negative or greater than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Not a valid withdrawal amount");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * @post increases th balance by amount if amount is non-negative
     * @throws IllegalArgumentException if amount is negative
     */
    public void deposit (double amount) {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Not a valid deposit amount");
        } else {
            balance += amount;
        }
    }

    /**
     * @post reduces the balance by amount and increases account's balance by amount if amount is non-negative and smaller than balance
     * @throws IllegalArgumentException if amount is negative or greater than balance
     */
    public void transfer (BankAccount account, double amount){
        if (amount <= balance && isAmountValid(amount)){
            balance -= amount;
            account.deposit(amount);
        }
        else {
            throw new IllegalArgumentException("Not good enough!");
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1 || email.indexOf('@') == 0 || email.indexOf('@') != email.lastIndexOf('@') 
        || email.indexOf('.') == -1 || email.indexOf('.') == 0 || email.indexOf('.') == email.length()-1 
        || email.indexOf('@') > email.indexOf('.') || email.indexOf('@') == email.indexOf('.')-1){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     *
     * @post returns true if the balance is positive, and has two decimal points or less
     * @throws IllegalArgumentException if negative or has more than two decimal points
     */
    public static boolean isAmountValid(double balance){
        if (balance <= 0){
            return false;
        }
        Double tempDouble = balance;
        String[] split = tempDouble.toString().split("\\.");
        if (split[1].length() > 2){
            return false;
        }
        return true;
    }
}