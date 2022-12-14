import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import date.*;

class DailyReport extends JInternalFrame implements ActionListener
//class DailyReport extends JFrame implements ActionListener

{
	JLabel l1,l2,date;
	JButton b1,b2,b3;
	JPanel panel;
	JScrollPane scroll;
	JComboBox cb;
	JTable table;
	ResultModel rmodel;
	ResultSet res;
	Connection con;
	DateChooserPanel dpane,dp;
	JLabel lt1, lt2;
	DailyReport()
	{
		super("Daily Report",true,true,true,true);
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
		setVisible(true);
	}
	void drawFrame()
	{
		setLayout(null);
		setBounds(10,10,800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		dpane = new DateChooserPanel();
		dpane.setBounds(300,135,200,125);
		add(dpane);


		l1=new JLabel(new ImageIcon("head2.jpg"));
		l1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,new Color(72,145,149)));
		l1.setBounds(0,0,783,80);
		add(l1);		

		dp = new DateChooserPanel();
		date=new JLabel(dp.getShortDate());
		date.setBounds(675,1,115,30);
		date.setBorder(BorderFactory.createMatteBorder(0,2,2,0,new Color(72,145,149)));
		l1.add(date);



		l1=new JLabel("Daily Report",JLabel.CENTER);
		l1.setBorder(BorderFactory.createBevelBorder(1));
		l1.setFont(new Font("Arial",Font.PLAIN,40));
		l1.setBounds(0,80,800,50);
		add(l1);

		table=new JTable();
		scroll=new JScrollPane(table);
		scroll.setBounds(0,265,800,330);
		add(scroll);
		
		b1=new JButton("Print");
		b1.setBounds(570,615,80,30);
		b1.addActionListener(this);
		add(b1);

		b2=new JButton("Exit");
		b2.setBounds(660,615,80,30);
		b2.addActionListener(this);
		add(b2);
		
		b3=new JButton("Show Report");
		b3.setBounds(520,210,150,30);
		b3.addActionListener(this);
		add(b3);
		
		lt1=new JLabel("Total");
		lt1.setBounds(50,615,100,30);
		lt1.setBorder(BorderFactory.createBevelBorder(1));
		add(lt1);

		lt2=new JLabel();
		lt2.setBounds(150,615,80,30);
		lt2.setBorder(BorderFactory.createBevelBorder(1));
		add(lt2);


	
	}

	void setTable()
	{
		try
		{
			String query="select * from Bill where Bdate=\'"+dpane.getShortDate()+"\'";
			System.out.println(query);
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(query);

			rmodel=new ResultModel();
			rmodel.setResultSet(rs);
			table.setModel(rmodel);
			
			query="select sum(Amt) from Bill where Bdate=\'"+dpane.getShortDate()+"\'";
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
		
		else if(ae.getSource()==b3)
		{
			setTable();
		}

	}
	public static void main(String args[])
	{
		new DailyReport();
	}	 
}








