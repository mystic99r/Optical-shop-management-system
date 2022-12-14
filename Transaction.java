import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import date.*;

class Transaction extends JInternalFrame implements MouseListener,ActionListener
{
	Statement stat;
	Connection con;
	 ResultSet rs;
	JLabel l1,l2,l3,l4,l5,l6,total,date,dateL;
	JButton b1,b2,b3,b4;
	JTextField tf2,tf3,tf4;
	JComboBox combo;
	DefaultListModel model;
	JScrollPane panel, panel1;
	JPanel panel2;
	JList list;
	JTable table;
	ResultModel rsmodel;
	DateChooserPanel dpane;

	Transaction()
	{
		super("Transaction Details",true,true,true,true);
		drawFrame();
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:database");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		setList();
		setTable();
		setCombo();
		setVisible(true);
	}
	
	void drawFrame()
	{
		setBounds(10,10,800,700);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		l6=new JLabel("BILL",JLabel.CENTER);
		l6.setBounds(10,35,750,30);
		l6.setFont(new Font("Times New Roman",Font.BOLD,40));
		l6.setForeground(new Color(183,89,210));
		add(l6);

		dpane=new DateChooserPanel();
		
		panel2=new JPanel(null);//base panel
		panel2.setBounds(10,100,750,460);
		panel2.setBorder(BorderFactory.createBevelBorder(1));
		add(panel2);

		dateL=new JLabel("Date  : ",JLabel.RIGHT);
		dateL.setBounds(550,10,100,30);
		//dateL.setBorder(BorderFactory.createBevelBorder(1));
		add(dateL);

		date=new JLabel(dpane.getShortDate());
		date.setBounds(660,10,120,30);
		//date.setBorder(BorderFactory.createBevelBorder(1));
		add(date);

		l5=new JLabel("Item List",JLabel.CENTER);
		l5.setBounds(500,20,240,30);
		l5.setFont(new Font("Times New Roman",Font.PLAIN,22));
		l5.setBorder(BorderFactory.createBevelBorder(1));
		panel2.add(l5);

		model=new DefaultListModel();
		list =new JList(model);
		list.setFont(new Font("Times New Roman",Font.PLAIN,20));
		list.addMouseListener(this);
		panel=new JScrollPane(list);
		panel.setBounds(500,50,240,340);
		//panel.setBorder(BorderFactory.createMatteBorder(1,2,1,1,Color.red));
		panel2.add(panel);

		l2=new JLabel("Bill No");
		l2.setBounds(10,10,60,30);
		panel2.add(l2);
	
		tf2=new JTextField();
		tf2.setBounds(120,10,80,30);
		tf2.setEnabled(false);
		panel2.add(tf2);
	
		l3=new JLabel("Customer Name");
		l3.setBounds(10,50,100,30);
		panel2.add(l3);
	
		combo=new JComboBox();
		//tf3=new JTextField();
		combo.setBounds(120,50,200,30);
		//tf3.setBackground(getBackground());
		//tf3.setBorder(null);
		combo.removeAll();
		panel2.add(combo);
		
		table=new JTable();
		panel1=new JScrollPane(table);
		panel1.setBounds(10,90,480,300);
		panel2.add(panel1);

		l4=new JLabel("TOTAL");
		l4.setBounds(350,390,50,30);
		panel2.add(l4);

		total=new JLabel();
		total.setBounds(400,390,80,30);
		panel2.add(total);
		
		b1=new JButton("Refresh");
		b1.setBounds(10,420,100,30);
		b1.addActionListener(this);
		panel2.add(b1);

		b2=new JButton("Save");
		b2.setBounds(110,420,100,30);
		b2.addActionListener(this);
		panel2.add(b2);
		
		b3=new JButton("Print");
		b3.setBounds(210,420,100,30);
		b3.addActionListener(this);
		b3.setEnabled(false);
		panel2.add(b3);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			Refresh();
		}
		else if(ae.getSource()==b2)
		{

			//addTransaction();
			b3.setEnabled(true);
			try
			{
				String query="insert into Bill(CustName, Bdate, Amt) values(?,?,?)";
				PreparedStatement pstat=con.prepareStatement(query);
				pstat.setString(1,(String)combo.getSelectedItem());
				pstat.setString(2,date.getText());
				pstat.setDouble(3,Double.parseDouble(total.getText()));
				pstat.executeUpdate();
				Statement stat=con.createStatement();
				ResultSet rs=stat.executeQuery("select max(Bid) from Bill");
				rs.next();
				tf2.setText(rs.getInt(1)+"");

				JOptionPane.showMessageDialog(this,"Record Saved");
				//Refresh();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==b3)
		{
			try
			{
				table.print();
			}
			catch(Exception e){}
			b3.setEnabled(false);
		}		
	}
	void Refresh()	
	{
		try
		{
			String query4="delete from Trans";
			PreparedStatement pstat=con.prepareStatement(query4);
			pstat.executeUpdate();
			setTable();				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void setCombo()
	{
		try
		{
			String query="select * from Customer";
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(query);
			while(rs.next())
			{
				combo.addItem(rs.getString("cname"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
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

	public void mouseExited(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource()==list)
		{
			if(me.getClickCount()==2)
			{
				addTransaction();
			}
		}		
	}
	void addTransaction()
	{
		System.out.println(list.getSelectedValue());

		String input=JOptionPane.showInputDialog(this,"Quantity"); //Quntity in String
		boolean flag=true;
		int qty=0;			
		try
		{
			qty=Integer.parseInt(input);		//Quntity in Integer
		}
		catch(Exception e)
		{
			flag=false;
			JOptionPane.showMessageDialog(this,"invalid");
		}


		if(flag==true)
		{
			try
			{
				String item=(String)list.getSelectedValue();	//Getting selected Item Name

				String query1="select price,qty,availqty,discount from Items where iname=\'"+item+"\'";

				Statement stat=con.createStatement();
				ResultSet rs=stat.executeQuery(query1);
				rs.next();

				int orignl=rs.getInt("qty");
				int avail=rs.getInt("availqty");
				double rate=rs.getDouble("price");
				double discount=rs.getDouble("discount");
				//System.out.println(rate);

				if(qty<avail)
				{
					String query2="insert into trans values(?,?,?,?,?)";
					PreparedStatement pstat=con.prepareStatement(query2);
					pstat.setString(1,item);
					pstat.setInt(2,qty);
					pstat.setDouble(3,rate);
					pstat.setDouble(4,qty*(rate-rate*discount/100));
					pstat.setDouble(5,discount);
					pstat.executeUpdate();

					//******************Subtracting user quantity from original quantity in stock

					query2="update Items set availqty=availqty-"+qty+"  where iname=\'"+item+"\'";
					pstat=con.prepareStatement(query2);
					pstat.executeUpdate();
					setTable();
				}
				else
					JOptionPane.showMessageDialog(this,"Quantity Not Available");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	void setTable()
	{
		try
		{
			String query3="select Iname as Description, Qty as Quantity, Amt as Rate, discount as Discount_,famt as Amount from trans";
			stat=con.createStatement();
			rs=stat.executeQuery(query3);
			
			rsmodel=new ResultModel();
			rsmodel.setResultSet(rs);
			table.setModel(rsmodel);
			
			query3="select sum(famt) from trans";
			stat=con.createStatement();
			rs=stat.executeQuery(query3);
			rs.next();
			
			double totalamt=rs.getDouble(1);
			total.setText(totalamt+"");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void main(String args[])
	{
		new Transaction();
	}
}








