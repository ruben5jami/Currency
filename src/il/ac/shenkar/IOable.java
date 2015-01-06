package il.ac.shenkar;

import org.w3c.dom.NodeList;

public interface IOable {
	public void writeToFile(NodeList list1,NodeList list2,NodeList list3,NodeList list4,NodeList list5,NodeList list6, NodeList list7);
	public Object readFromFile();
}
