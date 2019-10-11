package model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 7939932092226769237L;

	private static int count = 0;

	private int id;
	private String name;
	private String lastname;
	private HotelCategory hotelCategory;
	private RoomCategory roomCat;
	private String memberId;
	private boolean goldMember;
	private Gender gender;

	public Person(String name, String lastname, HotelCategory hotelCategory, RoomCategory roomCat, String memberId,
			boolean goldMember, Gender gender) {
		this.name = name;
		this.lastname = lastname;
		this.hotelCategory = hotelCategory;
		this.roomCat = roomCat;
		this.memberId = memberId;
		this.goldMember = goldMember;
		this.gender = gender;

		this.id = count;
		count++;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public HotelCategory getHotelCategory() {
		return hotelCategory;
	}

	public void setHotelCategory(HotelCategory hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public RoomCategory getRoomCat() {
		return roomCat;
	}

	public void setRoomCat(RoomCategory roomCat) {
		this.roomCat = roomCat;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public boolean isGoldMember() {
		return goldMember;
	}

	public void setGoldMember(boolean goldMember) {
		this.goldMember = goldMember;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
