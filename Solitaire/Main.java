package Solitaire;

public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		model.initialiseModel();
		View view = new View();
		Controller controller = new Controller(model, view);
	}

}
