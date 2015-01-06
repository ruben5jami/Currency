package il.ac.shenkar;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {
	private NodeList nameList;
	private NodeList unitList;
	private NodeList currencyCodeList;
	private NodeList countryList;
	private NodeList rateList;
	private NodeList changeList;
	private NodeList lastUpdate;
	private String lastUpdateTime;
	private File file;
	
	InputStream is = null;
    HttpURLConnection con = null;
    public void read() {
   		Coin[] coinArray = null;
    	try {
    		file = new File("Currency.txt");
    		URL url = new URL("http://www.boi.org.il/currency.xml");
    		con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("GET");
    		con.connect(); 
    		//reads from the XML file and update the currency file
    		is = con.getInputStream();
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		Document doc = builder.parse(is);
    		nameList = doc.getElementsByTagName("NAME");
    		unitList = doc.getElementsByTagName("UNIT");
    		currencyCodeList = doc.getElementsByTagName("CURRENCYCODE");
    		countryList = doc.getElementsByTagName("COUNTRY");
    		rateList = doc.getElementsByTagName("RATE");
    		changeList = doc.getElementsByTagName("CHANGE");
    		lastUpdate = doc.getElementsByTagName("LAST_UPDATE");
    		file.writeToFile(nameList,unitList,currencyCodeList,countryList,rateList,changeList,lastUpdate);//update the file
    		coinArray = file.readFromFile(); //and read from it	
    		lastUpdateTime = file.readLastUpdate();
    		System.out.println("the last update: "+lastUpdateTime);
    	}
    	catch (UnknownHostException e) {
			System.out.println("Unable to connect the server. taking data from the last update.");
			coinArray = file.readFromFile();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	catch(ParserConfigurationException e) {
    		e.printStackTrace();
    	}
    	catch(SAXException e) {
    	 	e.printStackTrace();
     	}
    	finally {
    		GUI gui = new GUI(coinArray,lastUpdateTime);
    		gui.start();
    		if(is!=null) {
    			try {
    				is.close();
    			}
    			catch(IOException e) {
    				e.printStackTrace();
    			}
    		}
    		if(con!=null) {
    			con.disconnect();
    		}
    	}
    }

}