import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import date.*;

class Item extends JInternalFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,date,l7,l8;
	JTextField tf1,tf2,tf4,tf5,tf7,tf8;
	JButton b1,b2,b3;
	JComboBox cb1;
	Connection con;
	DateChooserPanel dpane;
	
	Item()
	{
		super("Item Details",true,true,true,true);
		drawItem();
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:database");
				System.out.println("OK");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		setVisible(true);
	}
	
	Item(String name)
	{
		super("Item Details",true,true,true,true);
		drawItem();
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:database");
				System.out.println("OK");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		setData(name);
		setVisible(true);
	}

	void setData(String name)
	{
		try
		{
			String query="select * from Items where iname=\'"+name+"\'";
			System.out.println(query);
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(query);
			rs.next();
			
			int id=rs.getInt("iid");
			String nm=rs.getString("iname");
			String tp=rs.getString("type");
			int qty1=rs.getInt("qty");
			int avail1=rs.getInt("availqty");
			double prc=rs.getDouble("price");
			double dis=rs.getDouble("discount");

			tf1.setText(id+"");
			tf2.setText(nm);
			cb1.setSelectedItem(tp);
			tf4.setText(qty1+"");
			tf8.setText(avail1+"");
			tf5.setText(prc+"");
			tf7.setText(dis+"");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
	}
	void drawItem()
	{
		setLayout(null);
		setBounds(10,10,500,450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		l6=new JLabel(new ImageIcon("logo.jpg"));
		l6.setBounds(0,0,485,75);
		l6.setBorder(BorderFactory.createMatteBorder(3,3,3,3,new Color(72,145,149)));

		add(l6);

		dpane=new DateChooserPanel();
		date=new JLabel(dpane.getShortDate());
		date.setBounds(367,1,115,30);
		date.setBorder(BorderFactory.createMatteBorder(0,2,2,0,new Color(72,145,149)));
		l6.add(date);

		l1=new JLabel("Item Id");
		l1.setBounds(10,90,80,30);
		add(l1);

		tf1=new JTextField();
		tf1.setBounds(120,90,75,30);
		tf1.setEditable(false);
		tf1.setFont( new Font("Comic sans MS",Font.PLAIN,14));
		add(tf1);
		
		l2=new JLabel("Name");
		l2.setBounds(10,130,80,30);
		add(l2);

		tf2=new JTextField();
		tf2.setBounds(120,130,200,30);
		tf2.setFont( new Font("Comic sans MS",Font.PLAIN,14));
		add(tf2);

		l3=new JLabel("Type");
		l3.setBounds(10,170,80,30);	
		add(l3);

		cb1=new JComboBox();
		cb1.addItem("Frame");
		cb1.addItem("Glairs");
		cb1.addItem("Glasses");
		cb1.addItem("Lences");
		cb1.addItem("Solutions");
		cb1.setBounds(120,170,80,30);
		cb1.setFont( new Font("Comic sans MS",Font.BOLD,14));
		add(cb1);
		
		l4=new JLabel("Quantity");
		l4.setBounds(10,210,80,30);
		add(l4);

		tf4=new JTextField();
		tf4.setBounds(120,210,80,30);
		tf4.setFont( new Font("Comic sans MS",Font.PLAIN,14));
		add(tf4);


              		l8=new JLabel("Avilable Quantity");
		l8.setBounds(220,210,150,30);
		add(l8);

		tf8=new JTextField();
		tf8.setBounds(340,210,80,30);
		tf8.setFont( new Font("Comic sans MS",Font.PLAIN,14));
		add(tf8);

		l5=new JLabel("Price");
		l5.setBounds(10,250,80,30);
		add(l5);

		tf5=new JTextField();
		tf5.setBounds(120,250,80,30);
		tf5.setFont( new Font("Comic sans MS",Font.PLAIN,14));
		add(tf5);
		
		l7=new JLabel("Discount");
		l7.setBounds(10,290,80,30);
		add(l7);

		tf7=new JTextField();
		tf7.setBounds(120,290,80,30);
		tf7.setFont( new Font("Comic sans MS",Font.PLAIN,14));
		add(tf7);


		b1=new JButton("SAVE");
		b1.setBounds(90,345,90,30);
		b1.setFont( new Font("Comic sans MS",Font.BOLD,14));
		b1.addActionListener(this);
		add(b1);
		
		b2=new JButton("CLEAR");
		b2.setBounds(190,345,90,30);
		b2.setFont( new Font("Comic sans MS",Font.BOLD,14));
		b2.addActionListener(this);
		add(b2);

		b3=new JButton("EXIT");
		b3.setBounds(290,345,90,30);
		b3.setFont( new Font("Comic sans MS",Font.BOLD,14));
		b3.addActionListener(this);
		add(b3);

		
	}
	public void actionPerformed(ActionEvent ae)
	{
		int flag=0;
		if(ae.getSource()==b1)
		{
			String name=tf2.getText();
			if(name.length()==0)
			{
				JOptionPane.showMessageDialog(this,"Name Field Empty");				
				flag=1;
			}

			String type=(String)cb1.getSelectedItem();
			
			String qty=tf4.getText();
			int qt=0;
		
			if(qty.length()>0)
			{			
				try
				{
					qt=Integer.parseInt(qty);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this,"Invalid Quantity");				
					flag=1;
				}	
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Quantity Field Empty");				
				flag=1;
	
			}


			String avail=tf8.getText();
			int qt1=0;
		
			if(avail.length()>0)
			{			
				try
				{
					qt1=Integer.parseInt(avail);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this,"Invalid Quantity");				
					flag=1;
				}	
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Quantity Field Empty");				
				flag=1;
	
			}
	
			String price=tf5.getText();
			double cost=0;
			if(price.length()==0)
			{
				JOptionPane.showMessageDialog(this,"Price Field Empty");				
				flag=1;
			}
			else
			{	
				try
				{
					cost=Double.parseDouble(price);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this,"Invalid Price");				
					flag=1;
				} 
			}

			String disc=tf7.getText();
			double dsc=0;
			if(disc.length()==0)
			{	flag=1;
				JOptionPane.showMessageDialog(this,"Discount field Empty");
			}
			
			else
			{
			try
			{
				dsc=Double.parseDouble(disc);
				if(dsc<0||dsc>100)
					throw new Exception();		
			}
			catch(Exception e)
			{
				flag=1;
				JOptionPane.showMessageDialog(this,"Invalid Discount \nValid Range 0 to 100");
			}
			}
		

			if(flag==0)
			{
				String query="";
				if(tf1.getText().length()==0)
				{
					query="insert into Items(iname, type,qty,availqty,price,discount) values(?,?,?,?,?,?)";
				}
				else
				{
					query="update Items set iname=?, type=?, qty=?,availqty=?, price=?,discount=? where iid="+tf1.getText();								}
				try
				{
					PreparedStatement pstat=con.prepareStatement(query);
					pstat.setString(1,name);
					pstat.setString(2,type);
					pstat.setInt(3,qt);
					pstat.setInt(4,qt1);
					pstat.setDouble(5,cost);
					pstat.setDouble(6,dsc);
					pstat.executeUpdate();
	
					JOptionPane.showMessageDialog(this,"Data Entered");	
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}

	if(ae.getSource()==b2)
	{
			tf2.setText("");
			tf4.setText("");
			tf5.setText("");
			cb1.setSelectedIndex(0);
	}
		
		if(ae.getSource()==b3)
		{
			dispose();
		}

}
	public static void main(String args[])
	{
		new Item();
	}

}

























