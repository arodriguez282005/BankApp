/*
    Program Name: bankApp
    Program Date: 5/5/26
    Developer Names: Alejandro Rodriguez, Natalia Jackson
    Program Version: 6.0
*/

import java.io.File;
import java.util.*;

@SuppressWarnings("resource")

public class bankApp{
    
    // colors added if it works green, if it fails red
    
    public static final String RESET = "\u001B[0m"; // makes sure the color changes when needed
    public static final String GREEN = "\u001B[32m"; // green
    public static final String RED = "\u001B[31m"; // red
    // public static final String                  // if we wanty other color
    
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
            System.out.println(RED + "Invaild deposit." + RESET);
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
          System.out.println(RED +"Invalid withdraw." + RESET);
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
          System.out.println(GREEN + "The transfer was successful." + RESET);
      } 
      else if (amount > 0 && amount <= acctBalance && !running)
      {
        this.acctBalance -= amount;
        target.acctBalance += amount;
      }
      else 
      {
          System.out.println(RED + "The transfer has failed please try again." + RESET);
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
          System.out.println(RED + "Payment failed." + RESET);
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
                System.out.println(RED+ "Not enough money to buy " + stockAmt + " shares." + RESET);
            }
            else
            {
                acctBalance -= (stockPrice * stockAmt);
                System.out.println("You bought " + stockAmt + " of " + stockName + " at $" + stockPrice + " per share.");
                System.out.printf("Remaining " + brokerName + " investment balance: $%.2f\n" , acctBalance);
            }
        }
                // add intrest rate here I think 
                //add 7.5 % to savings after each transaction
                
         public void addInterest()
         {        
                acctBalance = acctBalance * 1.075;
                }

        public void printStock(){
            System.out.println("Shares of " + stockName + ": " + stockAmt);
        }
    }

    // attempted to create a file however VS code could not find it
    
    public static void main(String[] args) throws Exception 
    {

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
        

        Savings.transfer(Invest, 250, running);

        
        
        // login
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        try{
            Scanner inputUser = new Scanner(new File ("Usernames.txt"));

            while(inputUser.hasNext()){
                usernames.add(inputUser.nextLine());
            }
            inputUser.close();

        }catch(Exception e){
            
            e.printStackTrace();
            System.out.println("Error reading file Usernames.txt");
            return;

        }

         try{
            Scanner inputPass = new Scanner(new File ("Passwords.txt"));

            while(inputPass.hasNext()){
                passwords.add(inputPass.nextLine());
            }
            inputPass.close();

        }catch(Exception e){
            
            e.printStackTrace();
            System.out.println("Error reading file Passwords.txt");
            return;

        }

        int attempts = 3;
        Scanner scnr = new Scanner(System.in);
        while(attempts > 0){
            System.out.print("Enter account name: ");
            String userName = scnr.nextLine();
            
            int idx = usernames.indexOf(userName);
            if (idx == -1) {
                System.out.println(RED + "Username does not exist" + RESET);
                continue;
            }

            System.out.print("Password: ");
            String password = scnr.nextLine();
            if (passwords.get(idx).equals(password)){
                System.out.println(GREEN + "Login successful" + RESET);
                running = true;
                break;
            }
            else {
                attempts -= 1;
                System.out.println(RED + "Incorrect password, " + attempts + " remaining before lockout." + RESET);
            }
        }

        if(!running){
            System.out.println(RED + "Account is locked." + RESET);
        }

// while loop for main menu
        //Scanner scnr = new Scanner(System.in);
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
                System.out.println(Invest.brokerName + " option:");
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
                    Invest.printStock();
                }
                    // System.out.println()
                }

            case 7 -> {
                System.out.println();
                running = false;
                System.out.println(GREEN + "Goodbye!"+ RESET);
                System.out.println(GREEN + "Logout successful" + RESET);
                }


            default -> System.out.println(RED + "Invalid choice please try again." + RESET);
        } 	
    }

    }
}