package com.cameronbeeler;

public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount("12345-6789", 1000);
        System.out.println("Account: " + account.getAccountNumber() + "\nBalance: $" + account.getBalance());

        Thread tr1 = new Thread()
        {
            @Override
            public void run()
            {
                account.deposit(300);
                account.withdraw(50);
            }
        };

        Thread tr2 = new Thread()
        {
            @Override
            public void run()
            {
                account.deposit(203.75);
                account.withdraw(100);
            }
        };
        tr1.start();
        tr2.start();
        System.out.println("Account: " + account.getAccountNumber() + "\nBalance: $" + account.getBalance());
    }
}
