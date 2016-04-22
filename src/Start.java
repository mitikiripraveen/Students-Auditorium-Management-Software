import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager.*;
import java.sql.*;

public class Start 
{

	private JFrame frmSams;
	
	private static DataBase db = new DataBase();
	
	private static int b1_num = 0;
	private static int b2_num = 0;
	private static int b3_num = 0;
	private static int b4_num = 0;
	private static int b5_num = 0;
	
	private static int s = 0, y = 0;
	
	private static ArrayList<String> showNames; 
	private static Set<String> shows;
	
	private static ArrayList<String> showDates;
	private static Set<String> dates;
	
	private static ArrayList<String> showTimes;
	private static Set<String> times;
	
	private static ArrayList<String> balanceSheets;
	private static Set<String> balYear;
	
	private static String SPusername = "";
	private static String select = "";
	
	private static ArrayList<String> remove1, remove2;
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
			    {
			      UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
			    } 
			    catch (Exception e) 
			    {
			      e.printStackTrace();
			    }
				try 
				{
					//clear();
					
					Start window = new Start();
					window.frmSams.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Start() 
	{
		initialize();
	}
	
	
	private void initialize() 
	{
		frmSams = new JFrame();
		frmSams.setFont(new Font("Fertigo Pro", Font.BOLD | Font.PLAIN, 16));
		frmSams.setTitle("SAMS");
		frmSams.setBounds(100, 100, 517, 321);
		frmSams.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSams.getContentPane().setLayout(null);
		
		JButton seatQuery = new JButton("Seats Available");
		seatQuery.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
		seatQuery.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				select = "Initial";
				
				showNames = new ArrayList<String>();
				shows = new HashSet<String>();
				
				db.connect();
				try 
				{
					Statement smt1 = db.getConnection().createStatement();
					ResultSet rst1 = smt1.executeQuery("SELECT * FROM showdetails");
					
					while(rst1.next())
					{
						showNames.add(rst1.getString("Name"));
					}
					
					for(String st : showNames)
						shows.add(st);
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				
				ShowSelect sframe = new ShowSelect();
				sframe.setVisible(true);
				sframe.setResizable(false);
			}
		});
		seatQuery.setBounds(140, 77, 222, 56);
		frmSams.getContentPane().add(seatQuery);
		
