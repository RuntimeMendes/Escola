package accenture.escola.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnectionFactory() {

		String url = "jdbc:mysql://localhost/escola";
		String user = "root";
		String pass = "root";
		Connection cn = null;

		// The following code would not compile unless it's put inside a try catch
		// 1 - put it in a try block and handle ClassNotFoundException

		try {

			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successfully established! \n");

			//

		} catch (Exception e) {

			System.out.println("Falha na conexao!!");

		}
		return cn;

	}

}
