package br.studio.calbertofilho.killemall.containers;

public class VectorPosition {

	public float x, y;
	public static float worldX, worldY;

	public VectorPosition() {
		this(0, 0);
	}
	
	public VectorPosition(VectorPosition vector) {
		this(vector.x, vector.y);
	}
	
	public VectorPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void addX(float i) {
		x += i;
	}

	public void addY(float i) {
		y += i;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setVector(VectorPosition vector) {
		this.x = vector.x;
		this.y = vector.y;
	}

	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static void setWorldVar(float x, float y) {
		worldX = x;
		worldY = y;
	}

	public VectorPosition getWorldVar() {
		return new VectorPosition(x - worldX, y - worldY);
	}

	@Override
	public String toString() {
		return x + ", " + y;
	}

}