		JButton loginButton = new JButton("Staff Login");
		loginButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{	
				Login lg = new Login();
				lg.setVisible(true);
				lg.setResizable(false);
				
			}
		});
		loginButton.setBounds(140, 180, 222, 56);
		loginButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
		frmSams.getContentPane().add(loginButton);
		
		JLabel welcomeLabel = new JLabel("Welcome to Student Auditorium Management Software!!!");
		welcomeLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 15));
		welcomeLabel.setBounds(12, 13, 475, 33);
		frmSams.getContentPane().add(welcomeLabel);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	
	public class Login extends JFrame 
	{

		private JPanel contentPane;
		private JTextField userID;
		private JPasswordField password;
		
		
		public Login() 
		{
			setTitle("SAMS : Login");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 518, 331);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel userIDLabel = new JLabel("UserID");
			userIDLabel.setBounds(61, 59, 117, 40);
			userIDLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			userIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(userIDLabel);
			
			userID = new JTextField();
			userID.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			userID.setBounds(226, 59, 182, 40);
			contentPane.add(userID);
			userID.setColumns(10);
			
			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			passwordLabel.setBounds(61, 136, 117, 40);
			passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(passwordLabel);
			
			password = new JPasswordField();
			password.setBounds(226, 136, 182, 40);
			password.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(password);
			
			JButton verifyButton = new JButton("Verify");
			verifyButton.setBounds(182, 220, 129, 40);
			verifyButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			contentPane.add(verifyButton);
			verifyButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							
							Boolean next = false;
							
							ShowManagerGUI smframe;
							SalesPersonGUI spframe;
							ShowSelect sframe;
							
							String us;
							us = userID.getText();
							String pw = new String(password.getPassword());
							
							db.connect();
							Statement stm;
							try
							{
								stm = db.getConnection().createStatement();
								ResultSet rst = stm.executeQuery("SELECT UserID, Password FROM loginid WHERE UserID = '"+us+"'");
								
								while(rst.next())
								{
									if(rst.getString("Password").equals(pw))
									{
										if(us.substring(0, 2).equals("SM"))
										{
											next = true;
											dispose();
											smframe = new ShowManagerGUI();
											smframe.setVisible(true);
											smframe.setResizable(false);
										}
										
										else if(us.substring(0, 2).equals("SP"))
										{
											SPusername = us;
											next = true;
											dispose();
											spframe = new SalesPersonGUI();
											spframe.setVisible(true);
											spframe.setResizable(false);
										}
										else if(us.substring(0, 2).equals("AC"))
										{
											select = "AccountsClerk";
											next = true;
											dispose();
											
											ACInitialGUI ac = new ACInitialGUI();
											ac.setResizable(false);
											ac.setVisible(true);
											
										}
									}
										
								}
								
								if(!next)
									JOptionPane.showMessageDialog(null, "Please Re-enter the credentials");
							}
							catch (SQLException e) 
							{
								e.printStackTrace();
							}
							
						}
					});
		}
	}
	
	public class ShowManagerGUI extends JFrame 
	{

		private JPanel contentPane;
		private JButton editShow;

		public ShowManagerGUI()
		{
			setTitle("SAMS : Show Manager");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 609, 584);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			editShow = new JButton("Add a new Show");
			editShow.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			editShow.setBounds(59, 103, 214, 54);
			contentPane.add(editShow);
			editShow.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							ShowDetails sd = new ShowDetails();
							sd.setVisible(true);
							sd.setResizable(false);
							sd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						}
					});
			
			JButton balance = new JButton("Balance Sheets");
			balance.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			balance.setBounds(338, 102, 202, 54);
			balance.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							balanceSheets = new ArrayList<String>();
							balYear = new HashSet<String>();
							String date;
							
							db.connect();
							try 
							{
								Statement smt1 = db.getConnection().createStatement();
								ResultSet rst1 = smt1.executeQuery("SELECT * FROM showdetails");
								
								while(rst1.next())
								{
									if(rst1.getString("Showdate") != null)
									{
										date = rst1.getString("Showdate");
										balanceSheets.add(date.substring(7));
									}
								}
								
								for(String st : balanceSheets)
									balYear.add(st);
							} 
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
							
							s = 0;
							y = 0;
							if(balYear.isEmpty())
								JOptionPane.showMessageDialog(null, "Please enter shows then check for balance sheets");
							else
							{
								Balance b = new Balance();
								b.setVisible(true);
								b.setResizable(false);
							}
						}
					});
			contentPane.add(balance);
			
			JButton status = new JButton("Status of Booking and Amount collected");
			status.setBounds(59, 189, 481, 54);
			status.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			contentPane.add(status);
			status.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							select = "Status";
							
							showNames = new ArrayList<String>();
							shows = new HashSet<String>();
							
							db.connect();
							try 
							{
								Statement smt1 = db.getConnection().createStatement();
								ResultSet rst1 = smt1.executeQuery("SELECT * FROM showdetails");
								
								while(rst1.next())
								{
									showNames.add(rst1.getString("Name"));
								}
								
								for(String st : showNames)
									shows.add(st);
							} 
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
							
							ShowSelect sframe = new ShowSelect();
							sframe.setVisible(true);
							sframe.setResizable(false);
						}
					});
			
			JLabel greetingLabel = new JLabel("Greetings !!!, What would you like to do..");
			greetingLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 14));
			greetingLabel.setBounds(12, 26, 358, 34);
			greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(greetingLabel);
			
			JButton addSalesPerson = new JButton("Add Sales Person");
			addSalesPerson.setBounds(338, 281, 202, 54);
			addSalesPerson.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			contentPane.add(addSalesPerson);
			addSalesPerson.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{	
							EnterSP sp = new EnterSP();
							sp.setVisible(true);
							sp.setResizable(false);
							sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						}
					});
			
			JButton removeSalesPerson = new JButton("Remove Sales Person");
			removeSalesPerson.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			removeSalesPerson.setBounds(59, 281, 214, 54);
			contentPane.add(removeSalesPerson);
			removeSalesPerson.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							remove1 = new ArrayList<String>();
							remove2 = new ArrayList<String>();
							db.connect();
							try
							{
								Statement smt = db.getConnection().createStatement();
								String sel = "SELECT * FROM loginid";
								ResultSet rst = smt.executeQuery(sel);
								while(rst.next())
								{
									if(rst.getString("UserID").length() > 2 && rst.getString("UserID").substring(0, 2).equals("SP"))
									{
										remove1.add(rst.getString("Name"));
										remove2.add(rst.getString("UserID"));
									}
								}
							}
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
							
							RemoveSP sp = new RemoveSP();
							sp.setVisible(true);
							sp.setResizable(false);
							sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						}
					});
			
			JButton salesPersonStatus = new JButton("Status of each Sales Person");
			salesPersonStatus.setBounds(59, 369, 481, 54);
			salesPersonStatus.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			contentPane.add(salesPersonStatus);
			salesPersonStatus.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							ArrayList<String> sp = new ArrayList<>();
							ArrayList<Integer> am = new ArrayList<>();
							ArrayList<Double> cm = new ArrayList<>();
							String sr = "";
							
							db.connect();
							try 
							{
								Statement smt = db.getConnection().createStatement();
								ResultSet rst = smt.executeQuery("SELECT * from loginid");
								while(rst.next())
								{
									sr = rst.getString("UserID");
									if(sr.length() > 2 && sr.substring(0, 2).equals("SP"))
									{
										sp.add(rst.getString("UserID"));
										am.add(rst.getInt("Amount"));
										cm.add(rst.getDouble("Commission"));
									}
								}
								
								String str = "";
								for(int i = 0; i < sp.size(); ++i)
								{
									str += "*********************";
									str += "\nUserId : " + sp.get(i);
									str += "\nAmount : " + am.get(i);
									str += "\nCommission : " + cm.get(i);
									str += "\n";
								}
								
								DisplayGUI d = new DisplayGUI("SAMS", str);
								d.setVisible(true);
								d.setResizable(false);
								d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								
							}
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
							
						}
					});
			
			JButton logoutButton = new JButton("Logout");
			logoutButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 14));
			logoutButton.setBounds(407, 26, 129, 34);
			contentPane.add(logoutButton);
			logoutButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
						}
					});
			
			JButton passButton = new JButton("Change Password");
			passButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			passButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					PasswordGUI p = new PasswordGUI("SM");
					p.setResizable(false);
					p.setVisible(true);
				}
			});
			passButton.setBounds(180, 457, 214, 54);
			contentPane.add(passButton);
		}

	}
	
	public class ShowDetails extends JFrame 
	{

		private JPanel contentPane;
		private JTextField bPrice;
		private JTextField oPrice;
		private JTextField bSale;
		private JTextField oSale;
		private JTextField nameTextField;
		
		public ShowDetails() 
		{
			setTitle("SAMS : Show Details");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 630, 487);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel prLabel = new JLabel("Prices....");
			prLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			prLabel.setBounds(36, 68, 391, 31);
			contentPane.add(prLabel);
			
			JLabel bpLabel = new JLabel("Balcony");
			bpLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bpLabel.setHorizontalAlignment(SwingConstants.CENTER);
			bpLabel.setBounds(36, 120, 118, 31);
			contentPane.add(bpLabel);
			
			bPrice = new JTextField();
			bPrice.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bPrice.setBounds(185, 120, 102, 31);
			contentPane.add(bPrice);
			bPrice.setColumns(10);
			
			JLabel opLabel = new JLabel("Ordinary");
			opLabel.setBounds(324, 120, 118, 31);
			opLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			opLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(opLabel);
			
			oPrice = new JTextField();
			oPrice.setBounds(476, 120, 102, 31);
			oPrice.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(oPrice);
			oPrice.setColumns(10);
			
			JLabel stLabel = new JLabel("Seats for Sale...");
			stLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			stLabel.setBounds(36, 177, 372, 31);
			contentPane.add(stLabel);
			
			JLabel bsLabel = new JLabel("Balcony");
			bsLabel.setBounds(42, 240, 118, 31);
			bsLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(bsLabel);
			
			bSale = new JTextField();
			bSale.setBounds(185, 240, 102, 31);
			bSale.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(bSale);
			bSale.setColumns(10);
			
			JLabel osLabel = new JLabel("Ordinary");
			osLabel.setBounds(324, 240, 109, 31);
			osLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			osLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(osLabel);
			
			oSale = new JTextField();
			oSale.setBounds(476, 240, 102, 31);
			oSale.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(oSale);
			oSale.setColumns(10);
			
			JButton showDateButton = new JButton("Add shows on a Date");
			showDateButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					int bs, os, bp, op;
					b1_num = 0;
					b2_num = 0;
					b3_num = 0;
					b4_num = 0;
					String showName = nameTextField.getText(); 
					if(showName.isEmpty() || bSale.getText().isEmpty() || oSale.getText().isEmpty() ||
							bPrice.getText().isEmpty() || oPrice.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,
								"Kindly enter the previous fields before proceeding to Adding shows on a particular date");
						return;
					}
					else
					{
						try
						{
							if(Integer.parseInt(bPrice.getText()) > Integer.parseInt(oPrice.getText()))
							{
								bs = Integer.parseInt(bSale.getText());
								os = Integer.parseInt(oSale.getText());
								bp = Integer.parseInt(bPrice.getText());
								op = Integer.parseInt(oPrice.getText());
								
								
								nameTextField.setEditable(false);
								bSale.setEditable(false);
								oSale.setEditable(false);
								bPrice.setEditable(false);
								oPrice.setEditable(false);
								
								ShowDateGUI sdg = new ShowDateGUI(showName, bp, op, bs, os);
								sdg.setVisible(true);
								sdg.setResizable(false);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please re-enter the balcony seat price and ordinary seat price, "
										+ "Balcony seat price has to be higher than Ordinary seat price! ");
							}
						}
						catch(NumberFormatException e)
						{
							JOptionPane.showMessageDialog(null, "One or more fields are invalid !, Please Re-Enter.");
						}
						
					}
					
				}
			});
			showDateButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			showDateButton.setBounds(165, 319, 297, 37);
			contentPane.add(showDateButton);
			
			JButton saveButton = new JButton("Save");
			saveButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			saveButton.setBounds(232, 380, 137, 31);
			saveButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
						}
					});
			contentPane.add(saveButton);
			
			JLabel nameLabel = new JLabel("Name");
			nameLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			nameLabel.setBounds(73, 23, 63, 32);
			contentPane.add(nameLabel);
			
			nameTextField = new JTextField();
			nameTextField.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			nameTextField.setBounds(185, 24, 239, 32);
			contentPane.add(nameTextField);
			nameTextField.setColumns(10);
		}
	}

	public class ShowDateGUI extends JFrame 
	{

		private JPanel contentPane;
		private JSpinner start1, start2, start3, start4, start5;
		private JSpinner end1, end2, end3, end4, end5;
		private JDateChooser dateChooser;
		
		
		
		public ShowDateGUI(String Name, int BPrice, int OPrice, int BSale, int OSale) 
		{
			setTitle("SAMS");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 422, 679);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			String s = "30-Jul-2050";
			try {
				date = df.parse(s);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("d MMM, yyyy");
			dateChooser.setBounds(85, 95, 229, 38);
			dateChooser.setSelectableDateRange(new Date(), date);
			contentPane.add(dateChooser);
			
			JLabel dateLabel = new JLabel("Select a Date ....");
			dateLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			dateLabel.setBounds(30, 28, 274, 38);
			contentPane.add(dateLabel);
			
			JButton b1 = new JButton("+");
			b1.setBounds(12, 220, 47, 43);
			b1.setFont(new Font("Fertigo Pro", Font.BOLD | Font.PLAIN, 17));
			b1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							++b1_num;
							if(b1_num % 2 == 1)
							{
								start1.setVisible(true);
								end1.setVisible(true);
								b1.setText("-");
							}
							
							else
							{
								start1.setVisible(false);
								end1.setVisible(false);
								b1.setText("+");
							}
						}
					});
			contentPane.add(b1);
			
			JLabel startLabel = new JLabel("Start Time");
			startLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			startLabel.setBounds(95, 158, 113, 38);
			contentPane.add(startLabel);
			
			JLabel endLabel = new JLabel("End Time");
			endLabel.setBounds(247, 158, 107, 39);
			endLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(endLabel);
			
			start1 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor sde1 = new JSpinner.DateEditor(start1, "HH:mm:ss");
			start1.setEditor(sde1);
			start1.setValue(new Date());
			start1.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			start1.setBounds(85, 220, 113, 43);
			start1.setVisible(false);
			contentPane.add(start1);
			
			end1 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor ede1 = new JSpinner.DateEditor(end1, "HH:mm:ss");
			end1.setEditor(ede1);
			end1.setValue(new Date());
			end1.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			end1.setBounds(237, 220, 107, 43);
			end1.setVisible(false);
			contentPane.add(end1);
			
			JButton b2 = new JButton("+");
			b2.setBounds(12, 290, 47, 43);
			b2.setFont(new Font("Fertigo Pro", Font.BOLD | Font.PLAIN, 17));
			contentPane.add(b2);
			b2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					++b2_num;
					if(b2_num % 2 == 1)
					{
						start2.setVisible(true);
						end2.setVisible(true);
						b2.setText("-");
					}
					
					else
					{
						start2.setVisible(false);
						end2.setVisible(false);
						b2.setText("+");
					}
				}
			});
			
			start2 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor sde2 = new JSpinner.DateEditor(start2, "HH:mm:ss");
			start2.setEditor(sde2);
			start2.setValue(new Date());
			start2.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			start2.setBounds(85, 290, 113, 43);
			start2.setVisible(false);
			contentPane.add(start2);
			
			end2 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor ede2 = new JSpinner.DateEditor(end2, "HH:mm:ss");
			end2.setEditor(ede2);
			end2.setValue(new Date());
			end2.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			end2.setBounds(237, 290, 107, 43);
			end2.setVisible(false);
			contentPane.add(end2);
			
			JButton b3 = new JButton("+");
			b3.setBounds(12, 357, 47, 43);
			b3.setFont(new Font("Fertigo Pro", Font.BOLD | Font.PLAIN, 17));
			contentPane.add(b3);
			b3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					++b3_num;
					if(b3_num % 2 == 1)
					{
						start3.setVisible(true);
						end3.setVisible(true);
						b3.setText("-");
					}
					
					else
					{
						start3.setVisible(false);
						end3.setVisible(false);
						b3.setText("+");
					}
				}
			});
			
			start3 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor sde3 = new JSpinner.DateEditor(start3, "HH:mm:ss");
			start3.setEditor(sde3);
			start3.setValue(new Date());
			start3.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			start3.setBounds(85, 357, 113, 43);
			start3.setVisible(false);
			contentPane.add(start3);
			
			end3 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor ede3 = new JSpinner.DateEditor(end3, "HH:mm:ss");
			end3.setEditor(ede3);
			end3.setValue(new Date());
			end3.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			end3.setBounds(237, 357, 107, 43);
			end3.setVisible(false);
			contentPane.add(end3);
			
			JButton b4 = new JButton("+");
			b4.setBounds(12, 428, 47, 43);
			b4.setFont(new Font("Fertigo Pro", Font.BOLD | Font.PLAIN, 17));
			contentPane.add(b4);
			b4.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					++b4_num;
					if(b4_num % 2 == 1)
					{
						start4.setVisible(true);
						end4.setVisible(true);
						b4.setText("-");
					}
					
					else
					{
						start4.setVisible(false);
						end4.setVisible(false);
						b4.setText("+");
					}
				}
			});
			
			start4 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor sde4 = new JSpinner.DateEditor(start4, "HH:mm:ss");
			start4.setEditor(sde4);
			start4.setValue(new Date());
			start4.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			start4.setBounds(85, 428, 113, 43);
			start4.setVisible(false);
			contentPane.add(start4);
			
			end4 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor ede4 = new JSpinner.DateEditor(end4, "HH:mm:ss");
			end4.setEditor(ede4);
			end4.setValue(new Date());
			end4.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			end4.setBounds(237, 428, 107, 43);
			end4.setVisible(false);
			contentPane.add(end4);
			
			JButton b5 = new JButton("+");
			b5.setBounds(12, 496, 47, 43);
			b5.setFont(new Font("Fertigo Pro", Font.BOLD | Font.PLAIN, 17));
			contentPane.add(b5);
			b5.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					++b5_num;
					if(b5_num % 2 == 1)
					{
						start5.setVisible(true);
						end5.setVisible(true);
						b5.setText("-");
					}
					
					else
					{
						start5.setVisible(false);
						end5.setVisible(false);
						b5.setText("+");
					}
				}
			});
			
			start5 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor sde5 = new JSpinner.DateEditor(start5, "HH:mm:ss");
			start5.setEditor(sde5);
			start5.setValue(new Date());
			start5.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			start5.setBounds(85, 496, 113, 43);
			start5.setVisible(false);
			contentPane.add(start5);
			
			end5 = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor ede5 = new JSpinner.DateEditor(end5, "HH:mm:ss");
			end5.setEditor(ede5);
			end5.setValue(new Date());
			end5.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			end5.setBounds(237, 496, 107, 43);
			end5.setVisible(false);
			contentPane.add(end5);
			
			JButton saveButton = new JButton("Save");
			saveButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			saveButton.setBounds(150, 572, 97, 38);
			contentPane.add(saveButton);
			saveButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							Boolean status = true;
							Boolean preStatus = true;
							Date d = dateChooser.getDate();
							Date d1 = new Date();
							if(d.after(d1))
							{	
								SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								String dateString = df.format(d);
								int bprice = BPrice;
								int oprice = OPrice;
								int bseats = BSale;
								int oseats = OSale;
								String shown = new String(Name);
								if(preStatus && b1_num % 2 == 1 && b2_num % 2 == 1)
									preStatus = timeCompare(start1.getValue().toString().substring(11, 19),
											end1.getValue().toString().substring(11, 19),
											start2.getValue().toString().substring(11, 19),
											end2.getValue().toString().substring(11, 19));
								if(preStatus && b1_num % 2 == 1 && b3_num % 2 == 1)
									preStatus = timeCompare(start1.getValue().toString().substring(11, 19),
											end1.getValue().toString().substring(11, 19),
											start3.getValue().toString().substring(11, 19),
											end3.getValue().toString().substring(11, 19));
								if(preStatus && b1_num % 2 == 1 && b4_num % 2 == 1)
									preStatus = timeCompare(start1.getValue().toString().substring(11, 19),
											end1.getValue().toString().substring(11, 19),
											start4.getValue().toString().substring(11, 19),
											end4.getValue().toString().substring(11, 19));
								if(preStatus && b1_num % 2 == 1 && b5_num % 2 == 1)
									preStatus = timeCompare(start1.getValue().toString().substring(11, 19),
											end1.getValue().toString().substring(11, 19),
											start5.getValue().toString().substring(11, 19),
											end5.getValue().toString().substring(11, 19));
								if(preStatus && b2_num % 2 == 1 && b3_num % 2 == 1)
									preStatus = timeCompare(start2.getValue().toString().substring(11, 19),
											end2.getValue().toString().substring(11, 19),
											start3.getValue().toString().substring(11, 19),
											end3.getValue().toString().substring(11, 19));
								if(preStatus && b2_num % 2 == 1 && b4_num % 2 == 1)
									preStatus = timeCompare(start2.getValue().toString().substring(11, 19),
											end2.getValue().toString().substring(11, 19),
											start4.getValue().toString().substring(11, 19),
											end4.getValue().toString().substring(11, 19));
								if(preStatus && b2_num % 2 == 1 && b5_num % 2 == 1)
									preStatus = timeCompare(start2.getValue().toString().substring(11, 19),
											end2.getValue().toString().substring(11, 19),
											start5.getValue().toString().substring(11, 19),
											end5.getValue().toString().substring(11, 19));
								if(preStatus && b3_num % 2 == 1 && b4_num % 2 == 1)
									preStatus = timeCompare(start3.getValue().toString().substring(11, 19),
											end3.getValue().toString().substring(11, 19),
											start4.getValue().toString().substring(11, 19),
											end4.getValue().toString().substring(11, 19));
								if(preStatus && b3_num % 2 == 1 && b5_num % 2 == 1)
									preStatus = timeCompare(start3.getValue().toString().substring(11, 19),
											end3.getValue().toString().substring(11, 19),
											start5.getValue().toString().substring(11, 19),
											end5.getValue().toString().substring(11, 19));
								if(preStatus && b4_num % 2 == 1 && b5_num % 2 == 1)
									preStatus = timeCompare(start3.getValue().toString().substring(11, 19),
											end3.getValue().toString().substring(11, 19),
											start5.getValue().toString().substring(11, 19),
											end5.getValue().toString().substring(11, 19));
								if(preStatus)
								{
									if(b1_num % 2 == 1 || b2_num % 2 == 1 || b3_num % 2 == 1 || b4_num % 2 == 1 || b5_num % 2 == 1)
									{
										if(b1_num % 2 ==1)
										{
											
											db.connect();
											try 
											{
												if(validShow(start1.getValue().toString().substring(11, 19),
														end1.getValue().toString().substring(11, 19)))
												{	
													Statement st = db.getConnection().createStatement();
													ResultSet rt = st.executeQuery("SELECT * FROM showDetails WHERE showdate = '"+dateString+"'");
													while(rt.next())
													{
														status = timeCompare(rt.getString("STime"), rt.getString("ETime"), 
																start1.getValue().toString().substring(11, 19),
																end1.getValue().toString().substring(11, 19));
														if(!status)
															break;
													}
												
													if(status)
													{
														Statement stm = db.getConnection().createStatement();
														String add = "INSERT into showDetails (Name, BPrice, OPrice, BSale, OSale, ShowDate, STime, ETime, BFilled, OFilled)"
																+ " VALUES ('"+shown+"', '"+bprice+"', '"+oprice+"', '"+bseats+"', '"+oseats+"', '"+dateString+"',"
																+ " '"+start1.getValue().toString().substring(11, 19)+"', "
																+ "'"+end1.getValue().toString().substring(11, 19)+"', 0, 0)";
														stm.executeUpdate(add);
														dispose();
													}
													else
													{
														JOptionPane.showMessageDialog(null, "The show Timings entered in the 1st row "
																+ "clash with other show timings screening on the same day, Kindly try again!");
													}
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Please select valid show Timings in 1st row");
												}
												
											}
											catch (SQLException e) 
											{
												e.printStackTrace();
											}
											
										}
										
										if(b2_num % 2 ==1)
										{
											db.connect();
											try 
											{
												
												if(validShow(start2.getValue().toString().substring(11, 19),
														end2.getValue().toString().substring(11, 19)))
												{	
													Statement st = db.getConnection().createStatement();
													ResultSet rt = st.executeQuery("SELECT * FROM showDetails WHERE showdate = '"+dateString+"'");
													while(rt.next())
													{
														status = timeCompare(rt.getString("STime"), rt.getString("ETime"), 
																start2.getValue().toString().substring(11, 19),
																end2.getValue().toString().substring(11, 19));
														if(!status)
															break;
													}
												
													if(status)
													{
														Statement stm = db.getConnection().createStatement();
														String add = "INSERT into showDetails (Name, BPrice, OPrice, BSale, OSale, ShowDate, STime, ETime, BFilled, OFilled)"
																+ " VALUES ('"+shown+"', '"+bprice+"', '"+oprice+"', '"+bseats+"', '"+oseats+"', '"+dateString+"',"
																+ " '"+start2.getValue().toString().substring(11, 19)+"', "
																+ "'"+end2.getValue().toString().substring(11, 19)+"', 0, 0)";
														stm.executeUpdate(add);
														dispose();
													}
													else
													{
														JOptionPane.showMessageDialog(null, "The show Timings entered in the 2nd row "
																+ "clash with other show timings screening on the same day, Kindly try again!");
													}
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Please select valid show Timings in 2nd row");
												}
											}
											catch (SQLException e) 
											{
												e.printStackTrace();
											}
											
										}
										
										if(b3_num % 2 ==1)
										{
											db.connect();
											try 
											{
												
												if(validShow(start3.getValue().toString().substring(11, 19),
														end3.getValue().toString().substring(11, 19)))
												{	
													Statement st = db.getConnection().createStatement();
													ResultSet rt = st.executeQuery("SELECT * FROM showDetails WHERE showdate = '"+dateString+"'");
													while(rt.next())
													{
														status = timeCompare(rt.getString("STime"), rt.getString("ETime"), 
																start3.getValue().toString().substring(11, 19),
																end3.getValue().toString().substring(11, 19));
														if(!status)
															break;
													}
												
													if(status)
													{
														Statement stm = db.getConnection().createStatement();
														String add = "INSERT into showDetails (Name, BPrice, OPrice, BSale, OSale, ShowDate, STime, ETime, BFilled, OFilled)"
																+ " VALUES ('"+shown+"', '"+bprice+"', '"+oprice+"', '"+bseats+"', '"+oseats+"', '"+dateString+"',"
																+ " '"+start3.getValue().toString().substring(11, 19)+"', "
																+ "'"+end3.getValue().toString().substring(11, 19)+"', 0, 0)";
														stm.executeUpdate(add);
														dispose();
													}
													else
													{
														JOptionPane.showMessageDialog(null, "The show Timings entered in the 3rd row "
																+ "clash with other show timings screening on the same day, Kindly try again!");
													}
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Please select valid show Timings in 3rd row");
												}
											}
											catch (SQLException e) 
											{
												e.printStackTrace();
											}
											
										}
										
										if(b4_num % 2 ==1)
										{
											db.connect();
											try 
											{
												
												if(validShow(start4.getValue().toString().substring(11, 19),
														end4.getValue().toString().substring(11, 19)))
												{	
													Statement st = db.getConnection().createStatement();
													ResultSet rt = st.executeQuery("SELECT * FROM showDetails WHERE showdate = '"+dateString+"'");
													while(rt.next())
													{
														status = timeCompare(rt.getString("STime"), rt.getString("ETime"), 
																start4.getValue().toString().substring(11, 19),
																end4.getValue().toString().substring(11, 19));
														if(!status)
															break;
													}
												
													if(status)
													{
														Statement stm = db.getConnection().createStatement();
														String add = "INSERT into showDetails (Name, BPrice, OPrice, BSale, OSale, ShowDate, STime, ETime, BFilled, OFilled)"
																+ " VALUES ('"+shown+"', '"+bprice+"', '"+oprice+"', '"+bseats+"', '"+oseats+"', '"+dateString+"',"
																+ " '"+start4.getValue().toString().substring(11, 19)+"', "
																+ "'"+end4.getValue().toString().substring(11, 19)+"', 0, 0)";
														stm.executeUpdate(add);
														dispose();
													}
													else
													{
														JOptionPane.showMessageDialog(null, "The show Timings entered in the 4th row "
																+ "clash with other show timings screening on the same day, Kindly try again!");
													}
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Please select valid show Timings in 4th row");
												}
											}
											catch (SQLException e) 
											{
												e.printStackTrace();
											}
											
										}
										
										if(b5_num % 2 ==1)
										{
											db.connect();
											try 
											{
												
												if(validShow(start5.getValue().toString().substring(11, 19),
														end5.getValue().toString().substring(11, 19)))
												{	
													Statement st = db.getConnection().createStatement();
													ResultSet rt = st.executeQuery("SELECT * FROM showDetails WHERE showdate = '"+dateString+"'");
													while(rt.next())
													{
														status = timeCompare(rt.getString("STime"), rt.getString("ETime"), 
																start5.getValue().toString().substring(11, 19),
																end5.getValue().toString().substring(11, 19));
														if(!status)
															break;
													}
												
													if(status)
													{
														Statement stm = db.getConnection().createStatement();
														String add = "INSERT into showDetails (Name, BPrice, OPrice, BSale, OSale, ShowDate, STime, ETime, BFilled, OFilled)"
																+ " VALUES ('"+shown+"', '"+bprice+"', '"+oprice+"', '"+bseats+"', '"+oseats+"', '"+dateString+"',"
																+ " '"+start5.getValue().toString().substring(11, 19)+"', "
																+ "'"+end5.getValue().toString().substring(11, 19)+"', 0, 0)";
														stm.executeUpdate(add);
														dispose();
													}
													else
													{
														JOptionPane.showMessageDialog(null, "The show Timings entered in the 5th row "
																+ "clash with other show timings screening on the same day, Kindly try again!");
													}
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Please select valid show Timings in 5th row");
												}
											}
											catch (SQLException e) 
											{
												e.printStackTrace();
											}
										
										}
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Please select atleast a show time");;
									}
								}
								
							else
							{
									JOptionPane.showMessageDialog(null, "Clash of Times");
							}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Date selected is already over, Please enter a valid date!");
							}
						}
					});
			
		}
	}
	
	
	public class SalesPersonGUI extends JFrame 
	{

		private JPanel contentPane;
		
		public SalesPersonGUI() 
		{
			setTitle("SAMS : Sales Person");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 538, 357);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel greetingsLabel = new JLabel("Greetings !!, What would you like to do ..");
			greetingsLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 14));
			greetingsLabel.setBounds(26, 23, 341, 38);
			contentPane.add(greetingsLabel);
			
			JButton logoutButton = new JButton("Logout");
			logoutButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 14));
			logoutButton.setBounds(392, 22, 97, 38);
			logoutButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
						}
					});
			contentPane.add(logoutButton);
			
			JButton bookTicket = new JButton("Book Ticket");
			bookTicket.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					BookShow bookframe = new BookShow();
					bookframe.setVisible(true);
					bookframe.setResizable(false);
					
					showNames = new ArrayList<String>();
					shows = new HashSet<String>();
					
					db.connect();
					try 
					{
						Statement smt = db.getConnection().createStatement();
						ResultSet rst = smt.executeQuery("SELECT * FROM showdetails");
						
						while(rst.next())
						{
							showNames.add(rst.getString("Name"));
						}
						
						for(String st : showNames)
							shows.add(st);
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
				}
			});
			bookTicket.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bookTicket.setBounds(145, 97, 226, 43);
			contentPane.add(bookTicket);
			
			JButton cancelTicket = new JButton("Cancel Ticket");
			cancelTicket.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			cancelTicket.setBounds(145, 170, 226, 43);
			cancelTicket.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							Boolean status = false;
							String ts = JOptionPane.showInputDialog("Enter the TransactionID of ticket..");
							String sr = "SELECT * FROM ticket WHERE TransID = '"+ts+"'";
							int bBook = 0, oBook = 0, amount = 0;
							String sname = "", spuser = "", sdate = "", stime = "";
							
							db.connect();
							try 
							{
								Statement smt = db.getConnection().createStatement();
								ResultSet rst = smt.executeQuery(sr);
								
								while(rst.next())
								{
									status = true;
									sname = rst.getString("ShowName");
									spuser = rst.getString("SPID");
									sdate = rst.getString("ShowDate");
									stime = rst.getString("ShowTime");
									bBook = rst.getInt("BSeats");
									oBook = rst.getInt("OSeats");
									amount = rst.getInt("Amount");
								}
								if(status)
								{
									DateFormat dt = new SimpleDateFormat("dd-MMM-yyyy");
									try 
									{
										Date date = dt.parse(sdate);
										Date date1 = new Date();
										String st = date1.toString();
										Date dt1 = dt.parse(st.substring(8,10) + "-" + st.substring(4, 7) + "-" + st.substring(24));
										long diff = date.getTime() - dt1.getTime();
										long i  = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
										if(i >= 0)
										{
											int deduc;
											 	
											if(i >= 3)
												deduc = 5 * (bBook + oBook);
											else if(i < 3 && i > 1)
												deduc = 10 * oBook + 15 * bBook;
											else
												deduc = amount/ 2;
											String del = "DELETE FROM ticket WHERE TransID = '"+ts+"'";
											smt.executeUpdate(del);
										
											int before = 0;
											double com = 0;
											String sr1 = "SELECT * FROM loginid WHERE UserID = '"+spuser+"'";
											Statement smt2 = db.getConnection().createStatement();
											ResultSet rst2 = smt2.executeQuery(sr1);
											
											while(rst2.next())
											{
												before = rst2.getInt("Amount");
											}
											before = before - amount; 
											com = before * 0.05;
											
											String up1 = "UPDATE loginid SET Amount = '"+before+"', Commission = '"+com+"' WHERE UserID = '"+spuser+"'";
											Statement smt3 = db.getConnection().createStatement();
											smt3.executeUpdate(up1);
											
											int bfil = 0, ofil = 0;
											String sr2 = "SELECT * FROM showdetails WHERE Name = '"+sname+"' "
													+ "AND ShowDate = '"+sdate+"' AND STime = '"+stime+"'";
											Statement st4 = db.getConnection().createStatement();
											ResultSet rst4 = st4.executeQuery(sr2);
											while(rst4.next())
											{
												bfil = rst4.getInt("BFilled");
												ofil = rst4.getInt("OFilled");
											}
											bfil -= bBook;
											ofil -= oBook;
											
											String up2 = "UPDATE showdetails SET BFilled = '"+bfil+"', OFilled = '"+ofil+"', Cancellations = '"+deduc+"'"
													+ " WHERE Name = '"+sname+"' "
													+ "AND ShowDate = '"+sdate+"' AND STime = '"+stime+"'";
											Statement smt5 = db.getConnection().createStatement();
											smt5.executeUpdate(up2);
											
											
											amount-= deduc;
												
											JOptionPane.showMessageDialog(null, "Successfully Cancelled/n"
													+ "Amount refunded : " + amount);
										}
										else
										{
											JOptionPane.showMessageDialog(null, "You can't cancel tickets after watching show !");
										}
											
									}
									catch (ParseException e1)
									{
										e1.printStackTrace();
									}
								
									
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Invalid Ticket ID, Please try again");
								}
								
							}
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
						}
					});
			contentPane.add(cancelTicket);
			
			JButton passButton = new JButton("Change Password");
			passButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			passButton.setBounds(145, 241, 222, 43);
			passButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							PasswordGUI p = new PasswordGUI(SPusername);
							p.setResizable(false);
							p.setVisible(true);
						}
					});
			contentPane.add(passButton);
		}

	}
	
	public class BookShow extends JFrame 
	{

		private JPanel contentPane;
		private JTextField bBook;
		private JTextField oBook;
		private JComboBox showBox, dateBox, timeBox;
		private JButton showButton, dateButton, timeButton, bookButton;

		public BookShow() 
		{
			setTitle("SAMS");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 669, 514);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel insLabel = new JLabel("Kindly , select Show, then Date, then Show time ..");
			insLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			insLabel.setBounds(28, 25, 501, 39);
			contentPane.add(insLabel);
			
			showButton = new JButton("Show");
			showButton.setBounds(28, 91, 164, 39);
			showButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			showButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					DefaultComboBoxModel<String> Model = new DefaultComboBoxModel<String>();
					
					Iterator it = shows.iterator();
					while(it.hasNext())
						Model.addElement(it.next().toString());
					showBox.setModel(Model);
					showBox.setMaximumRowCount(3);
					
					showBox.setVisible(true);
					dateButton.setEnabled(true);
				}
			});
			contentPane.add(showButton);
			
			dateButton = new JButton("Show Date");
			dateButton.setBounds(243, 91, 164, 39);
			dateButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			dateButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					db.connect();
					Statement smt;
					showDates = new ArrayList<String>();
					dates = new HashSet<String>();
					try 
					{
						smt = db.getConnection().createStatement();
						ResultSet rst = smt.executeQuery("SELECT * FROM showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"'");
						while(rst.next())
						{
							showDates.add(rst.getString("ShowDate"));
						}
						
						for(String st : showDates)
							dates.add(st);
						
						DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>();
						
						Iterator it = dates.iterator();
						while(it.hasNext())
							md.addElement(it.next().toString());
						
						dateBox.setModel(md);
						dateBox.setMaximumRowCount(3);
							
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					
					timeButton.setEnabled(true);
					dateBox.setVisible(true);
				}
			});
			dateButton.setEnabled(false);
			contentPane.add(dateButton);
			
			timeButton = new JButton("Show Time");
			timeButton.setBounds(462, 91, 164, 39);
			timeButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			timeButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					
					db.connect();
					Statement smt;
					showTimes = new ArrayList<String>();
					times = new HashSet<String>();
					try 
					{
						smt = db.getConnection().createStatement();
						ResultSet rst = smt.executeQuery("SELECT * FROM showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"' "
								+ "AND ShowDate = '"+dateBox.getSelectedItem().toString()+"'");
						while(rst.next())
						{
							showTimes.add(rst.getString("STime"));
						}
						
						for(String st : showTimes)
							times.add(st);
						
						DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>();
						
						Iterator it = times.iterator();
						while(it.hasNext())
							md.addElement(it.next().toString());
						
						timeBox.setModel(md);
						timeBox.setMaximumRowCount(3);
							
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					timeBox.setVisible(true);
					bookButton.setEnabled(true);
				}
			});
			timeButton.setEnabled(false);
			contentPane.add(timeButton);
			
			showBox = new JComboBox();
			showBox.setBounds(28, 170, 164, 39);
			showBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			showBox.setVisible(false);
			contentPane.add(showBox);
			
			dateBox = new JComboBox();
			dateBox.setBounds(243, 170, 164, 39);
			dateBox.setVisible(false);
			dateBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(dateBox);
			
			timeBox = new JComboBox();
			timeBox.setBounds(462, 170, 164, 39);
			timeBox.setVisible(false);
			timeBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(timeBox);
			
			JLabel bLabel = new JLabel("No of Balcony Seats");
			bLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bLabel.setBounds(74, 254, 262, 39);
			contentPane.add(bLabel);
			
			bBook = new JTextField();
			bBook.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bBook.setBounds(380, 254, 116, 39);
			contentPane.add(bBook);
			bBook.setColumns(10);
			
			JLabel oLabel = new JLabel("No of Ordinary Seats");
			oLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			oLabel.setBounds(74, 330, 262, 39);
			contentPane.add(oLabel);
			
			oBook = new JTextField();
			oBook.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			oBook.setBounds(380, 330, 116, 39);
			contentPane.add(oBook);
			oBook.setColumns(10);
			
			bookButton = new JButton("BOOK");
			bookButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bookButton.setBounds(255, 406, 116, 39);
			bookButton.setEnabled(false);
			bookButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							try
							{
								if((!bBook.getText().isEmpty() && Integer.parseInt(bBook.getText()) > 0) || 
										(!oBook.getText().isEmpty() && Integer.parseInt(oBook.getText()) > 0))
								{
									db.connect();
									
									int boo, ooo;
									
									if(bBook.getText().isEmpty())
										boo = 0;
									else
										boo = Integer.parseInt(bBook.getText());
									
									if(oBook.getText().isEmpty())
										ooo = 0;
									else
										ooo = Integer.parseInt(oBook.getText());
									
									Statement smt;
									try 
									{
										smt = db.getConnection().createStatement();
										ResultSet rst = smt.executeQuery("SELECT * FROM showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"' "
												+ "AND ShowDate = '"+dateBox.getSelectedItem().toString()+"'"
														+ "AND STime = '"+timeBox.getSelectedItem().toString()+"'");
										while(rst.next())
										{
											if(boo <= (rst.getInt("BSale") - rst.getInt("BFilled")) 
													&& ooo <= (rst.getInt("OSale") - rst.getInt("OFilled")))
											{
												int bef = 0;
												Statement st9 = db.getConnection().createStatement();
												ResultSet rt9 = st9.executeQuery("SELECT * FROM loginid WHERE UserID = '"+SPusername+"'");
												while(rt9.next())
												{
													bef = rt9.getInt("Amount");
												}
												
												
												int amount;
												amount = rst.getInt("BPrice") * boo 
														+ rst.getInt("OPrice") * ooo;
												
												
												
												int newb = rst.getInt("BFilled") + boo;
												int newo = rst.getInt("OFilled") + ooo;
												
												Statement smt1 = db.getConnection().createStatement();
												ResultSet rt1 = smt1.executeQuery("select * from ticket where AutoInc >= 0");
												
												int id = 0;
												while(rt1.next())
												{
													id = rt1.getInt("AutoInc");
												}
												++id;
												String transID = "Ticket-" + Integer.toString(id);
												
												String q = "INSERT INTO ticket (TransID, SPID, ShowName, ShowDate, ShowTime, BSeats, OSeats, Amount)"
														+ "VALUES ('"+transID+"', '"+SPusername+"', '"+showBox.getSelectedItem().toString()+"', "
																+ "'"+dateBox.getSelectedItem().toString()+"',"
																		+ "'"+timeBox.getSelectedItem().toString()+"',"
																				+ "'"+boo+"',"
																						+ "'"+ooo+"',"
																								+ "'"+amount+"')";
												
												String u = "UPDATE showdetails SET BFilled = '"+newb+"' , OFilled = '"+newo+"' WHERE "
														+ "Name = '"+showBox.getSelectedItem().toString()+"' "
												+ "AND ShowDate = '"+dateBox.getSelectedItem().toString()+"'"
														+ "AND STime = '"+timeBox.getSelectedItem().toString()+"'";
												
												amount += bef;
												double com = amount * 0.05;
												
												String u1 = "UPDATE loginid SET Amount = '"+amount+"', Commission = '"+com+"' WHERE"
														+ " UserID = '"+SPusername+"'";
												
												Statement st3 = db.getConnection().createStatement();
												Statement st = db.getConnection().createStatement();
												Statement st2 = db.getConnection().createStatement();
												
												st3.executeUpdate(u1);
												
												st2.executeUpdate(u);
												
												st.executeUpdate(q);
												
												dispose();
												
												JOptionPane.showMessageDialog(null, "Successfully Booked \n"
														+ "Ticket Id : " + transID +
														"\nShow : " + showBox.getSelectedItem().toString() + 
														"\nShow Date : " + dateBox.getSelectedItem().toString() + 
														"\nShow Time : " + timeBox.getSelectedItem().toString() + 
														"\nBalcony Seats :" + boo +
														"\nOrdinary Seats : " + ooo);
											}
											
											else
											{
												if(Integer.parseInt(bBook.getText()) > (rst.getInt("BSale") - rst.getInt("BFilled")) 
														&& Integer.parseInt(oBook.getText()) > (rst.getInt("OSale") - rst.getInt("OFilled")))
														JOptionPane.showMessageDialog(null, 
																"Requested no of Balcony seats and Ordinary seats aren't available kindly select fewer seats");
												
												else if(Integer.parseInt(bBook.getText()) > (rst.getInt("BSale") - rst.getInt("BFilled")))
													JOptionPane.showMessageDialog(null, 
															"Requested no of Balcony seats aren't available kindly select fewer seats");
												
												else
													JOptionPane.showMessageDialog(null, 
															"Requested no of Ordinary seats aren't available kindly select fewer seats");
											}
										}
									}
									catch (SQLException e1) 
									{
										e1.printStackTrace();
									}
									
								}
								
								else
								{
									JOptionPane.showMessageDialog(null, "Please enter a valid no of seats to book!");
								}
							}
							catch(NumberFormatException e2)
							{
								JOptionPane.showMessageDialog(null, "Please enter a valid no of seats to book");
							}
						}
					});
			contentPane.add(bookButton);
		}
	}
	
	
	public class ShowSelect extends JFrame
	{

		private JPanel contentPane;
		private JComboBox showBox, dateBox, timeBox;
		private JButton showButton, dateButton, timeButton, continueButton;
		
		public ShowSelect()
		{
			setTitle("SAMS");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 669, 369);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel insLabel = new JLabel("Kindly , select Show, then Date, then Show time ..");
			insLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			insLabel.setBounds(28, 25, 501, 39);
			contentPane.add(insLabel);
			
			showButton = new JButton("Show");
			showButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					DefaultComboBoxModel<String> Model = new DefaultComboBoxModel<String>();
					
					Iterator it = shows.iterator();
					while(it.hasNext())
						Model.addElement(it.next().toString());
					showBox.setModel(Model);
					showBox.setMaximumRowCount(3);
					
					showBox.setVisible(true);
					dateButton.setEnabled(true);
				}
			});
			showButton.setBounds(28, 91, 164, 39);
			showButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(showButton);
			
			dateButton = new JButton("Show Date");
			dateButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					db.connect();
					Statement smt;
					showDates = new ArrayList<String>();
					dates = new HashSet<String>();
					try 
					{
						smt = db.getConnection().createStatement();
						ResultSet rst = smt.executeQuery("SELECT * FROM showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"'");
						while(rst.next())
						{
							showDates.add(rst.getString("ShowDate"));
						}
						
						for(String st : showDates)
							dates.add(st);
						
						DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>();
						
						Iterator it = dates.iterator();
						while(it.hasNext())
							md.addElement(it.next().toString());
						
						dateBox.setModel(md);
						dateBox.setMaximumRowCount(3);
							
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					timeButton.setEnabled(true);
					dateBox.setVisible(true);
				}
			});
			dateButton.setBounds(243, 91, 164, 39);
			dateButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			dateButton.setEnabled(false);
			contentPane.add(dateButton);
			
			timeButton = new JButton("Show Time");
			timeButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					db.connect();
					Statement smt;
					showTimes = new ArrayList<String>();
					times = new HashSet<String>();
					try 
					{
						smt = db.getConnection().createStatement();
						ResultSet rst = smt.executeQuery("SELECT * FROM showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"' "
								+ "AND ShowDate = '"+dateBox.getSelectedItem().toString()+"'");
						while(rst.next())
						{
							showTimes.add(rst.getString("STime"));
						}
						
						for(String st : showTimes)
							times.add(st);
						
						DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>();
						
						Iterator it = times.iterator();
						while(it.hasNext())
							md.addElement(it.next().toString());
						
						timeBox.setModel(md);
						timeBox.setMaximumRowCount(3);
							
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					timeBox.setVisible(true);
					continueButton.setEnabled(true);
				}
			});
			timeButton.setBounds(462, 91, 164, 39);
			timeButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			timeButton.setEnabled(false);
			contentPane.add(timeButton);
			
			showBox = new JComboBox();
			showBox.setBounds(28, 170, 164, 39);
			showBox.setVisible(false);
			showBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(showBox);
			
			dateBox = new JComboBox();
			dateBox.setBounds(243, 170, 164, 39);
			dateBox.setVisible(false);
			dateBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(dateBox);
			
			timeBox = new JComboBox();
			timeBox.setBounds(462, 170, 164, 39);
			timeBox.setVisible(false);
			timeBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(timeBox);
			
			continueButton = new JButton("CONTINUE");
			continueButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			continueButton.setBounds(255, 263, 121, 39);
			continueButton.setEnabled(false);
			continueButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
							
							if(select.equals("AccountsClerk"))
							{
								AccountsClerkGUI acframe = new AccountsClerkGUI(showBox.getSelectedItem().toString(),
										dateBox.getSelectedItem().toString(),
										timeBox.getSelectedItem().toString());
								acframe.setVisible(true);
								acframe.setResizable(false);
							}
							
							if(select.equals("Status"))
							{
								db.connect();
								int bs = 0, bf = 0, os = 0, of = 0, bp = 0, op = 0, c = 0;
								
								try 
								{
									Statement smt = db.getConnection().createStatement();
									String sr = "SELECT * from showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"' "
											+ "AND ShowDate = '"+dateBox.getSelectedItem().toString()+"' "
													+ "AND STime = '"+timeBox.getSelectedItem().toString()+"'";
									ResultSet rst = smt.executeQuery(sr);
									while(rst.next())
									{
										bs = rst.getInt("BSale");
										os = rst.getInt("OSale");
										bf = rst.getInt("BFilled");
										of = rst.getInt("OFilled");
										bp = rst.getInt("BPrice");
										op = rst.getInt("OPrice");
										c = rst.getInt("Cancellations");
									}
									
									JOptionPane.showMessageDialog(null, "Show : " + showBox.getSelectedItem().toString() +
											"\nDate : " + dateBox.getSelectedItem().toString() +
											"\nTime : " + timeBox.getSelectedItem().toString() +
											"\nBalcony Seats filled : " + bf + "/" + bs +
											"\nOrdinary Seats filled : " + of + "/" + os +
											"\nAmount Collected :-"
											+ "\n Balcony : " + bp * bf +
											"\nOrdinary : " + op * of +
											"\nCancellations : " + c);
								} 
								catch (SQLException e1) 
								{
									e1.printStackTrace();
								}
								
							}
							
							if(select.equals("Balance"))
							{
								db.connect();
								int l = 0, p = 0, o = 0 , am = 0, c = 0;
								
								try 
								{
									Statement smt = db.getConnection().createStatement();
									String sr = "SELECT * from showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"' "
											+ "AND ShowDate = '"+dateBox.getSelectedItem().toString()+"' "
													+ "AND STime = '"+timeBox.getSelectedItem().toString()+"'";
									ResultSet rst = smt.executeQuery(sr);

									while(rst.next())
									{
										am = rst.getInt("BFilled") * rst.getInt("BPrice") + rst.getInt("OFilled") * rst.getInt("OPrice") ;
										c = rst.getInt("Cancellations");
										l = rst.getInt("Logistics");
										p = rst.getInt("Payment");
										o = rst.getInt("Others");
									}
									
									JOptionPane.showMessageDialog(null, "Show : " + showBox.getSelectedItem().toString() +
											"\nDate : " + dateBox.getSelectedItem().toString() +
											"\nTime : " + timeBox.getSelectedItem().toString() +
											"\n***Amount Collected***" +
											"\nVia Bookings : " + am +
											"\nVia Cancellations : " + c + 
											"\n***Expenditure***"
											+ "\nLogistics : " + l +
											"\nPayment to Artists : " + p +
											"\nOthers : " + o);
								}
								catch (SQLException e1)
								{
									e1.printStackTrace();
								}
							}
							
							if(select.equals("Initial"))
							{
								db.connect();
								int bf = 0, bs = 0, of = 0 , os = 0;
								
								try 
								{
									Statement smt = db.getConnection().createStatement();
									String sr = "SELECT * from showdetails WHERE Name = '"+showBox.getSelectedItem().toString()+"' "
											+ "AND ShowDate = '"+dateBox.getSelectedItem().toString()+"' "
													+ "AND STime = '"+timeBox.getSelectedItem().toString()+"'";
									ResultSet rst = smt.executeQuery(sr);

									while(rst.next())
									{
										bf = rst.getInt("BFilled");
										of = rst.getInt("OFilled");
										bs = rst.getInt("BSale");
										os = rst.getInt("OSale");
									}
									
									int ba, oa;
									ba = bs - bf;
									oa = os - of;
									
									JOptionPane.showMessageDialog(null, "Show : " + showBox.getSelectedItem().toString() +
											"\nDate : " + dateBox.getSelectedItem().toString() +
											"\nTime : " + timeBox.getSelectedItem().toString() +
											"\nBalcony Seats available : " + ba + "/" + bs +
											"\nOrdinary Seats available : " + oa + "/" + os);
								}
								catch (SQLException e1)
								{
									e1.printStackTrace();
								}
							}
									
						}
					});
			contentPane.add(continueButton);
		}
	}

	public class AccountsClerkGUI extends JFrame
	{

		private JPanel contentPane;
		private JTextField logisticsTextField;
		private JTextField paymentTextField;
		private JTextField othersTextField;
		
		public AccountsClerkGUI(String sname, String sdate, String stime) 
		{
			setTitle("SAMS : Accounts Clerk");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 520, 419);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel label = new JLabel("Various Expenditures ..");
			label.setFont(new Font("Fertigo Pro", Font.PLAIN, 16));
			label.setBounds(30, 28, 300, 40);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			contentPane.add(label);
			
			JLabel logisticsLabel = new JLabel("Logistics");
			logisticsLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			logisticsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			logisticsLabel.setBounds(30, 102, 201, 34);
			contentPane.add(logisticsLabel);
			
			logisticsTextField = new JTextField();
			logisticsTextField.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			logisticsTextField.setBounds(261, 103, 187, 34);
			contentPane.add(logisticsTextField);
			logisticsTextField.setColumns(10);
			
			JLabel paymentLabel = new JLabel("Payment to Artists");
			paymentLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			paymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
			paymentLabel.setBounds(30, 162, 201, 34);
			contentPane.add(paymentLabel);
			
			paymentTextField = new JTextField();
			paymentTextField.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			paymentTextField.setBounds(261, 163, 187, 34);
			contentPane.add(paymentTextField);
			paymentTextField.setColumns(10);
			
			JLabel othersLabel = new JLabel("Others");
			othersLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			othersLabel.setHorizontalAlignment(SwingConstants.CENTER);
			othersLabel.setBounds(30, 227, 201, 34);
			contentPane.add(othersLabel);
			
			othersTextField = new JTextField();
			othersTextField.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			othersTextField.setBounds(261, 228, 187, 34);
			contentPane.add(othersTextField);
			othersTextField.setColumns(10);
			
			JButton saveButton = new JButton("Save");
			saveButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			saveButton.setBounds(198, 304, 118, 34);
			saveButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							db.connect();
							try 
							{
								Statement smt = db.getConnection().createStatement();
								try
								{
									String up = "UPDATE showdetails SET Logistics = '"+Integer.parseInt(logisticsTextField.getText())+"', "
											+ "Payment = '"+Integer.parseInt(paymentTextField.getText())+"', "
													+ "Others = '"+Integer.parseInt(othersTextField.getText())+"'"
															+ " WHERE Name = '"+sname+"' AND ShowDate = '"+sdate+"' AND STime = '"+stime+"'";
									smt.executeUpdate(up);
									JOptionPane.showMessageDialog(null, "Successfully updated !!");
									dispose();
								}
								catch(NumberFormatException e2)
								{
									JOptionPane.showMessageDialog(null, "One or more fields are invalid, Please Re-Enter");
								}
							}
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
						}
					});
			contentPane.add(saveButton);
		}
	}

	public class DisplayGUI extends JFrame
	{
		private JPanel contentPane;
		
		public DisplayGUI(String title, String str) 
		{
			setTitle(title);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 400, 500);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout());
			
			JTextArea textArea = new JTextArea(str);
			textArea.setFont(new Font("Fertigo Pro", Font.PLAIN, 18));
			textArea.setBounds(12, 13, 535, 583);
			textArea.setEditable(false);
			contentPane.add(new JScrollPane(textArea), BorderLayout.CENTER);
			
		}
	}
	
	public class Balance extends JFrame 
	{

		private JPanel contentPane;
		private JButton selectShow, bsButton;
		private JComboBox yearBox;
		private JLabel label;
		
		public Balance() 
		{
			setTitle("SAMS : Balance Sheets");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 402, 397);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JRadioButton sButton = new JRadioButton("Show");
			sButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			sButton.setBounds(42, 30, 105, 39);
			sButton.addItemListener(new ItemListener()
					{
						public void itemStateChanged(ItemEvent arg0) 
						{
								if(s % 2 == 0)
								{
									selectShow.setVisible(true);
									++s;
								}
								else
								{
									selectShow.setVisible(false);
									++s;
								}
						}
						
					});
			contentPane.add(sButton);
			
			JRadioButton yButton = new JRadioButton("Year");
			yButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			yButton.setBounds(246, 30, 105, 39);
			yButton.addItemListener(new ItemListener()
					{
						public void itemStateChanged(ItemEvent e)
						{
							if(y == 0)
							{
								DefaultComboBoxModel<String> Model = new DefaultComboBoxModel<String>();
								
								Iterator it = balYear.iterator();
								while(it.hasNext())
									Model.addElement(it.next().toString());
								yearBox.setModel(Model);
								yearBox.setMaximumRowCount(3);
							}
							if(y % 2 == 0)
							{
								label.setVisible(true);
								yearBox.setVisible(true);
								bsButton.setVisible(true);
								++y;
							}
							else
							{
								label.setVisible(false);
								yearBox.setVisible(false);
								bsButton.setVisible(false);
								++y;
							}
						}
					});
			contentPane.add(yButton);
			
			ButtonGroup radio = new ButtonGroup();
			radio.add(yButton);
			radio.add(sButton);
			
			label = new JLabel("Select Year");
			label.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			label.setBounds(37, 105, 146, 39);
			label.setVisible(false);
			contentPane.add(label);
			
			yearBox = new JComboBox();
			yearBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			yearBox.setBounds(225, 105, 114, 39);
			yearBox.setVisible(false);
			contentPane.add(yearBox);
			
			selectShow = new JButton("Select Show");
			selectShow.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			selectShow.setBounds(82, 193, 204, 39);
			selectShow.setVisible(false);
			selectShow.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
							select = "Balance";
							
							showNames = new ArrayList<String>();
							shows = new HashSet<String>();
							
							db.connect();
							try 
							{
								Statement smt1 = db.getConnection().createStatement();
								ResultSet rst1 = smt1.executeQuery("SELECT * FROM showdetails");
								
								while(rst1.next())
								{
									showNames.add(rst1.getString("Name"));
								}
								
								for(String st : showNames)
									shows.add(st);
							} 
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
							
							ShowSelect sframe = new ShowSelect();
							sframe.setVisible(true);
							sframe.setResizable(false);
						}
					});
			contentPane.add(selectShow);
			
			bsButton = new JButton("Get Balance Sheets");
			bsButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			bsButton.setBounds(66, 279, 243, 39);
			bsButton.setVisible(false);
			bsButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
							db.connect();
							String sr, s;
							int am = 0, lo = 0, p = 0, oth = 0;
							try 
							{
								Statement smt = db.getConnection().createStatement();
								sr = "SELECT * FROM showdetails";
								ResultSet rst = smt.executeQuery(sr);
								while(rst.next())
								{
									s = rst.getString("Showdate");
									if(s != null && s.length() > 3 && s.substring(7).equals(yearBox.getSelectedItem().toString()))
									{
										am += rst.getInt("BFilled") * rst.getInt("BPrice") + rst.getInt("OFilled") * rst.getInt("OPrice") +
												rst.getInt("Cancellations");
										lo += rst.getInt("Logistics");
										p += rst.getInt("Payment");
										oth += rst.getInt("Others");
									}
								}
								
								JOptionPane.showMessageDialog(null, 
										"Balance Sheet : " +
										"\nYear : " + yearBox.getSelectedItem().toString() +
										"\nAmount Collected : " + am +
										"\n***Expenditure***"
										+ "\nLogistics : " + lo +
										"\nPayment : " + p +
										"\nOthers : " + oth);
							}
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
						}
					});
			contentPane.add(bsButton);
		}
	}
	
	public static Boolean timeCompare(String st1, String et1, String st2, String et2)
	{
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		
		try
		{
			Date sdt1 = df.parse(st1);
			Date edt1 = df.parse(et1);
			Date sdt2 = df.parse(st2);
			Date edt2 = df.parse(et2);
			if(edt2.before(sdt1) || sdt2.after(edt1))
				return true;
			else
				return false;
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static Boolean validShow(String st, String et)
	{
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		
		try 
		{
			Date sdt = df.parse(st);
			Date edt = df.parse(et);
			if(edt.after(sdt))
				return true;
			else
				return false;
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public class ACInitialGUI extends JFrame 
	{

		private JPanel contentPane;
		public ACInitialGUI()
		{
			setTitle("SAMS");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 532, 285);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel greetLabel = new JLabel("Greetings !!, What would you like to do ..");
			greetLabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 14));
			greetLabel.setBounds(12, 27, 336, 35);
			contentPane.add(greetLabel);
			
			JButton logButton = new JButton("Logout");
			logButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			logButton.setBounds(372, 21, 130, 45);
			logButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
						}
					});
			contentPane.add(logButton);
			
			JButton enterButton = new JButton("Enter Expenditure");
			enterButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			enterButton.setBounds(126, 96, 238, 45);
			enterButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							select = "AccountsClerk";

							showNames = new ArrayList<String>();
							shows = new HashSet<String>();
							
							db.connect();
							try 
							{
								Statement smt1 = db.getConnection().createStatement();
								ResultSet rst1 = smt1.executeQuery("SELECT * FROM showdetails");
								
								while(rst1.next())
								{
									showNames.add(rst1.getString("Name"));
								}
								
								for(String st : showNames)
									shows.add(st);
							} 
							catch (SQLException e1) 
							{
								e1.printStackTrace();
							}
							
							ShowSelect ss = new ShowSelect();
							ss.setResizable(false);
							ss.setVisible(true);
							ss.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						}
					});
			contentPane.add(enterButton);
			
			JButton passButton = new JButton("Change Password");
			passButton.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			passButton.setBounds(126, 168, 238, 45);
			passButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							PasswordGUI p = new PasswordGUI("AC");
							p.setResizable(false);
							p.setVisible(true);
						}
					});
			contentPane.add(passButton);
		}
	}
	
	public class PasswordGUI extends JFrame 
	{

		private JPanel contentPane;
		private JPasswordField pw;
		private JButton update;
		
		public PasswordGUI(String us) 
		{
			setTitle("SAMS");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 255);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel pass = new JLabel("New Password");
			pass.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			pass.setHorizontalAlignment(SwingConstants.CENTER);
			pass.setBounds(24, 50, 162, 35);
			contentPane.add(pass);
			
			pw = new JPasswordField();
			pw.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			pw.setBounds(221, 50, 162, 35);
			contentPane.add(pw);
			
			update = new JButton("UPDATE");
			update.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			update.setBounds(133, 130, 162, 35);
			update.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							String too = new String(pw.getPassword());
							if(!too.isEmpty())
							{
								dispose();
								String pwd = new String(pw.getPassword());
								db.connect();
								try 
								{
									Statement smt = db.getConnection().createStatement();
									String up = "UPDATE loginid SET Password = '"+pwd+"' WHERE UserID = '"+us+"'";
									smt.executeUpdate(up);
									JOptionPane.showMessageDialog(null, "Password updated successfully !!");
								}
								catch (SQLException e1) 
								{
									e1.printStackTrace();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please enter a non empty password");
							}
						}
					});
			contentPane.add(update);
		}
	}
	
	public static void clear()
	{
		db.connect();
		try 
		{
			Statement smt1 = db.getConnection().createStatement();
			Statement smt2 = db.getConnection().createStatement();
			Statement smt3 = db.getConnection().createStatement();
			
			String del1 = "DELETE FROM loginid";
			String del2 = "DELETE FROM showdetails";
			String del3 = "DELETE FROM ticket";
			
			smt1.executeUpdate(del1);
			smt2.executeUpdate(del2);
			smt3.executeUpdate(del3);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public class EnterSP extends JFrame 
	{

		private JPanel contentPane;
		private JTextField name;
		private JTextField contact;
		public EnterSP()
		{
			setTitle("SAMS");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 266);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel nlabel = new JLabel("Name");
			nlabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			nlabel.setHorizontalAlignment(SwingConstants.CENTER);
			nlabel.setBounds(39, 30, 143, 33);
			contentPane.add(nlabel);
			
			name = new JTextField();
			name.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			name.setBounds(221, 30, 155, 33);
			contentPane.add(name);
			name.setColumns(10);
			
			JLabel clabel = new JLabel("Contact");
			clabel.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			clabel.setHorizontalAlignment(SwingConstants.CENTER);
			clabel.setBounds(39, 94, 143, 33);
			contentPane.add(clabel);
			
			contact = new JTextField();
			contact.setBounds(221, 94, 155, 33);
			contact.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			contentPane.add(contact);
			contact.setColumns(10);
			
			JButton rec = new JButton("RECRUIT");
			rec.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					if(!name.getText().isEmpty())
					{
						if(validContact(contact.getText()))
						{
							dispose();
							String us, pw;
						
							db.connect();
							try
							{
								Statement smt = db.getConnection().createStatement();
								ResultSet rst;
								rst = smt.executeQuery("select * from loginid where IntID >= 0");
							
								int id = 0;
								while(rst.next())
								{
									if(id < rst.getInt("IntID"))
										id = rst.getInt("IntID");
								}
								++id;
								us = "SP_SAMS_" + Integer.toString(id); // < 10 add a zero to username
								pw = "sams" + Integer.toString(id);
							
								JOptionPane.showMessageDialog(null, "Sales Person Recruited succesfully\nName : " + name.getText()
										+ "\nContact : " + contact.getText()
										+ "\nUsername : " + us + "\nPassword : "
										+ pw +"\n***Can be changed after first login***");
							
								String add = "INSERT into loginid (UserID, Password, Name, Contact) VALUES ('"+us+"', '"+pw+"', '"+name.getText()+"',"
										+ " '"+contact.getText()+"')";
								Statement smt1 = db.getConnection().createStatement();
								smt1.executeUpdate(add);
							
							}
							catch (SQLException e) 
							{
								e.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please enter a valid contact number!");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid Name!");
					}
				}
			});
			rec.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			rec.setBounds(150, 160, 128, 33);
			contentPane.add(rec);
		}
	}

	public static boolean validContact(String number)
	{
		int valid = 1;
		if(number.length() == 10)
		{
			for(int i = 0; i < number.length(); ++i)
			{
				if(((int)number.charAt(i) > 57) || ((int)number.charAt(i) < 48))
					valid = 0;
			}
		
			if(valid == 0)
				return false;
			else
				return true;
		}
		
		else
			return false;
	}
	
	public class RemoveSP extends JFrame
	{

		private JPanel contentPane;
		
		public RemoveSP() 
		{
			setTitle("SAMS");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 511, 314);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel label = new JLabel("Select the Sales Person");
			label.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			label.setBounds(86, 44, 309, 38);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(label);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			comboBox.setBounds(52, 119, 382, 44);
			
			DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>();
			for(int i = 0; i < remove1.size(); ++i)
			{
				String t = remove1.get(i) + "--" + remove2.get(i);
				md.addElement(t);
			}
			comboBox.setModel(md);
			comboBox.setMaximumRowCount(3);
			contentPane.add(comboBox);
			
			JButton rem = new JButton("REMOVE");
			rem.setFont(new Font("Fertigo Pro", Font.PLAIN, 17));
			rem.setBounds(182, 198, 120, 38);
			rem.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							dispose();
							if(comboBox.getSelectedIndex() >= 0)
							{
								String us = remove2.get(comboBox.getSelectedIndex());
								db.connect();
								try
								{
									Statement smt = db.getConnection().createStatement();
									String del = "DELETE FROM loginid WHERE UserID = '"+us+"'";
									smt.executeUpdate(del);
									JOptionPane.showMessageDialog(null, "Successfully fired !");
								}
								catch (SQLException e1)
								{
									e1.printStackTrace();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "No Sales Person to fire !");
							}
							
						}
					});
			contentPane.add(rem);
		}
	}

}
