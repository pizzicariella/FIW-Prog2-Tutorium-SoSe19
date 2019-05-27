package aufgabe5;

import java.util.LinkedList;
import java.awt.Point;


public class Model {
	State[][] field;
	LinkedList<Point> moves;
	
	public Model() {
		this.field = new State[7][7];
		this.moves = new LinkedList<>();
	}
	
	public void initialiseModel() {
		for(int i = 0; i<this.field.length; i++) {
			for(int j =0; j<this.field[i].length; j++) {
				if((i<2 && j<2) || (i<2 && j>4) || (i>4 && j<2) || (i>4 && j>4)) {
					this.field[i][j] = State.NOT;
				}
				else if(i == 3 && j == 3) {
					this.field[i][j] = State.FREE;
				}
				else {
					this.field[i][j] = State.USED;
				}
			}
		}
	}
	
	public void print() {
		for(int i = 0; i< this.field.length; i++) {
			for(int j = 0; j< this.field[i].length; j++) {
				if(this.field[i][j] == State.NOT || this.field[i][j] == State.FREE) {
					System.out.print("  ");
				}
				else {
					System.out.print("o ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void move(Point from, Point to) {
		if(this.field[from.x][from.y] == State.USED && this.field[to.x][to.y] == State.FREE) {
			if(from.x == to.x && Math.abs(from.y-to.y) == 2) {
				int col = (from.y > to.y ? from.y-1 : from.y+1);
				if(this.field[from.x][col] == State.USED) {
					this.field[from.x][col] = State.FREE;
					this.field[from.x][from.y] = State.FREE;
					this.field[to.x][to.y] = State.USED;
					this.moves.add(from);
					this.moves.add(to);
				} else {
					System.out.println("Das Feld zwischen from und to muss belegt sein!");
				}
			}
			else if(from.y == to.y && Math.abs(from.x-to.x) == 2) {
				int row = (from.x > to.x ? from.x-1 : from.x+1);
				if(this.field[row][from.y] == State.USED) {
					this.field[row][from.y] = State.FREE;
					this.field[from.x][from.y] = State.FREE;
					this.field[to.x][to.y] = State.USED;
					this.moves.add(from);
					this.moves.add(to);
				} else {
					System.out.println("Das Feld zwischen from und to muss belegt sein!");
				}
			}
			else {
				System.out.println("Kein gültiger Zug! Es muss genau ein Feld zwsischen from und to liegen.");
			}
		} else {
			System.out.println("from muss belegt sein und to muss frei sein");
		}
	}
	
	public void lastMoveUndo() {
		if(this.moves.size() >= 2) {
			Point to = this.moves.removeLast();
			Point from = this.moves.removeLast();
			this.field[to.x][to.y] = State.FREE;
			this.field[from.x][from.y] = State.USED;
			if(from.x == to.x) {
				int col = from.y>to.y ? from.y-1 : from.y+1;
				this.field[from.x][col] = State.USED;
			}
			else {
				int row = from.x>to.x ? from.x-1 : from.x+1;
				this.field[row][from.y] = State.USED;
			}
			System.out.println("letzter Zug wurde rückgängig gemacht.");
		}
		else {
			System.out.println("es wurden noch keine Züge ausgeführt.");
		}
		
	}
}
