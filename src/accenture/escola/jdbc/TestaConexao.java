package accenture.escola.jdbc;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class TestaConexao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	try {
		ConnectionFactory.getConnectionFactory();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Falha na conexao !!");
	}
		
	  

	}

}
