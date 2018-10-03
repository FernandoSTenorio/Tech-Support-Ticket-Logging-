package SystemTech;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import om.Ticket;

public class ControllerTickets implements ActionListener, WindowListener {

	JTextField username = null;
	JTextField txtUsername = null;
	JTextField txtId = null;
	JPasswordField newpass;
	JTextField tcId = null;
	JTextField creationDate = null;
	JTextField time = null;
	JTextField desc = null;
	long epochstart = 0;
	long epochend = 0;
	boolean confirm = true;

	String datetimeend = null;
	String datetimestart = null;

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		Component frame = null;

		if (JOptionPane.showConfirmDialog(frame, "Are you sure to close this window?", "Really Closing?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("addtickets")){
			techSupport();
		}
	}

	public void techSupport(){
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

			String techId = tcId.getText();
			String creation = creationDate.getText();
			String tm = time.getText();
			String dc = desc.getText();

			if (stmt.execute(" INSERT INTO `tickets` (`tech_id`, `creation`, `t_time`,`Description`) VALUES ('"+techId+"', '"+creation+"', '"+tm+"', '"+dc+"', NULL);")){
			}
		}
		catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} 
	}

	public void changePass(String usname, String newid, String pssd) {
		System.out.println(usname);
		System.out.println(newid);
		System.out.println(pssd);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		}catch(Exception ex ){}
		try{
			Connection conn = null;
			Statement stmt = Login.getStatment();
			ResultSet rs = null;

			conn =
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/ticket?user=root&password=");

			stmt = conn.createStatement();



			if(stmt.executeUpdate("UPDATE user SET password = '"+pssd+"', username = '"+usname+"' WHERE id = '"+newid+"'") > 0){
				rs = stmt.getResultSet();
			}else if(usname.equals(" ") && newid.equals(" ") && pssd.equals(" ")){

			}
		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());

		}
	}

	public List<Ticket> getAllTickets(){
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket;
		Statement stmt = Login.getStatment();
		ResultSet rs = null;;

		try{

			rs = stmt.executeQuery("SELECT * FROM tickets  ");

			while(rs.next()){
				ticket = new Ticket();
				ticket.setId(new Long(rs.getString("id")));
				ticket.setTechId(new Long(rs.getString("tech_id")));
				ticket.setCreation(rs.getDate("creation"));
				ticket.setClose(rs.getDate("close"));
				ticket.setPriority(rs.getString("priority"));
				ticket.setDescription(rs.getString("description"));

				tickets.add(ticket);
			} 

		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}

		return tickets;
	}

	public boolean insertTicket(Ticket ticket){

		Statement stmt = Login.getStatment();

		try{

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(stmt.execute(" INSERT INTO tickets (tech_id, creation,priority,description) VALUES ('"+ticket.getTechId()+"', '"+format.format(ticket.getCreation())+"','"+ticket.getPriority()+"', '"+ticket.getDescription()+"');"));
			{
				return true;
			}
		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}

		return false;
	}

	public boolean removeTicket(String id){

		Statement stmt = Login.getStatment();

		try{

			if(stmt.execute("DELETE FROM tickets WHERE id = '"+id+"'; "));
			{
				return true;
			}
		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}

		return false;
	}
	public boolean closingTicket(String id){

		Statement stmt = Login.getStatment();

		try{

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			if(stmt.executeUpdate("UPDATE tickets SET close = NOW()  WHERE id = '"+id+"' ;") >0 );
			{
				return true;

			} 
		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}

		return confirm;

	}
	public String checkPrices(String ticket){

		Statement stmt = Login.getStatment();
		ResultSet rs = null;
		
		try{
			
			
			rs = stmt .executeQuery("SELECT  COUNT(tech_id) * 50 from tickets where `close` IS  NULL;");
			
			while(rs.next()){
				ticket = rs.getString("COUNT(tech_id) * 50");
				
			}
		
		}catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}
		return ticket;
	}
	public String ticketsOpen(String tech){
		Statement stmt = Login.getStatment();
		ResultSet rs1 = null;
		
		try{

			rs1 = stmt .executeQuery("SELECT  COUNT(tech_id) * 50 from tickets where `close` IS NOT NULL;");
			
			while(rs1.next()){
				tech = rs1.getString(1);	
	
			}
		}catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}
		return tech;
	}
	public String checkTech(){
		String id = null;
		Statement stmt = Login.getStatment();
		ResultSet rs = null;
		
		try{
		
			rs = stmt .executeQuery("SELECT  DISTINCT tech_id, COUNT(creation) FROM tickets where `tech_id` = 2;");

			while(rs.next()){
				id = rs.getString("COUNT(creation)");
				
			}
		
		}catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}
		return id;
	}
	public String checkTech1(){
		String id = null;
		Statement stmt = Login.getStatment();
		ResultSet rs = null;
		
		try{

			rs = stmt .executeQuery("SELECT  DISTINCT tech_id, COUNT(creation) FROM tickets where `tech_id` = 1;");
			
			while(rs.next()){
				id = rs.getString("COUNT(creation)");	
				
			}		
		}catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}
		return id;	
	}
	public String checkTech2(){
		String id = null;
		Statement stmt = Login.getStatment();
		ResultSet rs = null;
		
		try{
		
			rs = stmt .executeQuery("SELECT  DISTINCT tech_id, COUNT(creation) FROM tickets where `tech_id` = 3;");
		
			while(rs.next()){
				id = rs.getString("COUNT(creation)");
			
			}	
		}catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}
		return id;		
	}
}