package locadora.janela;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import locadora.classe.Filme;

/**
 * Classe responsável por criar a janela de interação com Monociclos.
 * @author Gabriel
 */
public class JanelaFilme {
	/**
     * Método para criar a janela de filme.
     *
     * @return JFrame contendo a janela de filme.
     */
	public static JFrame CriarJanelaFilme() {
		JFrame janelaFilme = new JFrame("Filme");
		janelaFilme.setResizable(false);
		janelaFilme.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaFilme.setSize(500, 400);
		Container caixa = janelaFilme.getContentPane();
		caixa.setLayout(null);
		
		JLabel labelid_filme = new JLabel("Id Filme: ");
		JLabel labeltitulo = new JLabel("Titulo: ");
		JLabel labelgenero = new JLabel("Genero: ");
		JLabel labeldata_estreia = new JLabel("Data Estreia: ");
		
		//Posiciona os labels na janela
		labelid_filme.setBounds(60, 40, 100, 20);  // coluna, linha, largura, tamanho
		labeltitulo.setBounds(60, 70, 100, 20);  // coluna, linha, largura, tamanho
		labelgenero.setBounds(60, 100, 100, 20);  // coluna, linha, largura, tamanho
		labeldata_estreia.setBounds(60, 130, 100, 20);  // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextid_filme = new JTextField();
		JTextField jTexttitulo = new JTextField();
		JTextField jTextgenero = new JTextField();
		JTextField jTextdata_estreia = new JTextField();
		
		// Define se os campos estão habilitados ou não no início
		jTextid_filme.setEnabled(true);
		jTexttitulo.setEnabled(true);
		jTextgenero.setEnabled(true);
		jTextdata_estreia.setEnabled(true);
		
		// Posiciona os input box
		jTextid_filme.setBounds(130, 40, 160, 20);
		jTexttitulo.setBounds(130, 70, 160, 20);
		jTextgenero.setBounds(130, 100, 160, 20);
		jTextdata_estreia.setBounds(130, 130, 160, 20);
		
		// Adiciona os rótulos e os input box na janela
		janelaFilme.add(labelid_filme);
		janelaFilme.add(labeltitulo);
		janelaFilme.add(labelgenero);
		janelaFilme.add(labeldata_estreia);
		janelaFilme.add(jTextid_filme);
		janelaFilme.add(jTexttitulo);
		janelaFilme.add(jTextgenero);
		janelaFilme.add(jTextdata_estreia);
		
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(300, 40, 100, 20);
		janelaFilme.add(botaoConsultar);
		
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(300, 70, 100, 20);
		janelaFilme.add(botaoAtualizar);
		botaoAtualizar.setEnabled(true);
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setBounds(300, 100, 100, 20);
		janelaFilme.add(botaoCadastrar);
		botaoCadastrar.setEnabled(true);
		
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(300, 130, 100, 20);
		janelaFilme.add(botaoExcluir);
		botaoExcluir.setEnabled(true);

		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(300, 190, 100, 20);
		janelaFilme.add(botaoLimpar);

		Filme filme = new Filme();
		
		botaoConsultar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Consultar".
             *
             * @param e evento de ação.
             */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!filme.consultarFilme(Integer.parseInt(jTextid_filme.getText()))) {
						JOptionPane.showMessageDialog(janelaFilme, "Filme não encontrado, tente novamente.");
						botaoCadastrar.setEnabled(true);
					} else {
						jTextid_filme.setText(Integer.toString(filme.getIdFilme()));
						jTexttitulo.setText(filme.getTitulo());
						jTextgenero.setText(filme.getGenero());
						jTextdata_estreia.setText(filme.getDataEstreia());

						jTexttitulo.setEnabled(true);
						jTextgenero.setEnabled(true);
						jTextdata_estreia.setEnabled(true);
						botaoAtualizar.setEnabled(true);
						botaoCadastrar.setEnabled(true);
						JOptionPane.showMessageDialog(janelaFilme, "Filme encontrado!");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		
		botaoAtualizar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Atualizar".
             *
             * @param e evento de ação.
             */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaFilme, "Tem Certeza?") == 0)
						JOptionPane.showConfirmDialog(janelaFilme, "Filme Atualizado");
					{
						if (!filme.atualizarFilme(Integer.parseInt(jTextid_filme.getText()),
								jTexttitulo.getText(), jTextgenero.getText(), jTextdata_estreia.getText())) {
							JOptionPane.showMessageDialog(janelaFilme, "Não foi possivel atualizar o Filme");
						} else {
							JOptionPane.showMessageDialog(janelaFilme, "Atualização realizada");
						}

					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		
		botaoCadastrar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Cadastrar".
             *
             * @param e evento de ação.
             */
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaFilme, "Tem Certeza?") == 0) 
						JOptionPane.showMessageDialog(janelaFilme, "Filme Cadastrado");{
						if (!filme.consultarFilme(Integer.parseInt(jTextid_filme.getText()))) {
							filme.cadastrarFilme(Integer.parseInt(jTextid_filme.getText()),
									jTexttitulo.getText(), jTextgenero.getText(), jTextdata_estreia.getText());
						} else {
							JOptionPane.showMessageDialog(janelaFilme, "Filme já cadastrado");
							botaoCadastrar.setEnabled(true);
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		botaoExcluir.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Excluir".
             *
             * @param e evento de ação.
             */
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaFilme, "Tem Certeza?") == 0) {
						int id_filme = Integer.parseInt(jTextid_filme.getText());
						if (filme.consultarFilme(id_filme)) {
							filme.removerFilme(id_filme);
							JOptionPane.showMessageDialog(janelaFilme, "Filme removido com sucesso");
							// Limpar os campos de entrada após a exclusão
							jTextid_filme.setText("");
							jTexttitulo.setText("");
							jTextgenero.setText("");
							jTextdata_estreia.setText("");
						} else {
							JOptionPane.showMessageDialog(janelaFilme, "Filme não encontrado");
						}
					}
				} catch (Exception e2) {
					// Tratar exceção, se necessário
				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Limpar".
             *
             * @param e evento de ação.
             */
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextid_filme.setText(""); // Limpar campo
				jTexttitulo.setText(""); // Limpar campo
				jTextgenero.setText(""); // Limpar campo
				jTextdata_estreia.setText(""); //Limpar Campo
			}
		});
		return janelaFilme;
	}
}
