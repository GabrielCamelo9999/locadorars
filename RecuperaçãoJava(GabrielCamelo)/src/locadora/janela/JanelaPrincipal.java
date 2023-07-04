package locadora.janela;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

/**
 * Classe que representa a janela principal do programa.
 * @author Gabriel
 */
public class JanelaPrincipal {
		public static void apresentarMenu() {
			// Define a janela
			JFrame janelaPrincipal = new JFrame("Janela de opção"); // Janela Normal
			janelaPrincipal.setTitle("Janela de opção");
			janelaPrincipal.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
			janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			janelaPrincipal.setSize(800, 600); // Define tamanho da janela
			
			//Cria uma barra de menu para janale principal
			JMenuBar menuBar = new JMenuBar();
			
			//Adiciona a barra de menu ao frame
			janelaPrincipal.setJMenuBar(menuBar);
			
			//Define e adiciona menu na barra de menu
			JMenu menuFilme = new JMenu("Filme");
			JMenu menuPessoa = new JMenu("Cliente");
			JMenu menuAlugado = new JMenu("Alugado");
			
			menuBar.add(menuFilme);
			menuBar.add(menuPessoa);
			menuBar.add(menuAlugado);
			
			JMenuItem mFilme = new JMenuItem("Filme ");
			JMenuItem mPessoa = new JMenuItem("Pessoa ");
			JMenuItem mAlugado = new JMenuItem("Alugado ");
			
			menuFilme.add(mFilme);
			menuPessoa.add(mPessoa);
			menuAlugado.add(mAlugado);
			
			JFrame janelaCatalago = JanelaFilme.CriarJanelaFilme();
			//JFrame janelaCleintes = JanelaCliente;
			JFrame janelalocacao = JanelaAlugado.CriarJanelaAlugado();
			
			mFilme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					janelaCatalago.setVisible(true);
				}
			});
			mPessoa.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			mAlugado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					janelalocacao.setVisible(true);
				}
			});
			janelaPrincipal.setVisible(true);
		}
		/**
		 * Método principal que inicia a aplicação.
		 *
		 * @param args Argumentos da linha de comando.
		*/	
	public static void main(String[] args) {
		apresentarMenu();
	}
	
}
