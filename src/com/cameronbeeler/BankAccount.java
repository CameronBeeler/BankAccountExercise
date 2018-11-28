package com.cameronbeeler;

public
class BankAccount
{
    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance)
    {
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
    }

    public synchronized void deposit(double amount)
    {
            System.out.println("new deposit amount $" + amount);
            this.balance += amount;
            System.out.println("new balance amount $" + this.balance);
    }

    public synchronized void withdraw(double amount)
    {
            System.out.println("new withdrawal amount $" + amount);
            this.balance -= amount;
            System.out.println("new balance amount $" + this.balance);
    }

    public
    double getBalance()
    {
        return this.balance;
    }

    public
    String getAccountNumber()
    {
        return this.accountNumber;
    }
}
