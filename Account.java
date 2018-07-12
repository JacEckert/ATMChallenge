import java.math.*;

public class Account {

	private String pin;
	private BigDecimal balance         = BigDecimal.valueOf(0);
	private BigDecimal withdrawalLimit = BigDecimal.valueOf(500);
	private BigDecimal withdrawalLeft  = BigDecimal.valueOf(500);
	
	public Account(String pin) {
		this.pin = pin;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public BigDecimal deposit(BigDecimal deposit) {
		balance = balance.add(deposit);
		return balance;
	}
	
	public boolean withdraw(BigDecimal withdraw) {
		boolean errorFunds = withdraw.compareTo(balance) > 0;
		boolean errorLimit = withdraw.compareTo(withdrawalLeft) > 0;
		
		if(errorFunds || errorLimit) {
			return false;
		} else {
			balance = balance.subtract(withdraw);
			withdrawalLeft = withdrawalLeft.subtract(withdraw);
			return true;
		}
		
	}

}
