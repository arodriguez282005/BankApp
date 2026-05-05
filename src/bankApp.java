/*
    Program Name: bankApp
    Program Date: 5/5/26
    Developer Names: Alejandro Rodriguez, 
    Program Version: 1.0

*/


import java.util.*;
@SuppressWarnings("resource")

public class bankApp{
    
    //the class that Checking and Savings are going to be objects of
    //Investment will use a derived class based on this
    //currently missing a function to transfer/pay money
    public static class Account{
        //called placeholder because I dont know what to call it yet
        //gonna leave it intitialized in the class because I see no reason to change it in main
        String bankName = "placeholder";
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

    }

    //derived class since the investment account is going to do different things
    public static class investAccount extends Account{
        String brokerName = "SCHWAB";
        String stockName;
        double stockPrice;
        
    }

    //main where the switch statesments should be
    //currently missing switch statements because I have 0 clue what he wants
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        Account Checking = new Account();
        String tempName;
        double tempBal;


        // put this to test the Account class
        /*System.out.println("Enter the account name:");
        tempName = scnr.nextLine();
        Checking.setacctName(tempName);
        System.out.println("Enter the account balance:");
        tempBal = scnr.nextDouble();
        Checking.setacctBalance(tempBal);
        Checking.printall();*/
        

        
    }
}
