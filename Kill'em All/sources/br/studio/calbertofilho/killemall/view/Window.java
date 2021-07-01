package br.studio.calbertofilho.killemall.view;

import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import br.studio.calbertofilho.killemall.Panel;

@SuppressWarnings("serial")
public class Window extends JFrame {

	private BufferStrategy buffer;
	private Panel game;

	public Window() {
		setTitle("Kill'em All v1.0");						// Configura o t�tulo
		setIconImage(new ImageIcon("").getImage());			// Configura o �cone
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Define o que acontece ao clicar no bot�o 'Fechar' da janela
		setMinimumSize(getContentPane().getPreferredSize());// Configura o tamanho m�nimo que a janela poder� ter
		setMaximumSize(getContentPane().getPreferredSize());// Configura o tamanho m�ximo que a janela poder� ter
		pack();												// Atualiza as opera��es realizadas na janela
		setResizable(false);								// Configura o bot�o de 'Maximizar' e a opera��o de redimensionar nas bordas da janela
		setLocationRelativeTo(null);						// Configura a janela para abrir centralizada na tela
		setVisible(true);									// Mostra a janela
	}

	@Override
	public void addNotify() {
		super.addNotify();
		createBufferStrategy(4);
		buffer = getBufferStrategy();
		game = new Panel(buffer, 1280, 720);
		setContentPane(game);
	}

}
