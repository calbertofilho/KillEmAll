package br.studio.calbertofilho.killemall.view;

import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import br.studio.calbertofilho.killemall.Panel;

/**
 * Classe responsável pela criação e manipulação da janela do jogo
 * 
 * @since 01/07/2021
 * @version 1.0
 * @author Carlos Alberto Morais Moura Filho<br>
 * Faça sua doação: <a href="https://nubank.com.br/pagar/5wv6g/ZdcePGcCDT">Pix</a>
 */@SuppressWarnings("serial")
public class Window extends JFrame {

	private BufferStrategy buffer;
	private Panel game;

	public Window() {
		setTitle("Kill'em All v1.0");						// Configura o título
		setIconImage(new ImageIcon("").getImage());			// Configura o ícone
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Define o que acontece ao clicar no botão 'Fechar' da janela
		setMinimumSize(getContentPane().getPreferredSize());// Configura o tamanho mínimo que a janela poderá ter
		setMaximumSize(getContentPane().getPreferredSize());// Configura o tamanho máximo que a janela poderá ter
		pack();												// Atualiza as operações realizadas na janela
		setResizable(false);								// Configura o botão de 'Maximizar' e a operação de redimensionar nas bordas da janela
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
