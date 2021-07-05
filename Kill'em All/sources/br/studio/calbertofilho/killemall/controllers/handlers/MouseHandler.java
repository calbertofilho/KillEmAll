package br.studio.calbertofilho.killemall.controllers.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import br.studio.calbertofilho.killemall.view.Panel;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private static int mousePosX, mousePosY, mouseButton;

	public MouseHandler(Panel game) {
		mousePosX = -1;
		mousePosY = -1;
		mouseButton = -1;
		game.addMouseListener(this);
	}

	public static int getPosX() {
		return mousePosX;
	}

	public static int getPosY() {
		return mousePosY;
	}

	public int getButton() {
		return mouseButton;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePosX = e.getX();
		mousePosY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosX = e.getX();
		mousePosY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseButton = -1;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
