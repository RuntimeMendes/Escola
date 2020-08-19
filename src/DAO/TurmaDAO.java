package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Implements.Validations;
import Model.Disciplina;
import Model.Turma;
import accenture.escola.jdbc.ConnectionFactory;

public class TurmaDAO {

	public static void main(String[] args) throws SQLException, Exceptioncustom {

		// TurmaDAO.insertTurma("");
		TurmaDAO.consultaTurma();
		// TurmaDAO.updateTurma("Turma vai ser deletada",7);

	}

	public static int obterUltimoIdTurma() throws SQLException {
		int ultimoId = 0;

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT  MAX(idTURMA) FROM turma ");
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

	public static void consultaTurma() throws SQLException {

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT * FROM turma ");

			ResultSet rs = st.getResultSet();

			while (rs.next()) {

				System.out.println("Id = " + rs.getString(1) + " " + " Turma  ->" + rs.getString(2));

			}
			cn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insertTurma(String TURMAcol) throws SQLException, Exceptioncustom {
		Turma turma = new Turma();

		int obterIdTurma;
		obterIdTurma = TurmaDAO.obterUltimoIdTurma();

		if (obterIdTurma == 0) {
			obterIdTurma = obterIdTurma + 1;
		} else {
			obterIdTurma = obterIdTurma + 1;
		}

		String x = Validations.validarTurmadescricao(TURMAcol);

		turma.setTURMADescricao(x);

		String query = "insert into `turma`(idTURMA,TURMAcol) values (?,?);";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, obterIdTurma);
			preparedStatement.setString(2, turma.getTURMADescricao());

			int row = preparedStatement.executeUpdate();

			// rows affected
			System.out.println("Registro cadastrado " + row); // 1

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateTurma(String TURMAcol, int idDisciplina) throws SQLException, Exceptioncustom {

		Turma disciplinaVariaveldoObjeto = new Turma();
		String query = "UPDATE turma SET  TURMAcol =?  WHERE idTURMA =? ";

		disciplinaVariaveldoObjeto.setTURMADescricao(TURMAcol);

		disciplinaVariaveldoObjeto.setIDTurma(idDisciplina);

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setString(1, disciplinaVariaveldoObjeto.getTURMADescricao());
			preparedStatement.setInt(2, disciplinaVariaveldoObjeto.getIDTurma());

			int row = preparedStatement.executeUpdate();

			System.out.println("Registro Atualizado " + row);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void deleteTurma(int id) throws SQLException {
		int Idrow = id;
		String query = "delete from turma where idTURMA = ?";

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
