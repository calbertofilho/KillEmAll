package br.studio.calbertofilho.killemall.objects.entities.characters;

import java.awt.Graphics2D;

import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;
import br.studio.calbertofilho.killemall.objects.entities.Character;
import br.studio.calbertofilho.killemall.view.graphics.Sprite;

public class NonPlayer extends Character {

	public NonPlayer(Sprite sprite, VectorPosition origin, int size) {
		super(sprite, origin, size);
	}

	@Override
	public void input(MouseHandler mouse, KeyboardHandler keyboard) {}

	@Override
	public void render(Graphics2D graphics) {}

}
