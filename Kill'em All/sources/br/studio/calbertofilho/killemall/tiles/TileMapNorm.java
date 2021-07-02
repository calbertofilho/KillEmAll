package br.studio.calbertofilho.killemall.tiles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import br.studio.calbertofilho.killemall.containers.TileMap;
import br.studio.calbertofilho.killemall.graphics.Sprite;

public class TileMapNorm extends TileMap {

	private ArrayList<Block> blocks;
	private String[] tmpBlock;
	private int tmp;

	public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
		blocks = new ArrayList<Blocks>();
		tmpBlock = data.split(",");
		for (int i = 0; i < (width * height); i++) {
			tmp = Integer.parseInt(tmpBlock[i].replaceAll("\\s+", ""));
			if (tmp != 0)
				blocks.add(new NormBlock());
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		for (Block block : blocks)
			block.render(graphics);
	}

}
