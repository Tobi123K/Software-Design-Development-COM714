package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gui.FormEvent;
import model.HotelCategory;
import model.Database;
import model.RoomCategory;
import model.Gender;
import model.Person;

public class Controller {
	
	Database db = new Database ();
	
	public List<Person> getPeople(){
		return db.getPeople();
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String lastname = ev.getlastname();
		int hotelCat = ev.getHotelCategory();
		String roomCat = ev.getRoomCat();
		boolean isGold = ev.isGoldMember();
		String memberId = ev.getMemberId();
		String gender = ev.getGender();
		
		HotelCategory hotelCategory = null;
		
		switch(hotelCat) {
		
		case 0:
			hotelCategory = HotelCategory.oneStar;
			break;
		case 1:
			hotelCategory = HotelCategory.twoStar;
			break;
		case 2:
			hotelCategory = HotelCategory.threeStar;
			break;
		case 3:
			hotelCategory = HotelCategory.fourStar;
			break;
		
		}
		
		RoomCategory roomCategory;
		
		if(roomCat.equals("signle-bed room")) {
			roomCategory = RoomCategory.signleRoom;
		}
		else if(roomCat.equals("double-bed room")) {
			roomCategory = RoomCategory.doubleRoom;
		}
		else if(roomCat.equals("multi-bed room")) {
			roomCategory = RoomCategory.multiRoom;
		}
		else {
			roomCategory = RoomCategory.other;
			//System.err.println(roomCat);
		}
		
		Gender genderCat;
		
		if(gender.equals("male")) {
			genderCat = Gender.male;
		}
		else {
			genderCat = Gender.female;
		}
		
		Person person = new Person(name, lastname, hotelCategory, roomCategory, memberId, isGold ,genderCat);
		
		db.addPerson(person);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}
