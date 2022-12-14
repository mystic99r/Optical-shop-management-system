import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
class MainWindow extends JFrame implements ActionListener
{
	JPanel panel;
	JButton b1,b2, b3,b4,b5,b6,b7,b8;
	static JDesktopPane desk;
	JLabel label;
	Connection con;
	MainWindow()
	{
		super("Optical Shop Management System");
		drawFrame();
		setVisible(true);
		//getData();
	}
	void drawFrame()
	{
		setSize(1024,768);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel=new JPanel(null);
		panel.setBounds(0,0,170,725);
		panel.setBorder(BorderFactory.createMatteBorder(1,1,1,3,new Color(72,145,149)));
		add(panel);

		//b1=new JButton("CUSTOMER");
		b1=new JButton(new ImageIcon("cust.jpg"));
		b1.setBounds(10,5,150,50);
		b1.addActionListener(this);
		panel.add(b1);

		b2=new JButton(new ImageIcon("item.jpg"));
		b2.setBounds(10,60,150,50);
		b2.addActionListener(this);
		panel.add(b2);

		b3=new JButton(new ImageIcon("trans.jpg"));
		b3.setBounds(10,115,150,50);
		b3.addActionListener(this);
		panel.add(b3);

		b4=new JButton(new ImageIcon("itemreport.jpg"));
		b4.setBounds(10,170,150,50);
		b4.addActionListener(this);
		panel.add(b4);

		//b5=new JButton("CUSTOMER REPORT");
		b5=new JButton(new ImageIcon("custreport.jpg"));
		b5.setBounds(10,225,150,50);
		b5.addActionListener(this);
		panel.add(b5);

		b6=new JButton(new ImageIcon("bill.jpg"));
		b6.setBounds(10,280,150,50);
		b6.addActionListener(this);
		panel.add(b6);

		b7=new JButton(new ImageIcon("daily.jpg"));
		b7.setBounds(10,335,150,50);
		b7.addActionListener(this);
		panel.add(b7);

		b8=new JButton(new ImageIcon("month.jpg"));
		b8.setBounds(10,390,150,50);
		b8.addActionListener(this);
		panel.add(b8);

		desk=new JDesktopPane();
		desk.setBounds(170,0,850,725);
		add(desk);

		label=new JLabel(new ImageIcon("back.jpeg"));
		label.setBounds(0,0,870,725);
		desk.add(label);
	}
	void getData()
	{
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
		String query="select * from items";

		try
		{
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(query);
			String report="We must order these Items\n";
			int i=1;
			
			while(rs.next())
			{
				report+=i+" : "+rs.getString("iname")+" : "+rs.getString("qty")+"\n";
				i++;
			}
			JOptionPane.showMessageDialog(this,report);
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
			CustomerList c=new CustomerList();
			desk.add(c);
			try
			{
				c.setSelected(true);
			}
			catch(Exception e){}
		}
		else if(ae.getSource()==b2)
		{
			ItemList i=new ItemList();
			desk.add(i);
			try
			{
				i.setSelected(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==b3)	
		{
			Transaction t1=new Transaction();
			desk.add(t1);
			try
			{
				t1.setSelected(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		else if(ae.getSource()==b4)	
		{
			ItemReport ir = new ItemReport();
			desk.add(ir);
			try
			{
				ir.setSelected(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}

		else if(ae.getSource()==b5)	
		{   
			CustomerReport cr = new CustomerReport();
			desk.add(cr);
			try
			{
				cr.setSelected(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}
		else if(ae.getSource()==b6)	
		{
		
				BillReport br = new BillReport();
				desk.add(br);
				try
			{
				br.setSelected(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}
		else if(ae.getSource()==b7)
		{
			DailyReport dr=new DailyReport();
			desk.add(dr);
			try
			{
				dr.setSelected(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}
		
		else if(ae.getSource()==b8)
		{
			MonthlyReport mr=new MonthlyReport();
			desk.add(mr);
			try
			{
				mr.setSelected(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}
	}
	public static void main(String ar[])
	{
/*		try{
UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e){}  */
		new MainWindow();
	}
}












