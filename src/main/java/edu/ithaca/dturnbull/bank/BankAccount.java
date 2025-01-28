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
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            if (startingBalance >= 0){
                this.email = email;
                this.balance = startingBalance;
            }
            else {
                throw new IllegalArgumentException("Starting balance must be greater than 0");
            }
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
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
        if (amount < 0){
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
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
}