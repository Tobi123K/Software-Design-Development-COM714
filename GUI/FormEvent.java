package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

	private String name;
	private String lastname;
	private int hotelCategory;
	private String roomCat;
	private String memberId;
	private boolean goldMember;
	private String gender;

	public FormEvent(Object source) {
		super(source);

	}

	public FormEvent(Object source, String name, String lastname, int hotelCategory, String roomCat, String memberId,
			boolean goldMember, String gender) {
		super(source);

		this.name = name;
		this.lastname = lastname;
		this.hotelCategory = hotelCategory;
		this.roomCat = roomCat;
		this.memberId = memberId;
		this.goldMember = goldMember;
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public String getMemberId() {
		return memberId;
	}

	public boolean isGoldMember() {
		return goldMember;
	}

	public void setHotelCategory(int hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getlastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getHotelCategory() {
		return hotelCategory;
	}

	public String getRoomCat() {
		return roomCat;
	}
}
