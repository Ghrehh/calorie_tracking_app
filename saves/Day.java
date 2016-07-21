package saves;
import java.util.ArrayList;
import java.util.List;

public class Day {
	public int number;
	public List<Item> item_list = new ArrayList<Item>();

	public Day(int num) {
		this.number = num;
	}
	
	public void printItems(){
		for (int i = 0; i < item_list.size(); i++) {
			Item item = item_list.get(i);
			System.out.println( item.name + ": " + Integer.toString(item.calories));
		}
	}
	
	
	public void addItem(String name, int calories){
		Item item = new Item(name, calories);
		item_list.add(item);
	}
	
	public void removeItem(Item item){
		item_list.remove(item);
	}
	
	public int totalCalories(){ //totals up the calories of all the items for the day
		int total = 0;
		for (int i = 0; i < item_list.size(); i++) {
			total+= item_list.get(i).calories;
		}
		return total;
	}

}
