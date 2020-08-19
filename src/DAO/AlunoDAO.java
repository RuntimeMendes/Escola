package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Aluno;
import Model.AlunoTipoBolsa;
import accenture.escola.jdbc.ConnectionFactory;

public class AlunoDAO {

	Connection connection;
	AlunoTipoBolsa tipoBolsa;

	public static void main(String[] args) {

		try {
			// AlunoDAO.consultaAluno();
			// AlunoDAO.updateAluno(6, 450.0, "Não Bolsista");
			// AlunoDAO.consultaIDPessoaUltimoIdCadastrado();
			// AlunoDAO.calcularMensalidade(2);
			// AlunoDAO.insertAluno(6, 2);
			// AlunoDAO.cadastroAluno(90);
			AlunoDAO d = new AlunoDAO();
			// d.insert();
			// d.update("Maria Luiza",5,"073.567.123-67","FEMININO","B90-BOLSISTA");
			d.delete(1);
			// d.validarIdAluno(1);
			// AlunoDAO.obterUltimoIdAluno();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean validarIdAluno(int idAluno) throws SQLException {
		int alunoId = idAluno;
		boolean boleanId = false;
		String sql = "SELECT  idPessoa,idALUNO FROM aluno  WHERE idALUNO =" + alunoId;

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();) {

			Statement st = conn.createStatement();
			st.executeQuery(sql);
			ResultSet rs = st.getResultSet();

			if (rs.next()) {
				boleanId = true;
				System.out.println("Id válido já existe na base de dados");
			} else {
				boleanId = false;
				System.out.println("Id inválido não existe na base de dados");
			}
			conn.close();

		} catch (Exception e) {
			System.out.println("Exeception -> " + e);
		}

		return boleanId;
	}

	public static void consultaIDPessoaUltimoIdCadastrado() throws SQLException {

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT  MAX(idPessoa) FROM Pessoa");

			ResultSet rs = st.getResultSet();
			
			while (rs.next()) {

				System.out.println("Ultimo Id Cadastrado em Pessoa  = " + rs.getString(1));
			
			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int obterUltimoIdAluno() throws SQLException {

		int ultimoId = 0;
		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT  MAX(idALUNO) FROM aluno");

			ResultSet rs = st.getResultSet();
			
			while (rs.next()) {

				ultimoId = rs.getInt(1);
	
			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ultimoId;
	}

	public static double calcularMensalidade(int opcao) throws SQLException {

		Double valorMensalidade = 1000.0;
		double valorDesconto = 0;
		double valorFinal = 0.0;

		if (opcao == 0) {

			valorDesconto = valorMensalidade;
			System.out.println("Valor Integral " + valorDesconto);
		}
		if (opcao == 1) {
			valorDesconto = (valorMensalidade * 0.9);
			System.out.println("valor desconto " + AlunoTipoBolsa.B90 + "%");
		}
		if (opcao == 2) {
			valorDesconto = (valorMensalidade * 0.5);
			System.out.println("valor desconto " + AlunoTipoBolsa.B50 + "%");

		}
		if (opcao == 3) {
			valorDesconto = (valorMensalidade * 0.1);

			System.out.println("valor desconto " + AlunoTipoBolsa.B10 + "%");
		}

		return valorDesconto;
	}

	public static void consultaAluno() throws SQLException {

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT * FROM escola.aluno;");

			ResultSet rs = st.getResultSet();
			
			while (rs.next()) {

		System.out.println("Alunos = " + "IdPessoa " + rs.getString(1) + " " + "IdAluno " + rs.getString(2) 
		+ "Valor Mensalidade  = " +  rs.getString(3) + "  Tipo de Bolsa  " +   rs.getString(4));
				
			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insertAluno(int idPessoaparameter, int numeroDaMensalidade) throws SQLException {
	
		int idPessoa = idPessoaparameter;
		Aluno aluno = new Aluno();
		int idAluno = AlunoDAO.obterUltimoIdAluno();

		idAluno = idAluno + 1;
		aluno.setIDaluno(idAluno);

		Double valorMensalidade = AlunoDAO.calcularMensalidade(numeroDaMensalidade);
		String valorBolsa;

		if (valorMensalidade.equals(900.0)) {
			valorBolsa = "B90";
		}
		if (valorMensalidade.equals(500.0)) {
			valorBolsa = "B50";
		} else if (valorMensalidade.equals(100.0)) {
			valorBolsa = "B10";
		} else {
			valorBolsa = "Não Bolsista";

		}

		String query = "insert into `aluno`(idPessoa,idALUNO,ALUNOVrMensalidade,ALUNOTipoBolsa) values (?,?,?,?);";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, idPessoa);
			preparedStatement.setInt(2, aluno.getIDaluno());
			preparedStatement.setDouble(3, valorMensalidade);
			preparedStatement.setString(4, valorBolsa);
			// preparedStatement.setString(5, "B90");// Data truncation: Data too long

			int row = preparedStatement.executeUpdate();

			// rows affected
			System.out.println("Cadastrado  " + row); // 1

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateAluno(int IdP, double valorMensalidadeParameter, String tipoBolsaParameter)
			throws SQLException {

		int id = IdP;
		double valorMensalidade = valorMensalidadeParameter;
		String alunoTipoBolsa = tipoBolsaParameter;

		String query = "UPDATE  aluno SET ALUNOVrMensalidade =?, ALUNOTipoBolsa =? WHERE idPessoa =? ";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setDouble(1, valorMensalidade);
			preparedStatement.setString(2, alunoTipoBolsa);
			preparedStatement.setInt(3, id);

			int row = preparedStatement.executeUpdate();

			System.out.println("row " + row);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void delete(int id) throws SQLException {
		int Idrow = id;
		String query = "delete from aluno where idPessoa = ?";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, Idrow);
			int row = preparedStatement.executeUpdate();

			System.out.println("deleted" + row);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
