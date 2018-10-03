package SystemTech;
import javax.swing.*;

import om.BarChart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends JFrame implements ActionListener {

	public static Statement stmt = null;
	JTextField username = null;
	JPasswordField newpass = new JPasswordField(20);

	public static SystemAdmin admin;
	public static ControllerTickets controll = new ControllerTickets();
	public static TechSupport tech;
	public static Manager manager;
	public static BarChart bar;
	boolean developerMode = true;

	public static void main(String[] args) {
		new Login();
	}

	public Login() {

		this.setTitle("Login");
		this.setSize(350, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagConstraints c1 = new GridBagConstraints();
		
		JLabel label = new JLabel("Username: ");
		JLabel label1 = new JLabel("Password: ");       
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(label);
		username = new JTextField(20);
		panel.add(username);

		panel.add(label1);
		panel.add(newpass);
		
		JButton btn = new JButton("Close");
		panel.add(btn);
		btn.addActionListener(this);
		btn.setActionCommand("cls");
		this.getContentPane().add(panel);


		JButton button2 = new JButton("Login");
		panel.add(button2, c1);
		button2.addActionListener(this);      
		button2.setActionCommand("login");
		
		validate();
		repaint();
		this.setVisible(true);

	}

	public void login(String user, String pass) {

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

		}catch(Exception f ){}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn =
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/ticket?user=root&password=");
	
			stmt = conn.createStatement();

			if (stmt.execute("select * from user where username = '"+user+"' and password = '"+pass+"';")) {
				rs = stmt.getResultSet();
			}

			while(rs.next()){
				String type = rs.getString("type");
				System.out.println(type);
				if(type.equalsIgnoreCase("admin"))
				{
					admin = new SystemAdmin();
				}else if(type.equalsIgnoreCase("techsuport")){
					tech = new TechSupport();
				}else if(type.equalsIgnoreCase("manager")){
					manager = new Manager();
				}

				System.out.print(" ");
				String id = rs.getString("id");
				System.out.println("ID: " + id);

				String sid = rs.getString("username");
				System.out.println("UN: " + sid);

			} 
		} catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public static Statement getStatment(){

		try 
		{
			if (stmt == null){
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ticket?user=root&password=");
				stmt = conn.createStatement();
			}
		}
		catch(Exception ex ){}
		return stmt;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("login")){
			login(username.getText(), newpass.getText());
		}else if(e.getActionCommand().equals("cls")){
			Component createGui = null;
			int n = JOptionPane.showConfirmDialog(createGui, "Are you sure you wnt to close the program?",
					"Confirmation",JOptionPane.YES_NO_OPTION);
			if (n == 0){
				System.exit(0);
			}
		}	
	}
}