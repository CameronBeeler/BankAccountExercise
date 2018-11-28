package com.cameronbeeler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public
class BankAccount
{
    private       double balance;
    private       String accountNumber;
    private final Lock   lock;

    public BankAccount(String accountNumber, double initialBalance)
    {
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
        lock = new ReentrantLock();
    }

    public boolean deposit(double amount)
    {
        boolean status = false;
        try{
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS))
            {
                try
                {
                    System.out.println("new deposit amount $" + amount);
                    this.balance += amount;
                    System.out.println("new balance amount $" + this.balance);
                    status = true;
                }
                finally
                {
                    lock.unlock();
                }
            }
            else
            {
                System.out.println("Could not acquire lock");
            }
        }
        catch(InterruptedException e)
        {

        }
        System.out.println("Transaction status = " + status);
        return status;
    }

    public boolean withdraw(double amount)
    {
        boolean status = false;
        try{
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS))
            {
                try
                {
                    System.out.println("new withdrawal amount $" + amount);
                    this.balance -= amount;
                    System.out.println("new balance amount $" + this.balance);
                    status = true;
                }
                finally
                {
                    lock.unlock();
                }
            }
            else
            {
                System.out.println("Could not get the lock");
            }

        }
        catch(InterruptedException e)
        {

        }
        System.out.println("Transaction status = " + status);
        return status;
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


    public boolean transfer(BankAccount destinationAccount, double amount) {
        if (withdraw(amount))
        {
            if (destinationAccount.deposit(amount))
            {
                return true;
            }
            else
            {
                // The deposit failed. Refund the money back into the account.
                System.out.printf("%s: Destination account busy. Refunding money\n",
                                  Thread.currentThread().getName());
                deposit(amount);
            }
        }

        return false;
    }
}
