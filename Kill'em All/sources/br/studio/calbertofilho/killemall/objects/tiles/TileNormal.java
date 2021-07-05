package br.studio.calbertofilho.killemall.objects.tiles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import br.studio.calbertofilho.killemall.containers.TileMap;
import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.graphics.Sprite;
import br.studio.calbertofilho.killemall.tiles.blocks.NormalBlock;

public class TileNormal extends TileMap {

	private ArrayList<Block> blocks;
	private String[] tmpBlock;
	private int tmp;

	public TileNormal(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
		blocks = new ArrayList<Block>();
		tmpBlock = data.split(",");
		for (int i = 0; i < (width * height); i++) {
			tmp = Integer.parseInt(tmpBlock[i].replaceAll("\\s+", ""));
			if (tmp != 0)
				blocks.add(new NormalBlock(sprite.getSprite((int) ((tmp - 1) % tileColumns), (int) ((tmp - 1) / tileColumns)), new VectorPosition(((int) (i % width)), ((int) (i / height))), tileWidth, tileHeight));
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		for (Block block : blocks)
			block.render(graphics);
	}

}
