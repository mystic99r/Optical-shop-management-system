import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import date.*;

class ItemList extends JInternalFrame implements ActionListener
{
	Connection con;
	Statement stat;
	ResultSet rs;
	DefaultListModel model;
	JScrollPane panel; 
	JList list;
	JLabel l1,date;
	JButton b1,b2,b3,b4;
	DateChooserPanel dpane;

	ItemList()
	{
		//super("ITEM");
		super("Item List",true,true,true,true);
		drawFrame();
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		         con=DriverManager.getConnection("jdbc:odbc:database");
			System.out.println("Connection Established");
		}
			
		catch(Exception e)
		{
			System.out.println(e);
		}
	
		setList();
		setVisible(true);
	}

	void drawFrame()
	{
		setLayout(null);
		setSize(500,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		l1=new JLabel(new ImageIcon("clistlogo.jpg"));
		l1.setBounds(0,0,490,75);
		l1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,new Color(72,145,149)));
		add(l1);

		dpane=new DateChooserPanel();
		date = new JLabel(dpane.getShortDate());
		date.setBounds(368,1,117,30);
		date.setBorder(BorderFactory.createMatteBorder(0,2,2,0,new Color(72,145,149)));
		l1.add(date);


		model=new DefaultListModel();		
		list=new JList(model);
		list.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
		panel=new JScrollPane(list);
		panel.setBounds(0,75,500,430);
		add(panel);
	
		b1=new JButton("NEW");
		b1.setBounds(50,520,90,30);
		b1.addActionListener(this);
		b1.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(b1);

		b2=new JButton("EDIT");
		b2.setBounds(150,520,90,30);
		b2.addActionListener(this);
		b2.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(b2);
		
		b3=new JButton("DELETE");
		b3.setBounds(250,520,90,30);
		b3.addActionListener(this);
		b3.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(b3);

		b4=new JButton("EXIT");
		b4.setBounds(350,520,90,30);
		b4.addActionListener(this);
		b4.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		add(b4);


	}
	
	public void actionPerformed(ActionEvent ae)
	{	
		if(ae.getSource()==b1)
		{
			Item i=new Item();
			MainWindow.desk.add(i);
			try
			{
				i.setSelected(true);
			}
			catch(Exception e){}
			dispose();
		}
		
		else if(ae.getSource()==b2)
		{
			String name=(String)list.getSelectedValue();
			if(name!=null)
			{
			Item i=new Item(name);
			MainWindow.desk.add(i);
			try
			{
				i.setSelected(true);
			}
			catch(Exception e){}
			dispose();
			}		
			else
			JOptionPane.showMessageDialog(this,"Select Item To Be Edited");
		}
		else if(ae.getSource()==b3)
		{
			String name=(String)list.getSelectedValue();
			if(name!=null)
			{
				String query="delete from Items where iname=?";
				try
				{
					PreparedStatement pstat=con.prepareStatement(query);
					pstat.setString(1,name);
					pstat.executeUpdate();
					model.clear();
					setList();
					JOptionPane.showMessageDialog(this,"Record Deleted.");
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			else
				JOptionPane.showMessageDialog(this,"Select Item To Be Deleted.");
		}
		else if(ae.getSource()==b4)
			dispose();
	}	
	
	void setList()
	{
		try
		{
			String query="select iname from Items order by iname";
			stat=con.createStatement();
			rs=stat.executeQuery(query);
			while(rs.next())
			{
				model.addElement(rs.getString("iname"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}			
	}

	public static void main(String arg[])
		{
			new ItemList();
		}
	}





















