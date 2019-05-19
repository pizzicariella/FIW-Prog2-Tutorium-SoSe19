package aufgabe5;

import java.awt.Point;

public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		model.initialiseModel();
		model.print();
		System.out.println();
		model.move(new Point(1,3), new Point(3,3));
		model.print();
		model.move(new Point(4,3), new Point(2,3));
		model.print();
		model.move(new Point(4,1), new Point(4,3));
		model.print();
		model.lastMoveUndo();
		model.print();
		model.lastMoveUndo();
		model.print();
		model.lastMoveUndo();
		model.print();
	}

}
