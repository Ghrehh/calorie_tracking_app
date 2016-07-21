package saves;
import java.util.ArrayList;
import java.util.List;

public class Year {
	public int number;
	public List<Month> month_list = new ArrayList<Month>();
	
	public Year(int num) {
		this.number = num;
	}

}
