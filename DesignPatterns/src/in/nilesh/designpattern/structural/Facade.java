package in.nilesh.designpattern.structural;

class AccountNumberChecker {
	private int accountNumber = 123456;

	public int getAccountNumber() {
		return accountNumber;
	}

	public boolean checkAccountNumber(int accountNumberToCheck) {
		if (this.accountNumber != accountNumberToCheck) {
			System.out.println("Account Number " + accountNumberToCheck
					+ " is not valid");
			return false;
		}
		return true;
	}
}

class SocialSecurityNumberChecker {
	private int socialSecurityNumber = 814965;

	public int getAccountNumber() {
		return socialSecurityNumber;
	}

	public boolean checkSocialSecurityNumber(int socialSecurityNumberToCheck) {
		if (this.socialSecurityNumber != socialSecurityNumberToCheck) {
			System.out.println("Social Security Number "
					+ socialSecurityNumberToCheck + " is not valid");
			return false;
		}
		return true;
	}
}

class BankAccountFund {
	private double balance = 1000.00;

	public double getBalance() {
		return balance;
	}

	private void increaseBalanceBy(double cashToAdd) {
		balance += cashToAdd;
	}

	private void deduceBalanceBy(double cachToDeduce) {
		balance -= cachToDeduce;
	}

	public boolean checkAndWithDraw(double amountToWithdraw) {
		if (amountToWithdraw <= getBalance()) {
			deduceBalanceBy(amountToWithdraw);
			return true;
		}
		return false;
	}

	public void depositeMoney(double amountToDeposite) {
		increaseBalanceBy(amountToDeposite);
	}

	public void showCurrentBalance() {
		System.out.println("CURRENT BALANCE " + getBalance() + "\n");
	}
}

class BankAccountFacade {
	private int accountNumber;
	private int socialSecurityNumber;

	private AccountNumberChecker accountNumberChecker;
	private SocialSecurityNumberChecker socialSecurityNumberChecker;
	private BankAccountFund bankAccountFund;

	public BankAccountFacade(int accountNumber, int socialSecurityNumber) {
		this.accountNumber = accountNumber;
		this.socialSecurityNumber = socialSecurityNumber;

		accountNumberChecker = new AccountNumberChecker();
		socialSecurityNumberChecker = new SocialSecurityNumberChecker();
		bankAccountFund = new BankAccountFund();
	}

	public void withdrawCash(double amountToWithdraw) {
		if (accountNumberChecker.checkAccountNumber(accountNumber)
				&& socialSecurityNumberChecker.checkSocialSecurityNumber(socialSecurityNumber)) {
			
				boolean transactionStatus = bankAccountFund.checkAndWithDraw(amountToWithdraw);
				if (transactionStatus) {
					System.out.println("WITHDRAWAL TRANSACTION COMPLETE");
				} else {
					System.out.println("NO ENOUGH FUNDS");
				}

		} else {
			System.out.println("WITHDRAWAL TRANSACTION FAILED");
		}
		bankAccountFund.showCurrentBalance();
	}

	public void depositeCash(double amountToDeposite) {
		if (accountNumberChecker.checkAccountNumber(accountNumber)
				&& socialSecurityNumberChecker
						.checkSocialSecurityNumber(socialSecurityNumber)) {
			bankAccountFund.depositeMoney(amountToDeposite);
			System.out.println("DEPOSITE TRANSACTION COMPLETED");
		} else {
			System.out.println("WITHDRAWAL TRANSACTION FAILED");
		}
		bankAccountFund.showCurrentBalance();
	}

}

public class Facade {

	public static void main(String[] args) {
		BankAccountFacade bankAccount = new BankAccountFacade(123456, 814965);
		bankAccount.withdrawCash(800);
		bankAccount.withdrawCash(300);
		bankAccount.depositeCash(500);
		bankAccount.withdrawCash(300);
		
		BankAccountFacade badBankAccount = new BankAccountFacade(1234, 814965);
		badBankAccount.withdrawCash(800);
	}
}
