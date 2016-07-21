package ui;
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import datesandtime.*;
import saves.*;
import java.util.ArrayList;
import java.util.List;

public class GUIOld extends Frame implements ActionListener {
	
	public Saves save;
	public DateLogic dLogic;
	public Day currentDay;
	
	private Label nmLbl; //name field and label
	private TextField nmFld;
	
	private Label clLbl; //calorie field and label
	private TextField clFld;
	
	private Panel panel;
	private Panel outerPanel;
	
	private Scrollbar sBar;
	
	private Label pnlLbl;

	private List<Label> itmLblList;
	
	
	private Button sbmBtn;
	
	public GUIOld() {
		
		itmLblList = new ArrayList<Label>();
		
		save = new Saves();
		save.readSave();
		
		dLogic = new DateLogic();
		currentDay = dLogic.getDay(save.data, dLogic.year, dLogic.month, dLogic.day);
		
		setLayout(new FlowLayout());

	    nmLbl = new Label("Name");
	    add(nmLbl);
	    
	    nmFld = new TextField("", 10);
	    add(nmFld);
	    
	    clLbl = new Label("Calories");
	    add(clLbl);
	    
	    clFld = new TextField("", 10);
	    add(clFld);
	    
	    
	    
	    sbmBtn = new Button("Add");
	    add(sbmBtn);
	    sbmBtn.addActionListener(this);
	    
	    
	    
	    panel = new Panel();
	    panel.setBackground(new Color(0,255,255));
	    panel.setLayout(new FlowLayout());
	    
	    pnlLbl = new Label("Items Consumed On: " + Integer.toString(dLogic.day) + "/" + Integer.toString(dLogic.month)+ "/" + Integer.toString(dLogic.year) );
	    panel.add(pnlLbl);
	    panel.setPreferredSize(new Dimension(300,300));
	    panel.setMaximumSize(new Dimension(300,0));
	    
	    
	    printItems();
	   
	    
	    panel.add(sBar);
	    
	   
	    panel.setVisible(true);
	    add(panel);
	    
		setTitle("CalCounter");  // "super" Frame sets its title
	    setSize(400, 165);       // "super" Frame sets its initial window size
	    setVisible(true);
	    
	    pack();
	    
		
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		
		if (src == sbmBtn){
			  addItemToCurrentDay();
			  
			  panel.removeAll();
			  panel.add(pnlLbl);
			  printItems();
			  panel.revalidate();
			  panel.repaint();
			 

			  
		  }
		
	}
	
	private void addItemToCurrentDay(){
		String newName;
		int newCalories;
		
		newName = nmFld.getText();
		
		try {
			newCalories = Integer.parseInt(clFld.getText());
		}
		catch (NumberFormatException v) {
			newCalories = 0;
		}
		
		currentDay.addItem(newName, newCalories);
		save.writeSave();
		
		nmFld.setText("");
		clFld.setText("");
		
		
		  
		
	}

	private void printItems() {
		

		for(int i = 0; i < currentDay.item_list.size(); i++) {
			Item item = currentDay.item_list.get(i);
			
			String name = item.name;
			int calories = item.calories;
			
			Label itmLbl = new Label(name + ": " + Integer.toString(calories));
			itmLbl.setAlignment(1);
			itmLbl.setPreferredSize(new Dimension(200,20));
			panel.add(itmLbl);
			
			itmLblList.add(itmLbl);
		}

		
	}
}
