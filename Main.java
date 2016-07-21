import datesandtime.DateLogic;
import saves.*;
import ui.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Saves save = new Saves();
		save.readSave();

		DateLogic d = new DateLogic();
		Day test = d.getDate(save.data, 2012, 1, 3);
		test.addItem("poo", 200);
		test.printItems();
		//System.out.println(test.totalCalories());
		
		save.writeSave();
		//CLInterface.printOpenSave(save.data);
		//d.printDate();*/
		
		new GUI();
		
	}

}
