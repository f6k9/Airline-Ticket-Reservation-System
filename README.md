# ✈️ Airline Ticket Reservation System

A simple Java program that handles flight bookings, passenger records, and ticket purchases through text files. 

## 🌟 What It Does
* **Reads Data:** Automatically reads flight schedules and passenger lists from text files (`flight_passenger.txt`).
* **Books Seats:** Checks a visual seat map to see if a seat is free, assigns it to a passenger, and locks it.
* **Calculates Prices:** Figures out ticket costs automatically (including a 20% discount if flying to Jeddah, and price changes for Business or First Class).
* **Generates Invoices:** Saves full receipts and current flight seat maps directly into a clean output file (`output.txt`).

## 📁 Project Structure
* `Airline_Ticket_Reservation_System.java` — The main manager program that runs the loop.
* `Flight.java` — Holds flight info and creates the row/column seat map grid.
* `Passenger.java` — Holds basic passenger profile info (Name & Passport).
* `Ticket.java` — Combines the flight and passenger details together to calculate the final price.

## 🚀 How to Run It

1. Make sure you have your input files (`inputCommands.txt` and `flight_passenger.txt`) in the same folder as the code.
2. Open your terminal and compile the files:
   ```bash
   javac com/mycompany/airline_ticket_reservation_system/*.java
