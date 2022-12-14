import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class ItemReport extends JInternalFrame implements ActionListener
{
	JLabel l1,l2;
	JButton b1,b2;
	JPanel panel;
	JScrollPane scroll;
	JTable table;
	ResultModel rmodel;
	Connection con;
	
	ItemReport()
	{
		super("Item Report",true,true,true,true);
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
	
		l1=new JLabel("Item Report",JLabel.CENTER);
		l1.setBorder(BorderFactory.createBevelBorder(1));
		l1.setFont(new Font("Arial",Font.PLAIN,40));
		l1.setBounds(0,0,800,80);
		add(l1);

		table=new JTable();
		scroll=new JScrollPane(table);
		scroll.setBounds(0,90,800,500);
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
			String query="select iid as ID,iname as ItemName,type as Item_Type, qty as Quantity,price as Item_Price from Items order by type";
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
		new ItemReport();
	}	 
}



