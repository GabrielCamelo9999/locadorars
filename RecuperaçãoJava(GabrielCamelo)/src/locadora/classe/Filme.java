package locadora.classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import locadora.conexao.Conexao;

/**
 * Classe que representa um Monociclo.
 * 
 * @author Gabriel
 */
public class Filme {
	private int id_filme;
	private String titulo;
	private String genero;
	private String data_estreia;
	/**
     * Obtém o ID do filme.
     *
     * @return O ID do filme.
     */
	public int getIdFilme() {
		return id_filme;
	}
	/**
     * Define o ID do filme.
     *
     * @param idProduto O ID do filme.
     */
	public void setIdFilme(int id_filme) {
		this.id_filme = id_filme;
	}
	
	/**
     * Obtém o titulo do filme.
     *
     * @return O titulo do filme.
     */
	public String getTitulo() {
		return titulo;
	}
	/**
     * Define o titulo do filme.
     *
     * @param marca O Titulo  do filme.
     */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
     * Obtém o genero do filme.
     *
     * @return O genero do filme.
     */
	public String getGenero() {
		return genero;
	}
	/**
     * Define o genero do filme.
     *
     * @param marca O genero do filme.
     */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	/**
     * Obtém a data de estreia do filme.
     *
     * @return a data de estreia do filme.
     */
	public String getDataEstreia() {
		return data_estreia;
	}
	/**
     * Define a data de estreia do filme.
     *
     * @param marca A data da estreia do filme.
     */
	public void setDataEstreia(String data_estreia) {
		this.data_estreia = data_estreia;
	}
	/**
     * Cadastra do filme.
     *
     * @param id_filme       O ID do filme.
     * @param titulo         A titulo do filme.
     * @param genero         O genero do filme.
     * @param Data_estreia   A data da estreia do filme.
     * @return true se o cadastro foi realizado com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean cadastrarFilme(int id_filme, String titulo, String genero, String data_estreia) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();

            String sql = "INSERT INTO filme (id_filme, titulo, genero, data_estreia) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, id_filme);
            ps.setString(2, titulo);
            ps.setString(3, genero);
            ps.setString(4, data_estreia);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Nao foi feito o cadastro!");
                return false;
            }
            System.out.println("Cadastro realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar filme: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
    /**
     * Consulta do filme pelo ID do filme.
     *
     * @param id_filme O ID do filme a ser consultado.
     * @return true se o filme foi encontrado, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
	public boolean consultarFilme(int id_filme)	{
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM filme WHERE id_filme=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id_filme);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Filme não cafastrado!!");
				return false;
			} else {
				while (rs.next()) {
					this.id_filme = rs.getInt("id_filme");
					this.titulo = rs.getString("titulo");
					this.genero = rs.getString("genero");
					this.data_estreia = rs.getString("data_estreia");	
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o filme: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
     * Atualizar do filme.
     *
     * @param id_filme       O ID do filme.
     * @param titulo         A titulo do filme.
     * @param genero         O genero do filme.
     * @param Data_estreia   A data da estreia do filme.
     * @return true se o cadastro foi realizado com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
	public boolean atualizarFilme(int id_filme, String titulo, String genero, String data_estreia) {
		if (!consultarFilme(id_filme))
			return false;
			else {
				Connection conexao = null;
				try {
					conexao = Conexao.conectaBanco();
					String sql = "UPDATE filme Set titulo=?, genero=?, data_estreia, WHERE id_filme=?";
					PreparedStatement ps = conexao.prepareStatement(sql);
					ps.setString(1, titulo);
					ps.setString(2, genero);
					ps.setString(3, data_estreia);
					ps.setInt(4, id_filme);
					int totalRegistrosAfetados = ps.executeUpdate();
					if (totalRegistrosAfetados == 0)
						System.out.println("Não foi feita a atualizaação!!");
					else
						System.out.println("Atualização realizada!!");
					return true;
				} catch (SQLException erro) {
					System.out.println("Erro ao atualizar filme: " + erro.toString());
					return false;
				} finally {
					Conexao.fechaConexao(conexao);
				}
			}
	}
	/**
     * remover do filme pelo ID do filme.
     *
     * @param id_filme O ID do filme a ser consultado.
     * @return true se o filme foi encontrado, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
	public boolean removerFilme(int id_filme) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "DELETE FROM filme WHERE id_filme=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_filme);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Nao foi feito a remoção");
                return false;
            }
            System.out.println("Remoção realizada!!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao remover filme: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
	}
}
	
	
	
	
	
	
	