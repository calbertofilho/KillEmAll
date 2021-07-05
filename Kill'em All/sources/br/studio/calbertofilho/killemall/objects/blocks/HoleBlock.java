package br.studio.calbertofilho.killemall.objects.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.studio.calbertofilho.killemall.containers.AABB;
import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.objects.Block;

public class HoleBlock extends Block {

	public HoleBlock(BufferedImage image, VectorPosition position, int width, int height) {
		super(image, position, width, height);
	}

	@Override
	public boolean update(AABB p) {
		if (isInside(p))
			System.out.println("I'm in a hole...");
		return false;
	}

	private boolean isInside(AABB p) {
		if (p.getPosition().x + p.getXOffset() < position.x)
			return false;
		if (p.getPosition().y + p.getYOffset() < position.y)
			return false;
		if (width + position.x < p.getWidth() + (p.getPosition().x + p.getXOffset()))
			return false;
		if (height + position.y < p.getHeight() + (p.getPosition().y + p.getYOffset()))
			return false;
		return true;
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		graphics.setColor(Color.GREEN);
		graphics.drawRect((int) position.getWorldVar().x, (int) position.getWorldVar().y, width, height);
	}

}
