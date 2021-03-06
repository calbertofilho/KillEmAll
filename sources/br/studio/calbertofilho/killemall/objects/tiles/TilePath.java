package br.studio.calbertofilho.killemall.objects.tiles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.objects.Block;
import br.studio.calbertofilho.killemall.objects.TileMap;
import br.studio.calbertofilho.killemall.objects.blocks.PathBlock;
import br.studio.calbertofilho.killemall.view.graphics.Sprite;

public class TilePath extends TileMap {

	private ArrayList<Block> blocks;
	private String[] tmpBlock;
	private int tmp;

	public TilePath(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
		blocks = new ArrayList<Block>();
		tmpBlock = data.split(",");
		for (int i = 0; i < (width * height); i++) {
			tmp = Integer.parseInt(tmpBlock[i].replaceAll("\\s+", ""));
			if (tmp != 0)
				blocks.add(new PathBlock(sprite.getSprite((int) ((tmp - 1) % tileColumns), (int) ((tmp - 1) / tileColumns)), new VectorPosition(((int) (i % width) * tileWidth), ((int) (i / height) * tileHeight)), tileWidth, tileHeight));
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		for (Block block : blocks)
			block.render(graphics);
	}

}
