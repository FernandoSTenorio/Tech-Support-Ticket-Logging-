package SystemTech;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import om.BarChart;


public class Manager extends JFrame implements ActionListener{

	BarChart bar = new BarChart();

	private static final long serialVersionUID = 1L;
	private static final String String = null;
	final JButton btnRefresh;
	private JScrollPane scrollPane;
	



	public Manager(){
		this.setTitle("Manager");
		setSize(1000,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout() );	
		panel.add(bar);
		panel.validate();
		panel.setBounds(5, 5, 620, 500);

		panel.repaint();
		panel.setVisible(true);
		panel.validate();
		this.add(panel);

		JPanel panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createTitledBorder("Values in €"));
		panel1.setBounds(650, 5, 300, 100);
		panel.setLayout(null);
		String[][] ticket = new String[3][2];
		Login.controll.checkPrices(String);
		Login.controll.ticketsOpen(String);

		ticket[0][0]= Login.controll.ticketsOpen(String);
		ticket[1][0]= Login.controll.checkPrices(String);
		ticket[0][1] = "Close";
		ticket[1][1] = "Open";

		String [] colNames = {"Total Price (€50 per Ticket)", "Tickets"};

		JTable prices = new JTable(ticket , colNames);

		prices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		prices.getColumnModel().getColumn(0).setPreferredWidth(150);
		prices.setPreferredScrollableViewportSize(new Dimension(250, 40));
		scrollPane = new JScrollPane(prices);
		panel1.add(scrollPane);
		this.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createTitledBorder("Tech Support"));
		this.add(panel2);

		String[][] data = new String[5][2];
		Login.controll.checkTech();
		Login.controll.checkTech1();
		Login.controll.checkTech2();

		data[0][0]= "John";
		data[1][0]= "Johan";
		data[2][0]= "James";
		data[0][1]= Login.controll.checkTech1();
		data[1][1]= Login.controll.checkTech2();
		data[2][1]= Login.controll.checkTech();

		String [] colNames1 = {"Tech Name", "Tickets"};
		JTable table1 = new JTable(data, colNames1);

		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.getColumnModel().getColumn(1).setPreferredWidth(250);
		table1.setPreferredScrollableViewportSize(new Dimension(300, 150));
		scrollPane = new JScrollPane(table1);
		panel2.add(scrollPane);
		panel2.setBounds(650, 120, 350, 210);

		JPanel panel3 = new JPanel();
		this.add(panel3);
		panel3.setBounds(580, 350, 350, 100);
		JButton logOut = new JButton("Logout");
		logOut.addActionListener(this);
		logOut.setActionCommand("logout");
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				
				do_btnRefresh_actionPerformed(e);
				
			}

		});
		btnRefresh.setActionCommand("refresh");
		panel3.add(logOut);
		panel3.add(btnRefresh);

		validate();
		repaint();
		this.setVisible (true);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("logout")){

			int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
					"Confirmation",JOptionPane.YES_NO_OPTION);
			if (a == 0){
				dispose();
			}
		}
	}

	private void do_btnRefresh_actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("refresh")){
			if( e.getSource()==btnRefresh){
				Manager name = new Manager();
				String nm = name.getName();
				name.dispose();
				
			}
			 
			
		}

	}
}	