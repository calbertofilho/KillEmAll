package br.studio.calbertofilho.killemall.objects.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.studio.calbertofilho.killemall.containers.AABB;
import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.objects.Block;

public class NormalBlock extends Block {

	public NormalBlock(BufferedImage image, VectorPosition position, int width, int height) {
		super(image, position, width, height);
	}

	@Override
	public boolean update(AABB p) {
		return false;
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
	}

}
