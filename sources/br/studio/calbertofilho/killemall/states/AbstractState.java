package br.studio.calbertofilho.killemall.states;

import java.awt.Graphics2D;

import br.studio.calbertofilho.killemall.controllers.GameManager;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;

public abstract class AbstractState {

	protected GameManager gameManager;

	public AbstractState(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public abstract void input(MouseHandler mouse, KeyboardHandler keyboard);
	public abstract void update();
	public abstract void render(Graphics2D graphics);

}
