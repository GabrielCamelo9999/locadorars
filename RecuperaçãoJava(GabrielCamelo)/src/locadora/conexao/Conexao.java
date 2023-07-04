package locadora.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A classe Conexao ser para forncer métodos para estabelecer e fechar conexões com o banco de dados.
 * @author Gabriel
 */

public class Conexao {
	/**
	 * Estabelece uma conexão com o banco de dados.
	 * @return a conexão estabelecida.
	 * @throws ClassNotFoundException se o Driver JDBC não for encontrado.
	 * @throws SQLException SE OCORRER UM ERRO DE CONEXÃO COM O Banco de dado.
	 */
	public static Connection conectaBanco() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/locadora"; //URL DO Banco de dados
			String user = "root"; //Nome do usuario do banco
			String password = ""; //Senha do Banco
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver não encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conexão ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro não identificado: " + erro.toString());
		}
		return conexao;	
	}
	/**
	 * Fecha uma conexão com o Banco de dados.
	 * @param conexao a conexão a ser fechada
	 * @throws SQLException SE OCORRER UM ERRO DE CONEXÃO COM O Banco de dado.
	 */
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fecahr a conexão: " + erro.toString());
		}
		
	}
}
