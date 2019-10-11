package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import controller.Controller;

//Controller contain whole logic
public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;

	// Constructor
	public MainFrame() {
		super("Hotel Booking System");

		setLayout(new BorderLayout());

		textPanel = new TextPanel();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();

		controller = new Controller();

		tablePanel.setData(controller.getPeople());

		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);

			}
		});

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());

		setJMenuBar(createMenuBar());

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {

				controller.addPerson(e);
				tablePanel.referesh();
				/*
				 * String name = e.getName(); String lastname = e.getLastname(); int ageCat =
				 * e.getAgeCategory(); String roomCat = e.getRoomloymentCategory();
				 * 
				 * textPanel.appendText(name + ": " + lastname+ ":" + ageCat + ", " + roomCat +
				 * "\n");
				 * 
				 * System.out.println(e.getGender());
				 */

			}
		});
		// Adding to the MainFrame
		add(formPanel, BorderLayout.WEST);
		add(tablePanel, BorderLayout.CENTER);

		setMinimumSize(new Dimension(400, 300));
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Hotel Customer Form");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		showFormItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				formPanel.setVisible(menuItem.isSelected());

			}

		});
		// VK stands for Virtual Key
		// Setting up mnemonic
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		// Mnemonic is for one key Accelerator is for two keys ctrl + x
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		// Method for data importing
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.referesh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Loading Failed", "Loading Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}

		});

		// Method for data exporting
		exportDataItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());

					} catch (IOException e1) {

						JOptionPane.showMessageDialog(MainFrame.this, "Saving Failed", "Saving Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}

		});

		// Message Dialog JOptionPane
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * String text = JOptionPane.showInputDialog(MainFrame.this,
				 * "Enter your user name.", "Enter User Name.",
				 * JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE);
				 */

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit, the Hotel Booking System application?", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}

			}

		});

		return menuBar;
	}
}
