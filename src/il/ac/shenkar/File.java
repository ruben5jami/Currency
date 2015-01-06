package il.ac.shenkar;

import java.io.*;

import org.w3c.dom.NodeList;

public class File implements IOable {
	private int lineCounter = 0;
	private String name;
	private static Coin[] coinsArray = new Coin[14];
	public File(String nameVal){
		name = nameVal;
	}
	@Override
	public Coin[] readFromFile() {
			int numberOfFields = 6;
			FileReader fstream1 = null;
			FileReader fstream2 = null;
		    BufferedReader in1 = null;
		    BufferedReader in2 = null;
		    String coinName, coinCode, coinCountry, coinUnit, coinRate, coinChange , line;
		    try {
				fstream1 = new FileReader(name);
			    in1 = new BufferedReader(fstream1);
			    line = in1.readLine();
			    while (line != null) {
			    	line = in1.readLine();
			    	lineCounter++;
			    }
			    System.out.println("line counter = " +lineCounter);
			    fstream2 = new FileReader(name);
			    in2 = new BufferedReader(fstream2);
			    for (int i = 0 ; i < (lineCounter-1)/numberOfFields ; i++) { //to do line check
			    	coinName = in2.readLine();
			    	coinCode = in2.readLine();
			    	coinCountry = in2.readLine();
			    	coinUnit = in2.readLine();
			    	coinRate = in2.readLine();
			    	coinChange = in2.readLine();
			    	coinsArray[i] = new Coin(coinName,coinCode,coinCountry,coinUnit,coinRate,coinChange);
			    }
		    }
		     catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        finally {
	        	if (in1 != null) {
	        		try {
	        			in1.close();
	        		} catch (IOException e) {
						e.printStackTrace();
	        	}
	        }
	         	if (in2 != null) {
	        		try {
	        			in2.close();
	        		} catch (IOException e) {
						e.printStackTrace();
	        	}
	        }
		}
		    return coinsArray;
	}

	@Override
	public void writeToFile(NodeList nameList, NodeList unitList, NodeList currencyCodeList,
			NodeList countryList, NodeList rateList, NodeList changeList, NodeList lastUpdate) {
		BufferedWriter out = null;
		try  
		{
		    FileWriter fstream = new FileWriter(name, false); //true tells to append data.
		    out = new BufferedWriter(fstream);
		    for (int i=0 ; i<changeList.getLength() ; i++) {
		    	out.write(nameList.item(i).getFirstChild().getNodeValue());
		    	out.newLine();
		    	out.write(unitList.item(i).getFirstChild().getNodeValue());
		    	out.newLine();
		    	out.write(currencyCodeList.item(i).getFirstChild().getNodeValue());
		    	out.newLine();
		    	out.write(countryList.item(i).getFirstChild().getNodeValue());
		    	out.newLine();
		    	out.write(rateList.item(i).getFirstChild().getNodeValue());
		    	out.newLine();
		    	out.write(changeList.item(i).getFirstChild().getNodeValue());
		    	out.newLine();
		    }
		    out.write(lastUpdate.item(0).getFirstChild().getNodeValue());
		    out.newLine();
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		finally
		{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		
	}
	
	public String readLastUpdate() throws IOException {
		FileReader fstream = null;
	    BufferedReader in = null;
	    try {
			try {
				fstream = new FileReader(name);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    in = new BufferedReader(fstream);
		    String line = null;
		    for (int counter = 0  ; counter <= lineCounter ; counter++ , line = in.readLine()) {
		    	if (counter == lineCounter) {
		    		return line;
		    	}
		    }
	    }
        finally {
        	if (in != null) {
        		try {
        			in.close();
        		} catch (IOException e) {
					e.printStackTrace();
        		}
        	}
        }
	    return null;
	}
}

