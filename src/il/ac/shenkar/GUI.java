package il.ac.shenkar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class GUI {
	private JFrame mainFrame, tableFrame;
	private JButton btExchange, btTable;
	private JPanel panelFrom, panelTo, panelResult, panelBt, panelSum;
	private JList fromList , toList;
	private JTextArea fromTextArea, toTextArea ;
	private JTextField textFieldResult, sumTextField;
	private JLabel from, to, result,lastUpdate, sum;
	private ActionListener listener;
	private JTable table;

	public GUI(final Coin[] coinsArrayVal, String LastUpdate) {
		DefaultListModel model = new DefaultListModel();
		from = new JLabel(">>>");
		to = new JLabel("<<<");
		mainFrame = new JFrame("Exchange Center");
		btExchange = new JButton("Exchange");
		btTable = new JButton("Show rates table");
		lastUpdate = new JLabel("Last update: " + LastUpdate);
		panelFrom = new JPanel();
		panelTo = new JPanel();
		panelResult = new JPanel();
		panelBt = new JPanel();
		sum = new JLabel("Amount: ");
		result = new JLabel("Result: ");
		panelSum = new JPanel(new FlowLayout(0, 60, 15));
		fromList = new JList(model);
		toList = new JList(model);
		fromTextArea = new JTextArea();
		toTextArea = new JTextArea();
		textFieldResult = new JTextField(10);
		sumTextField = new JTextField(10);
		for (int i = 0 ; i<coinsArrayVal.length ; i++) {
			model.addElement(coinsArrayVal[i].getCurrencyCode()+"      "+coinsArrayVal[i].getRate());	
		}
			
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Object src = evt.getSource();
				if (src == btExchange) {
					String rateFrom = coinsArrayVal[fromList.getSelectedIndex()].getRate();
					int sum = Integer.parseInt(sumTextField.getText());
					float rateFrom1 = Float.parseFloat(rateFrom);
					String rateTo = coinsArrayVal[toList.getSelectedIndex()].getRate();
					float rateTo1 = Float.parseFloat(rateTo);
					float result = 0;
					result = sum*rateFrom1/rateTo1;
					textFieldResult.setText(String.valueOf(result));
				}
				if (src == btTable) {
					//TODO table
					
					tableFrame = new JFrame("Exchange Table");
					tableFrame.setLayout(new BorderLayout());
					tableFrame.setSize(600, 300);
					Object[] columnNames = {"NAME",
	                        "COUNTRY",
	                        "CURRENY CODE",
	                        "RATE",
	                        "CHANGE"};
					Object[][] data = new Object[14][5];
					
					for (int row = 0 ; row<=13 ; row++) {
						for (int col = 0 ; col<=4 ; col++) {
							switch(col)
							{
							case 0: data[row][col] = coinsArrayVal[row].getName();
							break;
							case 1: data[row][col] = coinsArrayVal[row].getCountry();
							break;
							case 2: data[row][col] = coinsArrayVal[row].getCurrencyCode();
							break;
							case 3: data[row][col] = coinsArrayVal[row].getRate();
							break;
							case 4: data[row][col] = coinsArrayVal[row].getChange();
							break;
							}
						}
					}
					
					table = new JTable(data, columnNames);
					tableFrame.add(new JScrollPane(table));
					tableFrame.setVisible(true);
					
				}
			}
		};
	}
	
	public void start() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fromList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fromList.setLayoutOrientation(JList.VERTICAL);
		fromList.setVisibleRowCount(-1);
		JScrollPane JSfromlist = new JScrollPane(fromList);
		JSfromlist.setPreferredSize(new Dimension(100,300));
		toList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		toList.setLayoutOrientation(JList.VERTICAL);
		toList.setVisibleRowCount(-1);
		JScrollPane JStolist = new JScrollPane(toList);
		JStolist.setPreferredSize(new Dimension(100,300));
		mainFrame.setLayout(new BorderLayout());
		panelBt.add(btExchange);
		panelBt.add(btTable);
		panelSum.add(sum, BorderLayout.NORTH);
		panelSum.add(sumTextField, BorderLayout.NORTH);
		panelSum.add(result, BorderLayout.SOUTH);
		panelSum.add(textFieldResult, BorderLayout.SOUTH);
		panelFrom.setLayout(new FlowLayout());
		panelTo.setLayout(new FlowLayout());
		panelTo.add(JStolist);
		panelFrom.add(from);
		panelFrom.add(JSfromlist);
		panelTo.add(to);
		//panelSum.setLayout(new FlowLayout(0, 40, 80));
		sumTextField.setText("1"); //the default sum of exchange is set to 1
		
		mainFrame.add(panelTo,BorderLayout.EAST);
		mainFrame.add(panelFrom,BorderLayout.WEST);
		mainFrame.add(panelBt,BorderLayout.SOUTH);
		//mainFrame.add(panelResult,BorderLayout.NORTH);
		mainFrame.add(panelSum,BorderLayout.CENTER);
		mainFrame.add(lastUpdate,BorderLayout.NORTH);
		mainFrame.setSize(600, 500);
		mainFrame.setVisible(true);
		btExchange.addActionListener(listener);
		btTable.addActionListener(listener);
	}
	
}
