import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Login extends JFrame implements ActionListener
{
	JLabel loginidL, passwordL;
	JTextField loginidT;
	JPasswordField passwordT;
	JButton bok,breset,bexit;
	Connection con;
	ResultSet rs;
	JLabel l1;

	Login()
	{
		super("Login");
		setLayout(null);
		setSize(410,220);
		setLocation(350,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		loginidL=new JLabel("Login ID");
		loginidL.setSize(80,20);
		loginidL.setLocation(10,10);
		add(loginidL);

		loginidT=new JTextField();
		loginidT.setSize(120,30);
		loginidT.setLocation(120,10);
		add(loginidT);

		passwordL=new JLabel("Password");
		passwordL.setSize(80,20);
		passwordL.setLocation(10,60);
		add(passwordL);

		passwordT=new JPasswordField();
		passwordT.setSize(120,30);
		passwordT.setLocation(120,60);
		add(passwordT);

		bok=new JButton("Login");
		bok.setSize(100,30);
		bok.setLocation(20,130);
		bok.addActionListener(this);
		add(bok);

		breset=new JButton("Clear");
		breset.setSize(100,30);
		breset.setLocation(150,130);
		breset.addActionListener(this);
		add(breset);

		bexit=new JButton("Exit");
		bexit.setSize(100,30);
		bexit.setLocation(280,130);
		bexit.addActionListener(this);
		add(bexit);
		
		l1=new JLabel(new ImageIcon("login.png"));
		l1.setBounds(300,0,110,30);
		add(l1);

		setVisible(true);

		try
		{

				Class.forName("net.ucanaccess.jdbc.ucanaccessDriver");
			         Connection con =DriverManager.getConnection
("jdbc:ucanaccess://E:/PROJECT OF OPTICAL SHOP/shop/data.mdb");
				System.out.println("Connection Established");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}		
	}
	
	public void reset()
	{
		loginidT.setText("");
		passwordT.setText("");
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==bok)
		{
			try
			{
				Class.forName("net.ucanaccess.jdbc.ucanaccessDriver");
			         Connection con =DriverManager.getConnection
("jdbc:ucanaccess://E:/PROJECT OF OPTICAL SHOP/shop/data.mdb");
				System.out.println("Connection Established");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

			String login=loginidT.getText();
			String pass=passwordT.getText();

			String query="select * from logintable where login=\'"+login+"\' and pass=\'"+pass+"\'";
			try
			{
				Statement stat=con.createStatement();
				ResultSet rs=stat.executeQuery(query);

				if(rs.next())
				{
					new MainWindow();
					dispose();
				}
				else
					JOptionPane.showMessageDialog(this,"Access Denied");			
			}
			catch(Exception e){}
		}
		else if(ae.getSource()==breset)
		{
			reset();
		}
		else if(ae.getSource()==bexit)
		{
			System.exit(0);
		}
	}

	public static void main(String arg[])
	{
		new Login();
	}
}






