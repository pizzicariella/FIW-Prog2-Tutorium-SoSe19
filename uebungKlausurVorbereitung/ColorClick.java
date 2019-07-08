package uebungKlausurVorbereitung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorClick extends JFrame implements ComponentListener{
	
	Color[][] colorMatrix;
	Canvas canvas;
	JLabel info;
	private final String startText = "Click to change color!";
	private int rectWidth;
	private int rectHeight;
	
	public ColorClick(int rows, int cols) {
		super("Color Click");
		this.colorMatrix = new Color[rows][cols];
		this.fillWithRandomColors();
		this.setSize(600, 590);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.canvas = new Canvas();
		this.canvas.addMouseListener(canvas);
		this.add(canvas, BorderLayout.CENTER);
		this.info = new JLabel(this.startText);
		this.add(info, BorderLayout.SOUTH);	
		this.addComponentListener(this);
		this.setVisible(true);
	}
	
	private void fillWithRandomColors() {
		for(int r = 0; r < this.colorMatrix.length; r++) {
			for(int c = 0; c < this.colorMatrix[r].length; c++) {
				this.colorMatrix[r][c] = this.getRandomColor();
			}
		}
	}
	
	private Color getRandomColor() {
		return new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
	}
	
	private class Canvas extends JPanel implements MouseListener{
		
		Graphics2D g2;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.g2 = (Graphics2D) g;
			this.drawMatrix();
		}
		
		private void drawMatrix() {
			int x = 0;
			int y = 0;
			for(int r = 0; r < colorMatrix.length; r++) {
				for(int c = 0; c < colorMatrix[r].length; c++) {
					this.drawRect(x, y, colorMatrix[r][c]);
					x += rectWidth;
				}
				y += rectHeight;
				x = 0;
			}
		}
		
		private void drawRect(int x, int y, Color c) {
			g2.setColor(c);
			g2.fillRect(x, y, rectWidth, rectHeight);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = e.getY() / rectHeight;
			int col = e.getX() / rectWidth;
			colorMatrix[row][col] = getRandomColor();
			repaint();
			
			info.setText("(w,h) = ("+ColorClick.this.getWidth()+", "+ColorClick.this.getHeight()+") --> (x,y) = ("+
			e.getX()+", "+e.getY()+") --> (r,c) = ("+row+", "+col+")"); 
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0){}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
		
	}

	public static void main(String[] args) {
		new ColorClick(5,4);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {}

	@Override
	public void componentMoved(ComponentEvent arg0) {}

	@Override
	public void componentResized(ComponentEvent e) {
		this.info.setText("(w,h) = ("+this.getWidth()+", "+this.getHeight()+")");
		this.rectWidth = this.canvas.getWidth() / this.colorMatrix[0].length;
		this.rectHeight = this.canvas.getHeight() / this.colorMatrix.length;
	}

	@Override
	public void componentShown(ComponentEvent arg0) {}

}
