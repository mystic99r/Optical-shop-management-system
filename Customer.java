import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import date.*;

class Customer extends JInternalFrame implements ActionListener
{
	JButton b1,b2,b3,b4;
	JLabel l1,l2,l3,l4,l5,l6,date;
	JTextField tf1,tf2,tf4,tf5;
	JTextArea ta1;
	JScrollPane scroll1;
	Connection con;
	DateChooserPanel dpane;

	Customer()
	{
		super("Customer Details",true,true,true,true);
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
		System.out.println("Frame Visible");
	}
	Customer(String name)
	{
		super("Customer Details",true,true,true,true);
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
		setData(name);
		setVisible(true);
		System.out.println("Frame Visible");
	}
	void setData(String name)
	{
		
		try
		{
		String query="select * from Customer where cname=\'"+name+"\'";
		System.out.println(query);
		Statement stat=con.createStatement();
		ResultSet rs=stat.executeQuery(query);
		rs.next();

		int id=rs.getInt("cid");
		String name1=rs.getString("cname");
		String addr=rs.getString("caddr");
		String cont=rs.getString("contact");
		//double dis=rs.getDouble("discount");

		tf1.setText(id+"");
		tf2.setText(name1);
		ta1.setText(addr);
		tf4.setText(cont);
		//tf5.setText(dis+"");
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
	}
	void drawFrame()
	{
		setLayout(null);
		setBounds(10,10,500,450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		l6=new JLabel(new ImageIcon("logo.jpg"));
		l6.setBounds(0,0,485,75);
		l6.setBorder(BorderFactory.createMatteBorder(3,3,3,3,new Color(72,145,149)));
		add(l6);

		dpane = new DateChooserPanel();
		date=new JLabel(dpane.getShortDate());
		date.setBounds(367,1,115,30);
		date.setBorder(BorderFactory.createMatteBorder(0,2,2,0,new Color(72,145,149)));
		l6.add(date);

		l1=new JLabel("Customer ID");
		l1.setLocation(10,90);
		l1.setSize(80,30);
		add(l1);

		tf1=new JTextField();
		tf1.setBounds(120,90,75,30);
		tf1.setEditable(false);
		tf1.setFont(new Font("Comic Sans MS",Font.PLAIN,14));
		add(tf1);

		l2=new JLabel("Name");
		l2.setLocation(10,130);
		l2.setSize(80,30);
		add(l2);
		

		tf2=new JTextField();
		tf2.setBounds(120,130,200,30);
		tf2.setFont(new Font("Comic Sans MS",Font.PLAIN,14));
		add(tf2);

		l3=new JLabel("Address");
		l3.setLocation(10,170);
		l3.setSize(80,30);
		add(l3);
		
		ta1=new JTextArea();
		scroll1=new JScrollPane(ta1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setLocation(120,170);
		scroll1.setSize(150,60);
		ta1.setFont(new Font("Comic Sans MS",Font.PLAIN,14));
		add(scroll1);
		
		l4=new JLabel("Contact");
		l4.setLocation(10,240);
		l4.setSize(150,30);
		add(l4);

		tf4=new JTextField();
		tf4.setBounds(120,240,110,30);
		tf4.setFont(new Font("Comic Sans MS",Font.PLAIN,14));
		add(tf4);


	/*	l5=new JLabel("Discount");
		l5.setLocation(10,280);
		l5.setSize(100,30);
		add(l5);

		tf5=new JTextField();
		tf5.setBounds(120,280,75,30);
		tf5.setFont(new Font("Comic Sans MS",Font.PLAIN,14));
		add(tf5);*/


		b1=new JButton("SAVE");
		b1.setBounds(100,345,90,30);
		b1.addActionListener(this);
		b1.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(b1);

		b2=new JButton("CLEAR");
		b2.setBounds(200,345,90,30);
		b2.addActionListener(this);
		b2.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(b2);

		b3=new JButton("EXIT");
		b3.setBounds(300,345,90,30);
		b3.addActionListener(this);
		b3.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(b3);

	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)   //save code
		{

			int flag=0;
			System.out.println("Button Preessed");
			
			String name=tf2.getText();
			if(name.length()>0)
			{
				for(int i=0;i<name.length();i++)
				{
					char ch=name.charAt(i);
					if((ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||ch==' ')
						continue;
					else
					{
						JOptionPane.showMessageDialog(this,"Invalid Name");
						flag=1;
						break;
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Name Field Empty");
				flag=1;
			}
			String addr=ta1.getText();
	
			if(addr.length()==0)
			{                   flag=1;
					JOptionPane.showMessageDialog(this,"Address Field Empty");
			}

			String cont=tf4.getText();
	

			if(cont.length()>0)
			{
				if(cont.length()>=10&&cont.length()<=12)
				{
					for( int i=0;i<cont.length();i++)			
					{
						char ch=cont.charAt(i);
						if(ch>='0'&&ch<='9')
							continue;
						else
						{
							JOptionPane.showMessageDialog(this,"Invalid Contact");
							flag=1;
							break;
						}
					}
				}
		
				else
				{
					flag=1;
					JOptionPane.showMessageDialog(this,"Invalid Contact");
				}
			}
			else
			{	flag=1;
				JOptionPane.showMessageDialog(this,"Contact Field Empty");
			}

			/*String disc=tf5.getText();
			double dsc=0;
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
			}*/
					
			if(flag==0)
			{
				String query="";
				if(tf1.getText().length()==0)
				{
					query="insert into Customer(cname, caddr, contact) values(?,?,?)";
				}
				else
				{
	query="update Customer set cname=?, caddr=?, contact=? where cid="+tf1.getText();					}
				try
				{
					System.out.println(query);
					PreparedStatement pstat=con.prepareStatement(query);
					pstat.setString(1,name);
					pstat.setString(2,addr);
					pstat.setString(3,cont);
					//pstat.setDouble(4,dsc);		
					pstat.executeUpdate();
					JOptionPane.showMessageDialog(this,"Record Saved.");	
				}
				catch(Exception e)
				{
					System.out.println(e);
				}				
			}//if
		}
		
		if(ae.getSource()==b2)
		{
			tf1.setText("");
			tf2.setText("");
			ta1.setText("");
			tf4.setText("");
			//tf5.setText("");
		}   
		
		if(ae.getSource()==b3)   //EXIT code
		{
			dispose();
		}
	}//action performed
	public static void main(String args[])
	{
		new Customer();
	}
}































