package View;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.AlunoDAO;
import DAO.Exceptioncustom;
import DAO.PessoaDAO;

public class main {

	public static void main(String[] args) throws SQLException, Exceptioncustom {
		// TODO Auto-generated method stub

		String nomePessoa;
		String PessoaCPF;
		String sexoPessoa;
		String tipoAluno;

		PessoaDAO p = new PessoaDAO();
		//Consulta de Pessoas
		//p.consultaNomesPessoas();
		
		//Cadastramento de Pessoa
	Scanner scan = new Scanner(System.in);

		System.out.print("Digite o nome da Pessoa:");
		nomePessoa = scan.next();

		System.out.print("Agora digite o CPF:");

		PessoaCPF = scan.next();

		System.out.print("Agora digite o Sexo :");
		sexoPessoa = scan.next();
		System.out.print("Agora digite o tipo do Aluno :");
		tipoAluno = scan.next();
		p.insertPessoa(nomePessoa, PessoaCPF, sexoPessoa, tipoAluno);
		
		
		//AlunoDAO aluno = new AlunoDAO();
		
		//aluno.consultaAluno();
		

	}

}
