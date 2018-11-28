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

    public void deposit(double amount)
    {
        try{
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS))
            {
                try
                {
                    System.out.println("new deposit amount $" + amount);
                    this.balance += amount;
                    System.out.println("new balance amount $" + this.balance);
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
    }

    public void withdraw(double amount)
    {
        try{
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS))
            {
                try
                {
                    System.out.println("new withdrawal amount $" + amount);
                    this.balance -= amount;
                    System.out.println("new balance amount $" + this.balance);
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
