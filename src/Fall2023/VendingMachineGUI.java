package Fall2023;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VendingMachineGUI extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new VendingMachineGUI();
	}

	private double cashInserted;
	private Product prod1;
	private Product prod2;
	
	// Variables for objects in GUI
	private JTextArea textArea;
	private JButton insertCashButton;
	private JButton buyProd1Button;
	private JButton buyProd2Button;
	private JButton getChangeButton;
	private JButton displayProductsButton;
	private JMenuItem clearDisplay;
	private JMenuItem stockProd1;
	private JMenuItem stockProd2;
	
	public VendingMachineGUI() {
		cashInserted = 0.0;
		prod1 = new Product("Rubber Ball (organic)", 15.99);
		prod2 = new Product("Cheese Wheel (stinky)", 79.99);
		prod1.stockMachine(10);
		prod2.stockMachine(5);
		buildWindow();
		println("Product 1: "+prod1);
		println("Product 2: "+prod2);
	}
	
	// This method responds to button clicks.
	public void actionPerformed(ActionEvent e) {
		Object thingClicked = e.getSource();
		if (thingClicked == displayProductsButton) {
			println("Product 1: "+prod1);
			println("Product 2: "+prod2);
		} else if (thingClicked == insertCashButton) {
			String userInput = JOptionPane.showInputDialog(this, "Enter how much?");
			double amount = Double.parseDouble(userInput);
			cashInserted += amount;
			println("Cash Inserted: $"+String.format("%.2f", cashInserted));
		} else if (thingClicked == buyProd1Button) {
			if (cashInserted >= prod1.getPrice()) {
				if(prod1.dispenseItem()) {
					cashInserted -= prod1.getPrice();
					println("Enjoy your new "+prod1.getName()+"!");
				} else {
					println("Out of stock!");
				}
			} else {
				println("Gimme more money!!!");
			}
		} else if (thingClicked == buyProd2Button) {
			if (cashInserted >= prod2.getPrice()) {
				if(prod2.dispenseItem()) {
					cashInserted -= prod2.getPrice();
					println("Enjoy your new "+prod2.getName()+"!");
				} else {
					println("Out of stock!");
				}
			} else {
				println("Gimme more money!!!");
			}
		} else if (thingClicked == getChangeButton) {
			println("Please take your $"+String.format("%.2f", cashInserted)+"!");
			cashInserted = 0;
		} else if (thingClicked == clearDisplay) {
			textArea.setText("");
			println("Product 1: "+prod1);
			println("Product 2: "+prod2);
		} else if (thingClicked == stockProd1) {
			String userInput = JOptionPane.showInputDialog(this,"Add how many items?");
			int thisMany = Integer.parseInt(userInput);
			prod1.stockMachine(thisMany);
		} else if (thingClicked == stockProd2) {
			String userInput = JOptionPane.showInputDialog(this,"Add how many items?");
			int thisMany = Integer.parseInt(userInput);
			prod2.stockMachine(thisMany);
		}
	}
	
	// This method prints a string in the GUI text area.
	public void println(Object s) {
		textArea.append(s.toString()+"\n");
	}
	
	// This method builds the GUI window!
	private void buildWindow() {
		setTitle("Vending Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		
		add(buildButtonPanel(), BorderLayout.EAST);
		
		setJMenuBar(buildMenuBar());
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// This method builds the button panel in the GUI window.
	private JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
		
		JPanel insertCashPanel = new JPanel(new FlowLayout());
		insertCashButton = new JButton("Insert Cash");
		insertCashButton.addActionListener(this);
		insertCashPanel.add(insertCashButton);
		
		JPanel buyProd1Panel = new JPanel(new FlowLayout());
		buyProd1Button = new JButton("Product 1");
		buyProd1Button.addActionListener(this);
		buyProd1Panel.add(buyProd1Button);
		
		JPanel buyProd2Panel = new JPanel(new FlowLayout());
		buyProd2Button = new JButton("Product 2");
		buyProd2Button.addActionListener(this);
		buyProd2Panel.add(buyProd2Button);
		
		JPanel getChangePanel = new JPanel(new FlowLayout());
		getChangeButton = new JButton("Get Change");
		getChangeButton.addActionListener(this);
		getChangePanel.add(getChangeButton);
		
		JPanel displayProductsPanel = new JPanel(new FlowLayout());
		displayProductsButton = new JButton("Display Products");
		displayProductsButton.addActionListener(this);
		displayProductsPanel.add(displayProductsButton);
		
		buttonPanel.add(insertCashPanel);
		buttonPanel.add(buyProd1Panel);
		buttonPanel.add(buyProd2Panel);
		buttonPanel.add(getChangePanel);
		buttonPanel.add(displayProductsPanel);
		
		return buttonPanel;
	}
	
	// This method builds the menu bar in the GUI window.
	private JMenuBar buildMenuBar() {
		clearDisplay = new JMenuItem("Clear display");
		clearDisplay.addActionListener(this);
		stockProd1 = new JMenuItem("Stock Prod 1");
		stockProd1.addActionListener(this);
		stockProd2 = new JMenuItem("Stock Prod 2");
		stockProd2.addActionListener(this);
		
		JMenu manageMenu = new JMenu("Manage");
		manageMenu.add(clearDisplay);
		manageMenu.add(stockProd1);
		manageMenu.add(stockProd2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(manageMenu);
		
		return menuBar;
	}
	
}
