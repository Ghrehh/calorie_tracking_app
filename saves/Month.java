package saves;
import java.util.ArrayList;
import java.util.List;

public class Month {
	public int number;
	public List<Day> day_list = new ArrayList<Day>();
	
	public Month(int num) {
		this.number = num;
	}

}
