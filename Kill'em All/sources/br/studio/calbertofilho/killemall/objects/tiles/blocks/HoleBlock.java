package br.studio.calbertofilho.killemall.objects.tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.studio.calbertofilho.killemall.containers.AABB;
import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.objects.tiles.Block;

public class HoleBlock extends Block {

	public HoleBlock(BufferedImage image, VectorPosition position, int width, int height) {
		super(image, position, width, height);
	}

	@Override
	public boolean update(AABB p) {
		return false;
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		graphics.setColor(Color.GREEN);
		graphics.drawRect((int) position.getWorldVar().x, (int) position.getWorldVar().y, width, height);
	}

}
