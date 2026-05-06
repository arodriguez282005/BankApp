/*
    Program Name: bankApp
    Program Date: 5/5/26
    Developer Names: Alejandro Rodriguez, Natalia Jackson
    Program Version: 4.0

*/


import java.util.*;
@SuppressWarnings("resource")

public class bankApp{
    
    //the class that Checking and Savings are going to be objects of
    //Investment will use a derived class based on this
    //currently missing a function to transfer/pay money this is done now not sure if it works though


    public static class Account{
        //called placeholder because I dont know what to call it yet
        //gonna leave it intitialized in the class because I see no reason to change it in main
        String bankName = "Java Bank";
        String acctName;
        double acctBalance;

        public void setacctName(String input){
            acctName = input;
        }

        public String getacctName(){
            return acctName;
        }

        public void setacctBalance(double input){
            acctBalance = input;
        }

        public double getacctBalance(){
            return acctBalance;
        }

        public void printall(){
            System.out.println(acctName + " balance: $" + acctBalance);
        }

        // deposite money

    public void deposit(double amount)
    {
        if(amount >0)
        {
            acctBalance += amount;
        }
        else
        {
            System.out.println("Invaild deposit.");
        }
    }

// withdraw money

public void withdraw(double amount)
{
    if (amount > 0 && amount <= acctBalance)
    {
        acctBalance -= amount;
    } 
    else 
    {
        System.out.println("Invalid withdraw.");
    }
}

// transfer money to another account

public void transfer(Account target, double amount)
{
    if (amount > 0 && amount <= acctBalance)
    {
        this.acctBalance -= amount;
        target.acctBalance += amount;
        System.out.println("the Transfer was successful.");
    } 
    else 
    {
        System.out.println("The transfer has failed please try again.");
    }
}

// pay 

public void pay(double amount, String description)
{
    if (amount > 0 && amount <= acctBalance)
    {
        withdraw(amount); 
        System.out.println("Paid $" + amount + " for " + description);
    }
    else 
    {
        System.out.println("Payment failed.");
    }
}
    }

    //derived class since the investment account is going to do different things
    public static class investAccount extends Account{
        String brokerName = "SCHWAB";
        String stockName = "Best Buy";
        double stockPrice = 58.56;
        double stockAmt;
        double temp2;

        public void setStockAmt(double input)
        {
            stockAmt = input;
        }

        public void stockPurchase()
        {
            temp2 = Math.floor(acctBalance / (stockPrice * stockAmt));
            if (temp2 < 1) 
            {
                System.out.println("Not enough money to buy " + stockAmt + " shares.");
            }
            else
            {
                acctBalance -= (stockPrice * stockAmt);
                System.out.println("You bought " + stockAmt + " of " + stockName);
                System.out.printf("Remaining balance: $%.2f" , acctBalance);
            }
        }
        
    }

    //main where the switch statesments should be
    //currently missing switch statements because I have 0 clue what he wants
    // not sre what he means by switch statements either 
    public static void main(String[] args) throws Exception 
    {
        Scanner scnr = new Scanner(System.in);
        Account Checking = new Account();
        Account Savings = new Account();
        investAccount Invest = new investAccount();
        String tempName;
        double tempBal;
        // this might be what he wants but not sure if i did it right


        Checking.setacctName("Checking");
        Checking.setacctBalance(1500);
        
        Savings.setacctName("Savings");
        Savings.setacctBalance(500);

        Invest.setacctName("Investment");
        Invest.setacctBalance(0);
        

        Savings.transfer(Invest, 250);


        boolean running = true;

        while (running)
        {
            System.out.println("\n=== JAVA BANK MENU ===");
            System.out.println("1. View your Balances");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Pay");
            System.out.println("6. Investment");
            System.out.println("7. Exit");
            System.out.print("Choose your option: ");

        int choice = scnr.nextInt();

        switch (choice) 
        {

            case 1:
                Checking.printall();
                Savings.printall();
                Invest.printall();
                break;

            case 2:
                System.out.print("Deposit into (1) Checking (2) Savings: ");
                int d = scnr.nextInt();
                System.out.print("Amount: ");
                double damt = scnr.nextDouble();
                if (d == 1) Checking.deposit(damt); // damt = deopsit account
                else Savings.deposit(damt);
                break;

            case 3:
                System.out.print("Withdraw from (1) Checking (2) Savings: ");
                int w = scnr.nextInt();
                System.out.print("Amount: ");
                double wamt = scnr.nextDouble();
                if (w == 1) Checking.withdraw(wamt); // wamt = withdrawaccount
                else Savings.withdraw(wamt);
                break;

            case 4:
                System.out.print("Transfer (1): Checking to Savings , (2): Savings to Checking , (3): Savings to Investment, (4): Investment to Checking");
                int t = scnr.nextInt();
                System.out.print("Amount: ");
                double tamt = scnr.nextDouble();
                if (t == 1) {
                    Checking.transfer(Savings, tamt);  // tamt = transfer account
                }
                else if (t == 2) {
                    Savings.transfer(Checking, tamt);
                }
                else if (t == 3){
                    Savings.transfer(Invest, tamt);
                }
                else{
                    Invest.transfer(Checking, tamt);
                }
                break;

            case 5:
                scnr.nextLine(); 
                System.out.print("Description: ");
                String desc = scnr.nextLine();
                System.out.print("Amount: ");
                double pamt = scnr.nextDouble();
                Checking.pay(pamt, desc);
                break;

            case 6:
                 scnr.nextLine(); 
                 System.out.print("How many shares do you want to purchase: ");
                 double temp3 = scnr.nextDouble();
                 Invest.setStockAmt(temp3);
                 Invest.stockPurchase();
                 // System.out.println()
                break;

            case 7:
                running = false;
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Invalid choice please try again.");
        } 	
    }


        // put this to test the Account class
        /*System.out.println("PLease enter the account name:");
        tempName = scnr.nextLine();
        Checking.setacctName(tempName);
        System.out.println("Enter the account balance:");
        tempBal = scnr.nextDouble();
        Checking.setacctBalance(tempBal);
        Checking.printall();*/
        

        
    }
}