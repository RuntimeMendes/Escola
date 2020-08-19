package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Pessoa;
import Model.PessoaSexo;
import Model.PessoaTipo;
import accenture.escola.jdbc.ConnectionFactory;

public class PessoaDAO {

	PessoaSexo sexo;
	PessoaTipo tipoPessoa;

	// criar uma interface com assinatura dos metodos e uma classe que tem todas as
	// valida�oes
	// tirar do dao ... dao ta fazendo muita coisa

	public static void main(String[] args) throws SQLException, Exceptioncustom {

		PessoaDAO.consultaNomesPessoas();
		// PessoaDAO.consultaIDPessoa();
		// PessoaDAO.insertPessoa("RomualdoTX", "073.699wwwwwwwwwwwww.","M", "Aluno");
		// PessoaDAO.updatePessoa("Aline","999.999.999-99", "F", "Aluno", 12);
		// s PessoaDAO.delete(13);
		// PessoaDAO.validarCPF("oooooooooooooooooooooooooo");
		// PessoaDAO.validarNome("");
	}

	public static void consultaIDPessoa() throws SQLException {

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT idPessoa FROM pessoa");

			ResultSet rs = st.getResultSet();
		
			while (rs.next()) {

				System.out.println("exibe " + rs.getString(1) + " - \t");
				

			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void consultaNomesPessoas() throws SQLException {

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT idPessoa,PESSOAnome FROM Pessoa");

			ResultSet rs = st.getResultSet();
			// System.out.println("test " + rs);
			while (rs.next()) {

				System.out.println("Id = " + rs.getString(1) + " " + "  Nome ->" + rs.getString(2));
			

			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insertPessoa(String nomePessoa, String PessoaCPF, String sexoPessoa1, String tipoAluno)
			throws SQLException, Exceptioncustom {

		Pessoa p = new Pessoa();
		
		p.setPESSOA_nome(nomePessoa);
		p.setPESSOA_cpf(PessoaCPF);

		PessoaDAO.validarNome(p.getPESSOA_nome());
		PessoaDAO.validarCPF(p.getPESSOA_cpf());
		
		
		String sexoPessoa = validarSexopessoa(sexoPessoa1);
		String TipoAlunoRecebeoRetornoDoMetodo = validarPessoaTipo(tipoAluno);

		String query = "insert into `pessoa`(PESSOAnome,PESSOAcpf,PESSOAsexo,PESSOAtipo,TURMA_idTURMA) values (?,?,?,?,?);";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			
			if (sexoPessoa.equals("")) {
				conn.close();
			}
			if (TipoAlunoRecebeoRetornoDoMetodo.equals("")) {
				conn.close();
			}

			preparedStatement.setString(1, p.getPESSOA_nome());
			preparedStatement.setString(2, p.getPESSOA_cpf());
			preparedStatement.setString(3, sexoPessoa);
			preparedStatement.setString(4, TipoAlunoRecebeoRetornoDoMetodo);
			preparedStatement.setInt(5, 2);

			int row = preparedStatement.executeUpdate();

			// rows affected
			System.out.println("Registro Salvo " + row); // 1

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updatePessoa(String nomeF, String cpf, String sexo, String tipo, int Idpessoa)
			throws SQLException, Exceptioncustom {

		Pessoa p = new Pessoa();

		p.setPESSOA_nome(nomeF);
		p.setPESSOA_cpf(cpf);
		p.setIDpessoa(Idpessoa);

		PessoaDAO.validarNome(p.getPESSOA_nome());
		PessoaDAO.validarCPF(p.getPESSOA_cpf());
		String sexoPessoa = validarSexopessoa(sexo);
		String TipoAlunoRecebeoRetornoDoMetodo = validarPessoaTipo(tipo);

		String query = "UPDATE  pessoa SET  PESSOAnome =?, PESSOAcpf =?,PESSOAsexo=?,PESSOAtipo=? WHERE idPessoa =? ";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setString(1, p.getPESSOA_nome());
			preparedStatement.setString(2, p.getPESSOA_cpf());
			preparedStatement.setString(3, sexoPessoa);
			preparedStatement.setString(4, TipoAlunoRecebeoRetornoDoMetodo);
			preparedStatement.setInt(5, p.getIDpessoa());

			int row = preparedStatement.executeUpdate();

			System.out.println("Registro Atualizado ! " + row);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void delete(int id) throws SQLException {
		int Idrow = id;
		String query = "delete from pessoa where idPessoa = ?";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, Idrow);
			int row = preparedStatement.executeUpdate();

			System.out.println("Registro apagado" + row);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String validarSexopessoa(String Pessoatiposexo) throws SQLException, Exceptioncustom {

		String sexoPessoa = null;
		try {
			if (Pessoatiposexo.equals("Feminino") || Pessoatiposexo.equals("FEMININO") || Pessoatiposexo.equals("F")) {
				sexoPessoa = PessoaSexo.FEMININO.toString();

			}

			if (Pessoatiposexo.equals("Masculino") || Pessoatiposexo.equals("MASCULINO")
					|| Pessoatiposexo.equals("M")) {
				sexoPessoa = PessoaSexo.MASCULINO.toString();

			}

			if (Pessoatiposexo.equals("Outros") || Pessoatiposexo.equals("OUTROS") || Pessoatiposexo.equals("O")) {
				sexoPessoa = PessoaSexo.OUTROS.toString();

			}

			if (Pessoatiposexo.equals("") | Pessoatiposexo.equals(null)) {

				throw new Exceptioncustom("tipo do sexo n�o pode ficar vazio");

			}

		}
		// TODO: handle exception
		catch (Exceptioncustom e) {
			// TODO: handle exception
			System.out.println("Exception -- " + e.fillInStackTrace());
		}

		return sexoPessoa;
	}

	public static String validarPessoaTipo(String PessoaTipoParameter) throws Exceptioncustom {

		String tipoPessoa = "";
		String result;
		if (PessoaTipoParameter.equals("Aluno") || PessoaTipoParameter.equals("ALUNO")) {
			tipoPessoa = PessoaTipo.ALUNO.toString();
		}

		if (PessoaTipoParameter.equals("Aluno Bolsista") || PessoaTipoParameter.equals("ALUNO BOLSISTA")) {
			tipoPessoa = PessoaTipo.ALUNO_BOLSISTA.toString();

		}
		if (PessoaTipoParameter.equals("") | PessoaTipoParameter.equals(null)) {

			throw new Exceptioncustom("tipo da PESSOA n�o pode ficar vazio");
		}
		
		if (!PessoaTipoParameter.equals("Aluno Bolsista") | PessoaTipoParameter.equals("ALUNO BOLSISTA")) {

			throw new Exceptioncustom("Campos devem ser preenchidos com Aluno ou Aluno Bolsista ");
		}

		return result = tipoPessoa;

	}

	public static int validarCPF(String cpfParameter) throws Exceptioncustom {

		Boolean validarCpf = false;
		String cpf;
		int tamanho = 0;

		cpf = cpfParameter;
		tamanho = cpf.length();

		System.out.println("O tamanho do CPF � de: " + tamanho);

		if (tamanho > 14) {

			throw new Exceptioncustom("O CPF deve ser at� 14 caracteres");
		} else {
			validarCpf = true;
		}

		return tamanho;

	}

	public static int validarNome(String nomeParameter) throws Exceptioncustom {

		Boolean validarNome = false;
		String nomePessoa;
		int tamanho = 0;

		nomePessoa = nomeParameter;
		tamanho = nomePessoa.length();

		System.out.println("O tamanho do nome � de: " + tamanho);

		if (tamanho > 45) {

			throw new Exceptioncustom("O campo nome  deve ser at� 45 caracteres");
		}
		if (nomePessoa.equals("")) {
			throw new Exceptioncustom("O campo nome n�o pode ficar vazio");
		} else {
			validarNome = true;
		}

		return tamanho;

	}

}
