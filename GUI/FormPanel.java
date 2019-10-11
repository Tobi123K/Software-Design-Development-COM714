package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	private JLabel nameLabel;
	private JLabel lastnameLabel;
	private JTextField nameField;
	private JTextField lastnameField;
	private JButton okBtn;
	private FormListener formListener;
	private JList hotelList;
	private JComboBox roomCombo;
	private JCheckBox memberCheck;
	private JTextField memberField;
	private JLabel memberLabel;

	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);

		// Constructor
		nameLabel = new JLabel("Name");
		lastnameLabel = new JLabel("Last Name: ");
		nameField = new JTextField(10);
		lastnameField = new JTextField(10);
		hotelList = new JList();
		// Combobox Component
		roomCombo = new JComboBox();
		memberCheck = new JCheckBox();
		memberField = new JTextField(10);
		memberLabel = new JLabel("Member ID: ");
		okBtn = new JButton("OK");

		// Set up mnemonics
		okBtn.setMnemonic(KeyEvent.VK_O);

		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);

		// RadioButton component
		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("femal");
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");

		maleRadio.setSelected(true);
		genderGroup = new ButtonGroup();
		// Set up gender radios
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);

		// Set up member ID
		memberLabel.setEnabled(false);
		memberField.setEnabled(false);

		memberCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicket = memberCheck.isSelected();
				memberLabel.setEnabled(isTicket);
				memberField.setEnabled(isTicket);
			}
		});

		// Set up list box for hotel standards
		DefaultListModel HotelModel = new DefaultListModel();
		HotelModel.addElement(new HotelCategory(0, "30-60 GBP per night"));
		HotelModel.addElement(new HotelCategory(1, "60-120 GBP per night"));
		HotelModel.addElement(new HotelCategory(2, "120-300 GBP per night"));
		HotelModel.addElement(new HotelCategory(3, "300-more GBP per night"));
		hotelList.setModel(HotelModel);

		hotelList.setPreferredSize(new Dimension(160, 75));
		hotelList.setBorder(BorderFactory.createEtchedBorder());
		hotelList.setSelectedIndex(1);

		// Set up combo box for bed room standards
		DefaultComboBoxModel roomModel = new DefaultComboBoxModel();
		roomModel.addElement("signle-bed room");
		roomModel.addElement("double-bed room");
		roomModel.addElement("multi-bed room");
		roomCombo.setModel(roomModel);
		roomCombo.setSelectedIndex(0);
		roomCombo.setEditable(true);

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Inserting values from fields
				String name = nameField.getText();
				String lastname = lastnameField.getText();
				HotelCategory hotelCat = (HotelCategory) hotelList.getSelectedValue();
				String roomCat = (String) roomCombo.getSelectedItem();
				String memberId = memberField.getText();
				boolean goldMember = memberCheck.isSelected();

				String gender = genderGroup.getSelection().getActionCommand();

				// System.out.println(hotelCat.getId());

				FormEvent ev = new FormEvent(this, name, lastname, hotelCat.getId(), roomCat, memberId, goldMember,
						gender);

				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Booking");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponent();

	}

	public void layoutComponent() {
		setLayout(new GridBagLayout());

		// Setting up the Layout
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 1;
		gc.weighty = 0.1;

		// ***************First row*************** //
		gc.gridy = 0;

		gc.gridx = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		// ***************Next row*************** //

		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lastnameLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(lastnameField, gc);

		// ****************Next row*************** //
		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Hotel Standard: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(hotelList, gc);

		// ****************Next row*************** //
		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Room Option: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(roomCombo, gc);

		// ****************Next row*************** //
		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Member"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(memberCheck, gc);
		// ****************Next row*************** //
		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(memberLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(memberField, gc);

		// ****************Next row*************** //
		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Gender"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);

		// ****************Next row*************** //
		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);

		// ***************Next row*************** //
		// Incrementing the row each time
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);

	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	class HotelCategory {
		private int id;
		private String text;

		public HotelCategory(int id, String text) {
			this.id = id;
			this.text = text;
		}

		public String toString() {
			return text;
		}

		public int getId() {
			return id;
		}
	}
}
