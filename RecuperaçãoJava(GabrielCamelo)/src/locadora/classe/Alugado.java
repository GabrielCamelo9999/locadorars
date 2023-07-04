package locadora.classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import locadora.conexao.Conexao;
/**
 * Classe que representa a Propriedade de um produto em uma garagem.
 * @author Gabriel
 */
public class Alugado {
	private int id_alugado;
	private int id_pessoa;
	private int id_filme;
	private String data_alocacao;
	/**
     * Obtém o ID do Alugado..
     *
     * @return o ID do Alugado.
     */
	public int getIdAlugado() {
		return id_alugado;
	}
	/**
     * Define o ID do Alugado.
     *
     * @param id_alugado o ID do Alugado.
     */
	public void setIdAlugado(int id_alugado) {
		this.id_alugado = id_alugado;
	}
	/**
     * Obtém o ID do Pessoa.
     *
     * @return o ID do Pessoa.
     */
	public int getIdPessoa() {
		return id_pessoa;
	}
	/**
     * Define o ID do Pessoa.
     *
     * @param id_pessoa o ID do Pessoa.
     */
	public void setIdPessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	/**
     * Obtém o ID do Filme.
     *
     * @return o ID do Filme.
     */
	public int getIdFilme() {
		return id_filme;
	}
	/**
     * Define o ID do Filme.
     *
     * @param id_filme o ID do Filme.
     */
	public void setIdFilme(int id_filme) {
		this.id_filme = id_filme;
	}
	/**
     * Obtém a Data da Alocação.
     *
     * @return a Data da Alocação.
     */
	public String getDataAlocacao() {
		return data_alocacao;
	}
	/**
     * Define a Data da Alocação.
    *
    * @param data_alocacao da Data da Alocação.
    */
	public void setDataAlocacao(String data_alocacao) {
		this.data_alocacao = data_alocacao;
	}
	/**
     * Cadastra uma propriedade.
     *
     * @param id_alugado  O ID do alugado.
     * @param id_pessoa      O ID da pessoa.
     * @param id_filme      O ID do filme.
     * @param data_alocacao  A data alocação.
     * @return true se o cadastro foi realizado com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean cadastrarAlugado(int id_alugado, int id_pessoa, int id_filme, String data_alocacao) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "INSERT INTO alugado (id_alugado, id_pessoa, id_filme, data_alocacao) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_alugado);
            ps.setInt(2, id_pessoa);
            ps.setInt(3, id_filme);
            ps.setString(4, data_alocacao);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi feito o cadastro!");
                return false;
            }
            System.out.println("Cadastro realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar alocação: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
    /**
     * Consulta uma propriedade pelo seu ID.
     *
     * @param id_alugado O ID do filme alugado a ser consultada.
     * @return true se a propriedade foi encontrada, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean consultarAlugado(int id_alugado) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "SELECT * FROM alugado WHERE id_alugado=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_alugado);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("O filme não está alugado!");
                return false;
            } else {
                while (rs.next()) {
                    this.id_alugado = rs.getInt("id_alugado");
                    this.id_pessoa = rs.getInt("id_pessoa");
                    this.id_filme = rs.getInt("id_filme");
                    this.data_alocacao = rs.getString("data_alocacao");
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar filme alugado: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
    /**
     * Alugado uma propriedade.
     *
     * @param id_alugado  O ID do alugado.
     * @param id_pessoa      O ID da pessoa.
     * @param id_filme      O ID do filme.
     * @param data_alocacao  A data alocação.
     * @return true se o cadastro foi realizado com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean atualizarAlugado(int id_alugado, int id_pessoa, int id_filme, String data_alocacao) {
        if (!consultarAlugado(id_alugado))
            return false;
        else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaBanco();
                String sql = "UPDATE alugado SET id_pessoa =?, id_filme =?, data_alocacao =?, WHERE id_alugado =?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, id_pessoa);
                ps.setInt(2, id_filme);
                ps.setString(3, data_alocacao);
                ps.setInt(4, id_alugado);
                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0)
                    System.out.println("Não foi feita a atualização!");
                else
                    System.out.println("Atualização realizada!");
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao atualizar pessoa: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }
    /**
     * Remover um filme no estado alugado pelo seu ID.
     *
     * @param id_alugado O ID do filme alugado a ser consultada.
     * @return true se a propriedade foi encontrada, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean removerAlugado(int id_alugado) {
        if (!consultarAlugado(id_alugado))
            return false;
        else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaBanco();
                String sql = "DELETE FROM alugado WHERE id_alugado=?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, id_alugado);
                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0)
                    System.out.println("Não foi feita a remoção!");
                else
                    System.out.println("Remoção realizada!");
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao remover filme no estado alugadpo: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }
    /**
    * Consultar um filme alugado pelo seu ID.
    *
    * @param id_alugado O ID do filme alugado a ser consultada.
    * @return true se a propriedade foi encontrada, false caso contrário.
    * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
    */
    public boolean consultarAlugadoPessoa(int id_pessoa) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "SELECT * FROM alugado WHERE id_pessoa=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_pessoa);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("A pessoa não possui filme alugado!");
                return false;
            } else {
                while (rs.next()) {
                    this.id_alugado = rs.getInt("id_alugado");
                    this.id_pessoa = rs.getInt("id_pessoa");
                    this.id_filme = rs.getInt("id_filme");
                    this.data_alocacao = rs.getString("data_alocacao");
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar filme alugado: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
}
