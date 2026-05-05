import java.util.*;
@SuppressWarnings("resource")

public class App {
    public static class Account{
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

    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        Account Checking = new Account();
        String tempName;
        double tempBal;
        System.out.println("Enter the account name:");
        tempName = scnr.nextLine();
        Checking.setacctName(tempName);
        System.out.println("Enter the account balance:");
        tempBal = scnr.nextDouble();
        Checking.setacctBalance(tempBal);

        Checking.printall();
        
        
    }
}
