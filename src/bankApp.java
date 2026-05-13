/*
    Program Name: bankApp
    Program Date: 5/5/26
    Developer Names: Alejandro Rodriguez, Natalia Jackson
    Program Version: 5.1
*/


import java.util.Scanner;
@SuppressWarnings("resource")

public class bankApp{
    
    //the class that Checking and Savings are going to be objects of
    //Investment uses a derived class based on this
    
    public static class Account
    {
        //bank name is initialized here because there is no reason to change it in main
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

        public String getbankName(){
            return bankName;
        }

        public void printall(){
            System.out.println(acctName + " balance: $" + acctBalance);
        
        
        }

    // deposits money into any of the objects that use Account as a basis
    // includes an error message if the user tries to deposit 0 or a negative number
    
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

  // withdraws money from any of the accounts that use Account as a basis
  // includes an error message if the withdrawl would put the account into the negatives
  
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

  // transfers money from one account to another
  // includes an error message if the transfer would be invalid
  
  public void transfer(Account target, double amount, boolean running)
  {
      if (amount > 0 && amount <= acctBalance && running)
      {
          this.acctBalance -= amount;
          target.acctBalance += amount;
          System.out.println("The transfer was successful.");
      } 
      else if (amount > 0 && amount <= acctBalance && !running)
      {
        this.acctBalance -= amount;
        target.acctBalance += amount;
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
    
   
   public static class investAccount extends Account
    {
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
                System.out.println("You bought " + stockAmt + " of " + stockName + " at $" + stockPrice + " per share.");
                System.out.printf("Remaining SCHWAB investment balance: $%.2f\n" , acctBalance);
            }
        }
                // add intrest rate here I think 
                //add 7.5 % to savings after each transaction
                
         public void addInterest()
         {        
                acctBalance = acctBalance * 1.075;
                }
    }

    // attempted to create a file however VS code could not find it
    
    public static void main(String[] args) throws Exception 
    {
        //could not get file to open properly
        /* File Passwords = new File("Passwords.txt");
        //File Usernames = new File("Usernames.txt");
        int idx = 0;
        String[] passArray = new String[3];
        //String[] nameArray = new String[3];

        try(Scanner passScan = new Scanner(Passwords)){
            while(passScan.hasNextLine()){
                passArray[idx] = passScan.nextLine();
                idx += 1;
                System.out.println("Yes this is working");
            }
        } catch (FileNotFoundException e) {
            System.out.println("PASSWORD FAILED.");
            //System.out.println(Passwords.());
            e.printStackTrace();
        }
            */


        Scanner scnr = new Scanner(System.in);
        Account Checking = new Account();
        Account Savings = new Account();
        investAccount Invest = new investAccount();

	// initializing values

        Checking.setacctName("Checking");
        Checking.setacctBalance(1500);
        
        Savings.setacctName("Savings");
        Savings.setacctBalance(500);

        Invest.setacctName("Investment");
        Invest.setacctBalance(0);

        boolean running = false;
        

        Savings.transfer(Invest, 250, running); // running

// true or false statement

        running = true;
        
        // login
        
System.out.print("Enter account name: ");
String userName = scnr.nextLine();
System.out.print("Password: ");
String password = scnr.nextLine();
System.out.println("Login successful");

// while loop for main menu

        while (running)
        {
            System.out.println("\n=== JAVA BANK MENU ===");
            System.out.println("1. View your Balances");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Pay");
            System.out.println("6. SCHWAB Investment");
            System.out.println("7. Exit");
            System.out.print("Choose your option: ");

        int choice = scnr.nextInt();

    //main menu this is where the  switch statesments are and what they do

        switch (choice) 
        {

            case 1 -> {
                System.out.println();
                System.out.println(Checking.getbankName() + " balances: ");
                Checking.printall();
                Savings.printall();
                Invest.printall();
                }

            case 2 -> {
                System.out.println();
                System.out.print("Deposit into (1) Checking (2) Savings: ");
                int d = scnr.nextInt();
                System.out.print("Amount: ");
                double damt = scnr.nextDouble();
                if (d == 1) Checking.deposit(damt); // damt = deopsit account
                else Savings.deposit(damt);
                }

            case 3 -> {
                Invest.addInterest();
                System.out.println();
                System.out.print("Withdraw from (1) Checking (2) Savings: ");
                int w = scnr.nextInt();
                System.out.print("Amount: ");
                double wamt = scnr.nextDouble();
                if (w == 1) Checking.withdraw(wamt); // wamt = withdraw amount
                else Savings.withdraw(wamt);
                }

            case 4 -> {
                Invest.addInterest();
                System.out.println();
                System.out.println("Transfer Options: ");
                System.out.println("1: Checking to Savings "); 
                System.out.println("2: Savings to Checking ");
                System.out.println("3: Savings to Investment ");
                System.out.println("4: Investment to Checking ");
                int t = scnr.nextInt();
                System.out.print("Amount: ");
                double tamt = scnr.nextDouble();
                switch (t) {
                    case 1 -> Checking.transfer(Savings, tamt, running);  // tamt = transfer amount
                    case 2 -> Savings.transfer(Checking, tamt, running);
                    case 3 -> Savings.transfer(Invest, tamt, running);
                    default -> Invest.transfer(Checking, tamt, running);
                }
                }


            case 5 -> {
                System.out.println();
                scnr.nextLine(); 
                System.out.print("Description(What is the money for?): ");
                String desc = scnr.nextLine();
                System.out.print("Amount: ");
                double pamt = scnr.nextDouble();
                Checking.pay(pamt, desc);
                }

            case 6 -> {
                System.out.println();
                scnr.nextLine(); 
                System.out.print("SCHWAB option");
                System.out.println("(1) Purchase shares");
                System.out.println("(2) View portfolio");
                int t = scnr.nextInt();
                if(t == 1){
                System.out.print("How many shares do you want to purchase?: ");
                double temp3 = scnr.nextDouble();
                Invest.setStockAmt(temp3);
                Invest.stockPurchase();
                }
                else
                {
                Invest.printall();
                }
                    // System.out.println()
                }

            case 7 -> {
                System.out.println();
                running = false;
                System.out.println("Goodbye!");
                System.out.println("Logout successful");
                }


            default -> System.out.println("Invalid choice please try again.");
        } 	
    }
        
    }
}