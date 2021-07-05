package br.studio.calbertofilho.killemall.objects.tiles;

import java.awt.Graphics2D;
import java.util.HashMap;

import br.studio.calbertofilho.killemall.containers.VectorPosition;
import br.studio.calbertofilho.killemall.objects.Block;
import br.studio.calbertofilho.killemall.objects.TileMap;
import br.studio.calbertofilho.killemall.objects.blocks.HoleBlock;
import br.studio.calbertofilho.killemall.objects.blocks.ObjectBlock;
import br.studio.calbertofilho.killemall.view.graphics.Sprite;

public class TileObject extends TileMap {

	private static HashMap<String, Block> objects;
	private String[] tmpBlock;
	private Block tempBlock;
	private int tmp;

	public TileObject(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
		objects = new HashMap<String, Block>();
		tmpBlock = data.split(",");
		for (int i = 0; i < (width * height); i++) {
			tmp = Integer.parseInt(tmpBlock[i].replaceAll("\\s+", ""));
			if (tmp != 0)
				if (tmp == 172)
					tempBlock = new HoleBlock(sprite.getSprite((int) ((tmp - 1) % tileColumns), (int) ((tmp - 1) / tileColumns)), new VectorPosition(((int) (i % width)), ((int) (i / height))), tileWidth, tileHeight);
				else
					tempBlock = new ObjectBlock(sprite.getSprite((int) ((tmp - 1) % tileColumns), (int) ((tmp - 1) / tileColumns)), new VectorPosition(((int) (i % width)), ((int) (i / height))), tileWidth, tileHeight);
			objects.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock);
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		for (Block block : objects.values())
			block.render(graphics);
	}

}
