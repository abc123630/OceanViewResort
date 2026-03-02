package com.ocean.model;

public class Reservation {

    private int id;
    private String guestName;
    private String address;
    private String contactNo;
    private int roomId;
    private String checkInDate;
    private String checkOutDate;

    // Room Details (for billing)
    private String roomNumber;
    private String roomType;
    private double ratePerNight;

    // Constructor
    public Reservation(int id, String guestName, String address,
                       String contactNo, int roomId,
                       String checkInDate, String checkOutDate) {

        this.id = id;
        this.guestName = guestName;
        this.address = address;
        this.contactNo = contactNo;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public int getId() { return id; }
    public String getGuestName() { return guestName; }
    public String getAddress() { return address; }
    public String getContactNo() { return contactNo; }
    public int getRoomId() { return roomId; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }

    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getRatePerNight() { return ratePerNight; }

    // Setters for Room Info
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRatePerNight(double ratePerNight) {
        this.ratePerNight = ratePerNight;
    }
}