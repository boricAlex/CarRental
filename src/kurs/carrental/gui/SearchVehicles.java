package kurs.carrental.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kurs.carrental.beans.NameBean;
import kurs.carrental.beans.Vehicle;
import kurs.carrental.services.VehicleService;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchVehicles extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<NameBean> cbOffice;
	private JComboBox<NameBean> cbCategory;
	private JComboBox<NameBean> cbStatus;
	private JTextArea textArea;

	private VehicleService srv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchVehicles dialog = new SearchVehicles();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.init();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws SQLException {
		srv = new VehicleService("jdbc:mysql://localhost/carrental", "dev", "mysql");
		srv.createConnection(true);

		List<NameBean> categories = srv.getNamedItems("VehicleCategory", "CategoryId", "Name");
		NameBean allCats = new NameBean();
		allCats.setName("All categories");
		allCats.setPk(0);
		categories.add(0, allCats);

		List<NameBean> statuses = srv.getNamedItems("VehicleStatus", "StatusId", "Status");
		NameBean allStatuses = new NameBean();
		allStatuses.setName("All statuses");
		allStatuses.setPk(0);
		statuses.add(0, allStatuses);

		List<NameBean> offices = srv.getNamedItems("Office", "OfficeId", "Name");
		NameBean allOffices = new NameBean();
		allOffices.setName("All offices");
		allOffices.setPk(0);
		offices.add(0, allOffices);

		cbOffice.setModel(new DefaultComboBoxModel<>(offices.toArray(new NameBean[0])));
		cbCategory.setModel(new DefaultComboBoxModel<>(categories.toArray(new NameBean[0])));
		cbStatus.setModel(new DefaultComboBoxModel<>(statuses.toArray(new NameBean[0])));
	}

	/**
	 * Create the dialog.
	 */
	public SearchVehicles() {
		setBounds(100, 100, 672, 472);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		cbOffice = new JComboBox<NameBean>();
		cbOffice.setBounds(12, 27, 155, 22);
		contentPanel.add(cbOffice);

		cbCategory = new JComboBox<NameBean>();
		cbCategory.setBounds(188, 27, 155, 22);
		contentPanel.add(cbCategory);

		cbStatus = new JComboBox<NameBean>();
		cbStatus.setBounds(366, 27, 155, 22);
		contentPanel.add(cbStatus);

		textArea = new JTextArea();
		textArea.setBounds(12, 111, 594, 255);
		contentPanel.add(textArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						textArea.setText(null);
						int officeId = ((NameBean) cbOffice.getSelectedItem()).getPk();
						int statusId = ((NameBean) cbStatus.getSelectedItem()).getPk();
						int categoryId = ((NameBean) cbCategory.getSelectedItem()).getPk();
						System.out
								.println("Office: " + officeId + ", Status: " + statusId + ", Category: " + categoryId);
						try {
							List<Vehicle> vehs = srv.searchVehicles(officeId == 0 ? null : officeId,
									statusId == 0 ? null : statusId, categoryId == 0 ? null : categoryId);
							for (Vehicle v : vehs) {
								textArea.append(v + "\n");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
