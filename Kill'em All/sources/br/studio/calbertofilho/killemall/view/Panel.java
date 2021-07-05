package br.studio.calbertofilho.killemall.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import br.studio.calbertofilho.killemall.controllers.GameManager;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;

/**
 * Classe responsável pela tela de exibição e o controle de fluxo da execução do jogo
 * 
 * @since 01/07/2021
 * @version 1.0
 * @author Carlos Alberto Morais Moura Filho<br>
 * Faça sua doação: <a href="https://nubank.com.br/pagar/5wv6g/ZdcePGcCDT">Pix</a>
 */@SuppressWarnings("serial")
public class Panel extends JPanel implements Runnable {

	private final int BN = 1000000000;					// Um bilhão = 1 x 10^9 (BN)
	private final double GF = 60.0;						// Frequência do jogo (GAME_FREQUENCY)
	private final double TBU = BN / GF;					// Tempo antes da atualização (TIME_BEFORE_UPDATE)
	private final int MUBR = 5;							// Quantas vezes deve atualizar antes da renderização (MUST_UPDATE_BEFORE_RENDER)
	private final double TF = 60.0;						// Frequência desejada para o jogo (TARGET_FPS)
	private final double TTBR = BN / TF;				// Tempo total antes da renderização (TOTAL_TIME_BEFORE_RENDER)
	private double lastUpdateTime, lastRenderTime, now;	// Variáveis de controle
	private static int oldFrameCount;
	private int frameCount, lastSecondTime, updateCount, thisSecond;
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private BufferStrategy buffer;
	private Graphics graphics; 
	private Graphics2D graphics2D;
	private MouseHandler mouse;
	private KeyboardHandler keyboard;
	private GameManager gameManager;

	public Panel(BufferStrategy buffer, int width, int height) {
		this.buffer = buffer;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
		setDoubleBuffered(true);
		running = false;
	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this, "GameThread");
			thread.start();
		}
	}

	public void initGraphics() {
		image = new BufferedImage((int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
		graphics2D = (Graphics2D) image.getGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics = (Graphics) buffer.getDrawGraphics();
	}

	public void init() {
		running = true;
		initGraphics();
		mouse = new MouseHandler(this);
		keyboard = new KeyboardHandler(this);
		gameManager = new GameManager(this);
	}

	@Override
	public void run() {									// Método herdado que é executado ininterruptamente pela thread
		init();
		lastUpdateTime = System.nanoTime();
		frameCount = 0;
		oldFrameCount = 0;
		lastSecondTime = (int) (lastUpdateTime / BN);
		while (running) {
			now = System.nanoTime();
			updateCount = 0;
			while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
				update();
				input(mouse, keyboard);
				lastUpdateTime += TBU;
				updateCount++;
			}
			if ((now - lastUpdateTime) > TBU)
				lastUpdateTime = now - TBU;
			input(mouse, keyboard);
			render();
			draw();
			lastRenderTime = now;
			frameCount++;
			thisSecond = (int) (lastUpdateTime / BN);
			if (thisSecond > lastSecondTime) {
				if (frameCount != oldFrameCount) {
//					System.out.println("NEW SECOND: " + thisSecond + " - FPS: " + frameCount);
					oldFrameCount = frameCount;
				}
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			while (((now - lastRenderTime) < TTBR) && ((now - lastUpdateTime) < TBU)) {
				Thread.yield();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("ERROR: Yielding thread");
					e.printStackTrace();
				}
				now = System.nanoTime();
			}
		}
	}

	private void input(MouseHandler mouse, KeyboardHandler keyboard) {
		gameManager.input(mouse, keyboard);
	}

	private void update() {
		gameManager.update();
	}

	private void render() {
		if (graphics2D != null) {
			graphics2D.setColor(new Color(66, 134, 244));
			graphics2D.fillRect(0, 0, (int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight());
			gameManager.render(graphics2D);
		}
	}

	private void draw() {
		do {
			graphics.drawImage(image, 8, 31, (int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight(), null);
//			graphics.dispose();
			buffer.show();
		} while (buffer.contentsLost());
	}

	public static int getFPS() {
		return oldFrameCount;
	}

}
