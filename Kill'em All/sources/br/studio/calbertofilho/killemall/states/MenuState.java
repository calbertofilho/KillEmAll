package br.studio.calbertofilho.killemall.states;

import java.awt.Graphics2D;

import br.studio.calbertofilho.killemall.controllers.StateManager;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;

public class MenuState extends AbstractState {

	public MenuState(StateManager gameManager) {
		super(gameManager);
	}

	@Override
	public void input(MouseHandler mouse, KeyboardHandler keyboard) {}

	@Override
	public void update() {}

	@Override
	public void render(Graphics2D graphics) {}

}
