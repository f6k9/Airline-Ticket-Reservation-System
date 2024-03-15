package com.mycompany.airline_ticket_reservation_system;


/*
* Name : Faris Ahmad Khayat
* ID : 2337100
* Section Number : CS8
*  Assignment number : 1
*/

//class for flights,passengers,seat row and column, classType,comfirmation and reservation numbers
public class Ticket {
    private Flight flight = new Flight();
    private Passenger passenger = new Passenger();
    private int seatRow;
    private String seatNumber;
    private String classType;
    private int reservatoinComfirmationNumber = 100;
    private static int reservationNumber = 0;
//-----------------------------------------------------------------------------
//default constructor
    public Ticket() {
    }
//custom constructor
    public Ticket(Flight flight, Passenger passenger, int seatRow, String seatNumber, String classType) {
        this.flight = flight;
        this.passenger = passenger;
        this.seatRow = seatRow+1;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.reservatoinComfirmationNumber += reservationNumber++;
    }
//-----------------------------------------------------------------------------
//getters
    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public int getReservatoinComfirmationNumber() {
        return reservatoinComfirmationNumber;
    }
//-----------------------------------------------------------------------------    
//setters
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
//-----------------------------------------------------------------------------    
//calculates the ticket price for the flight
    public double ticketPrice(){
        double basePrice = 2000;
        double ticket = basePrice;
        
        if (flight.getArrivalCity().equalsIgnoreCase("JED")) {
        ticket *= 0.8;  // 20% discount for flights departing from JED
        }
    
    
        if (classType.equalsIgnoreCase("BusinessClass")) {
        ticket *= 3 ;
        } else if (classType.equalsIgnoreCase("FirstClass")) {
            ticket *= 5;
        }
    
        
        // Apply VTAX
        ticket *= 1.15;
        
        ticket = Math.round(ticket * 100.0) / 100.0;
        
        return ticket;
    
    
    }
//-----------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Ticket{" + "flight=" + flight + ", passenger=" + passenger + ", seatRow=" + seatRow + ", seatNumber=" + seatNumber + ", classType=" + classType + ", reservatoinComfirmationNumber=" + reservatoinComfirmationNumber + '}';
    }
    
    
    
}
