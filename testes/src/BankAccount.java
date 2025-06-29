import java.util.Date;
import java.util.String;

public class BankAccount {
	int numberAccount;
	int numberAgency;
	double sale;
 	String username;
	Date dateOfBirth;

	public BankAccount(int numberAccount, int numberAgency, double sale,  String username, Date dateOfBirth){
		this.numberAccount = numberAccount;
		this.numberAgency = numberAgency;
		this.sale = sale;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
	}

	protected double withdraw(double value){
		if(this.sale < value){
			return 0.0;
		}

		this.sale = this.sale - value;

		return value;
	}

	protected void cancellation(String justification){
		System.out.println("Parabéns pelo cancelamento, efetuado com sucesso");
		Sytem.out.println("Seu motivo foi:"+justification);
	}

	protected void consult(){
	        Sytem.out.println("Seu saldo atual eh:"+this.sale);
	}

	protected void 	consultationBetweenTwoDates(Date starAt, Date endAt){
		// logica
	}
}
