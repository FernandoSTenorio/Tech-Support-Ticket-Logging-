package SystemTech;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SingleSelectionModel;
import javax.swing.table.DefaultTableModel;
import om.Ticket;

@SuppressWarnings("serial")
public class TechSupport extends JFrame implements ActionListener, WindowListener{
	
	private JTable table;
	private JTextField tcId;
	private JTextField desc;
	private JComboBox<String> c;
	private String[] s = {"urgente" , "normal", "longterm"};
	private JButton btRemove;
	private JButton btLogOut;
	private JTextArea text;

	public TechSupport(){

		this.setTitle("Tech Support");
		setVisible (true);
		setSize(1000,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("View Tickets"));	
		panel.setBounds(5, 5, 550, 490);
		this.add(panel);

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Id");
		model.addColumn("Tech_ID");
		model.addColumn("Creation");
		model.addColumn("Close");
		model.addColumn("Priority");
		model.addColumn("Desciprtion");

		table = new JTable(model);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.setPreferredScrollableViewportSize(new Dimension(500, 350));
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		btRemove = new JButton("Remove");
		btRemove.addActionListener(this);
		btRemove.setActionCommand("remove");
		panel.add(btRemove);
		btLogOut = new JButton("Logout");
		btLogOut.addActionListener(this);
		btLogOut.setActionCommand("logout");
		panel.add(btLogOut);
		this.getContentPane().add(panel);


		JPanel panel1 = new JPanel();
		JLabel lbl = new JLabel("Tech Support");
		panel1.add(lbl);
		tcId = new JTextField(20);
		panel1.add(tcId, lbl);
		JLabel dc = new JLabel("Priority");
		panel1.add(dc);
		c = new JComboBox<String>(s);
		panel1.add(c, dc);
		c.setVisible(true);
		JLabel lb = new JLabel("Description:");
		panel1.add(lb);
		text = new JTextArea(12, 25);
		text.setBounds(520, 100, 200, 150);
		panel1.add(text);

		JButton addButton = new JButton("Add Tickets");
		addButton.addActionListener(this);
		addButton.setActionCommand("addTickets");
		panel1.add(addButton);
		JButton closeButton = new JButton("Close Ticket");
		closeButton.addActionListener((ActionEvent e)->{
			if (table.getSelectedRow() != 0) {
				Ticket ticket = new Ticket();
				ticket.setClose(new Date());
				Long id = (Long) ((DefaultTableModel) table.getModel()).getValueAt(table.getSelectedRow(), 0);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				int n = JOptionPane.showConfirmDialog(null, "Ticket Closed",
						"Confirmation",JOptionPane.YES_NO_OPTION);
				if( n == 0 && Login.controll.closingTicket(id.toString())){	
					((DefaultTableModel) table.getModel()).setValueAt(format.format(ticket.getClose()), table.getSelectedRow(), 3);
				}
			}
		});
		panel1.add(closeButton);
		panel1.setBounds(560, 50, 390, 400);
		panel1.setBorder(BorderFactory.createTitledBorder("Support Tickets"));
		this.add(panel1);	

		upDateTicketsTable();
	}

	public void upDateTicketsTable(){
		List<Ticket> tickets = Login.controll.getAllTickets();
		Ticket ticket;
		int i, j;

		for(j = ((DefaultTableModel) table.getModel()).getRowCount()-1; j >=0; j--){
			((DefaultTableModel) table.getModel()).removeRow(j);
		}

		for(i = 0; i<tickets.size(); i++)
		{
			ticket = tickets.get(i);
			((DefaultTableModel) table.getModel()).addRow(new Object[] 
					{ticket.getId(),
					ticket.getTechId(), 
					ticket.getCreation(), ticket.getClose(),
					ticket.getPriority(), ticket.getDescription()});
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("addTickets")){
			Ticket ticket = new Ticket();
			ticket.setTechId(new Long(tcId.getText()));
			ticket.setCreation(new Date());
			ticket.setPriority(c.getSelectedItem().toString());
			ticket.setDescription(new String(text.getText()));

			if(Login.controll.insertTicket(ticket)) {
				JOptionPane.showMessageDialog(this, "Ticket Added Successful", "", JOptionPane.INFORMATION_MESSAGE);
				upDateTicketsTable();
			}

		}
		else if(e.getActionCommand().equals("remove")) {
			if (table.getSelectedRow() != -1) {
				Long id = (Long) ((DefaultTableModel) table.getModel()).getValueAt(table.getSelectedRow(), 0);

				int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this ticket?",
						"Confirmation",JOptionPane.YES_NO_OPTION);
				if (n == 0 &&  Login.controll.removeTicket(id.toString())){
					((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());

				}
			}
		}else if (e.getActionCommand().equals("logout")) {

			int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
					"Confirmation",JOptionPane.YES_NO_OPTION);
			if (a == 0){
				dispose();
			}
		}
	}

	public void windowOpened(WindowEvent e) {

	}
	public void windowClosing(WindowEvent e) {

		if (JOptionPane.showConfirmDialog(btLogOut, "Are you sure to close this window?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_NO_OPTION) {
			System.exit(DISPOSE_ON_CLOSE);
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
