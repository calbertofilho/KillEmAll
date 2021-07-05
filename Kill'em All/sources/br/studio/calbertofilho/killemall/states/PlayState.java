package br.studio.calbertofilho.killemall.states;

import java.awt.Graphics2D;

import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.controllers.GameManager;
import br.studio.calbertofilho.killemall.controllers.TileMapManager;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;
import br.studio.calbertofilho.killemall.objects.characters.Player;
import br.studio.calbertofilho.killemall.view.Panel;
import br.studio.calbertofilho.killemall.view.graphics.Font;
import br.studio.calbertofilho.killemall.view.graphics.Sprite;

public class PlayState extends AbstractState {

	private Font font00, font01, font02, font03, font04, font05, font06, font07, font08, font09, font10, font11, font12, font13, font14;
	private Player player;
	private String fps, text00, text01;
	private int score, fontSize, offSet;
	private TileMapManager tileManager;

	public PlayState(GameManager gameManager) {
		super(gameManager);
		score = 0;
		fontSize = 20;
		offSet = 20;
		tileManager = new TileMapManager("assets\\tiles\\tilemap.tmx");
		font00 = new Font("assets\\fonts\\font.png", 10, 10);
		font01 = new Font("assets\\fonts\\fontBattleCircuit.png", 40, 40);
		font02 = new Font("assets\\fonts\\fontDragonBreed.png", 40, 40);
		font03 = new Font("assets\\fonts\\fontHatTrickHero95.png", 40, 40);
		font04 = new Font("assets\\fonts\\fontMajorTitle.png", 40, 40);
		font05 = new Font("assets\\fonts\\fontNinjaGaiden.png", 40, 40);
		font06 = new Font("assets\\fonts\\fontOutfoxies.png", 40, 40);
		font07 = new Font("assets\\fonts\\fontPachinkoSexyReaction2.png", 40, 40);
		font08 = new Font("assets\\fonts\\fontPanicBomber.png", 40, 40);
		font09 = new Font("assets\\fonts\\fontRapidHero.png", 40, 40);
		font10 = new Font("assets\\fonts\\fontRayForce.png", 40, 40);
		font11 = new Font("assets\\fonts\\fontRobotron.png", 40, 40);
		font12 = new Font("assets\\fonts\\fontRType.png", 40, 40);
		font13 = new Font("assets\\fonts\\fontStreetFighterII.png", 40, 40);
		font14 = new Font("assets\\fonts\\fontSuperMarioBros3.png", 40, 40);
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
		score++;
		if (score > 999999999)
			score = 0;
		fps = Panel.getFPS() + " FPS ";
		text00 = "The quick brown fox jumps over the lazy dog";
		text01 = "A rapida raposa marrom pula sobre o cao preguicoso";
		tileManager.render(graphics);
		Sprite.drawArray(graphics, font05, fps,  new VectorPosition(GameManager.getGameWidth() - (fps.length() * fontSize), 10), fontSize, fontSize, offSet, 0);
		Sprite.drawArray(graphics, font01, text00, new VectorPosition(10, 10), fontSize, fontSize, offSet, 0);
		Sprite.drawArray(graphics, font07, text01, new VectorPosition(10, 10 + fontSize + 5), fontSize, fontSize, offSet, 0);
		Sprite.drawArray(graphics, font00, String.valueOf(score), new VectorPosition(GameManager.getGameWidth() - (String.valueOf(score).length() * fontSize), GameManager.getGameHeight() - fontSize - 8), fontSize, fontSize, 15, 0);
		player.render(graphics);
	}

}
