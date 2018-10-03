package SystemTech;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;


public class SystemAdmin extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUsername = new JLabel("Username:	   ");
	private JLabel lblID = new JLabel("ID:                 ");
	private JLabel lblNPassword = new JLabel("New Password:");
	private JTextField txtUsername = new JTextField(35);
	private JPasswordField pass = new JPasswordField(35);
	private JTextField txtId = new JTextField(35);
	private JPasswordField newpass = new JPasswordField(35);
	JTextField username;
	JComboBox<String> c;
	String[] st = {"techsuport", "manager", "admin"};

	TechSupport tech = null;

	ControllerTickets controll = null;

	Login user = null;

	public SystemAdmin(){

		controll = new ControllerTickets();
		this.setTitle("System Admin");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(550, 750);
		setLocationRelativeTo(null);
		this.setLayout(new GridLayout(5,0));

		JPanel panel = new JPanel();
		username = new JTextField(35);
		panel.add(username);
		panel.setBorder(BorderFactory.createTitledBorder("Username"));
		this.add(panel);
		c = new JComboBox<String>(st);
		panel.add(c);

		JPanel panel1 = new JPanel();
		pass = new JPasswordField(35);
		panel1.add(pass);
		panel1.setBorder(BorderFactory.createTitledBorder("Password"));
		this.add(panel1);


		JPanel pn = new JPanel();
		JButton register = new JButton ("Register");
		pn.add(register);
		register.addActionListener(this);
		register.setActionCommand("register");

		this.add(pn);

		newPass1();
		//sysadmin();
		validate();
		repaint();	
	}





	public void newPass1(){

		JPanel pn1 = new JPanel();
		pn1.setBorder(BorderFactory.createTitledBorder("Change here your password"));
		this.add(pn1);
		JPanel lbl = new JPanel(new GridLayout());
		lbl.add(lblUsername);
		lbl.add(lblID);

		JPanel txt = new JPanel(new GridLayout());
		txt.add(txtUsername);
		txt.add(txtId);

		JPanel renew = new JPanel(new GridLayout());
		renew.add(lblNPassword);
		renew.add(newpass);

		JPanel form = new JPanel();
		form.add(lbl);
		form.add(txt);
		pn1.add(lblUsername);
		pn1.add(txtUsername);
		pn1.add(lblID);
		pn1.add(txtId);
		pn1.add(lblNPassword);
		pn1.add(newpass);
		JButton button1 = new JButton("Save Changes");
		pn1.add(button1);
		button1.addActionListener(this);			
		button1.setActionCommand("changepass");

		JButton logout = new JButton("Logout");
		pn1.add(logout);
		logout.addActionListener(this);
		logout.setActionCommand("logout");


	}		
	public void sysadmin(){

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		}catch(Exception ex ){}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;;
		try {
			conn =
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/ticket?user=root&password=");

			stmt = conn.createStatement();

			String un = username.getText();
			String pw = pass.getText();
			String tp = c.getSelectedItem().toString();


			if (stmt.execute(" INSERT INTO user (`username`, `password`, `type`) VALUES ('"+un+"', '"+pw+"', '"+tp+"');")) {

			}
		}
		catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} 
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		if(e.getActionCommand().equals("changepass")){
			controll.changePass(txtUsername.getText(),txtId.getText(), new String (newpass.getPassword()));
			JOptionPane.showMessageDialog(this, "Password Update Successful", "", JOptionPane.INFORMATION_MESSAGE);

		}if(e.getActionCommand().equals("register")){
			sysadmin();
			JOptionPane.showMessageDialog(this, "User Regitered", "", JOptionPane.INFORMATION_MESSAGE);	
		}	else if(e.getActionCommand().equals("logout")){

			int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
					"Confirmation",JOptionPane.YES_NO_OPTION);
			if (a == 0){
				dispose();	
			}			
		}
	}
}
