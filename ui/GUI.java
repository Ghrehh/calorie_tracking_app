package ui;

import javax.swing.*;

import datesandtime.DateLogic;
import saves.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUI extends JFrame implements ActionListener  {
	
	private DateLogic dLogic;
	
	public Day currentDay;
	public Saves save;
	
	private JPanel topPanel;
		private JPanel datePanel;
			private JLabel dateLabel;
		private JPanel formPanel;
			private JTextField nameField;
			private JTextField calField;
			private JButton subButton;
			
			private TextPrompt namePH;
			private TextPrompt calPH;
			
	private JPanel middlePanel;
	private JScrollPane middleScroll;
	
	private JPanel bottomPanel;
		public JLabel totalLabel;

	public GUI() {
		
		save = new Saves();
		save.readSave();
		
		dLogic = new DateLogic();
		currentDay = dLogic.getDay(save.data, dLogic.year, dLogic.month, dLogic.day);
		
		topPanel = new JPanel();
		topPanel.setBackground(new Color(0,0,0));
		topPanel.setLayout(new GridLayout(3, 1));
		this.add(topPanel, BorderLayout.PAGE_START);
		
			datePanel = new JPanel();
			datePanel.setBackground(new Color(255,50,120));
			datePanel.setLayout(new FlowLayout());
			topPanel.add(datePanel);
				
				dateLabel = new JLabel(dLogic.currentDate());
				datePanel.add(dateLabel);
			
			
			formPanel = new JPanel();
			formPanel.setBackground(new Color(255,120,120));
			formPanel.setLayout(new FlowLayout());
			topPanel.add(formPanel);
			
			
				nameField = new JTextField(10);
				formPanel.add(nameField);
				
				namePH = new TextPrompt("Name", nameField);
				namePH.setForeground( Color.GRAY );
				namePH.changeAlpha(0.5f);
				namePH.changeStyle(Font.ITALIC);
				
				calField = new JTextField(10);
				formPanel.add(calField);
				
				calPH = new TextPrompt("Calories", calField);
				calPH.setForeground( Color.GRAY );
				calPH.changeAlpha(0.5f);
				calPH.changeStyle(Font.ITALIC);
				
				subButton = new JButton("Add");
				subButton.addActionListener(this);
				formPanel.add(subButton);
		
		middlePanel = new JPanel();
		middlePanel.setBackground(new Color(5,250,250));
		middlePanel.setLayout(new GridLayout(0, 1));
		
		middleScroll = new JScrollPane(middlePanel);
		this.add(middleScroll);

		
		
		bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255,255,255));
		this.add(bottomPanel, BorderLayout.PAGE_END);
		
			totalLabel = new JLabel( "Total Calories: " + Integer.toString(currentDay.totalCalories()) );
			bottomPanel.add(totalLabel);
			
			this.displayItems();
		this.setTitle("Calorie Counter");
		this.setSize(400,500);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		
		if (src == subButton){
			  newItem();
		  }
		
	}
	
	private void displayItems() {
		
		middlePanel.removeAll();
		middlePanel.revalidate();
		middlePanel.repaint();
		
		List<Item> list = new ArrayList<Item>(currentDay.item_list);
		Collections.reverse(list);
		
		for (int i = 0; i < list.size(); i++) {
			new ItemPanel(list.get(i), middlePanel, currentDay, save, totalLabel);
		}
		
		this.totalLabel.setText("Total Calories: " + Integer.toString(currentDay.totalCalories()) );
		
	}

	private void newItem(){
		String newName;
		int newCalories;
		
		newName = nameField.getText();
		
		try {
			newCalories = Integer.parseInt(calField.getText());
		}
		catch (NumberFormatException v) {
			newCalories = 0;
		}
		
		currentDay.addItem(newName, newCalories);
		save.writeSave();
		
		nameField.setText("");
		calField.setText("");
		
		displayItems();
	}
	
}
