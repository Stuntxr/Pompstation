/**
 * Created by Jurrien Reyme, Johny Diran, Earvin Small
 * ingeleverd op 09/04/2018
 * Selfservice pompstation
 */
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Gui extends JFrame {

	private JFrame frame;
	private JTextField p1Liters, p2Liters, p3Liters, p4Liters, p5Liters, p6Liters, p7Liters, p8Liters ;
	private JTextField p1Amount, p2Amount, p3Amount, p4Amount, p5Amount, p6Amount, p7Amount, p8Amount;
	private static JTextField p1KenNum;
	private JTextField p2KenNum;
	private JTextField p3KenNum;
	private JTextField p4KenNum;
	private JTextField p5KenNum;
	private JTextField p6KenNum;
	private JTextField p7KenNum;
	private JTextField p8KenNum;
	private double showLiters, showAmount, oilPrice;
	private JTextArea textArea;
	private JComboBox<Object> p1ComboBox, p2ComboBox, p3ComboBox, p4ComboBox, p5ComboBox, p6ComboBox, p7ComboBox, p8ComboBox;
	private JButton p1PaidBtn;
	private double Diesel, Unleaded, SuperUnleaded;
	
	DecimalFormat df = new DecimalFormat("#0.00");
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmChangeOilPrice;
	private JMenuItem mntmExit;
	private JMenuItem mntmShowOilPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 866, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 70, 75, 75, 0, 110, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.getContentPane().setBackground(new Color(21, 25, 40));//Custom background color hex #151928
		frame.setTitle("Selfservice Pompstation");
		//setForeground(new Color(160, 162, 171)); //Custom jlabel color #a0a2ab color-hex.com
		
		
		JLabel lblFuelDispencer = new JLabel("Fuel Dispencer");
		lblFuelDispencer.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblFuelDispencer = new GridBagConstraints();
		gbc_lblFuelDispencer.insets = new Insets(0, 0, 5, 5);
		gbc_lblFuelDispencer.gridx = 1;
		gbc_lblFuelDispencer.gridy = 1;
		frame.getContentPane().add(lblFuelDispencer, gbc_lblFuelDispencer);
		
		JLabel lblLiters = new JLabel("Liters");
		lblLiters.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblLiters = new GridBagConstraints();
		gbc_lblLiters.insets = new Insets(0, 0, 5, 5);
		gbc_lblLiters.gridx = 3;
		gbc_lblLiters.gridy = 1;
		frame.getContentPane().add(lblLiters, gbc_lblLiters);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 4;
		gbc_lblAmount.gridy = 1;
		frame.getContentPane().add(lblAmount, gbc_lblAmount);
		
		JLabel lblSelectoiltype = new JLabel("Select Oil Type");
		lblSelectoiltype.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblSelectoiltype = new GridBagConstraints();
		gbc_lblSelectoiltype.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectoiltype.gridx = 5;
		gbc_lblSelectoiltype.gridy = 1;
		frame.getContentPane().add(lblSelectoiltype, gbc_lblSelectoiltype);
		
		JLabel lbllicensePlate = new JLabel("licensePlate");
		lbllicensePlate.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lbllicensePlate = new GridBagConstraints();
		gbc_lbllicensePlate.insets = new Insets(0, 0, 5, 5);
		gbc_lbllicensePlate.gridx = 6;
		gbc_lbllicensePlate.gridy = 1;
		frame.getContentPane().add(lbllicensePlate, gbc_lbllicensePlate);
		
		JLabel lblUnlockPomp = new JLabel("Unlock Pomp");
		lblUnlockPomp.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblUnlockPomp = new GridBagConstraints();
		gbc_lblUnlockPomp.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnlockPomp.gridx = 7;
		gbc_lblUnlockPomp.gridy = 1;
		frame.getContentPane().add(lblUnlockPomp, gbc_lblUnlockPomp);
		
		JLabel lblClientPayment = new JLabel("Client Payment");
		lblClientPayment.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblClientPayment = new GridBagConstraints();
		gbc_lblClientPayment.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientPayment.gridx = 8;
		gbc_lblClientPayment.gridy = 1;
		frame.getContentPane().add(lblClientPayment, gbc_lblClientPayment);
		
		JButton pomp1 = new JButton("1");
		GridBagConstraints gbc_pomp1 = new GridBagConstraints();
		gbc_pomp1.insets = new Insets(0, 0, 5, 5);
		gbc_pomp1.gridx = 1;
		gbc_pomp1.gridy = 2;
		frame.getContentPane().add(pomp1, gbc_pomp1);
		
		//Displays how much liters the customer will tank at pomp1
		p1Liters = new JTextField();
		p1Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp1: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p1ComboBox.getSelectedIndex() == 0 || p1ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p1ComboBox, "Please select a oil type first for pomp 1", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//Calculate how much the customer must pay if ** liters is entered in the system
						showLiters = Double.parseDouble(p1Liters.getText()) * oilPrice;
		         		p1Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
					} catch (NumberFormatException e) {
					
					}			
				}
				
			}
		});
		GridBagConstraints gbc_p1Liters = new GridBagConstraints();
		gbc_p1Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p1Liters.gridx = 3;
		gbc_p1Liters.gridy = 2;
		frame.getContentPane().add(p1Liters, gbc_p1Liters);
		p1Liters.setColumns(10);
		
		//Displays how much SRD the customer wants to tank at pomp1
		p1Amount = new JTextField();
		p1Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp 1: If the user start typing in the amount in pomp1 amount field and no oil type is selected a error message should pop up telling them to select oil type
				if (p1ComboBox.getSelectedIndex() == 0 || p1ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p1ComboBox, "Please select a oil type first for pomp 1", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p1Amount.getText()) / oilPrice;
		         		//String test = String.valueOf(show);
		         		p1Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e) {
						
					}
				}
				
			}
		});
		GridBagConstraints gbc_p1Amount = new GridBagConstraints();
		gbc_p1Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p1Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1Amount.gridx = 4;
		gbc_p1Amount.gridy = 2;
		frame.getContentPane().add(p1Amount, gbc_p1Amount);
		p1Amount.setColumns(10);
		
		//Show 3 different oil types for pomp1
		p1ComboBox = new JComboBox<Object>();
		p1ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Oil price can be calculated based on the selected oil price
				if(p1ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p1ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p1ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p1ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p1ComboBox = new GridBagConstraints();
		gbc_p1ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p1ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1ComboBox.gridx = 5;
		gbc_p1ComboBox.gridy = 2;
		frame.getContentPane().add(p1ComboBox, gbc_p1ComboBox);
		
		p1KenNum = new JTextField();
		GridBagConstraints gbc_p1KenNum = new GridBagConstraints();
		gbc_p1KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p1KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p1KenNum.gridx = 6;
		gbc_p1KenNum.gridy = 2;
		frame.getContentPane().add(p1KenNum, gbc_p1KenNum);
		p1KenNum.setColumns(10);
		
		JToggleButton p1Toggle = new JToggleButton("Locked");
		p1Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p1Toggle.isSelected()) {
					p1Toggle.setText("Unlocked");
				} else {
					p1Toggle.setText("Locked");	
				}
				
				Double enteredCashAmount = Double.parseDouble(p1Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p1Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {

										String paid = df.format(counter1);
										String liter = df.format(counter2);
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								
								counter1 = counter1 + 0.01;
								counter2 = counter2 + 0.01;
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				new Thread(runnable).start();// Start thread named runnable
			}
		
		});
		GridBagConstraints gbc_p1Toggle = new GridBagConstraints();
		gbc_p1Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p1Toggle.gridx = 7;
		gbc_p1Toggle.gridy = 2;
		frame.getContentPane().add(p1Toggle, gbc_p1Toggle);
		
		//If paid button is click the tank information is send to text area
		p1PaidBtn = new JButton("Paid");
		p1PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p1Amount.getText().length() > 0 && p1Liters.getText().length() > 0 && p1KenNum.getText().length() > 0) {
					textArea.setText("Customer Paid: SRD " + p1Amount.getText() + ". Pomp1 is ready to be used.");
					//pomp1Toggle.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(p1PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}			
			
     		}
		});
		
		GridBagConstraints gbc_p1PaidBtn = new GridBagConstraints();
		gbc_p1PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p1PaidBtn.gridx = 8;
		gbc_p1PaidBtn.gridy = 2;
		frame.getContentPane().add(p1PaidBtn, gbc_p1PaidBtn);
		
		JButton pomp2 = new JButton("2");
		GridBagConstraints gbc_pomp2 = new GridBagConstraints();
		gbc_pomp2.insets = new Insets(0, 0, 5, 5);
		gbc_pomp2.gridx = 1;
		gbc_pomp2.gridy = 3;
		frame.getContentPane().add(pomp2, gbc_pomp2);
		
		p2Liters = new JTextField();
		p2Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp 2: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p2ComboBox.getSelectedIndex() == 0 || p2ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p2ComboBox, "Please select a oil type first for pomp 2", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
				try {
					//Calculate how much the customer must pay if ** liters is entered in the system
					showLiters = Double.parseDouble(p2Liters.getText()) * oilPrice;
	         		p2Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Oops, something went wrong. Try Again.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			}
		});
		GridBagConstraints gbc_p2Liters = new GridBagConstraints();
		gbc_p2Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p2Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2Liters.gridx = 3;
		gbc_p2Liters.gridy = 3;
		frame.getContentPane().add(p2Liters, gbc_p2Liters);
		p2Liters.setColumns(10);
		
		p2Amount = new JTextField();
		p2Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//If you start typing in the pomp2 Amount field and no oil type is selected an pop up error should show
				if (p2ComboBox.getSelectedIndex() == 0 || p2ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p2ComboBox, "Please select a oil type first for pomp 2", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}	else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p2Amount.getText()) / oilPrice;
						p2Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Oops, something went wrong. Try Again.", "Warning", JOptionPane.WARNING_MESSAGE);
				
				}

					}
			}
		});
		GridBagConstraints gbc_p2Amount = new GridBagConstraints();
		gbc_p2Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p2Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2Amount.gridx = 4;
		gbc_p2Amount.gridy = 3;
		frame.getContentPane().add(p2Amount, gbc_p2Amount);
		p2Amount.setColumns(10);
		
		p2ComboBox = new JComboBox<Object>();
		p2ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Depending on the selected oil type a price will be added to variable oilPrice
				if(p2ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p2ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p2ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p2ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p2ComboBox = new GridBagConstraints();
		gbc_p2ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p2ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2ComboBox.gridx = 5;
		gbc_p2ComboBox.gridy = 3;
		frame.getContentPane().add(p2ComboBox, gbc_p2ComboBox);
		
		p2KenNum = new JTextField();
		GridBagConstraints gbc_p2KenNum = new GridBagConstraints();
		gbc_p2KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p2KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p2KenNum.gridx = 6;
		gbc_p2KenNum.gridy = 3;
		frame.getContentPane().add(p2KenNum, gbc_p2KenNum);
		p2KenNum.setColumns(10);
		
		JToggleButton p2Toggle = new JToggleButton("Locked");
		p2Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p2Toggle.isSelected()) {
					p2Toggle.setText("Unlocked");
				} else {
					p2Toggle.setText("Locked");	
				}
				Double enteredCashAmount = Double.parseDouble(p2Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p2Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				//Method that automatically counts to the amount paid
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {
										//Limit counter to 2 decimals and assign output to string paid and liter
										String paid = df.format(counter1);
										String liter = df.format(counter2);
										//show amount 
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								//updates the counter variable with 0.01
								
								counter1 += 0.01;
								counter2 += 0.01;
								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							//if counter amount is higher or equal to entered amount it will stop counting
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				//Thread that starts the method runnable
				new Thread(runnable).start();
			}
		});
		GridBagConstraints gbc_p2Toggle = new GridBagConstraints();
		gbc_p2Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p2Toggle.gridx = 7;
		gbc_p2Toggle.gridy = 3;
		frame.getContentPane().add(p2Toggle, gbc_p2Toggle);
		
		JButton p2PaidBtn = new JButton("Paid");
		p2PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p2Amount.getText().length() > 0 && p2Liters.getText().length() > 0 && p2KenNum.getText().length() > 0) {
					//textArea.setText("Customer Paid: SRD " + p2Amount.getText() + ". Pomp2 is ready to be used.");
					textArea.append("\n Customer Paid: SRD " + p2Amount.getText() + ". Pomp2 is ready to be used.");
				} else {
					JOptionPane.showMessageDialog(p2PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}
				
			}
		});
		GridBagConstraints gbc_p2PaidBtn = new GridBagConstraints();
		gbc_p2PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p2PaidBtn.gridx = 8;
		gbc_p2PaidBtn.gridy = 3;
		frame.getContentPane().add(p2PaidBtn, gbc_p2PaidBtn);
		
		JButton pomp3 = new JButton("3");
		GridBagConstraints gbc_pomp3 = new GridBagConstraints();
		gbc_pomp3.insets = new Insets(0, 0, 5, 5);
		gbc_pomp3.gridx = 1;
		gbc_pomp3.gridy = 4;
		frame.getContentPane().add(pomp3, gbc_pomp3);
		
		p3Liters = new JTextField();
		p3Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp3: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p3ComboBox.getSelectedIndex() == 0 || p3ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p3ComboBox, "Please select a oil type first for pomp 3", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//Calculate how much the customer must pay if ** liters is entered in the system
						showLiters = Double.parseDouble(p3Liters.getText()) * oilPrice;
		         		p3Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
					} catch (NumberFormatException e) {
					
					}			
				}
				
			}
		});
		GridBagConstraints gbc_p3Liters = new GridBagConstraints();
		gbc_p3Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p3Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p3Liters.gridx = 3;
		gbc_p3Liters.gridy = 4;
		frame.getContentPane().add(p3Liters, gbc_p3Liters);
		p3Liters.setColumns(10);
		
		p3Amount = new JTextField();
		p3Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//If you start typing in the pomp3 Amount field and no oil type is selected an pop up error should show
				if (p3ComboBox.getSelectedIndex() == 0 || p3ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p3ComboBox, "Please select a oil type first for pomp 3", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}	else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p3Amount.getText()) / oilPrice;
						p3Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e1) {
				
				}

					}
			}
		});
		GridBagConstraints gbc_p3Amount = new GridBagConstraints();
		gbc_p3Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p3Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p3Amount.gridx = 4;
		gbc_p3Amount.gridy = 4;
		frame.getContentPane().add(p3Amount, gbc_p3Amount);
		p3Amount.setColumns(10);
		
		p3ComboBox = new JComboBox<Object>();
		p3ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Depending on the selected oil type a price will be added to variable oilPrice
				if(p3ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p3ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p3ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p3ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p3ComboBox = new GridBagConstraints();
		gbc_p3ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p3ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p3ComboBox.gridx = 5;
		gbc_p3ComboBox.gridy = 4;
		frame.getContentPane().add(p3ComboBox, gbc_p3ComboBox);
		
		p3KenNum = new JTextField();
		GridBagConstraints gbc_p3KenNum = new GridBagConstraints();
		gbc_p3KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p3KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p3KenNum.gridx = 6;
		gbc_p3KenNum.gridy = 4;
		frame.getContentPane().add(p3KenNum, gbc_p3KenNum);
		p3KenNum.setColumns(10);
		
		JToggleButton p3Toggle = new JToggleButton("Locked");
		p3Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p3Toggle.isSelected()) {
					p3Toggle.setText("Unlocked");
				} else {
					p3Toggle.setText("Locked");	
				}
		
				Double enteredCashAmount = Double.parseDouble(p3Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p3Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				//Method that automatically counts to the amount paid
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {

										String paid = df.format(counter1);//Limit counter to 2 decimals and assign output to string paid
										String liter = df.format(counter2);// limit counter
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								//updates the counter variable with 0.01
								
								counter1 += 0.01;
								counter2 += 0.01;
								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							//if counter amount is higher or equal to entered amount it will stop counting
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				//Thread that starts the method runnable
				new Thread(runnable).start();
			
			}
		});
		GridBagConstraints gbc_p3Toggle = new GridBagConstraints();
		gbc_p3Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p3Toggle.gridx = 7;
		gbc_p3Toggle.gridy = 4;
		frame.getContentPane().add(p3Toggle, gbc_p3Toggle);
		
		JButton p3PaidBtn = new JButton("Paid");
		p3PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p3Amount.getText().length() > 0 && p3Liters.getText().length() > 0 && p3KenNum.getText().length() > 0) {
					textArea.setText("Customer Paid: SRD " + p3Amount.getText() + ". Pomp 3 is ready to be used.");
					//pomp1Toggle.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(p3PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}
				
			}
		});
		GridBagConstraints gbc_p3PaidBtn = new GridBagConstraints();
		gbc_p3PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p3PaidBtn.gridx = 8;
		gbc_p3PaidBtn.gridy = 4;
		frame.getContentPane().add(p3PaidBtn, gbc_p3PaidBtn);
		
		JButton pomp4 = new JButton("4");
		GridBagConstraints gbc_pomp4 = new GridBagConstraints();
		gbc_pomp4.insets = new Insets(0, 0, 5, 5);
		gbc_pomp4.gridx = 1;
		gbc_pomp4.gridy = 5;
		frame.getContentPane().add(pomp4, gbc_pomp4);
		
		p4Liters = new JTextField();
		p4Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pom4: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p4ComboBox.getSelectedIndex() == 0 || p4ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p4ComboBox, "Please select a oil type first for pomp 4", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//Calculate how much the customer must pay if ** liters is entered in the system
						showLiters = Double.parseDouble(p4Liters.getText()) * oilPrice;
		         		p4Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
					} catch (NumberFormatException e) {
					
					}			
				}
				
			}
		});
		p4Liters.setColumns(10);
		GridBagConstraints gbc_p4Liters = new GridBagConstraints();
		gbc_p4Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p4Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p4Liters.gridx = 3;
		gbc_p4Liters.gridy = 5;
		frame.getContentPane().add(p4Liters, gbc_p4Liters);
		
		p4Amount = new JTextField();
		p4Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//If you start typing in the pomp4 Amount field and no oil type is selected an pop up error should show
				if (p4ComboBox.getSelectedIndex() == 0 || p4ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p4ComboBox, "Please select a oil type first for pomp 4", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}	else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p4Amount.getText()) / oilPrice;
						p4Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e1) {
				
				}

					}
			}
		});
		p4Amount.setColumns(10);
		GridBagConstraints gbc_p4Amount = new GridBagConstraints();
		gbc_p4Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p4Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p4Amount.gridx = 4;
		gbc_p4Amount.gridy = 5;
		frame.getContentPane().add(p4Amount, gbc_p4Amount);
		
		p4ComboBox = new JComboBox<Object>();
		p4ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Depending on the selected oil type a price will be added to variable oilPrice
				if(p4ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p4ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p4ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p4ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p4ComboBox = new GridBagConstraints();
		gbc_p4ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p4ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p4ComboBox.gridx = 5;
		gbc_p4ComboBox.gridy = 5;
		frame.getContentPane().add(p4ComboBox, gbc_p4ComboBox);
		
		p4KenNum = new JTextField();
		GridBagConstraints gbc_p4KenNum = new GridBagConstraints();
		gbc_p4KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p4KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p4KenNum.gridx = 6;
		gbc_p4KenNum.gridy = 5;
		frame.getContentPane().add(p4KenNum, gbc_p4KenNum);
		p4KenNum.setColumns(10);
		
		JToggleButton p4Toggle = new JToggleButton("Locked");
		p4Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p4Toggle.isSelected()) {
					p4Toggle.setText("Unlocked");
				} else {
					p4Toggle.setText("Locked");	
				}
				Double enteredCashAmount = Double.parseDouble(p4Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p4Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				//Method that automatically counts to the amount paid
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {

										String paid = df.format(counter1);//Limit counter to 2 decimals and assign output to string paid
										String liter = df.format(counter2);// limit counter
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								//updates the counter variable with 0.01
								
								counter1 += 0.01;
								counter2 += 0.01;
								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							//if counter amount is higher or equal to entered amount it will stop counting
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				//Thread that starts the method runnable
				new Thread(runnable).start();
					
			}
		});
		GridBagConstraints gbc_p4Toggle = new GridBagConstraints();
		gbc_p4Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p4Toggle.gridx = 7;
		gbc_p4Toggle.gridy = 5;
		frame.getContentPane().add(p4Toggle, gbc_p4Toggle);
		
		JButton p4PaidBtn = new JButton("Paid");
		p4PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p4Amount.getText().length() > 0 && p4Liters.getText().length() > 0 && p4KenNum.getText().length() > 0) {
					textArea.setText("Customer Paid: SRD " + p4Amount.getText() + ". Pomp 4 is ready to be used.");
					//pomp1Toggle.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(p4PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}
				
			}
		});
		GridBagConstraints gbc_p4PaidBtn = new GridBagConstraints();
		gbc_p4PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p4PaidBtn.gridx = 8;
		gbc_p4PaidBtn.gridy = 5;
		frame.getContentPane().add(p4PaidBtn, gbc_p4PaidBtn);
		
		JButton pomp5 = new JButton("5");
		GridBagConstraints gbc_pomp5 = new GridBagConstraints();
		gbc_pomp5.insets = new Insets(0, 0, 5, 5);
		gbc_pomp5.gridx = 1;
		gbc_pomp5.gridy = 6;
		frame.getContentPane().add(pomp5, gbc_pomp5);
		
		p5Liters = new JTextField();
		p5Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp5: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p5ComboBox.getSelectedIndex() == 0 || p5ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p5ComboBox, "Please select a oil type first for pomp 5", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//Calculate how much the customer must pay if ** liters is entered in the system
						showLiters = Double.parseDouble(p5Liters.getText()) * oilPrice;
		         		p5Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
					} catch (NumberFormatException e) {
					
					}			
				}
				
			}
		});
		GridBagConstraints gbc_p5Liters = new GridBagConstraints();
		gbc_p5Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p5Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p5Liters.gridx = 3;
		gbc_p5Liters.gridy = 6;
		frame.getContentPane().add(p5Liters, gbc_p5Liters);
		p5Liters.setColumns(10);
		
		p5Amount = new JTextField();
		p5Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//If you start typing in the pomp5 Amount field and no oil type is selected an pop up error should show
				if (p5ComboBox.getSelectedIndex() == 0 || p5ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p5ComboBox, "Please select a oil type first for pomp 5", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}	else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p5Amount.getText()) / oilPrice;
						p5Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e1) {
				
				}

					}
			}
		});
		GridBagConstraints gbc_p5Amount = new GridBagConstraints();
		gbc_p5Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p5Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p5Amount.gridx = 4;
		gbc_p5Amount.gridy = 6;
		frame.getContentPane().add(p5Amount, gbc_p5Amount);
		p5Amount.setColumns(10);
		
		p5ComboBox = new JComboBox<Object>();
		p5ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Depending on the selected oil type a price will be added to variable oilPrice
				if(p5ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p5ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p5ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p5ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p5ComboBox = new GridBagConstraints();
		gbc_p5ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p5ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p5ComboBox.gridx = 5;
		gbc_p5ComboBox.gridy = 6;
		frame.getContentPane().add(p5ComboBox, gbc_p5ComboBox);
		
		p5KenNum = new JTextField();
		GridBagConstraints gbc_p5KenNum = new GridBagConstraints();
		gbc_p5KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p5KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p5KenNum.gridx = 6;
		gbc_p5KenNum.gridy = 6;
		frame.getContentPane().add(p5KenNum, gbc_p5KenNum);
		p5KenNum.setColumns(10);
		
		JToggleButton p5Toggle = new JToggleButton("Locked");
		p5Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p5Toggle.isSelected()) {
					p5Toggle.setText("Unlocked");
				} else {
					p5Toggle.setText("Locked");	
				}
				Double enteredCashAmount = Double.parseDouble(p5Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p5Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				//Method that automatically counts to the amount paid
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {

										String paid = df.format(counter1);//Limit counter to 2 decimals and assign output to string paid
										String liter = df.format(counter2);// limit counter
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								//updates the counter variable with 0.01
								
								counter1 += 0.01;
								counter2 += 0.01;
								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							//if counter amount is higher or equal to entered amount it will stop counting
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				//Thread that starts the method runnable
				new Thread(runnable).start();
			}
		});
		GridBagConstraints gbc_p5Toggle = new GridBagConstraints();
		gbc_p5Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p5Toggle.gridx = 7;
		gbc_p5Toggle.gridy = 6;
		frame.getContentPane().add(p5Toggle, gbc_p5Toggle);
		
		JButton p5PaidBtn = new JButton("Paid");
		p5PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p5Amount.getText().length() > 0 && p5Liters.getText().length() > 0 && p5KenNum.getText().length() > 0) {
					textArea.setText("Customer Paid: SRD " + p5Amount.getText() + ". Pomp 5 is ready to be used.");
					//pomp1Toggle.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(p5PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}
				
			}
		});
		GridBagConstraints gbc_p5PaidBtn = new GridBagConstraints();
		gbc_p5PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p5PaidBtn.gridx = 8;
		gbc_p5PaidBtn.gridy = 6;
		frame.getContentPane().add(p5PaidBtn, gbc_p5PaidBtn);
		
		JButton pomp6 = new JButton("6");
		GridBagConstraints gbc_pomp6 = new GridBagConstraints();
		gbc_pomp6.insets = new Insets(0, 0, 5, 5);
		gbc_pomp6.gridx = 1;
		gbc_pomp6.gridy = 7;
		frame.getContentPane().add(pomp6, gbc_pomp6);
		
		p6Liters = new JTextField();
		p6Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp6: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p6ComboBox.getSelectedIndex() == 0 || p6ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p6ComboBox, "Please select a oil type first for pomp 6", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//Calculate how much the customer must pay if ** liters is entered in the system
						showLiters = Double.parseDouble(p6Liters.getText()) * oilPrice;
		         		p6Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
					} catch (NumberFormatException e) {
					
					}			
				}
				
			}
		});
		GridBagConstraints gbc_p6Liters = new GridBagConstraints();
		gbc_p6Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p6Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p6Liters.gridx = 3;
		gbc_p6Liters.gridy = 7;
		frame.getContentPane().add(p6Liters, gbc_p6Liters);
		p6Liters.setColumns(10);
		
		p6Amount = new JTextField();
		p6Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//If you start typing in the pomp6 Amount field and no oil type is selected an pop up error should show
				if (p6ComboBox.getSelectedIndex() == 0 || p6ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p6ComboBox, "Please select a oil type first for pomp 6", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}	else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p6Amount.getText()) / oilPrice;
						p6Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e1) {
				
				}

					}
			}
		});
		GridBagConstraints gbc_p6Amount = new GridBagConstraints();
		gbc_p6Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p6Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p6Amount.gridx = 4;
		gbc_p6Amount.gridy = 7;
		frame.getContentPane().add(p6Amount, gbc_p6Amount);
		p6Amount.setColumns(10);
		
		p6ComboBox = new JComboBox<Object>();
		p6ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Depending on the selected oil type a price will be added to variable oilPrice
				if(p6ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p6ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p6ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p6ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p6ComboBox = new GridBagConstraints();
		gbc_p6ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p6ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p6ComboBox.gridx = 5;
		gbc_p6ComboBox.gridy = 7;
		frame.getContentPane().add(p6ComboBox, gbc_p6ComboBox);
		
		p6KenNum = new JTextField();
		GridBagConstraints gbc_p6KenNum = new GridBagConstraints();
		gbc_p6KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p6KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p6KenNum.gridx = 6;
		gbc_p6KenNum.gridy = 7;
		frame.getContentPane().add(p6KenNum, gbc_p6KenNum);
		p6KenNum.setColumns(10);
		
		JToggleButton p6Toggle = new JToggleButton("Locked");
		p6Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p6Toggle.isSelected()) {
					p6Toggle.setText("Unlocked");
				} else {
					p6Toggle.setText("Locked");	
				}
				Double enteredCashAmount = Double.parseDouble(p6Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p6Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				//Method that automatically counts to the amount paid
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {

										String paid = df.format(counter1);//Limit counter to 2 decimals and assign output to string paid
										String liter = df.format(counter2);// limit counter
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								//updates the counter variable with 0.01
								
								counter1 += 0.01;
								counter2 += 0.01;
								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							//if counter amount is higher or equal to entered amount it will stop counting
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				//Thread that starts the method runnable
				new Thread(runnable).start();
			}
		});
		GridBagConstraints gbc_p6Toggle = new GridBagConstraints();
		gbc_p6Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p6Toggle.gridx = 7;
		gbc_p6Toggle.gridy = 7;
		frame.getContentPane().add(p6Toggle, gbc_p6Toggle);
		
		JButton p6PaidBtn = new JButton("Paid");
		p6PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p6Amount.getText().length() > 0 && p6Liters.getText().length() > 0 && p6KenNum.getText().length() > 0) {
					textArea.setText("Customer Paid: SRD " + p6Amount.getText() + ". Pomp 6 is ready to be used.");
					//pomp1Toggle.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(p6PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}
				
			}
		});
		GridBagConstraints gbc_p6PaidBtn = new GridBagConstraints();
		gbc_p6PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p6PaidBtn.gridx = 8;
		gbc_p6PaidBtn.gridy = 7;
		frame.getContentPane().add(p6PaidBtn, gbc_p6PaidBtn);
		
		JButton pomp7 = new JButton("7");
		GridBagConstraints gbc_pomp7 = new GridBagConstraints();
		gbc_pomp7.insets = new Insets(0, 0, 5, 5);
		gbc_pomp7.gridx = 1;
		gbc_pomp7.gridy = 8;
		frame.getContentPane().add(pomp7, gbc_pomp7);
		
		p7Liters = new JTextField();
		p7Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp 7: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p7ComboBox.getSelectedIndex() == 0 || p7ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p7ComboBox, "Please select a oil type first for pomp 7", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//Calculate how much the customer must pay if ** liters is entered in the system
						showLiters = Double.parseDouble(p7Liters.getText()) * oilPrice;
		         		p7Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
					} catch (NumberFormatException e) {
					
					}			
				}
				
			}
		});
		GridBagConstraints gbc_p7Liters = new GridBagConstraints();
		gbc_p7Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p7Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p7Liters.gridx = 3;
		gbc_p7Liters.gridy = 8;
		frame.getContentPane().add(p7Liters, gbc_p7Liters);
		p7Liters.setColumns(10);
		
		p7Amount = new JTextField();
		p7Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//If you start typing in the pomp7Amount field and no oil type is selected an pop up error should show
				if (p7ComboBox.getSelectedIndex() == 0 || p7ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p7ComboBox, "Please select a oil type first for pomp 7", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}	else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p7Amount.getText()) / oilPrice;
						p7Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e1) {
				
				}

					}
			}
		});
		GridBagConstraints gbc_p7Amount = new GridBagConstraints();
		gbc_p7Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p7Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p7Amount.gridx = 4;
		gbc_p7Amount.gridy = 8;
		frame.getContentPane().add(p7Amount, gbc_p7Amount);
		p7Amount.setColumns(10);
		
		p7ComboBox = new JComboBox<Object>();
		p7ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Depending on the selected oil type a price will be added to variable oilPrice
				if(p7ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p7ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p7ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p7ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p7ComboBox = new GridBagConstraints();
		gbc_p7ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p7ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p7ComboBox.gridx = 5;
		gbc_p7ComboBox.gridy = 8;
		frame.getContentPane().add(p7ComboBox, gbc_p7ComboBox);
		
		p7KenNum = new JTextField();
		GridBagConstraints gbc_p7KenNum = new GridBagConstraints();
		gbc_p7KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p7KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p7KenNum.gridx = 6;
		gbc_p7KenNum.gridy = 8;
		frame.getContentPane().add(p7KenNum, gbc_p7KenNum);
		p7KenNum.setColumns(10);
		
		JToggleButton p7Toggle = new JToggleButton("Locked");
		p7Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p7Toggle.isSelected()) {
					p7Toggle.setText("Unlocked");
				} else {
					p7Toggle.setText("Locked");	
				}
				Double enteredCashAmount = Double.parseDouble(p7Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p7Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				//Method that automatically counts to the amount paid
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {

										String paid = df.format(counter1);//Limit counter to 2 decimals and assign output to string paid
										String liter = df.format(counter2);// limit counter
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								//updates the counter variable with 0.01
								
								counter1 += 0.01;
								counter2 += 0.01;
								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							//if counter amount is higher or equal to entered amount it will stop counting
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				//Thread that starts the method runnable
				new Thread(runnable).start();	
			}
		});
		GridBagConstraints gbc_p7Toggle = new GridBagConstraints();
		gbc_p7Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p7Toggle.gridx = 7;
		gbc_p7Toggle.gridy = 8;
		frame.getContentPane().add(p7Toggle, gbc_p7Toggle);
		
		JButton p7PaidBtn = new JButton("Paid");
		p7PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p7Amount.getText().length() > 0 && p7Liters.getText().length() > 0 && p7KenNum.getText().length() > 0) {
					textArea.setText("Customer Paid: SRD " + p7Amount.getText() + ". Pomp 7 is ready to be used.");
					//pomp1Toggle.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(p7PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}
				
			}
		});
		GridBagConstraints gbc_p7PaidBtn = new GridBagConstraints();
		gbc_p7PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p7PaidBtn.gridx = 8;
		gbc_p7PaidBtn.gridy = 8;
		frame.getContentPane().add(p7PaidBtn, gbc_p7PaidBtn);
		
		JButton pomp8 = new JButton("8");
		GridBagConstraints gbc_pomp8 = new GridBagConstraints();
		gbc_pomp8.insets = new Insets(0, 0, 5, 5);
		gbc_pomp8.gridx = 1;
		gbc_pomp8.gridy = 9;
		frame.getContentPane().add(pomp8, gbc_pomp8);
		
		p8Liters = new JTextField();
		p8Liters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//for pomp8: If the user start typing in the liters in p1Liters field and no oil type is selected a error message should pop up telling them to select oil type
				if (p8ComboBox.getSelectedIndex() == 0 || p8ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p8ComboBox, "Please select a oil type first for pomp 8", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//Calculate how much the customer must pay if ** liters is entered in the system
						showLiters = Double.parseDouble(p8Liters.getText()) * oilPrice;
		         		p8Amount.setText(String.format("%.2f", showLiters));//displays how much the customer needs to pay for x liters
					} catch (NumberFormatException e) {
					
					}			
				}
				
			}
		});
		GridBagConstraints gbc_p8Liters = new GridBagConstraints();
		gbc_p8Liters.insets = new Insets(0, 0, 5, 5);
		gbc_p8Liters.fill = GridBagConstraints.HORIZONTAL;
		gbc_p8Liters.gridx = 3;
		gbc_p8Liters.gridy = 9;
		frame.getContentPane().add(p8Liters, gbc_p8Liters);
		p8Liters.setColumns(10);
		
		p8Amount = new JTextField();
		p8Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//If you start typing in the pomp8Amount field and no oil type is selected an pop up error should show
				if (p8ComboBox.getSelectedIndex() == 0 || p8ComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(p8ComboBox, "Please select a oil type first for pomp 8", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}	else {
					try {
						//Calculate how many liters the customer will get if they pay a specific amount
						showAmount = Double.parseDouble(p8Amount.getText()) / oilPrice;
						p8Liters.setText(String.format("%.2f", showAmount));//displays how many liters to customer will get
					} catch (NumberFormatException e1) {
				
				}

					}
			}
		});
		GridBagConstraints gbc_p8Amount = new GridBagConstraints();
		gbc_p8Amount.insets = new Insets(0, 0, 5, 5);
		gbc_p8Amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_p8Amount.gridx = 4;
		gbc_p8Amount.gridy = 9;
		frame.getContentPane().add(p8Amount, gbc_p8Amount);
		p8Amount.setColumns(10);
		
		p8ComboBox = new JComboBox<Object>();
		p8ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Depending on the selected oil type a price will be added to variable oilPrice
				if(p8ComboBox.getSelectedItem().toString().equals("Diesel")) {
					oilPrice = 6.06;//Diesel price
				} else if (p8ComboBox.getSelectedItem().toString().equals("Unleaded")) {
					oilPrice = 6.52;//Unleaded price
				} else if (p8ComboBox.getSelectedItem().toString().equals("SuperUnleaded")) {
					oilPrice = 6.72;//Super unleaded price
				} 
			}
		});
		p8ComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Diesel", "Unleaded", "SuperUnleaded"}));
		GridBagConstraints gbc_p8ComboBox = new GridBagConstraints();
		gbc_p8ComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_p8ComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_p8ComboBox.gridx = 5;
		gbc_p8ComboBox.gridy = 9;
		frame.getContentPane().add(p8ComboBox, gbc_p8ComboBox);
		
		p8KenNum = new JTextField();
		GridBagConstraints gbc_p8KenNum = new GridBagConstraints();
		gbc_p8KenNum.insets = new Insets(0, 0, 5, 5);
		gbc_p8KenNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_p8KenNum.gridx = 6;
		gbc_p8KenNum.gridy = 9;
		frame.getContentPane().add(p8KenNum, gbc_p8KenNum);
		p8KenNum.setColumns(10);
		
		JToggleButton p8Toggle = new JToggleButton("Locked");
		p8Toggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If toggle button is pressed it will Show unlocked, when pressed again it will change to Locked
				if(p8Toggle.isSelected()) {
					p8Toggle.setText("Unlocked");
				} else {
					p8Toggle.setText("Locked");	
				}
				Double enteredCashAmount = Double.parseDouble(p8Amount.getText());//Get the entered string value from pomp amount field and convert it to a integer
				Double enteredLiterAmount = Double.parseDouble(p8Liters.getText());//Get the entered string value from pomp liters field and convert it to double
				
				//Method that automatically counts to the amount paid
				Runnable runnable = new Runnable() {
					Double counter1 = 0.00;
					Double counter2 = 0.00;
					
					public void run() {
						while(true) {
							
							while (counter1 < enteredCashAmount || counter2 < enteredLiterAmount) {
								
								SwingUtilities.invokeLater (new Runnable() {
									public void run() {

										String paid = df.format(counter1);//Limit counter to 2 decimals and assign output to string paid
										String liter = df.format(counter2);// limit counter
										p1Amount.setText(paid);
										p1Liters.setText(liter);
																				
									}
								});
								//updates the counter variable with 0.01
								
								counter1 += 0.01;
								counter2 += 0.01;
								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException ex) {
									System.err.println(ex.toString());
								}
							}
							//if counter amount is higher or equal to entered amount it will stop counting
							if(counter1 >= enteredCashAmount || counter2 >= enteredLiterAmount) {
								break;
							}
							counter1 = 0.00;
							counter2 = 0.00;
						}
					}
				};
				
				//Thread that starts the method runnable
				new Thread(runnable).start();
			}
		});
		GridBagConstraints gbc_p8Toggle = new GridBagConstraints();
		gbc_p8Toggle.insets = new Insets(0, 0, 5, 5);
		gbc_p8Toggle.gridx = 7;
		gbc_p8Toggle.gridy = 9;
		frame.getContentPane().add(p8Toggle, gbc_p8Toggle);
		
		JButton p8PaidBtn = new JButton("Paid");
		p8PaidBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if liter, amount field and licensePlate is not filled in it will produce an error message 
				if(p8Amount.getText().length() > 0 && p8Liters.getText().length() > 0 && p8KenNum.getText().length() > 0) {
					textArea.setText("Customer Paid: SRD " + p8Amount.getText() + ". Pomp 8 is ready to be used.");
					//pomp1Toggle.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(p8PaidBtn, "Amount, Liters or licensePlate is not filled in.");
				}
				
			}
		});
		GridBagConstraints gbc_p8PaidBtn = new GridBagConstraints();
		gbc_p8PaidBtn.insets = new Insets(0, 0, 5, 5);
		gbc_p8PaidBtn.gridx = 8;
		gbc_p8PaidBtn.gridy = 9;
		frame.getContentPane().add(p8PaidBtn, gbc_p8PaidBtn);
		
		//Shows oil prices
		JTable table = new JTable();
		table.setBackground(new Color(21, 25, 40));
		table.setForeground(Color.LIGHT_GRAY);
		table.setShowGrid(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Diesel", Diesel},
				{"Unleaded", Unleaded},
				{"SuperUnleaded", SuperUnleaded},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(89);
		
		//Tab
		
		//Table name
		JLabel label = new JLabel("Oil Prices");
		label.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 11;
		frame.getContentPane().add(label, gbc_label);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 2;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 2;
		gbc_table.gridy = 12;
		frame.getContentPane().add(table, gbc_table);
		
		//Displays when the pomp is running, stopped or is unlocked
		textArea = new JTextArea();
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 3;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 5;
		gbc_textArea.gridy = 12;
		frame.getContentPane().add(textArea, gbc_textArea);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmShowOilPrice = new JMenuItem("Show Oil Price");
		mnFile.add(mntmShowOilPrice);
		
		mntmChangeOilPrice = new JMenuItem("Change Oil Price");
		//Open window to change oil price
		mntmChangeOilPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();//closes login screen
				Prices prices = new Prices(); //Create object of second window
				prices.setVisible(true);
				//prices.revalidate();
				//prices.repaint();
				
			}
		});
		mnFile.add(mntmChangeOilPrice);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
	}


}
