import java.util.Scanner;
import java.math.*;

public class ATM {

	private static String head  = "\n\n   ____________________  ___   \n   ___    |/__  __/__   |/  /  \n   __  /| |__  /  __  /|_/ /   \n   _  ___ |_  /   _  /  / /    \n   /_/  |_/__/    /_/  /_/     \n_______________________________\n\n";
	private static String pin   = "Enter PIN to continue: ";
	private static String menu  = "1. Show Balance\n2. Make a Deposit\n3. Make a Withdrawal\n";
	private static String done  = "Type 'DONE' to exit\n";
	private static String error = "";
	private static String body  = "";
	
	private static Scanner scan = new Scanner(System.in);

	
	public static void main(String []args) {
		
		boolean active = true;
		String pinInput;
		do {
			System.out.print(head + error + pin);
			pinInput = scan.next();
			error = "\t\t\t Invalid input\n";
		} while(!pinInput.matches("[0-9]{4}"));
		error = "";		
		Account account = new Account(pinInput);
		
		body = menu + done;
		do {
			System.out.println(head + body);
			switch (scan.next().toUpperCase()) {
				case "1": case "BALANCE": case "SHOW BALANCE": showBalance(account);
					break;
				case "2": case "DEPOSIT": case "MAKE A DEPOSIT": makeDeposit(account);
					break;
				case "3": case "WITHDRAW": case "MAKE A WITHDRAWAL": makeWithdrawal(account);
					break;
				case "EXIT": case "QUIT": case "DONE": active = false;
					break;
			}
		} while(active);
	}
	
	//calls Account to retrieve account balance and displays the result
	private static void showBalance(Account account) {
		body = "Your current balance is: $" + account.getBalance() + " \n\n" + menu + done;
	}
	
	
	//reads input until a properly formatted, positive, number is given
		//then calls Account to validate and perform deposit
	private static void makeDeposit(Account account) {
		body = "Deposit amount: ";
		String str = getNumber();
		account.deposit(new BigDecimal(str));
		body = menu + done;
	}
	
	
	//reads input until a properly formatted, positive, number is given
		//then calls Account to validate and perform withdrawal
	private static void makeWithdrawal(Account account) {
		body = "Withdraw amount: ";
		String str = getNumber();
		while(!account.withdraw(new BigDecimal(str))) {
			error = "\t\t\t Value exceeds allowed withdrawal\n";
			System.out.print(head + error + body);
			str = scan.next();
		}
		error = "";
		body = menu + done;
	}
	
	
	//helper method to loop input request until value returned
		//is a properly formatted, positive, number
	private static String getNumber() {
		String str;
		do {
			System.out.print(head + error + body);
			error = "\t\t\t Invalid input\n";
			str = scan.next();
		} while(!str.matches("\\d*\\.?\\d*"));
		error = "";
		return str;
	}
}
