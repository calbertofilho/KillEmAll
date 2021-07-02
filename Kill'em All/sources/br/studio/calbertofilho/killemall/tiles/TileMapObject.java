package br.studio.calbertofilho.killemall.tiles;

import java.awt.Graphics2D;
import java.util.HashMap;

import br.studio.calbertofilho.killemall.containers.TileMap;
import br.studio.calbertofilho.killemall.graphics.Sprite;

public class TileMapObject extends TileMap {

	private static HashMap<String, Block> objects;
	private String[] tmpBlock;
	private Block tempBlock;
	private int tmp;

	public TileMapObject(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
		objects = new HashMap<String, Block>();
		tmpBlock = data.split(",");
		for (int i = 0; i < (width * height); i++) {
			tmp = Integer.parseInt(tmpBlock[i].replaceAll("\\s+", ""));
			if (tmp != 0) {
				if (tmp == 172)
					tempBlock = new HoleBlock();
				else
					tempBlock = new ObjectBlock();
			}
			objects.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock);
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		for (Block block : objects.value())
			block.render(graphics);
	}

}
