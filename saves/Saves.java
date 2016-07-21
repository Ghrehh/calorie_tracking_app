package saves;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Saves {

	public List<Year> data = new ArrayList<Year>();
	
	public Saves() {

		 
	}

	public void readSave(){
		try {
	         File inputFile = new File("src/data.xml");
	         
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         
	         System.out.println(doc.getDocumentElement().getNodeName());
	         NodeList years = doc.getElementsByTagName("year");
	         
	         for (int i = 0; i < years.getLength(); i++) { //make  years
	        	 Node n = years.item(i);
	        	 int year_value = Integer.parseInt(n.getAttributes().getNamedItem("number").getNodeValue());
	        	 Year year = new Year(year_value);
	        	 data.add(year);
	        	 
	        	 Element e = (Element)years.item(i);
	        	 NodeList months = e.getElementsByTagName("month");
	        	 
	        	 for (int i2 = 0; i2 < months.getLength(); i2++) { //make  months
	        		 Node n2 = months.item(i2);
	        		 int month_value = Integer.parseInt(n2.getAttributes().getNamedItem("number").getNodeValue());
	        		 Month month = new Month(month_value);
	        		 
	        		 year.month_list.add(month);
	        		 
	        		 Element e2 = (Element)months.item(i2);
		        	 NodeList days = e2.getElementsByTagName("day");
		        	 
		        	 for (int i3 = 0; i3 < days.getLength(); i3++) { //make  days
		        		 Node n3 = days.item(i3);
		        		 
		        		 int day_value = Integer.parseInt(n3.getAttributes().getNamedItem("number").getNodeValue());
		        		 Day day = new Day(day_value);
		        		 month.day_list.add(day);
		        		 
		        		 Element e3 = (Element)days.item(i3); //don't confuse item the xml element with the listarray item 
			        	 NodeList items = e3.getElementsByTagName("item");
			        	 
			        	 for (int i4 = 0; i4 < items.getLength(); i4++) { //make  items
			        		 Node n4 = items.item(i4);
			        		 
			        		 String item_name_value = n4.getAttributes().getNamedItem("name").getNodeValue();
			        		 int item_calories_value = Integer.parseInt(n4.getAttributes().getNamedItem("calories").getNodeValue());
			        		 
			        		 Item item = new Item(item_name_value, item_calories_value);
			        		 day.item_list.add(item);
			        	 } 
		        	 }
	        	 }
	         }
 
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	public void writeSave(){
		try {
			
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         
	         // root element
	         Element rootElement = doc.createElement("save");
	         doc.appendChild(rootElement);
	         
	         //sets years
	         for(int i = 0; i < data.size(); i++){ 
	        	 Year year = data.get(i);
	        	 
	        	 Element yearElement = doc.createElement("year");
		         rootElement.appendChild(yearElement);
		         
		         Attr attr = doc.createAttribute("number");
		         attr.setValue(Integer.toString(year.number));
		         yearElement.setAttributeNode(attr);
		         
		       //sets months
		         for(int i2 = 0; i2 < year.month_list.size(); i2++){
		        	 Month month = year.month_list.get(i2);
		        	 
		        	 Element monthElement = doc.createElement("month");
			         yearElement.appendChild(monthElement);
			         
			         Attr attr2 = doc.createAttribute("number");
			         attr2.setValue(Integer.toString(month.number));
			         monthElement.setAttributeNode(attr2);
			         
			         for(int i3 = 0; i3 < month.day_list.size(); i3++){
			        	 Day day = month.day_list.get(i3);
			        	 
			        	 Element dayElement = doc.createElement("day");
				         monthElement.appendChild(dayElement);
				         
				         Attr attr3 = doc.createAttribute("number");
				         attr3.setValue(Integer.toString(day.number));
				         dayElement.setAttributeNode(attr3);
				         
				         for(int i4 = 0; i4 < day.item_list.size(); i4++){
				        	 Item item = day.item_list.get(i4);
				        	 
				        	 Element itemElement = doc.createElement("item");
					         dayElement.appendChild(itemElement);
					         
					         Attr attr4 = doc.createAttribute("name");
					         attr4.setValue(item.name);
					         itemElement.setAttributeNode(attr4);
					         
					         attr4 = doc.createAttribute("calories");
					         attr4.setValue(Integer.toString(item.calories));
					         itemElement.setAttributeNode(attr4);
				         }
			         }
		         }
	         }

	         /*//  supercars element
	         Element supercar = doc.createElement("year");
	         rootElement.appendChild(supercar);

	         // setting attribute to element
	         Attr attr = doc.createAttribute("number");
	         attr.setValue("1");
	         supercar.setAttributeNode(attr);

	         // carname element
	         Element carname = doc.createElement("carname");
	         Attr attrType = doc.createAttribute("type");
	         attrType.setValue("formula one");
	         carname.setAttributeNode(attrType);
	         carname.appendChild(doc.createTextNode("Ferrari 101"));
	         supercar.appendChild(carname);*/


	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //indentation on xml file
	         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	         
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("src/data.xml"));
	         transformer.transform(source, result);
	         
	         // Output to console for testing
	         /*StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);*/
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	public void printOpenSave(){
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
