package com.cameronbeeler;

public class Main {

    public static void main(String[] args) {

        BankAccount account1 = new BankAccount("12345-6789", 500);
        System.out.println("Account: " + account1.getAccountNumber() + "\nBalance: $" + account1.getBalance());
        BankAccount account2 = new BankAccount("98765-432", 1000.00);
        System.out.println("Account: " + account2.getAccountNumber() + "\nBalance: $" + account2.getBalance());

        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();

//        Thread tr1 = new Thread(new Runnable()
//        {
//            @Override
//            public
//            void run()
//            {
//                account.deposit(300);
//                account.withdraw(50);
//            }
//        });
//
//        Thread tr2 = new Thread(new Runnable()
//        {
//            @Override
//            public
//            void run()
//            {
//                account.deposit(203.75);
//                account.withdraw(100);
//            }
//        });
//
//        tr1.start();
//        tr2.start();
//        System.out.println("Account: " + account.getAccountNumber() + "\nBalance: $" + account.getBalance());
    }
}

