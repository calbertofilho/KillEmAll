package br.studio.calbertofilho.killemall.objects.characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;
import br.studio.calbertofilho.killemall.objects.Character;
import br.studio.calbertofilho.killemall.states.PlayState;
import br.studio.calbertofilho.killemall.view.graphics.Sprite;

public class Player extends Character {

	public Player(Sprite sprite, VectorPosition origin, int size) {
		super(sprite, origin, size);
		setMaxSpeed(3f);
		setAcc(2f);
		setDeacc(0.3f);
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);
	}

	@Override
	public void input(MouseHandler mouse, KeyboardHandler keyboard) {
		if (mouse.getButton() == MouseEvent.BUTTON1)
			System.out.println("Player position (x, y): " + position.toString());
		up = keyboard.upKey.isDown();
		down = keyboard.downKey.isDown();
		right = keyboard.rightKey.isDown();
		left = keyboard.leftKey.isDown();
		attack = keyboard.attackKey.isDown();
	}

	@Override
	public void update() {
		super.update();
		move();
		if (!bounds.collidesTile(dx, 0)) {
			PlayState.addMapPosX(dx);
			position.addX(dx);
		}
		if (!bounds.collidesTile(0, dy)) {
			PlayState.addMapPosY(dy);
			position.addY(dy);
		}
	}

	protected void move() {
		if (up) {
			dy -= acc;
			if (dy < -maxSpeed)
				dy = -maxSpeed;
		} else
			if (dy < 0) {
				dy += deacc;
				if (dy > 0)
					dy = 0;
			}
		if (down) {
			dy += acc;
			if (dy > maxSpeed)
				dy = maxSpeed;
		} else
			if (dy > 0) {
				dy -= deacc;
				if (dy < 0)
					dy = 0;
			}
		if (left) {
			dx -= acc;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
		} else
			if (dx < 0) {
				dx += deacc;
				if (dx > 0)
					dx = 0;
			}
		if (right) {
			dx += acc;
			if (dx > maxSpeed)
				dx = maxSpeed;
		} else
			if (dx > 0) {
				dx -= deacc;
				if (dx < 0)
					dx = 0;
			}
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(((int) (position.getWorldVar().x + bounds.getXOffset())), ((int) (position.getWorldVar().y + bounds.getYOffset())), (int) bounds.getWidth(), (int) bounds.getHeight());
		graphics.drawImage(animation.getImage(), (int) (position.getWorldVar().x), (int) (position.getWorldVar().y), size, size, null);
	}

}
