package net.codejava.hibernate.EventVisualization.client;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class AdminView {

	private JFrame frame;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField_0;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblId_1;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField textField_id;
	private JTextField textField_username;
	private JTextField textField_password;
	private JButton btn_add_2;
	private JButton btn_update_2;
	private JButton btn_delete_2;
	
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private String col_table[]= {"Id", "Name", "Description", "Release Date", "Type", "Imdb No", "Imdb Sum"};
	private JScrollPane scrollPane_2;
	private DefaultTableModel tableModel_2;
	private String col_table_2[]= {"Id", "Username", "Password", "Premium"};

	
	private AdminController adminController;
	private JButton btnDisplayAll;
	private JTextField textField_premium;
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { System.out.println("Admin View run");
					AdminView window = new AdminView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminView() {
		adminController = new AdminController(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 749, 386);

		// used to firstly close the server before closing the window
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	
		        	adminController.closeServer();     	
		            System.exit(0);
		        }
		    }
		});
		
		frame.getContentPane().setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(10, 25, 400, 133);
		frame.getContentPane().add(table_1);
		scrollPane = new JScrollPane(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.RowDataSelected();
			}
		});
		scrollPane.setBounds(10, 25, 400, 133);
		frame.getContentPane().add(scrollPane);
		
		table_2 = new JTable();
		table_2.setBounds(10, 198, 400, 139);
		frame.getContentPane().add(table_2);
		scrollPane_2 = new JScrollPane(table_2);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.UserRowDataSelected();
			}
		});
		scrollPane_2.setBounds(10, 198, 400, 139);
		frame.getContentPane().add(scrollPane_2);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(439, 14, 46, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(439, 39, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(439, 64, 66, 14);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblReleaseDate = new JLabel("Release Date");
		lblReleaseDate.setBounds(439, 89, 76, 14);
		frame.getContentPane().add(lblReleaseDate);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(439, 114, 46, 14);
		frame.getContentPane().add(lblType);
		
		JLabel lblImdbno = new JLabel("Imdb_no");
		lblImdbno.setBounds(439, 139, 46, 14);
		frame.getContentPane().add(lblImdbno);
		
		JLabel lblImdbs = new JLabel("Imdb_s");
		lblImdbs.setBounds(439, 164, 46, 14);
		frame.getContentPane().add(lblImdbs);
		
		textField_0 = new JTextField();
		textField_0.setBounds(517, 11, 86, 20);
		frame.getContentPane().add(textField_0);
		textField_0.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(517, 36, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(517, 61, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(517, 86, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(517, 111, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(517, 136, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(517, 161, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JButton btn_add = new JButton("Add");
		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adminController.btnAddClicked();
			}
		});
		btn_add.setBounds(621, 55, 89, 23);
		frame.getContentPane().add(btn_add);
		
		JButton btn_update = new JButton("Update");
		btn_update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnUpdateClicked();
			}
		});
		btn_update.setBounds(621, 80, 89, 23);
		frame.getContentPane().add(btn_update);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnDeleteClicked();
			}
		});
		btn_delete.setBounds(621, 110, 89, 23);
		frame.getContentPane().add(btn_delete);
		
		lblId_1 = new JLabel("Id");
		lblId_1.setBounds(439, 211, 46, 14);
		frame.getContentPane().add(lblId_1);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(439, 236, 66, 14);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(439, 261, 66, 14);
		frame.getContentPane().add(lblPassword);
		
		textField_id = new JTextField();
		textField_id.setBounds(517, 208, 86, 20);
		frame.getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		textField_username = new JTextField();
		textField_username.setBounds(517, 233, 86, 20);
		frame.getContentPane().add(textField_username);
		textField_username.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setBounds(517, 258, 86, 20);
		frame.getContentPane().add(textField_password);
		textField_password.setColumns(10);
		
		btn_add_2 = new JButton("Add");
		btn_add_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adminController.btnAddUserClicked();
			}
		});
		btn_add_2.setBounds(621, 207, 89, 23);
		frame.getContentPane().add(btn_add_2);
		
		btn_update_2 = new JButton("Update");
		btn_update_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adminController.btnUpdateUserClicked();
			}
		});
		btn_update_2.setBounds(621, 232, 89, 23);
		frame.getContentPane().add(btn_update_2);
		
		btn_delete_2 = new JButton("Delete");
		btn_delete_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnDeleteUserClicked();
			}
		});
		btn_delete_2.setBounds(621, 257, 89, 23);
		frame.getContentPane().add(btn_delete_2);
		
		btnDisplayAll = new JButton("Display All");
		btnDisplayAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnDisplayAllClicked();
			}
		});
		btnDisplayAll.setBounds(439, 314, 107, 23);
		frame.getContentPane().add(btnDisplayAll);
		
		JLabel lblPremium = new JLabel("Premium");
		lblPremium.setBounds(439, 289, 46, 14);
		frame.getContentPane().add(lblPremium);
		
		textField_premium = new JTextField();
		textField_premium.setBounds(517, 286, 86, 20);
		frame.getContentPane().add(textField_premium);
		textField_premium.setColumns(10);
		
		tableModel = new DefaultTableModel(col_table, 0);
		tableModel_2 = new DefaultTableModel(col_table_2, 0);
	}
	
	
	public void displayData() {
		// get the model from the jtable
	    DefaultTableModel model = (DefaultTableModel)table_1.getModel();

	    // get the selected row index
	    int selectedRowIndex = table_1.getSelectedRow();
	       
	    // set the selected row data into jtextfields
	    String id = model.getValueAt(selectedRowIndex, 0).toString();
	    String name = model.getValueAt(selectedRowIndex, 1).toString();
	    String descr = model.getValueAt(selectedRowIndex, 2).toString();
	    String release_date = model.getValueAt(selectedRowIndex, 3).toString();
	    String type = model.getValueAt(selectedRowIndex, 4).toString();
	    String imdb_no = model.getValueAt(selectedRowIndex, 5).toString();
	    String imdb_s = model.getValueAt(selectedRowIndex, 6).toString();
	    

	    textField_0.setText(id);
	    textField_1.setText(name);
	    textField_2.setText(descr);
	    textField_3.setText(release_date);
	    textField_4.setText(type);
	    textField_5.setText(imdb_no);
	    textField_6.setText(imdb_s);
	}
	
	public void displayUserData() {
		// get the model from the jtable
	    DefaultTableModel model = (DefaultTableModel)table_2.getModel();

	    // get the selected row index
	    int selectedRowIndex = table_2.getSelectedRow();
	       
	    // set the selected row data into jtextfields
	    String id = model.getValueAt(selectedRowIndex, 0).toString();
	    String username = model.getValueAt(selectedRowIndex, 1).toString();
	    String password = model.getValueAt(selectedRowIndex, 2).toString();
	    String premium = model.getValueAt(selectedRowIndex, 3).toString();

	    textField_id.setText(id);
	    textField_username.setText(username);
	    textField_password.setText(password);
	    textField_premium.setText(premium);   
	}
	
	public Object[] getData() {
		Object[] data = {textField_0.getText().toString(),
						textField_1.getText().toString(), 
						textField_2.getText().toString(), 
						textField_3.getText().toString(),
						textField_4.getText().toString(),
						textField_5.getText().toString(),
						textField_6.getText().toString()};
		return data;
	}
	
	public void displayShows(List<Object[]> data) {
		tableModel.setRowCount(0);	 // delete the old rows	
		table_1.setModel(tableModel);
		     
		for(Object[] show: data) {
			 tableModel.addRow(show);	
		}		
	}
	
	public void displayUsers(List<Object[]> data) {
		tableModel_2.setRowCount(0);	 // delete the old rows	
		table_2.setModel(tableModel_2);
		     
		for(Object[] show: data) {
			 tableModel_2.addRow(show);	
		}		
	}
	
		
	public Object[] getUserData() {
		Object[] data = {textField_id.getText().toString(),
				textField_username.getText().toString(), 
				textField_password.getText().toString(), 
				textField_premium.getText().toString()};
		return data;
	}
	
	
	
}
