package br.studio.calbertofilho.killemall.objects.tiles.blocks;

import java.awt.image.BufferedImage;

import br.studio.calbertofilho.killemall.containers.AABB;
import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.objects.tiles.Block;

public class NormalBlock extends Block {

	public NormalBlock(BufferedImage image, VectorPosition position, int width, int height) {
		super(image, position, width, height);
	}

	@Override
	public boolean update(AABB p) {
		return false;
	}

}
