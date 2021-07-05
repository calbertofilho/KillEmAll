package br.studio.calbertofilho.killemall.graphics;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame, numFrames, count, delay, timesPlayed;

	public Animation(BufferedImage[] frames) {
		setFrames(frames);
	}

	public Animation() {
		timesPlayed = 0;
	}

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		count = 0;
		timesPlayed = 0;
		delay = 2;
		numFrames = frames.length;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public void setFrame(int frame) {
		currentFrame = frame;
	}

	public void setNumFrames(int num) {
		numFrames = num;
	}

	public void update() {
		if (delay == -1) 
			return;
		count++;
		if (count == delay) {
			currentFrame++;
			count = 0;
		}
		if (currentFrame == numFrames) {
			currentFrame = 0;
			timesPlayed++;
		}
	}

	public int getDelay() {
		return delay;
	}
	
	public int getFrame() {
		return currentFrame;
	}

	public int getCount() {
		return count;
	}

	public BufferedImage getImage() {
		return frames[currentFrame];
	}

	public boolean hasPlayed(int num) {
		return timesPlayed == num;
	}

	public boolean hasPlayedOnce() {
		return timesPlayed > 0;
	}

}
