package OOD;

import java.util.*;


public class ATM{
	
	private Map<String, Account> database;
	private DebitCard curCard;
	private Account curAccount;
	public ATM(Map<String, Account> database){
		this.database = database;
	}
	
	public boolean insertDebitCard(DebitCard card, String pwd) {
		if(card == null) return false;
		if(curCard != null) {
			System.out.println("Someone is using ATM!");
			return false;
		}
		if(!card.getPassword().equals(pwd)) {
			System.out.println("Wrong password!");
			return false;
		}
		curCard = card;
		curAccount = database.get(curCard.getAccountNumber());
		if(curAccount == null) {
			System.out.println("No such account!");
			curCard = null;
			return false;
		}
		return true;
	}
	
	public boolean deposit(double amount) {
		if(curAccount == null) {
			System.out.println("Please inseart card first!");
			return false;
		}
		return curAccount.deposit(amount);
	}
	
	public boolean withdrawal(double amount) {
		if(curAccount == null) {
			System.out.println("Please inseart card first!");
			return false;
		}
		return curAccount.withdrawal(amount);
	}
	
	public double checkBalance() {
		return curAccount.getBalance();
	}
	
	public boolean logout() {
		if(curAccount == null) {
			System.out.println("You already logout!");
			return false;
		}
		curCard = null;
		curAccount = null;
		return true;
	}
	
	// Test
	public static void main(String[] args) {
    	Account ac1 = new Account("John JJ", "0800-123-321", "0381-3241-3456-4188", "pwd1");
    	DebitCard card1 = ac1.getDebitCard();
    	Account ac2 = new Account("Ker Main", "0253-335-321", "4354-5245-2123-6486", "pwd2");
    	DebitCard card2 = ac2.getDebitCard();
    	Account fakeAc = new Account("Faker", "0q3r21", "435qrcqre6486", "ff");
    	DebitCard fakeCard = fakeAc.getDebitCard();
    	Map<String, Account> database = new HashMap<>();
    	database.put(ac1.getAccountNumber(), ac1);
    	database.put(ac2.getAccountNumber(), ac2);
    	
    	ATM atm = new ATM(database);
    	System.out.println("atm.logout(): " + atm.logout()); // F
    	System.out.println("atm.withdraw(10.4): " + atm.withdrawal(10.4)); // F
    	System.out.println("atm.insertDebitCard(fakeCard, \"ff\"): " + atm.insertDebitCard(fakeCard, "ff")); // F
    	System.out.println("atm.insertDebitCard(card1, \"pwd2\"): " + atm.insertDebitCard(card1, "pwd2")); // wrong pwd, F
    	System.out.println("atm.insertDebitCard(card1, \"pwd1\"): " + atm.insertDebitCard(card1, "pwd1")); // T
    	System.out.println("atm.insertDebitCard(card2, \"pwd2\"): " + atm.insertDebitCard(card2, "pwd2")); // someone is using, F

    	System.out.println("atm.checkBalance(): "+ atm.checkBalance()); // 0
    	System.out.println("atm.withdrawal(10.4): " + atm.withdrawal(10.4)); // not enough money, F
    	System.out.println("atm.deposit(100.4): " + atm.deposit(100.4)); // T
    	
    	System.out.println("atm.withdrawal(10.4): " + atm.withdrawal(1000.4)); // not enough money, F
    	System.out.println("atm.withdrawal(50): " + atm.withdrawal(50.4)); // T
    	System.out.println("atm.checkBalance(): "+ atm.checkBalance()); // 50
    	
    	System.out.println("atm.logout(): " + atm.logout()); // T
    	System.out.println("atm.logout(): " + atm.logout()); // F
    	
    }
}


class DebitCard{
    private String cardNumber;
    private String password;
    private String accountNumber;
    public DebitCard(String cardNumber, String password, String accountNumber){
        this.cardNumber = cardNumber;
        this.password = password;
        this.accountNumber = accountNumber;
    }
    String getCardNumber() {
    	return cardNumber;
    }
    String getAccountNumber(){
        return accountNumber;
    }
    String getPassword(){
        return password;
    }
}

class Account{
    private String accountHolder;
    private String accountNumber;
    private DebitCard debitCard;
    private double balance;
    public Account(String accountHolder, String accountNumber, String cardNumber, String password){
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        debitCard = new DebitCard(cardNumber, password, accountNumber);
    }
    
    DebitCard getDebitCard() {
    	return debitCard;
    }
    
    String getAccountNumber() {
    	return accountNumber;
    }
    
    double getBalance(){
        return balance;
    }

    boolean deposit(double amount){
    	if(amount < 0) {
    		System.out.println("Amount should be a positive number!");
    		return false;
    	}
        balance += amount;
        return true;
    }

    boolean withdrawal(double amount){
    	if(amount < 0) {
    		System.out.println("Amount should be a positive number!");
    		return false;
    	}
    	if(amount > balance) {
    		System.out.println("Not enough money!");
    		return false;
    	}else {
    		balance += amount;
            return true;
    	}
    }
    
}


