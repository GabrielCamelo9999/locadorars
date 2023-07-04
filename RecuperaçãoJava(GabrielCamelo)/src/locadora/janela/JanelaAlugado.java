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

import locadora.classe.Alugado;

/**
 * Classe que representa a janela de propriedade.
 * @author Gabriel
 */
public class JanelaAlugado {
	/**
     * Cria e retorna a janela de alugado.
     *
     * @return a janela de alugado
     */
	public static JFrame CriarJanelaAlugado() {
		JFrame janelaAlugado = new JFrame("Alugado"); // Janela Normal
        janelaAlugado.setResizable(false); // A janela não poderá ter o tamanho ajustado
        janelaAlugado.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaAlugado.setSize(500, 400); // Define tamanho da janela
        
        // Define o layout da janela
        Container caixa = janelaAlugado.getContentPane();
        caixa.setLayout(null);
        
        // Define os labels dos campos
        JLabel labelid_alugado = new JLabel("Id Alugado: ");
        JLabel labelid_pessoa = new JLabel("Id Pessoa: ");
        JLabel labelid_filme = new JLabel("Id Filme: ");
        JLabel labeldata_alocacao = new JLabel("Data Alocação: ");
        
        // Posiciona os labels na janela
        labelid_alugado.setBounds(50, 40, 120, 20); // coluna, linha, largura, tamanho
        labelid_pessoa.setBounds(50, 70, 120, 20); // coluna, linha, largura, tamanho
        labelid_filme.setBounds(50, 100, 120, 20);
        labeldata_alocacao.setBounds(50, 130, 120, 20);
        
        // Define os input box
        JTextField jTextid_alugado = new JTextField();
        JTextField jTextid_pessoa = new JTextField();
        JTextField jTextid_filme = new JTextField();
        JTextField jTextdata_Alocacao = new JTextField();
        
        // Define se os campos estão habilitados ou não no início
        jTextid_alugado.setEnabled(true);
        jTextid_pessoa.setEnabled(true);
        jTextid_filme.setEnabled(true);
        jTextdata_Alocacao.setEnabled(true);
        
        // Posiciona os input box
        jTextid_alugado.setBounds(140, 40, 160, 20);
        jTextid_pessoa.setBounds(140, 70, 160, 20);
        jTextid_filme.setBounds(140, 100, 160, 20);
        jTextdata_Alocacao.setBounds(140, 130, 160, 20);
        
        // Adiciona os rótulos e os input box na janela
        janelaAlugado.add(labelid_alugado);
        janelaAlugado.add(labelid_pessoa);
        janelaAlugado.add(labelid_filme);
        janelaAlugado.add(labeldata_alocacao);
        janelaAlugado.add(jTextid_alugado);
        janelaAlugado.add(jTextid_pessoa);
        janelaAlugado.add(jTextid_filme);
        janelaAlugado.add(jTextdata_Alocacao);
        Alugado alugado = new Alugado();
        
        JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(310, 40, 100, 20);
        janelaAlugado.add(botaoConsultar);
        
        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.setBounds(310, 70, 100, 20);
        janelaAlugado.add(botaoAtualizar);
        botaoAtualizar.setEnabled(true);
        
        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(310, 130, 100, 20);
        janelaAlugado.add(botaoExcluir);
        botaoExcluir.setEnabled(true);
        
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(310, 100, 100, 20);
        janelaAlugado.add(botaoCadastrar);
        botaoCadastrar.setEnabled(true);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(310, 190, 100, 20);
        janelaAlugado.add(botaoLimpar);
        
        /**
         * Ação executada ao clicar no botão "Cadastrar".
         */
        botaoCadastrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaAlugado, "Tem Certeza?") == 0) {
                        JOptionPane.showMessageDialog(janelaAlugado, "Cadastro realizado");

                        int id_alugado = Integer.parseInt(jTextid_alugado.getText());
                        int id_pessoa = Integer.parseInt(jTextid_pessoa.getText());
                        int id_filme = Integer.parseInt(jTextid_filme.getText());
                        String data_alocacao = jTextdata_Alocacao.getText();

                        if (!alugado.consultarAlugado(id_alugado)) {
                            alugado.cadastrarAlugado(id_alugado, id_pessoa, id_filme, data_alocacao);
                            JOptionPane.showMessageDialog(janelaAlugado, "Cadastro nao realizado");
                        } else {
                            JOptionPane.showMessageDialog(janelaAlugado, "Já alugado");
                            botaoCadastrar.setEnabled(false);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(janelaAlugado, "Erro ao cadastar");
                }
            }
        });
        /**
         * Ação executada ao clicar no botão "Atualizar".
         */
        botaoAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaAlugado, "Tem Certeza?") == 0) {
                        JOptionPane.showMessageDialog(janelaAlugado, "Atualizacao realizada");

                        int id_alugado = Integer.parseInt(jTextid_alugado.getText());
                        int id_pessoa = Integer.parseInt(jTextid_pessoa.getText());
                        int id_filme = Integer.parseInt(jTextid_filme.getText());
                        String data_alocacao = jTextdata_Alocacao.getText();

                        if (!alugado.atualizarAlugado(id_alugado, id_pessoa, id_filme, data_alocacao)) {
                            JOptionPane.showMessageDialog(janelaAlugado, "Não foi possível atualizar");
                        } else {
                            JOptionPane.showMessageDialog(janelaAlugado, "Atualização realizada");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(janelaAlugado, "Verifique os campos numéricos");
                } catch (Exception ex) {
                    // TODO: Trate qualquer outra exceção que possa ocorrer durante a atualização
                }
            }
        });
        /**
         * Ação executada ao clicar no botão "Excluir".
         */
        botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaAlugado, "Tem Certeza?") == 0) {
                        JOptionPane.showMessageDialog(janelaAlugado, "Cadastro excluido");

                        // Aqui você pode adicionar a lógica para excluir a pessoa com base no ID
                        int id_alugado = Integer.parseInt(jTextid_pessoa.getText());
                       alugado.removerAlugado(id_alugado);
                        // Adicione qualquer lógica adicional que você precise após excluir a pessoa
                    }
                } catch (Exception e2) {
                    // TODO: Trate qualquer exceção que possa ocorrer ao excluir a pessoa
                }
            }
        });
        /**
         * Ação executada ao clicar no botão "Consultar".
         */
        botaoConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!alugado.consultarAlugado(Integer.parseInt(jTextid_alugado.getText()))) {
                        JOptionPane.showMessageDialog(janelaAlugado,
                                "Não encontrada, tente novamente.");
                        botaoCadastrar.setEnabled(true);
                    } else {
                        jTextid_pessoa.setText(String.valueOf(alugado.getIdPessoa()));
                        jTextid_filme.setText(String.valueOf(alugado.getIdFilme()));
                        jTextdata_Alocacao.setText(alugado.getDataAlocacao());

                        jTextid_pessoa.setEnabled(true);
                        jTextid_filme.setEnabled(true);
                        jTextdata_Alocacao.setEnabled(true);

                        botaoCadastrar.setEnabled(true);
                        botaoAtualizar.setEnabled(true);

                        JOptionPane.showMessageDialog(janelaAlugado, "Propriedade encontrada");
                        botaoAtualizar.setEnabled(true);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        /**
         * Ação executada ao clicar no botão "Limpar".
         */
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTextid_alugado.setText(""); // Limpar campo
                jTextid_pessoa.setText(""); // Limpar campo
                jTextid_filme.setText(""); // Limpar campo
                jTextdata_Alocacao.setText("");

                jTextid_pessoa.requestFocus(); // Colocar o foco em um campo
            }
        });

        return janelaAlugado;
	}
}
