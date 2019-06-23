package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToe extends JFrame{
	JLabel message;
	JButton[] buttons;
	JRadioButton mensch, maschine, rbSchwarz, rbRot;
	boolean rotDran = true;
	boolean zuende = false;
	boolean computer;
	boolean gameGoing = false;

	TicTacToe()
	{
		super("TicTacToe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel labelPanel = createLabelPanel();
		this.add(labelPanel, BorderLayout.NORTH);

		JPanel mainPanel = createMainPanel();
		this.add(mainPanel); // CENTER

		JPanel buttonPanel = createButtonPanel();
		this.add(buttonPanel, BorderLayout.SOUTH);

		this.setSize(400, 400);
		this.setVisible(true);
	}

	JPanel createLabelPanel()
	{
		JPanel panel = new JPanel();

		message = new JLabel("TicTacToe");	// message ist Objektvariable vom Typ JLabel
		message.setFont(new Font("Verdana", Font.BOLD, 24));
		message.setForeground(Color.BLACK);
		panel.add(message);

		return panel;
	}

	JPanel createMainPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3,10,10));

		buttons = new JButton[9];	// buttons ist Objektvariable vom Typ JButton[]
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setFont(new Font("Verdana", Font.BOLD, 48));
			buttons[i].addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton)e.getSource();
					if(gameGoing && !zuende && !button.getText().equals("X") && !button.getText().equals("O"))
					{
						if(rotDran)
						{
							button.setForeground(Color.RED);
							button.setText("O");
							if(gewonnen())
							{
								message.setForeground(Color.RED);
								message.setText("Rot hat gewonnen!");
								zuende = true;
								gameGoing = false;
							}
							else if(unentschieden())
							{
								message.setText("Unentschieden");
								zuende = true;
								gameGoing = false;
							}
							else
							{
								message.setForeground(Color.BLACK);
								message.setText("Schwarz ist dran");
								rotDran = false;
							}
							if(computer) {
								computerMove();
								if(gewonnen())
								{
									message.setForeground(Color.BLACK);
									message.setText("Schwarz hat gewonnen!");
									zuende = true;
									gameGoing = false;
								}
								else if(unentschieden())
								{
									message.setText("Unentschieden");
									zuende = true;
									gameGoing = false;
								}
								else
								{
									message.setForeground(Color.RED);
									message.setText("Rot ist dran");
									rotDran = true;
								}	
							}
						}
						else
						{
							button.setForeground(Color.BLACK);
							button.setText("X");
							if(gewonnen())
							{
								message.setForeground(Color.BLACK);
								message.setText("Schwarz hat gewonnen!");
								zuende = true;
								gameGoing = false;
							}
							else if(unentschieden())
							{
								message.setText("Unentschieden");
								zuende = true;
								gameGoing = false;
							}
							else
							{
								message.setForeground(Color.RED);
								message.setText("Rot ist dran");
								rotDran = true;
							}	
						} 
					}
				}

			});
			panel.add(buttons[i]);
		}
		return panel;
	}

	JPanel createButtonPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		/*JButton bNeuRot = new JButton("Rot beginnt");
		bNeuRot.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for(JButton button : buttons)
				{
					button.setText("");
					rotDran = true;
					message.setForeground(Color.RED);
					message.setText("Rot ist dran");
				}
				ende = false;
			}

		});
		panel.add(bNeuRot);

		JButton bNeuSchwarz = new JButton("Schwarz beginnt");
		bNeuSchwarz.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for(JButton button : buttons)
				{
					button.setText("");
					rotDran = false;
					message.setForeground(Color.BLACK);
					message.setText("Schwarz ist dran");
				}
				ende = false;
			}

		});
		panel.add(bNeuSchwarz);*/

		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2,1,5,5));
		mensch = new JRadioButton("Mensch vs. Mensch", true);
		maschine = new JRadioButton("Mensch vs. Maschine");
		ButtonGroup rbg1 = new ButtonGroup();
		rbg1.add(mensch);
		rbg1.add(maschine);
		west.add(mensch);
		west.add(maschine);
		panel.add(west, BorderLayout.WEST);

		JPanel east = new JPanel();
		east.setLayout(new GridLayout(2,1,5,5));
		rbSchwarz = new JRadioButton("Schwarz beginnt", true);
		rbRot = new JRadioButton("Rot beginnt");
		ButtonGroup rbg2 = new ButtonGroup();
		rbg2.add(rbSchwarz);
		rbg2.add(rbRot);
		east.add(rbSchwarz);
		east.add(rbRot);
		panel.add(east, BorderLayout.EAST);


		JPanel north = new JPanel();
		JButton start = new JButton("Start");
		JButton ende = new JButton("ende");
		start.setBackground(Color.DARK_GRAY);
		start.setForeground(Color.WHITE);
		ende.setBackground(Color.DARK_GRAY);
		ende.setForeground(Color.WHITE);
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gameGoing = true;
				for(JButton button : buttons)
				{
					button.setText("");
				}
				if(rbSchwarz.isSelected()) {
					rotDran = false;
					message.setForeground(Color.BLACK);
					message.setText("Schwarz ist dran");
				} else {
					rotDran = true;
					message.setForeground(Color.RED);
					message.setText("Rot ist dran");
				}
				if(maschine.isSelected()) {
					computer = true;
					if(rbSchwarz.isSelected()) {
						computerMove();
						message.setText("Rot ist dran");
						rotDran = true;
					}
				} else {
					computer = false;
				}
				zuende = false;
			}

		});

		ende.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(TicTacToe.this, "Wollen Sie wirklich beenden?", "Programmende", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(ok==JOptionPane.OK_OPTION)
				{
					setVisible(false);
					dispose();
					System.exit(0);
				}
			}
		});
		
		north.add(start);
		north.add(ende);
		panel.add(north, BorderLayout.NORTH);

		JPanel south = new JPanel();
		south.setBackground(Color.DARK_GRAY);
		JLabel label = new JLabel("Hinweis: Maschine spielt stets in schwarz");
		label.setForeground(Color.WHITE);
		south.add(label);
		panel.add(south, BorderLayout.SOUTH);

		return panel;
	}

	private void computerMove() {
		boolean moveMade = false;
		for(JButton button: buttons) {
			if(button.getText().equals("")) {
				button.setForeground(Color.BLACK);
				button.setText("X");
				if(!gewonnen()) {
					button.setText("");
					button.setText("O");
					rotDran = true;
					if(gewonnen()) {
						button.setText("X");
						moveMade=true;
						break;
					} else {
						button.setText("");
					}
				} else {
					moveMade=true;
					break;
				}
			}
		}
		if(!moveMade) {
			for(JButton button: buttons) {
				if(button.getText().equals("")) {
					button.setText("X");
					break;
				}
			}
		}
	}

	private boolean gewonnen()
	{
		String dran = rotDran ? "O" : "X";

		// Zeilen
		if(buttons[0].getText().equals(dran) && buttons[1].getText().equals(dran) && buttons[2].getText().equals(dran)) return true;
		if(buttons[3].getText().equals(dran) && buttons[4].getText().equals(dran) && buttons[5].getText().equals(dran)) return true;
		if(buttons[6].getText().equals(dran) && buttons[7].getText().equals(dran) && buttons[8].getText().equals(dran)) return true;

		// Spalten
		if(buttons[0].getText().equals(dran) && buttons[3].getText().equals(dran) && buttons[6].getText().equals(dran)) return true;
		if(buttons[1].getText().equals(dran) && buttons[4].getText().equals(dran) && buttons[7].getText().equals(dran)) return true;
		if(buttons[2].getText().equals(dran) && buttons[5].getText().equals(dran) && buttons[8].getText().equals(dran)) return true;

		// Diagonalen
		if(buttons[0].getText().equals(dran) && buttons[4].getText().equals(dran) && buttons[8].getText().equals(dran)) return true;
		if(buttons[2].getText().equals(dran) && buttons[4].getText().equals(dran) && buttons[6].getText().equals(dran)) return true;

		return false;
	}

	private boolean voll()
	{
		for(int i=0; i<buttons.length; i++)
		{
			if(!buttons[i].getText().equals("X") && !buttons[i].getText().equals("O")) return false;
		}
		return true;
	}

	private boolean unentschieden()
	{
		return (voll() && !gewonnen());
	}

	public static void main(String[] args) {
		new TicTacToe();
	}

}
