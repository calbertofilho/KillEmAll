package br.studio.calbertofilho.killemall.states;

import java.awt.Graphics2D;

import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.controllers.StateManager;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;
import br.studio.calbertofilho.killemall.entities.objects.Player;
import br.studio.calbertofilho.killemall.graphics.Font;
import br.studio.calbertofilho.killemall.graphics.Sprite;

public class PlayState extends AbstractState {

	private Font font, text, score, points;
	private Player player;

	public PlayState(StateManager gameManager) {
		super(gameManager);
		font = new Font("assets\\fonts\\ArcadeFont1.png", 16, 16);
		text = new Font("assets\\fonts\\ArcadeFont3.png", 32, 32);
		score = new Font("assets\\fonts\\ArcadeFont2.png", 24, 24);
		points = new Font("assets\\fonts\\ArcadeFont4.png", 32, 32);
		player = new Player(new Sprite("assets\\images\\players\\human.png", 64, 64), new VectorPosition(100, 100), 64);
//		player = new Player(new Sprite("assets\\images\\players\\golem.png", 64, 64), new VectorPosition(100, 100), 64);
//		player = new Player(new Sprite("assets\\images\\players\\sorcerer.png", 32, 32), new VectorPosition(100, 100), 64);
	}

	@Override
	public void input(MouseHandler mouse, KeyboardHandler keyboard) {
		player.input(mouse, keyboard);
	}

	@Override
	public void update() {
		player.update();
	}

	@Override
	public void render(Graphics2D graphics) {
		Sprite.drawArray(graphics, font, "The quick brown fox jumps over the lazy dog", new VectorPosition(50, 30), 16, 16, 16, 0);
		Sprite.drawArray(graphics, text, "A rapida raposa marrom pula sobre o cao preguicoso", new VectorPosition(50, 50), 16, 16, 16, 0);
		Sprite.drawArray(graphics, score, "PONTOS", new VectorPosition(50, 70), 16, 16, 16, 0);
		Sprite.drawArray(graphics, points, "0000", new VectorPosition(160, 66), 20, 20, 20, 0);
		player.render(graphics);
	}

}
