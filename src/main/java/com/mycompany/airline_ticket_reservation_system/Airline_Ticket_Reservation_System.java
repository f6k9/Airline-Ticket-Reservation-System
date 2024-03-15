
package com.mycompany.airline_ticket_reservation_system;

/*
* Name : Faris Ahmad Khayat
* ID : 2337100
* Section Number : CS8
*  Assignment number : 1
*/

import java.util.Scanner;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Airline_Ticket_Reservation_System {

    public static void main(String[] args) throws FileNotFoundException {
        
    //Files 
        File inputCommands = new File("inputCommands.txt");
        File flight_passenger = new File("flight_passenger.txt");
        File output = new File("output.txt");
    //scanner to read from files
        Scanner inputReader = new Scanner(inputCommands);
        Scanner flightpassengerReader = new Scanner(flight_passenger);
    //write to file 
        PrintWriter pen = new PrintWriter(output);
         
        final int MAX_TICKET = 1000;
        
        //check if file exists
        if (!flight_passenger.exists()) {
            pen.println("File flight_passenger not available");
            System.exit(0);
        }
        //adding number of flights and passengers
        int numberOfFlights = flightpassengerReader.nextInt(); 
        int numberOfPassengers = flightpassengerReader.nextInt();
        //create all the necessary Arrays
        Flight[] flights = new Flight[numberOfFlights];
        Passenger [] passenger = new Passenger[numberOfPassengers];
        Ticket [] ticket = new Ticket[MAX_TICKET];
        //add flights
        for (int i = 0; i < numberOfFlights ; i++) {
            
            if (flightpassengerReader.next().equalsIgnoreCase("AddFlight")) {
                flights [i] = new Flight(flightpassengerReader.next(),flightpassengerReader.next(),flightpassengerReader.next(),flightpassengerReader.nextInt(),flightpassengerReader.nextInt(),flightpassengerReader.nextInt());
                   pen.println("FLight " + flights[i].getFlightNumber() + " added successfully");    
            }
        }
        //add passengers
        for (int i = 0; i < numberOfPassengers ; i++) {
            
            if (flightpassengerReader.next().equalsIgnoreCase("AddPassenger")) {
                passenger [i] = new Passenger(flightpassengerReader.next(),flightpassengerReader.next());
                   pen.println("Passenger " + passenger[i].getName() + " added successfully"); 
            }
        }
        
        int ticketIndex = 0;
        //loop that checks if there is more to read in file
        while(inputReader.hasNext()){
            
             String command = inputReader.next();
            //command BookTicket
            if (command.equalsIgnoreCase("BookTicket")) {
                String passportnumber = inputReader.next();
                String flightNumber = inputReader.next();
                int row = inputReader.nextInt() -1;
                String column = inputReader.next();
                  
                String classType = inputReader.next();

                          bookTicket(ticket,ticketIndex,passenger,passportnumber,flights,flightNumber,row,column,classType,pen);
                ticketIndex++;
            }   
            //command GenerateInvoice
            else if (command.equalsIgnoreCase("GenerateInvoice")){
                int reservationNumber = inputReader.nextInt();
                    GenerateInvoice(ticket, ticketIndex, reservationNumber,pen);
            }
            //command GenerateIFlightInvoice
            else if (command.equalsIgnoreCase("GenerateIFlightnvoice")){
                double totalInvoicePrice = 0.0;
                String flightNumber = inputReader.next();
                    GenerateFlightInvoice(ticket, ticketIndex,flights,flightNumber,pen);
                    boolean ticketFound = false;
                    for (int i = 0; i < ticketIndex; i++) {
                            if (ticket[i] != null && ticket[i].getFlight().getFlightNumber().equals(flightNumber)) {
                                    pen.println("Ticket Information:");
                            pen.println("Reservation Confirmation Number= " +ticket[i].getReservatoinComfirmationNumber()+", Flight Number="+ticket[i].getFlight().getFlightNumber()+", Passenger Name= "+ticket[i].getPassenger().getName()+", Seat Number= "+ ticket[i].getSeatRow() + ticket[i].getSeatNumber() + " , classType= " + ticket[i].getClassType());
                                totalInvoicePrice += ticket[i].ticketPrice();
                                ticketFound = true;
                            }
                            
                    }
                    if (ticketFound) {
                    pen.println("\nTotal Invoice price =" + totalInvoicePrice);
                }
                    
                    
            }
        
                 
        }
      pen.flush();
      pen.close(); // Closes the (pen) PrintWriter
      inputReader.close(); //close to make resorces available for other activities
      flightpassengerReader.close();//close to make resorces available for other activities
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// generate Flight invoice with Flight number and seat plan  
//passed the print writer for convenience sake and not create a new one
    public static void GenerateFlightInvoice(Ticket [] tickets,int tindex,Flight [] flights,String flightNumber,PrintWriter pen){
        boolean found = false;
        for (int i = 0; i < tindex; i++) {
            if (tickets[i] != null && tickets[i].getFlight().getFlightNumber().equals(flightNumber)) {
                              
                found = true;
                pen.println("\n*********************Generate Flight Invoice**************************\n");
                pen.println("Seat Plane for flight " + flightNumber + " is:");
                pen.println("************************************");
                pen.println(tickets[i].getFlight().printSeatPlan());
                
                break;
            }
        }
            if (!found) {
            
                pen.println("\n*********************Generate Flight  Invoice**************************\n");
                pen.println("Flight Not Found or No Ticket booked for this flight");
            }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
// generate Ticket invoice with all information    
//passed the print writer for convenience sake and not create a new one
    public static void GenerateInvoice(Ticket [] tickets,int tindex,int Res,PrintWriter pen){
        boolean found = false;
        for (int i = 0; i < tindex; i++) {
            if (tickets[i] != null && tickets[i].getReservatoinComfirmationNumber() == Res) {
                              
                found = true;
                pen.println("\n*********************Generate Ticket Invoice**************************\n");
                pen.println("Ticket Information:");
                pen.println("Reservation Confirmation Number= " +tickets[i].getReservatoinComfirmationNumber()+", Flight Number="+tickets[i].getFlight().getFlightNumber()+", Passenger Name= "+tickets[i].getPassenger().getName()+", Seat Number= "+ tickets[i].getSeatRow() + tickets[i].getSeatNumber() + " , classType= " + tickets[i].getClassType());
                pen.println("Total ticket price = " + tickets[i].ticketPrice());
                break;
            }
            }
            if (!found) {
            
                pen.println("\n*********************Generate Ticket  Invoice**************************\n");
                pen.println("Reservation Number is not available");
            }
        
        
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
    public static int SearchTicket(Ticket [] tickets,int tindex,int Res){
        for (int i = 0; i < tindex; i++) {
         if (tickets[i] != null && tickets[i].getReservatoinComfirmationNumber() == Res) {
            return i; // Ticket found
        }
    }
    return -1; // Ticket not found
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//method for booking ticket 
//passed the print writer for convenience sake and not create a new one
//could be changed to void 
    public static boolean bookTicket(Ticket [] ticket,int ticketIndex,Passenger [] passenger,String passportnumber,Flight [] flights,String flightNumber,int row,String column,String classType,PrintWriter pen){
        int PassengerIndex = SearchPassenger(passenger, passportnumber);
        int FlightIndex = SearchFlights(flights, flightNumber);
        boolean booked;
        pen.println("\n*********************BookTicket**************************\n" );
        if (PassengerIndex >= 0) {
                    if (FlightIndex >= 0) {
                        if(flights[FlightIndex].isSeatAvailable(row,column.charAt(0))){
                           ticket[ticketIndex] = new Ticket(flights[FlightIndex],passenger[PassengerIndex],row,String.valueOf(column),classType);
                           flights[FlightIndex].bookSeat(row, column.charAt(0), passenger[PassengerIndex].getPassportNumber());
                           StringBuilder str = new StringBuilder("Seat booked successfully.\nTicket Information: \nReservation Confirmation Number= " + ticket[ticketIndex].getReservatoinComfirmationNumber() + ", Flight Number=" + flightNumber + ", Passenger Name =" + passenger[PassengerIndex].getName() + ", Seat Number= " + ((int)row+1) + column + " , classType= " + classType);
                            pen.println(str);
                            booked = true;
                        }else{
                            pen.println("Seat " + ((int)row +1) + column + " is already Reserved Or Not found");
                            booked = false;
                        }
                    }else{
                        pen.println("Flight "+ flightNumber + " Not Found" );
                        booked = false;
                    }
                }else{
                    pen.println("Passenger with Passport number " + passportnumber + " is not Registered");
                    booked = false;
                }
        return booked;
        
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//searches fo specific passenger in the array
    public static int SearchPassenger(Passenger [] passenger,String passPort){
        for (int i = 0; i < passenger.length; i++) {
         if (passenger[i] != null && passenger[i].getPassportNumber().equals(passPort)) {
            return i; // Passenger found
         }
        }
      return -1; // Passenger not found
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//searches fo specific flight in the array
    public static int SearchFlights(Flight [] flight,String flightNumber){
        for (int i = 0; i < flight.length; i++) {
            if (flight[i] != null && flight[i].getFlightNumber().equals(flightNumber)) {
                // Flight found
                return i; 
            }
        }
      // Flight not found
      return -1;
    }
        
}

    

