package br.studio.calbertofilho.killemall.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.studio.calbertofilho.killemall.containers.AABB;
import br.studio.calbertofilho.killemall.containers.VectorPosition;

public abstract class Block {

	protected int width, height;
	protected BufferedImage image;
	protected VectorPosition position;

	public Block(BufferedImage image, VectorPosition position, int width, int height) {
		this.image = image;
		this.position = position;
		this.width = width;
		this.height = height;
	}

	public abstract boolean update(AABB p);

	public void render(Graphics2D graphics) {
		graphics.drawImage(image, (int) position.getWorldVar().x, (int) position.getWorldVar().y, width, height, null);
//		System.out.println(getClass().getSimpleName());
	}

}
