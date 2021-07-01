package br.studio.calbertofilho.killemall.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Font {

	private final static int TILE_SIZE = 32;
	private BufferedImage FONTSHEET = null;
	private BufferedImage[][] fontArray;
	private BufferedImage font;
	private int width, height;
	private int widthCharacter, heightCharacter;
	private int fontX, fontY, characterValue;

	public Font(String file, int width, int height) {
		this.width = width;
		this.height = height;
		System.out.println("Loading: " + file + "...");
		FONTSHEET = loadFont(file);
		widthCharacter = FONTSHEET.getWidth() / width;
		heightCharacter = FONTSHEET.getHeight() / height;
		loadFontArray();
	}

	public Font(String file) {
		this(file, TILE_SIZE, TILE_SIZE);
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
		heightCharacter = FONTSHEET.getHeight() / height;
	}
	
	public void setWidth(int width) {
		this.width = width;
		widthCharacter = FONTSHEET.getWidth() / width;
	}

	private BufferedImage loadFont(String file) {
		font = null;
		try {
			font = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		} catch (Exception e) {
			System.out.println("ERROR: Could not load file: " + file);
			e.printStackTrace();
		}
		return font;
	}

	private void loadFontArray() {
		fontArray = new BufferedImage[widthCharacter][heightCharacter];
		for (int i = 0; i < widthCharacter; i++) {
			for (int j = 0; j < heightCharacter; j++) {
				fontArray[i][j] = getCharacter(i, j);
			}
		}
	}

	public BufferedImage getFontSheet() {
		return FONTSHEET;
	}

	public BufferedImage[][] getFontArray(int i) {
		return fontArray;
	}

	public BufferedImage[] getFontArrayLine(int i) {
		return fontArray[i];
	}

	private BufferedImage getCharacter(int i, int j) {
		return getFontSheet().getSubimage(i * width, j * height, width, height);
	}
	
	public Image getFont(char character) {
		characterValue = character - (int) '!';
		fontX = characterValue % widthCharacter;
		fontY = characterValue / widthCharacter;
		return getCharacter(fontX, fontY);
	}
	
}
