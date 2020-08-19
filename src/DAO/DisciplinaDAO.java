package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Implements.Validations;
import Model.Disciplina;
import Model.Pessoa;
import accenture.escola.jdbc.ConnectionFactory;

public class DisciplinaDAO {

	public static void main(String[] args) throws SQLException, Exceptioncustom {
		// TODO Auto-generated method stub
		// Validations.obterUltimoIdAluno();
		DisciplinaDAO.insertDisciplina("", 8);
		// DisciplinaDAO.consultaDisciplinas();
		// DisciplinaDAO.updateDisciplina("SQL", 6, 2);
		// DisciplinaDAO.deleteDisciplina(5);

	}

	public static void consultaDisciplinas() throws SQLException {

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT * FROM  disciplina");

			ResultSet rs = st.getResultSet();
			
			while (rs.next()) {

				System.out.println("Id = " + rs.getString(1) + " " + " Nome disciplina  ->" + rs.getString(2) + " "
						+ "Disciplina Credito  " + rs.getString(3));
				

			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insertDisciplina(String nomeDisciplinaParameter, int disciplinaCreditoParameter)
			throws SQLException, Exceptioncustom {

		Disciplina disciplinaVariaveldoObjeto = new Disciplina();

		int obterIdDisciplinaBanco;
		obterIdDisciplinaBanco = Validations.obterUltimoIdAluno(); // DisciplinaDAO.obterUltimoIdAluno();
		if (obterIdDisciplinaBanco == 0) {
			obterIdDisciplinaBanco = obterIdDisciplinaBanco + 1;
		} else {
			obterIdDisciplinaBanco = obterIdDisciplinaBanco + 1;
		}
		Validations.validarNome(nomeDisciplinaParameter);

		disciplinaVariaveldoObjeto.setIDdisciplina(obterIdDisciplinaBanco);
		disciplinaVariaveldoObjeto.setDisciplinaNome(nomeDisciplinaParameter);
		disciplinaVariaveldoObjeto.setDisciplinaCreditos(disciplinaCreditoParameter);

		String query = "insert into `disciplina`(idDISCIPLINA,DISCIPLINANome,DISCIPLINAcredito) values (?,?,?);";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, disciplinaVariaveldoObjeto.getIDdisciplina());// Id da disciplina
			preparedStatement.setString(2, disciplinaVariaveldoObjeto.getDisciplinaNome());
			preparedStatement.setInt(3, disciplinaVariaveldoObjeto.getDisciplinaCreditos());

			int row = preparedStatement.executeUpdate();

			// rows affected
			System.out.println("Registro cadastrado " + row); // 1

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateDisciplina(String nomeDisciplinaParameter, int disciplinaCreditoParameter,
			int idDisciplina) throws SQLException, Exceptioncustom {

		Disciplina disciplinaVariaveldoObjeto = new Disciplina();
		String query = "UPDATE  disciplina SET  DISCIPLINANome =?, DISCIPLINAcredito =?  WHERE idDISCIPLINA =? ";

		disciplinaVariaveldoObjeto.setDisciplinaNome(nomeDisciplinaParameter);
		disciplinaVariaveldoObjeto.setDisciplinaCreditos(disciplinaCreditoParameter);
		disciplinaVariaveldoObjeto.setIDdisciplina(idDisciplina);

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setString(1, disciplinaVariaveldoObjeto.getDisciplinaNome());
			preparedStatement.setInt(2, disciplinaVariaveldoObjeto.getDisciplinaCreditos());
			preparedStatement.setInt(3, disciplinaVariaveldoObjeto.getIDdisciplina());

			int row = preparedStatement.executeUpdate();

			System.out.println("Registro atualizado " + row);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void deleteDisciplina(int id) throws SQLException {
		int Idrow = id;
		String query = "delete from disciplina where idDISCIPLINA = ?";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, Idrow);
			int row = preparedStatement.executeUpdate();

			System.out.println("Registro excluído" + row);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
