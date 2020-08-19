package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Implements.Validations;
import Model.DisciplinaAluno;
import Model.Turma;
import accenture.escola.jdbc.ConnectionFactory;

public class DisciplinaAlunoDAO {

	public static void main(String[] args) throws SQLException, Exceptioncustom {
		// TODO Auto-generated method stub
		DisciplinaAlunoDAO.consultaDisciplinaAluno();

	}

	public static List<String> consultaDisciplinaAluno() throws SQLException {

		List<String> lstIdDisciplina = new ArrayList<String>();
		List<String> lstIdPessoa = new ArrayList<String>();
		List<String> lstIdAluno = new ArrayList<String>();

		try {

			Connection cn = ConnectionFactory.getConnectionFactory();
			Statement st = cn.createStatement();

			st.executeQuery("SELECT disciplina.idDISCIPLINA,aluno.idPessoa,aluno.idALUNO" + "	FROM disciplina,aluno "
					+ "WHERE disciplina.idDISCIPLINA = aluno.idALUNO");

			ResultSet rs = st.getResultSet();

			while (rs.next()) {

				lstIdDisciplina.add(rs.getString(1));
				lstIdPessoa.add(rs.getString(2));
				lstIdAluno.add(rs.getString(3));

				System.out.println("Id Disciplina = " + rs.getString(1) + " " + " Id Pessoa  = " + rs.getString(2) + " "
						+ "Id Aluno " + rs.getString(3));

			}
			cn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		String todosNomes = String.join(", ", lstIdDisciplina + "\n");

		for (int i = 0; i < 1; i++) {

			lstIdDisciplina.get(i);
			System.out.println("erts " + lstIdDisciplina);
		}

		return null;

	}

	public static void insertIdDisciplinaAluno(int idDisciplina, int idPessoa, int idAluno)
			throws SQLException, Exceptioncustom {

		Validations.validarDisciplinaAluno(idDisciplina, idPessoa, idAluno);
		DisciplinaAluno disciplinaAluno = new DisciplinaAluno();

		disciplinaAluno.setDisciplina_IDDisciplina(idDisciplina);
		disciplinaAluno.setAluno_IDPessoa(idPessoa);
		disciplinaAluno.setAluno_IDAluno(idAluno);

		String query = "insert into `disciplinaaluno`(idDISCIPLINA,idPessoa,idALUNO) values (?,?,?);";

		try (Connection conn = (Connection) ConnectionFactory.getConnectionFactory();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, disciplinaAluno.getDisciplina_IDDisciplina());
			preparedStatement.setInt(2, disciplinaAluno.getAluno_IDPessoa());
			preparedStatement.setInt(3, disciplinaAluno.getAluno_IDAluno());
		
			int row = preparedStatement.executeUpdate();

			// rows affected
			System.out.println("Registro cadastrado " + row); // 1

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
