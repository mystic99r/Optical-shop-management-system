import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import date.*;

class BillReport extends JInternalFrame implements ActionListener
//class BillReport extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,date;
	JButton b1,b2;
	JPanel panel;
	JScrollPane scroll;
	JTable table;
	ResultModel rmodel;
	Connection con;
         DateChooserPanel dpane;
	
	BillReport()
	{
	         //super("Bill Report");
		super("Bill Report",true,true,true,true);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		drawFrame();
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("Jdbc:Odbc:database");
			System.out.println("OK");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		setTable();
		setVisible(true);
	}
	void drawFrame()
	{
		setLayout(null);
		setBounds(10,10,800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		l3=new JLabel(new ImageIcon("head2.jpg"));
		l3.setBorder(BorderFactory.createMatteBorder(3,3,3,3,new Color(72,145,149)));
		l3.setBounds(0,0,783,80);
		add(l3);
		
		dpane = new DateChooserPanel();
		date=new JLabel(dpane.getShortDate());
		date.setBounds(675,1,115,30);
		date.setBorder(BorderFactory.createMatteBorder(0,2,2,0,new Color(72,145,149)));
		l3.add(date);


	
		
		l1=new JLabel("Bill Report",JLabel.CENTER);
		l1.setBorder(BorderFactory.createBevelBorder(1));
		l1.setFont(new Font("Arial",Font.PLAIN,40));
		l1.setBounds(0,80,800,80);
		add(l1);
		


		table=new JTable();
		scroll=new JScrollPane(table);
		scroll.setBounds(0,170,800,425);
		add(scroll);
		
		b1=new JButton("Print");
		b1.setBounds(570,615,80,30);
		b1.addActionListener(this);
		add(b1);

		b2=new JButton("Exit");
		b2.setBounds(660,615,80,30);
		b2.addActionListener(this);
		add(b2);
	}
	void setTable()
	{
		try
		{
			String query="select BID as BillID,CustName as CustomerName,Bdate as BillDate,Amt as Amount from Bill";
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(query);

			rmodel=new ResultModel();
			rmodel.setResultSet(rs);
			table.setModel(rmodel);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{
				table.print();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==b2)
			dispose();
	}
	public static void main(String args[])
	{
		new BillReport();
	}	 
}
