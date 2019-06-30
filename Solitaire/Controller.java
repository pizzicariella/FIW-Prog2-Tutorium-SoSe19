package Solitaire;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class Controller implements MouseListener, ActionListener{
	Model model;
	View view;
	Point from;
	Point to;
	public boolean movePossible = true;
	
	public Controller(Model model, View view) {
		this.model= model;
		this.view = view;
		view.getUndo().addActionListener(this);
		view.getCanvas().addMouseListener(this);
		view.update(model.getField());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = (e.getX()-20)/view.FELDSIZE;
		int y = (e.getY()-20)/view.FELDSIZE;
		Point p = new Point(y,x);
		if(movePossible){
			from = p;
			movePossible=false;
		}
		else{
			to = p;
			movePossible=true;
		}
		if(movePossible){
			if(model.move(from, to)){
				view.update(model.getField());
				if(model.gewonnen()){
					JOptionPane.showMessageDialog(view, "Herzlichen Glückwunsch! Sie haben gewonnen!");
				}
				else if(model.verloren()){
					JOptionPane.showMessageDialog(view, "Leider haben Sie das Spiel verloren", "Verloren", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(view, "Für die gewählten Felder ist der Zug nicht möglich. "
						+ "Wählen Sie neu!");
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.model.lastMoveUndo();
		view.update(model.getField());
	}
	
}
