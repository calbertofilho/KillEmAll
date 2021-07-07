package br.studio.calbertofilho.killemall.controllers;

import java.awt.Graphics2D;
import java.util.ArrayList;

import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;
import br.studio.calbertofilho.killemall.states.AbstractState;
import br.studio.calbertofilho.killemall.states.GameOverState;
import br.studio.calbertofilho.killemall.states.MenuState;
import br.studio.calbertofilho.killemall.states.PauseState;
import br.studio.calbertofilho.killemall.states.PlayState;
import br.studio.calbertofilho.killemall.view.Panel;

public class GameManager {

	private ArrayList<AbstractState> states;
	private static VectorPosition map;
	private static Panel game;
	private static final int PLAY = 0;
	private static final int MENU = 1;
	private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;

	public GameManager(Panel game) {
		GameManager.game = game;
		map = new VectorPosition((float) game.getWidth(), (float) game.getHeight());
		VectorPosition.setWorldVar(map.x, map.y);
		states = new ArrayList<AbstractState>();
		add(PLAY);
	}

	public void pop(int state) {
		states.remove(state);
	}

	public void add(int state) {
		if (state == PLAY)
			states.add(new PlayState(this));
		if (state == MENU)
			states.add(new MenuState(this));
		if (state == PAUSE)
			states.add(new PauseState(this));
		if (state == GAME_OVER)
			states.add(new GameOverState(this));
	}

	public void addAndPop(int state) {
		states.remove(0);
		add(state);
	}

	public void input(MouseHandler mouse, KeyboardHandler keyboard) {
		for (AbstractState state : states)
			state.input(mouse, keyboard);
	}

	public void update() {
		VectorPosition.setWorldVar(map.x, map.y);
		for (AbstractState state : states)
			state.update();
	}

	public void render(Graphics2D graphics) {
		for (AbstractState state : states)
			state.render(graphics);
	}

	public static int getGameWidth() {
		return game.getWidth();
	}

	public static int getGameHeight() {
		return game.getHeight();
	}

}
