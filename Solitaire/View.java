package Solitaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class View extends JFrame{ 

	Canvas canvas;
	JButton undo;
	State[][] field;
	final int FELDSIZE =75;
	final int KREISSIZE = 15;
	
	public View() {
		super("Solitaer");
		this.canvas=new Canvas();
		this.undo = new JButton("Undo");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,650);
		this.add(canvas, BorderLayout.CENTER);
		this.add(undo, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public JButton getUndo() {
		return undo;
	}
	
	public void setUndo(JButton undo) {
		this.undo = undo;
	}

	public void update(State[][] field) {
		this.field = field;
		canvas.repaint();
	}
	
	class Canvas extends JPanel{
		private Graphics2D g2;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.g2 = (Graphics2D)g;
			int x = 20;
			int y =20;
			for(int i =0; i<field.length;i++) {
				for(int j=0;j<field.length;j++) {
					if(field[i][j] == State.NOT) {
						rectZeichnen(Color.LIGHT_GRAY,x,y);
					}
					else if(field[i][j] == State.FREE) {
						rectZeichnen(new Color(222,184,135), x,y);
					}
					else if(field[i][j] == State.USED) {
						rectZeichnen(new Color(222,184,135),x,y);
						ovalZeichnen(x+FELDSIZE/2-KREISSIZE/2, y+FELDSIZE/2-KREISSIZE/2);
					}
					x+=FELDSIZE;
				}
				y+=FELDSIZE;
				x=20;
			}
		}
		
		private void rectZeichnen(Color c, int x, int y) {
			if(c == null) {
				System.out.println("color is null");
			}
			if(g2 == null) {
				System.out.println("g2 is null in rectZeichnen");
			}
			g2.setColor(c);
			g2.fill3DRect(x, y,FELDSIZE, FELDSIZE,true);
			g2.setColor(Color.WHITE);
			g2.draw3DRect(x, y,FELDSIZE,FELDSIZE,true);
		}
		
		private void ovalZeichnen(int x, int y) {
			g2.setColor(Color.WHITE);
			g2.fillOval(x,y, KREISSIZE,KREISSIZE);
		}
	}
}
