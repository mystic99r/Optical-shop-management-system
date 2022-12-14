import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import date.*;

class MonthlyReport1 extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,date;
	JButton b1;
	Connection con;
	DateChooserPanel dpane,dpane1;
//	JComboBox cb;
	ResultModel rmodel;
	Statement stat;
	ResultSet res;
	JTable tab;
	JScrollPane pane;

	MonthlyReport1()
	{
		super("Monthly Report.");
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("Jdbc:Odbc:database");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		drawFrame();
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			//setData();
		}
	}
	void drawFrame()
	{
		setLayout(null);
		setBounds(10,10,800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		l1=new JLabel(new ImageIcon("logo.jpg"));
		l1.setBounds(0,0,800,75);
		l1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,new Color(75,144,149)));
		add(l1);
		
		dpane=new DateChooserPanel();
		date=new JLabel(dpane.getShortDate());
		date.setBounds(650,1,115,30);
		date.setBorder(BorderFactory.createMatteBorder(0,2,2,0,new Color(75,144,149)));
		l1.add(date);
		
		l3=new JLabel("Select Month-");
		l3.setBounds(280,100,100,30);
		l3.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(l3);

              /* cb=new JComboBox();
		cb.addItem("January");
		cb.addItem("February");
		cb.addItem("March");
		cb.addItem("April");
		cb.addItem("May");
		cb.addItem("June");
		cb.addItem("July");
		cb.addItem("August");
		cb.addItem("September");
		cb.addItem("October");
		cb.addItem("November");
		cb.addItem("December");
		cb.setBounds(400,100,90,30);
		cb.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(cb);*/
		
		System.out.println("calender");
		dpane1=new DateChooserPanel();
		System.out.println("calender");
		dpane1.setBounds(400,100,800,300);
		add(dpane1);

		b1=new JButton("Show Report");
		b1.setBounds(20,100,150,30);
		b1.addActionListener(this);
		add(b1);
		
		tab=new JTable();
		pane=new JScrollPane();
		pane.add(tab);
		pane.setBounds(10,180,800,580);
		add(pane);
	}
	
	/*void setData()
	{
		try
		{
				
			String query="select * from Bill where Bdate=\'"+dpane.getShortDate()+"\'";
//			String query="select * from Bill where Bdate=\'"+cb.getSelectedItem()+"\'";
			stat=con.createStatement();
			res=stat.executeQuery(query);

				
			rmodel=new ResultModel();
			rmodel.setResultSet(res);
			tab.setModel(rmodel);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}*/
	public static void main(String args[])
	{	
		new MonthlyReport1();
	}
}


