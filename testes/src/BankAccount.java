import java.time.LocalDate;
import java.util.List;

public class BankAccount {
	int numberAccount;
	int numberAgency;
	double sale;
 	String username;
	LocalDate LocalDateOfBirth;
	boolean active = true;

	public BankAccount(int numberAccount, int numberAgency, double sale,  String username, LocalDate LocalDateOfBirth){
		this.numberAccount = numberAccount;
		this.numberAgency = numberAgency;
		this.sale = sale;
		this.username = username;
		this.LocalDateOfBirth = LocalDateOfBirth;
	}

	protected void withdraw(double value){
		if(this.sale >= value){
			this.sale = this.sale - value;
		}
	}

	protected void cancellation(String justification){
		System.out.println("Parabéns pelo cancelamento, efetuado com sucesso");
		System.out.println("Seu motivo foi:"+justification);
	}

	protected double consult(){
		System.out.println("Seu saldo atual eh:"+this.sale);
		return  this.sale;
	}

	protected List<BankAccount> consultationBetweenTwoLocalDates(LocalDate starAt, LocalDate endAt){
		return  ;
	}

	protected void makeTransfer(
			BankAccount accountToTransfer,
			double transferValue
	){
		accountToTransfer.sale += transferValue;
	}
}
