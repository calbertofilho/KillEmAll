package br.studio.calbertofilho.killemall.containers;

import br.studio.calbertofilho.killemall.objects.Character;
import br.studio.calbertofilho.killemall.objects.tiles.TileSolid;

public class AABB { //Axis Aligned Bounding Boxes

	private VectorPosition position;
	private float width, height, radius, xOffset, yOffset, ax, ay, bx, by, cx, cy, xDelta, yDelta;
	private int size, xt, yt;
	private Character actor;
	private String index;

	public AABB(VectorPosition position, int width, int height) {
		this();
		setBox(position, width, height);
	}

	public AABB(VectorPosition position, int radius, Character actor) {
		this();
		setCircle(position, radius, actor);
	}

	public AABB() {
		xOffset = 0;
		yOffset = 0;
	}

	public VectorPosition getPosition() {
		return position;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setXOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getXOffset() {
		return xOffset;
	}

	public void setYOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getYOffset() {
		return yOffset;
	}

	public int getSize() {
		return size;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRadius() {
		return radius;
	}

	public void setBox(VectorPosition position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.size = Math.max(width, height);
	}

	private void setCircle(VectorPosition position, int radius, Character actor) {
		this.position = position;
		this.radius = radius;
		this.actor = actor;
		this.size = radius;
	}

	public boolean collides(AABB boundingBox) {
		ax = ((position.x + xOffset) + (this.width / 2));
		ay = ((position.y + yOffset) + (this.height / 2));
		bx = ((boundingBox.getPosition().x + boundingBox.getXOffset()) + (boundingBox.width / 2));
		by = ((boundingBox.getPosition().y + boundingBox.getYOffset()) + (boundingBox.height / 2));
		if (Math.abs(ax - bx) < ((this.width / 2) + (boundingBox.getWidth() / 2)))
			if (Math.abs(ay - by) < ((this.height / 2) + (boundingBox.getHeight() / 2)))
				return true;
		return false;
	}

	public boolean collidesCircleBox(AABB boundingBox) {
		cx = (float) (position.getWorldVar().x + radius / Math.sqrt(2) - actor.getSize() / Math.sqrt(2));
		cy = (float) (position.getWorldVar().y + radius / Math.sqrt(2) - actor.getSize() / Math.sqrt(2));
		xDelta = cx - Math.max(boundingBox.position.getWorldVar().x + (boundingBox.getWidth() / 2), Math.min(cx, boundingBox.position.getWorldVar().x));
		yDelta = cy - Math.max(boundingBox.position.getWorldVar().y + (boundingBox.getHeight() / 2), Math.min(cy, boundingBox.position.getWorldVar().y));
		if ((xDelta * xDelta + yDelta * yDelta) < ((this.radius / Math.sqrt(2)) * (this.radius / Math.sqrt(2))))
			return true;
		return false;
	}

	public boolean collidesTile(float ax, float ay) {
		for (int i = 0; i < 4; i++) {
			xt = (int) ((position.x + ax) + (i % 2) * width + xOffset) / 64;
			yt = (int) ((position.y + ay) + ((int) (i / 2)) * height + yOffset) / 64;
			index = String.valueOf(xt) + "," + String.valueOf(yt);
			if (TileSolid.getObjects().containsKey(index)) {
				return TileSolid.getObjects().get(index).update(this);
			}
		}
		return false;
	}

}
