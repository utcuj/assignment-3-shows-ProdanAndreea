package net.codejava.hibernate.EventVisualization.client;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import net.codejava.hibernate.EventVisualization.server.ClientServer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClientView {

	private JFrame frame;

	
	private ClientController client;
	
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private String col_table[]= {"Name", "Type"};
	private JScrollPane scrollPane_2;
	private DefaultTableModel tableModel_2;
	private String col_table_2[]= {"Description", "Release Date", "imdb"};
	private JScrollPane scrollPane_3;
	private DefaultTableModel tableModel_3;
	private String col_table_3[]= {"Name"};
	
	private JTable table_1;
	private JTextField textField_search;
	private JTable table_2;
	private JTable table_3;
	private JTextField textField_add;
	private JTextField textField_rate;

	
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { System.out.println("Client View run");
					ClientView window = new ClientView();
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
	public ClientView() {  System.out.println("View");
		client = new ClientController(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// add shutdown hook
		
		frame = new JFrame();
		frame.setBounds(100, 100, 754, 486);
		
		// used to firstly close the server before closing the window
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	
		        	client.closeServer();     	
		            System.exit(0);
		        }
		    }
		});
		
		frame.getContentPane().setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(25, 31, 223, 169);
		frame.getContentPane().add(table_1);
		scrollPane = new JScrollPane(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				client.RowDataSelected();
			}
		});
		scrollPane.setBounds(10, 38, 388, 161);
		frame.getContentPane().add(scrollPane);
		
		JButton btn_display_all = new JButton("Display All");
		btn_display_all.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.btnDisplayAllClicked();
			}
		});
		btn_display_all.setBounds(79, 9, 103, 23);
		frame.getContentPane().add(btn_display_all);
		
		JLabel lblShows = new JLabel("Shows");
		lblShows.setBounds(10, 13, 46, 14);
		frame.getContentPane().add(lblShows);
		
		textField_search = new JTextField();
		textField_search.setBounds(20, 210, 111, 20);
		frame.getContentPane().add(textField_search);
		textField_search.setColumns(10);
		
		JButton btn_search = new JButton("Search");
		btn_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.btnSearchClicked();
			}
		});
		btn_search.setBounds(152, 209, 89, 23);
		frame.getContentPane().add(btn_search);
		
		table_2 = new JTable();
		table_2.setBounds(10, 265, 388, 96);
		frame.getContentPane().add(table_2);
		scrollPane_2 = new JScrollPane(table_2);
		scrollPane_2.setBounds(10, 265, 388, 96);
		frame.getContentPane().add(scrollPane_2);
		
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setBounds(10, 240, 46, 14);
		frame.getContentPane().add(lblDetails);
		
		table_3 = new JTable();
		table_3.setBounds(408, 39, 320, 161);
		frame.getContentPane().add(table_3);
		scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(408, 39, 320, 161);
		frame.getContentPane().add(scrollPane_3);
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(408, 13, 46, 14);
		frame.getContentPane().add(lblHistory);
		
		JButton btnAddToViewed = new JButton("Add to Viewed");
		btnAddToViewed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.btnAddToViewedClicked();
			}
		});
		btnAddToViewed.setBounds(10, 414, 121, 23);
		frame.getContentPane().add(btnAddToViewed);
		
		textField_add = new JTextField();
		textField_add.setBounds(155, 415, 86, 20);
		frame.getContentPane().add(textField_add);
		textField_add.setColumns(10);
		
		textField_rate = new JTextField();
		textField_rate.setBounds(155, 382, 86, 20);
		frame.getContentPane().add(textField_rate);
		textField_rate.setColumns(10);
		
		JButton btn_rate = new JButton("Rate");
		btn_rate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.btnRateClicked();
			}
		});
		btn_rate.setBounds(10, 380, 89, 23);
		frame.getContentPane().add(btn_rate);
		tableModel = new DefaultTableModel(col_table, 0);
		tableModel_2 = new DefaultTableModel(col_table_2, 0);
		tableModel_3 = new DefaultTableModel(col_table_3, 0);
		
		
		
	}
	
	
	public void displayShows(List<Object[]> data) {
		
		tableModel.setRowCount(0);	 // delete the old rows	
		table_1.setModel(tableModel);
		
		System.out.println("View: ");
        for(Object[] ob : data) {
	    	 System.out.println(ob[0] + " " + ob[1]);
        }
        
		for(Object[] show: data) {
			 tableModel.addRow(show);	 
		}
		
	}
	
	
	public String getSearchData() {
		return textField_search.getText();
	}
	
	
	public void displayDetails(List<Object[]> data) {
		tableModel_2.setRowCount(0);	 // delete the old rows	
		table_2.setModel(tableModel_2);
		
		System.out.println("View 2: ");
        for(Object[] ob : data) {
	    	 System.out.println(ob[0] + " " + ob[1] + " " + ob[2]);
        }
        
		for(Object[] show: data) {
			 tableModel_2.addRow(show);	 
		}
	}
	
	public String getRowData() {
		// get the model from the jtable
	    DefaultTableModel model = (DefaultTableModel)table_1.getModel();

	    // get the selected row index
	    int selectedRowIndex = table_1.getSelectedRow();
	       
	    // set the selected row data into jtextfields
	    String name = model.getValueAt(selectedRowIndex, 0).toString();

	    textField_add.setText(name);
	   
	    return name;
	}	
	
	public String getAddTextField() {
		System.out.println("!!!!!!" +  textField_add.getText().toString());
		return textField_add.getText().toString(); 
	}
	
	
	public void displayHistory(List<Object[]> data) {
		tableModel_3.setRowCount(0);	 // delete the old rows	
		table_3.setModel(tableModel_3);
		
		System.out.println("View 3: ");
        for(Object[] ob : data) {
	    	 System.out.println(ob[0]);
        }
        
		for(Object[] show: data) {
			 tableModel_3.addRow(show);	 
		}
	}
	
	public String getRateTextField() {		
		return textField_rate.getText().toString();		
	}
	
	
	
	
	
}
