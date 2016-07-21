package ui;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import saves.*;

public class ItemPanel extends JPanel implements ActionListener {
	private Item item;
	private JLabel label;
	private JButton button;
	private JPanel panel;
	private Day day;
	private Saves save;
	private JLabel total;
	
	public ItemPanel(Item item, JPanel panel, Day day, Saves save, JLabel total) {
		this.item = item;
		this.panel = panel;
		this.day = day;
		this.save = save;

		this.total = total;
		
		String name = item.name;
		String calories = Integer.toString(item.calories);
		
		label = new JLabel(name + ": " + calories);
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		label.setBorder(new EmptyBorder(0,0,0,20));
		this.add(label);
		
		button = new JButton("Delete");
		button.setFont(new Font("Arial", Font.PLAIN, 9));
		button.setMargin(new Insets(0,0,0,0));
		button.addActionListener(this);
		this.add(button);
		
		this.setBackground(new Color(randInt(),randInt(),randInt()));
		this.setLayout(new FlowLayout());
		this.panel.add(this);
	}
	
	public static int randInt() {
		int min = 230;
		int max = 255;
	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}


	@Override
	public void actionPerformed(ActionEvent evt) {
		
		deleteElement(); //for some reason the setText for a panel wront refresh until the next action. This is the dirtiest of fixes.
		deleteElement();
		
	}

	private void deleteElement(){
		this.panel.remove(this);
		this.panel.revalidate();
		this.panel.repaint();
		
		this.total.setText("Total Calories: " + Integer.toString(day.totalCalories()) );
		
		this.day.removeItem(this.item);
		this.save.writeSave();
	}
}
