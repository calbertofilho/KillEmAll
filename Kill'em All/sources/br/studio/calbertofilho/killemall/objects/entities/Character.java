package br.studio.calbertofilho.killemall.objects.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.studio.calbertofilho.killemall.containers.AABB;
import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.controllers.handlers.KeyboardHandler;
import br.studio.calbertofilho.killemall.controllers.handlers.MouseHandler;
import br.studio.calbertofilho.killemall.graphics.Animation;
import br.studio.calbertofilho.killemall.graphics.Sprite;

public abstract class Character {

	private int RIGHT = 0;
	private int LEFT = 1;
	private int DOWN = 2;
    private int UP = 3;
//    private int FALLEN = 4;
//    private int ATTACK = 5;
//    private int IDLE = 6;
	protected Sprite sprite;
	protected VectorPosition position;
	protected int size;
	protected Animation animation;
	protected int currentMotion;
	protected boolean up, down, right, left, attack;
	protected int attackSpeed, attackDuration;
	protected float dx, dy, maxSpeed, acc, deacc;
	protected AABB hitBounds, bounds;

	public Character(Sprite sprite, VectorPosition origin, int size) {
		this.sprite = sprite;
		position = origin;
		this.size = size;
		bounds = new AABB(origin, size, size);
		hitBounds = new AABB(new VectorPosition(origin.x + (size / 2), origin.y), size, size);
		animation = new Animation();
		setAnimation(RIGHT, sprite.getSpriteArrayMotion(RIGHT), 10);
	}

	private void setAnimation(int motion, BufferedImage[] frames, int delay) {
		currentMotion = motion;
		animation.setFrames(frames);
		animation.setDelay(delay);
	}

	public Animation getAnimation() {
		return animation;
	}

	private void animate() {
		if (up) {
			if ((currentMotion != UP) || (animation.getDelay() == -1)) {
				setAnimation(UP, sprite.getSpriteArrayMotion(UP), 5);
			}
		}
		else if (down) {
			if ((currentMotion != DOWN) || (animation.getDelay() == -1)) {
				setAnimation(DOWN, sprite.getSpriteArrayMotion(DOWN), 5);
			}
		}
		else if (right) {
			if ((currentMotion != RIGHT) || (animation.getDelay() == -1)) {
				setAnimation(RIGHT, sprite.getSpriteArrayMotion(RIGHT), 5);
			}
		}
		else if (left) {
			if ((currentMotion != LEFT) || (animation.getDelay() == -1)) {
				setAnimation(LEFT, sprite.getSpriteArrayMotion(LEFT), 5);
			}
		}
		else {
			setAnimation(currentMotion, sprite.getSpriteArrayMotion(currentMotion), -1);
		}
	}

	private void setHitBoxDirection() {
		if (up) {
			hitBounds.setYOffset(-size / 2);
			hitBounds.setXOffset(-size / 2);
		} else if (down) {
			hitBounds.setYOffset(size / 2);
			hitBounds.setXOffset(-size / 2);
		} else if (right) {
			hitBounds.setXOffset(0);
			hitBounds.setYOffset(0);
		} else if (left) {
			hitBounds.setXOffset(-size);
			hitBounds.setYOffset(0);
		}
	}

	public void update() {
		animate();
		setHitBoxDirection();
		animation.update();
	}

	public abstract void input(MouseHandler mouse, KeyboardHandler keyboard);

	public abstract void render(Graphics2D graphics);

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setAcc(float acc) {
		this.acc = acc;
	}

	public void setDeacc(float deacc) {
		this.deacc = deacc;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public AABB getBounds() {
		return bounds;
	}

}
