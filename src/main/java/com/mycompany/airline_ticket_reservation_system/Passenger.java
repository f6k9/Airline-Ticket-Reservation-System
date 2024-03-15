package com.mycompany.airline_ticket_reservation_system;


/*
* Name : Faris Ahmad Khayat
* ID : 2337100
* Section Number : CS8
*  Assignment number : 1
*/

// class for the passenger's passport number and name
public class Passenger {
    private String passportNumber;
    private String name;
//-----------------------------------------------------------------------------
//default constructor
    public Passenger() {
    }
//custom constructor
    public Passenger(String passportNumber, String name) {
        this.passportNumber = passportNumber;
        this.name = name;
    }
//-----------------------------------------------------------------------------
//getters
    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }
//-----------------------------------------------------------------------------
//setters
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
//-----------------------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }
//-----------------------------------------------------------------------------
  //make changes on an object    
    public void updateDetails(String newName){
        this.name = newName;
    }
//-----------------------------------------------------------------------------
//converts the object to a string
    @Override
    public String toString() {
        return "Passenger{" + "passportNumber=" + passportNumber + ", name=" + name + '}';
    }
//-----------------------------------------------------------------------------
 
}
