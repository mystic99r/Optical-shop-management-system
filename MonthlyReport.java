import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import date.*;

class MonthlyReport extends JInternalFrame implements ActionListener
{
	JLabel l1,l2,l3,date;
	JButton b1,b2,b3;
	Connection con;
	DateChooserPanel dpane,dpane1;
	JComboBox cb;
	ResultModel rmodel;
	Statement stat;
	ResultSet res;
	JTable tab;
	JScrollPane pane;
	JLabel lt1, lt2;

	MonthlyReport()
	{
		//super("Monthly Report",true,true,true,true);
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
			setData();
		}
		else if(ae.getSource()==b2)
		{
			try
			{
				tab.print();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==b3)
			dispose();
	}
	void drawFrame()
	{
		setLayout(null);
		setBounds(10,10,800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		l1=new JLabel(new ImageIcon("head2.jpg"));
		l1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,new Color(72,145,149)));
		l1.setFont(new Font("Arial",Font.PLAIN,40));
		l1.setBounds(0,0,785,80);
		add(l1);
		
		dpane = new DateChooserPanel();
		date=new JLabel(dpane.getShortDate());
		date.setBounds(675,1,115,30);
		date.setBorder(BorderFactory.createMatteBorder(0,2,2,0,new Color(72,145,149)));
		l1.add(date);
		
		
		l3=new JLabel("Select Month-");
		l3.setBounds(280,100,100,30);
		l3.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(l3);

                  cb=new JComboBox();
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
		add(cb);
		
		
		b1=new JButton("Show Report");
		b1.setBounds(20,100,150,30);
		b1.addActionListener(this);
		add(b1);
		
		tab=new JTable();
		pane=new JScrollPane(tab);
		pane.setBounds(10,150,770,430);
		add(pane);

		b2=new JButton("Print");
		b2.setBounds(570,615,80,30);
		b2.addActionListener(this);
		add(b2);

		b3=new JButton("Exit");
		b3.setBounds(660,615,80,30);
		b3.addActionListener(this);
		add(b3);

		lt1=new JLabel("Total");
		lt1.setBounds(600,580,100,30);
		lt1.setBorder(BorderFactory.createBevelBorder(1));
		add(lt1);

		lt2=new JLabel();
		lt2.setBounds(700,580,80,30);
		lt2.setBorder(BorderFactory.createBevelBorder(1));
		add(lt2);

	}
	
	void setData()
	{
		try
		{
				
			String query="select BID as BillID,CustName as Customer,Bdate as Date_,Amt as Amount from Bill where Bdate LIKE \'%"+cb.getSelectedItem()+"%\'";
			System.out.println(query);
			stat=con.createStatement();
			res=stat.executeQuery(query);

			rmodel=new ResultModel();
			rmodel.setResultSet(res);
			tab.setModel(rmodel);

			query="select sum(Amt) from Bill where Bdate LIKE \'%"+cb.getSelectedItem()+"%\'";
			System.out.println(query);
			stat=con.createStatement();
			res=stat.executeQuery(query);
			res.next();
			lt2.setText(res.getString(1));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String args[])
	{	
		new MonthlyReport();
	}
}








