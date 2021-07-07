package br.studio.calbertofilho.killemall.view.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import br.studio.calbertofilho.killemall.containers.VectorPosition;

public class Sprite {

	private final static int DEFAULT_TILE_SIZE = 32;
	private BufferedImage SPRITESHEET = null;
	private BufferedImage[][] spriteArray;
	private BufferedImage sprite;
	private int width, height;
	private int widthSprite, heightSprite;
	private static float posX, posY;

	public Sprite(String file, int width, int height) {
		this.width = width;
		this.height = height;
		System.out.println("Loading: " + file + "...");
		SPRITESHEET = loadSprite(file);
		widthSprite = SPRITESHEET.getWidth() / width;
		heightSprite = SPRITESHEET.getHeight() / height;
		loadSpiteArray();
	}

	public Sprite(String file) {
		this(file, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE);
	}
	
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height) {
		this.height = height;
		heightSprite = SPRITESHEET.getHeight() / height;
	}
	
	public void setWidth(int width) {
		this.width = width;
		widthSprite = SPRITESHEET.getWidth() / width;
	}

	private BufferedImage loadSprite(String file) {
		sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		} catch (Exception e) {
			System.out.println("ERROR: Could not load file: " + file);
			e.printStackTrace();
		}
		return sprite;
	}

	private void loadSpiteArray() {
		spriteArray = new BufferedImage[heightSprite][widthSprite];
		for (int j = 0; j < heightSprite; j++)
			for (int i = 0; i < widthSprite; i++)
				spriteArray[j][i] = getSprite(i, j);
	}

	public BufferedImage getSpriteSheet() {
		return SPRITESHEET;
	}

	public BufferedImage getSprite(int i, int j) {
		return getSpriteSheet().getSubimage(i * width, j * height, width, height);
	}

	public BufferedImage[][] getSpriteArray() {
		return spriteArray;
	}

	public BufferedImage[] getSpriteArrayMotion(int i) {
		return spriteArray[i];
	}

	public static void drawArray(Graphics2D graphics, ArrayList<BufferedImage> images, VectorPosition vector, int width, int height, int xOffset, int yOffset) {
		posX = vector.x;
		posY = vector.y;
		for (BufferedImage image : images)
			graphics.drawImage(image, (int) posX, (int) posY, width, height, null);
		posX += xOffset;
		posY += yOffset;
	}

	public static void drawArray(Graphics2D graphics, Font font, String word, VectorPosition vector, int width, int height, int xOffset, int yOffset) {
		posX = vector.x;
		posY = vector.y;
		for (int i = 0; i < word.length(); i++) {
			if (!Character.isWhitespace(word.charAt(i)))
				graphics.drawImage(font.getFont(word.charAt(i)), (int) posX, (int) posY, width, height, null);
			posX += xOffset;
			posY += yOffset;
		}
	}

}
