package com.mycompany.airline_ticket_reservation_system;


/*
* Name : Faris Ahmad Khayat
* ID : 2337100
* Section Number : CS8
*  Assignment number : 1
*/

// class for the flight number, departure, arrival, gate number and seat map
public class Flight {
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private int gateNumber;
    private int row;
    private int column;
    private String [][] seatMap;
    
//-----------------------------------------------------------------------------
//default constructor
    public Flight() {
    }
//custom constructor
    public Flight(String flightNumber, String departureCity, String arrivalCity, int gateNumber, int row, int column) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.gateNumber = gateNumber;
        this.row = row;
        this.column = column;
        this.seatMap = new String[row][column];
    }
//-----------------------------------------------------------------------------
//getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public String[][] getSeatMap() {
        return seatMap;
    }
//-----------------------------------------------------------------------------    
//setters
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }
//-----------------------------------------------------------------------------    
//changes the array value at row and column to the passenger ID if seat is available
    public boolean bookSeat(int row,char column,String passengerID) {
        if (isSeatAvailable(row,column)) {
            this.seatMap[row][((int)column)-'A'] = passengerID;
            return true;
        }
        else 
            return false;
        
    }
//-----------------------------------------------------------------------------  
//checks if array slot is empty or not
    public boolean isSeatAvailable(int row,char column) {
        if (row >= 0 && row < seatMap.length && (column - 'A') >= 0 && (column - 'A') < seatMap[0].length) {
            return seatMap[row][column - 'A'] == null;
        } else {
         return false;
        }
    }
//-----------------------------------------------------------------------------     
// prints a seat map
    public String printSeatPlan(){
         
        StringBuilder seatPlan = new StringBuilder();
        seatPlan.append("Row           a            b            c            d            e            f\n");
        for (int i = 0; i < row; i++) {
        seatPlan.append(String.format("%-13s", i));
            for (int j = 0; j < column; j++) {
                if (seatMap[i][j] == null) {
                 seatPlan.append(String.format("%-13s", "O"));
                }   else {
                seatPlan.append(String.format("%-13s", seatMap[i][j]));
                    }
            }

            seatPlan.append("\n");
        }
       
        return seatPlan.toString();
    }    
//-----------------------------------------------------------------------------      
    @Override
    public String toString() {
        return "Flight{" + "flightNumber=" + flightNumber + ", departureCity=" + departureCity + ", arrivalCity=" + arrivalCity + ", gateNumber=" + gateNumber + ", seatMap=" + seatMap + ", row=" + row + ", column=" + column + '}';
    }
    
    
}
