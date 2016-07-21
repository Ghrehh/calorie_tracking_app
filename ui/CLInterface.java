package ui;

import java.util.List;
import saves.*;

public class CLInterface {

	public CLInterface() {
		// TODO Auto-generated constructor stub
	}
	
	public static void printOpenSave(List<Year> data){
		System.out.println();
        for (int i = 0; i < data.size(); i++) {
	       	Year year = data.get(i);
	       	System.out.println(year.number + ":  ");
       	 
			for (int i2 = 0; i2 < year.month_list.size(); i2++){
				Month month = year.month_list.get(i2);
				System.out.println("-" + month.number);
				
				for (int i3 = 0; i3 < month.day_list.size(); i3++){
					Day day = month.day_list.get(i3);
					System.out.println("--" + day.number);
					
					for (int i4 = 0; i4 < day.item_list.size(); i4++){
						Item item = day.item_list.get(i4);
						System.out.println("---" + item.name + " - " + item.calories);
					}
				}
			}
        }
        
	}

}
