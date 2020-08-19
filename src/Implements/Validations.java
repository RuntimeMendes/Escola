package Implements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.Exceptioncustom;
import accenture.escola.jdbc.ConnectionFactory;

public class Validations implements IDisciplina {
	

	
	public static int obterUltimoIdAluno() throws SQLException {

		int ultimoId = 0;
		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT MAX(idDISCIPLINA) FROM disciplina");

			ResultSet rs = st.getResultSet();
			// System.out.println("test " + rs);
			while (rs.next()) {

				ultimoId = rs.getInt(1);
		
				// System.out.println( rs.getString(2) + " - ");

			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return ultimoId;
	}
	
	
	public static int validarNome(String nomeParameter) throws Exceptioncustom {

		Boolean validarNome = false;
		String nomePessoa;
		int tamanho = 0;

		nomePessoa = nomeParameter;
		tamanho = nomePessoa.length();

		//System.out.println("O tamanho do nome é de: " + tamanho);

		if (tamanho > 45) {

			throw new Exceptioncustom("O campo nome  deve ser até 45 caracteres");
		}
		if (nomePessoa.equals("")) {
			throw new Exceptioncustom("O campo nome não pode ficar vazio");
		} else {
			validarNome = true;
		}

		return tamanho;

	}
	
	public static String validarTurmadescricao(String nomeParameter) throws Exceptioncustom {

		Boolean validarNome = false;
		String turmaDescricao;
		int tamanho = 0;

		turmaDescricao = nomeParameter;
		tamanho = turmaDescricao.length();

		//System.out.println("O tamanho do nome é de: " + tamanho);

		if (tamanho > 45) { 

			throw new Exceptioncustom("O campo nome  deve ser até 45 caracteres");
		} if(turmaDescricao .equals("")) {
			turmaDescricao = "Não identificado";
		}
		
		return turmaDescricao ;

	}
	
	public static void validarDisciplinaAluno(int idDisciplina,int idPessoa,int idAluno) throws Exceptioncustom {

	
	
		Integer disciplinaId = idDisciplina;
		Integer pessoaId = idPessoa;
		Integer  alunoId = idAluno;
	
	
        if(disciplinaId == null || pessoaId == null ||  alunoId == null) {
		
			throw new Exceptioncustom("Existem campos vazios, estes campos não podem ficar vazios");
		}
		
	

	}
	
	
	
}
