package Model;

public class Aluno  extends Pessoa{
	
	private int IDaluno;
	private double AlunoVLMensalidade;
	 AlunoTipoBolsa  tipoBolsa;
	
	
	
	
	public int getIDaluno() {
		return IDaluno;
	}




	public void setIDaluno(int iDaluno) {
		IDaluno = iDaluno;
	}




	public double getAlunoVLMensalidade() {
		return AlunoVLMensalidade;
	}




	public void setAlunoVLMensalidade(double alunoVLMensalidade) {
		AlunoVLMensalidade = alunoVLMensalidade;
	}





	public static void main(String[] args) {
		System.out.println(AlunoTipoBolsa.B10);
	}
	
	
	
	

}
