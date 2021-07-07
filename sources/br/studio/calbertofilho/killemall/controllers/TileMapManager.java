package br.studio.calbertofilho.killemall.controllers;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.studio.calbertofilho.killemall.objects.TileMap;
import br.studio.calbertofilho.killemall.objects.tiles.TilePath;
import br.studio.calbertofilho.killemall.objects.tiles.TileSolid;
import br.studio.calbertofilho.killemall.view.graphics.Sprite;

public class TileMapManager {

	private final int DEFAULT_BLOCK_SIZE = 64;
	private ArrayList<TileMap> tileMap;
	private String imagePath, layerName;
	private int width, height, tileWidth, tileHeight, tileCount, tileColumns, layers;
	private Sprite sprite;
	private String[] data;
	private DocumentBuilderFactory builderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private NodeList list;
	private Node node;
	private Element element;

	public TileMapManager() {
		tileMap = new ArrayList<TileMap>();
	}

	public TileMapManager(String path) {
		this();
		System.out.println("Loading: " + path + "...");
		addTileMap(path, DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE);
	}

	public void addTileMap(String path, int blockWidth, int blockHeight) {
		width = 0;
		height = 0;
		try {
			builderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = builderFactory.newDocumentBuilder();
			document = documentBuilder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
			document.getDocumentElement().normalize();
			list = document.getElementsByTagName("image");
			node = list.item(0);
			element = (Element) node;
			imagePath = element.getAttribute("source");
			list = document.getElementsByTagName("tileset");
			node = list.item(0);
			element = (Element) node;
			tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
			tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
			tileCount = Integer.parseInt(element.getAttribute("tilecount"));
			tileColumns = Integer.parseInt(element.getAttribute("columns"));
			sprite = new Sprite("assets\\tiles\\" + imagePath, tileWidth, tileHeight);
			list = document.getElementsByTagName("layer");
			layers = list.getLength();
			data = new String[layers];
			for (int i = 0; i < data.length; i++) {
				node = list.item(i);
				element = (Element) node;
				layerName = "Layer " + element.getAttribute("name");
				if (i <= 0) {
					width = Integer.parseInt(element.getAttribute("width"));
					height = Integer.parseInt(element.getAttribute("height"));
				}
				data[i] = element.getElementsByTagName("data").item(0).getTextContent();
				System.out.println("------ " + layerName + " ------\n" + data[i]);
				if (i >= 1)
					tileMap.add(new TilePath(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				else
					tileMap.add(new TileSolid(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
			}
		} catch (Exception e) {
			System.out.println("ERROR: Can not read tilemap");
			e.printStackTrace();
		}
	}

	public void render(Graphics2D graphics) {
		for (TileMap tile : tileMap)
			tile.render(graphics);
	}

}
