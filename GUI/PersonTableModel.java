package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel {

	private List<Person> db;

	private String[] colNames = { "ID", "Name", "Last Name", "Hotel Category", "Room Category", "Gender",
			"Member ID" };

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	public PersonTableModel() {
		this.db = db;
	}

	public void setData(List<Person> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);

		switch (col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getLastname();
		case 3:
			return person.getHotelCategory();
		case 4:
			return person.getRoomCat();
		case 5:
			return person.getGender();
		case 6:
			return person.getMemberId();
		}

		return null;
	}

}
