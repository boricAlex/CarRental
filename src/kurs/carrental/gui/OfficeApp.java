package kurs.carrental.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OfficeApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficeApp window = new OfficeApp();
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
	public OfficeApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnBookings = new JMenu("Bookings");
		menuBar.add(mnBookings);
		
		JMenuItem mntmSearch = new JMenuItem("Search...");
		mntmSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnBookings.add(mntmSearch);
		
		JMenuItem mntmAdd = new JMenuItem("Add...");
		mnBookings.add(mntmAdd);
		
		JMenu mnVehicles = new JMenu("Vehicles");
		menuBar.add(mnVehicles);
		
		JMenuItem mntmAdd_1 = new JMenuItem("Add...");
		mnVehicles.add(mntmAdd_1);
		
		JMenuItem mntmSearch_1 = new JMenuItem("Search...");
		mntmSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchVehicles dialog = new SearchVehicles();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.init();
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		mnVehicles.add(mntmSearch_1);
	}

}
