package com.cameronbeeler;

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
        lock.lock();
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

    public synchronized void withdraw(double amount)
    {
        lock.lock();
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
