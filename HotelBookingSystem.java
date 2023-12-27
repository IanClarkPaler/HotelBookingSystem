package Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;





public class HotelBookingSystem {
	// this is the lists where we store rooms and bookings.
    static List<Room> rooms;
    static List<Booking> bookings;
    
    public static void main(String[] args) {
    	//initialize rooms and bookings.
        initializeRooms();
        bookings = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
        	//displays main menu and this handles the users input(their choice)
            displayMenu();
            //error handling just in case they put letters 
            try {
                int choice = getValidChoice(sc);
            

            switch (choice) {
                case 1:
                    showAvailableRooms(sc);
                    break;
                case 2:
                    bookARoom(sc);
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Exiting Hotel Booking System. Thank you!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            } catch (Exception e) {
                sc.nextLine(); // prompt user again if they put invalid input
                System.out.println("Invalid choice. Please enter a valid number.");
                continue;
            } 
        }
    }
    //method that initialize the rooms
    public static void initializeRooms() {
        rooms = new ArrayList<>();

        // Standard Rooms
        rooms.add(new Room(1, "Standard", 150.0));
        rooms.add(new Room(2, "Standard", 150.0));
        rooms.add(new Room(3, "Standard", 150.0));

        // Twin Rooms
        rooms.add(new Room(4, "Twin", 1150.0));
        rooms.add(new Room(5, "Twin", 1150.0));
        rooms.add(new Room(6, "Twin", 1150.0));

        // Deluxe Rooms
        rooms.add(new Room(7, "Deluxe", 2000.0));
        rooms.add(new Room(8, "Deluxe", 2000.0));
        rooms.add(new Room(9, "Deluxe", 2000.0));
    }
    //this is the method that displays the main menu
    private static void displayMenu() {
        System.out.println("\n       Hotel Booking System Menu:");
        System.out.println("     ______________________________");
        System.out.println("     |                            |");
        System.out.println("     | 1. Show Available Rooms    |");
        System.out.println("     | 2. Book a Room             |");
        System.out.println("     | 3. View Bookings           |");
        System.out.println("     | 4. Exit                    |");
        System.out.println("     |____________________________|");
        System.out.print("\n          Enter your choice: \n");
        
    }
   // The getValidChoice method is added to ensure the user enters a valid numeric choice.
    private static int getValidChoice(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next(); 
        }
        return sc.nextInt();
    }

  
    

    //display the available rooms ..based on what the user choose(standard,twin,or deluxe)
    private static void showAvailableRooms(Scanner sc) {
    	boolean validChoice = false;

        while (!validChoice) {
            

        System.out.println(                    "\n    ________________________________________");
        System.out.println(                    "    |===    Please choose room type     ===|");
        System.out.println(                   "    |______________________________________|_________________________");
        System.out.println(                  "   /"+"|  _               _       |          |             |           |");
        System.out.println(                  "  /"+" | |_| ROOM TYPE   |_|      |  Bed █   |  Person ▄   |   PRICE   |");
        System.out.println(                   " /"+"  |__________________________|__________|_____________|___________|");
        System.out.println(                   "|   |                          |          |             |           |");
        System.out.println(                   "|   | 1: Standard Room         |     █    |    ▄ ▄      |  $150     |");
        System.out.println(                   "|   |                    _     |          |             |           |");
        System.out.println(                   "|   | 2: Twin Room      |_|    |    █ █   |    ▄ ▄      |  $1,550   |");
        System.out.println(                   "|   |                          |          |             |           |");
        System.out.println(                   "|   | 3: Deluxe Room           |   █ █ █  |   ▄ ▄ ▄     |  $2,0000  |");
        System.out.println(                   "|___|__________________________|__________|_____________|___________|");
        System.out.print("\n          Enter your choice: \n");
            int choice1 = sc.nextInt();
            switch (choice1) {
                case 1:
                    if (areAllRoomsOccupied(rooms, "Standard")) {
                        System.out.println("All Standard Rooms are occupied. Please choose a different room type.");
                    } else {
                        displayAvailableRooms("Standard Rooms", "Standard");
                        validChoice = true;
                    }
                    break;
                case 2:
                    displayAvailableRooms("Twin Rooms", "Twin");
                    validChoice = true;
                    break;
                case 3:
                    displayAvailableRooms("Deluxe Rooms", "Deluxe");
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid room type choice. Please try again.");
            }
        }
    }
        
    //this method is use to check if all rooms in a specific category is all occupied
    private static boolean areAllRoomsOccupied(List<Room> roomList, String roomCategory) {
        for (Room room : roomList) {
            if (room.getCategory().equalsIgnoreCase(roomCategory) && !room.isOccupied) {
                return false;
            }
        }
        return true;
    }
    //this method where we implement the logic that should display the available rooms
    private static void displayAvailableRooms(String roomType, String roomCategory) {
        System.out.println("\n               |=== " + roomType + " ===|");

        boolean availableRoomsFound = true;

        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(roomCategory) && !room.isOccupied) {
                System.out.println("         __________________________________");
                System.out.println("        | Room " + room.getRoomNumber() +
                                      "  Price: $" + room.getPrice() + " per night |");
                System.out.println("        |__________________________________|");
                availableRoomsFound = true;
            } else if (room.getCategory().equalsIgnoreCase(roomCategory) && room.isOccupied) {
                System.out.println("Room " + room.getRoomNumber() + " is occupied!");
            }
        }

        if (!availableRoomsFound) {
            System.out.println("No available rooms in this category.");
        }
    }

    //book a room method
 // The getValidRoomNumber method is added to ensure the user enters a valid room number.
    private static int getValidRoomNumber(Scanner scanner, String roomCategory) {
        int minRoomNumber;
        int maxRoomNumber;
        
        switch (roomCategory.toLowerCase()) {
            case "standard":
                minRoomNumber = 1;
                maxRoomNumber = 3;
                break;
            case "twin":
                minRoomNumber = 4;
                maxRoomNumber = 6;
                break;
            case "deluxe":
                minRoomNumber = 7;
                maxRoomNumber = 9;
                break;
            default:
                throw new IllegalArgumentException("Invalid room category.");
        }

        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid room number.");
                scanner.next(); // consume the invalid input
            }

            int roomNumber = scanner.nextInt();
            if (roomNumber >= minRoomNumber && roomNumber <= maxRoomNumber) {
                return roomNumber;
            } else {
                System.out.println("Invalid room number for " + roomCategory + " rooms. Please enter a valid room number.");
            }
        }
    }

    // ...

    // Book a room method
    private static void bookARoom(Scanner scanner) {
        

        System.out.print("\nEnter your name: ");
        String guestName = scanner.next();

        try {
        	 System.out.println(                    "\n    ________________________________________");
             System.out.println(                    "    |===    Please choose room type     ===|");
             System.out.println(                   "    |______________________________________|_________________________");
             System.out.println(                  "   /"+"|  _               _       |          |             |           |");
             System.out.println(                  "  /"+" | |_| ROOM TYPE   |_|      |  Bed █   |  Person ▄   |   PRICE   |");
             System.out.println(                   " /"+"  |__________________________|__________|_____________|___________|");
             System.out.println(                   "|   |                          |          |             |           |");
             System.out.println(                   "|   | 1: Standard Room         |     █    |    ▄ ▄      |  $150     |");
             System.out.println(                   "|   |                    _     |          |             |           |");
             System.out.println(                   "|   | 2: Twin Room      |_|    |    █ █   |    ▄ ▄      |  $1,550   |");
             System.out.println(                   "|   |                          |          |             |           |");
             System.out.println(                   "|   | 3: Deluxe Room           |   █ █ █  |   ▄ ▄ ▄     |  $2,0000  |");
             System.out.println(                   "|___|__________________________|__________|_____________|___________|");
             System.out.print("\n          Enter your choice: \n");
             int roomTypeChoice = getValidChoice(scanner);

             String roomCategory;
             switch (roomTypeChoice) {
                 case 1:
                     roomCategory = "Standard";
                     break;
                 case 2:
                     roomCategory = "Twin";
                     break;
                 case 3:
                     roomCategory = "Deluxe";
                     break;
                 default:
                     System.out.println("Invalid room type choice. Please try again.");
                     return; // Exit the method if the choice is invalid.
             }

            System.out.print("\nEnter the room number you want to book: ");
            int roomNumber = getValidRoomNumber(scanner, roomCategory);

            Room selectedRoom = getRoomByNumber(roomNumber);

            if (selectedRoom != null) {
                String checkInDate;
                String checkOutDate;

                do {
                    System.out.print("\nEnter check-in date (YYYY-MM-DD): ");
                    checkInDate = scanner.next();

                    System.out.print("\nEnter check-out date (YYYY-MM-DD): ");
                    checkOutDate = scanner.next();

                    if (!isValidDate(checkInDate) || !isValidDate(checkOutDate) || checkOutDate.compareTo(checkInDate) <= 0) {
                        System.out.println("Invalid dates. Please ensure check-out date is later than check-in date.");
                    } else if (selectedRoom.isOccupied && checkInDate.compareTo(selectedRoom.occupiedUntil) <= 0) {
                        System.out.println("Room is already booked for the specified dates. Please choose advance dates.");
                    }
                } while (!isValidDate(checkInDate) || !isValidDate(checkOutDate) || checkOutDate.compareTo(checkInDate) <= 0 || (selectedRoom.isOccupied && checkInDate.compareTo(selectedRoom.occupiedUntil) <= 0));

                double totalCost = calculateTotalCost(selectedRoom, checkInDate, checkOutDate);

                selectedRoom.isOccupied = true;
                selectedRoom.occupiedUntil = checkOutDate;

                bookings.add(new Booking(guestName, roomNumber, checkInDate, checkOutDate, totalCost, roomCategory));
                System.out.println("Room booked successfully! Total cost: $" + totalCost);
            } else {
                System.out.println("Invalid room number. Please enter a valid room number.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            scanner.nextLine(); // consume the invalid input
        }
    }
    // The isValidDate method is added to check if a given string is a valid date in the specified format(only works for current year).
    private static boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date parsedDate = sdf.parse(date);

            // Check if the year is the current year or a future year
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);

            calendar.setTime(parsedDate);
            int inputYear = calendar.get(Calendar.YEAR);

            if (inputYear <= currentYear) {
                return true;
            } else {
                System.out.println("Invalid year. Please enter the current year or a future year.");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date. Please enter a valid date.");
            return false;
        }
    }


    //to check the total cost of the guest
    private static double calculateTotalCost(Room room, String checkInDate, String checkOutDate) {
        int numberOfNights = calculateNumberOfNights(checkInDate, checkOutDate);
        return numberOfNights * room.price;
    }

    public static int calculateNumberOfNights(String checkInDate, String checkOutDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(checkInDate);
            Date endDate = sdf.parse(checkOutDate);
            long diffInMilliseconds = Math.abs(endDate.getTime() - startDate.getTime());
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
            return (int) diffInDays;

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter dates in the format YYYY-MM-DD.");
            return 0;
        }
    }

    //view bookings method
    private static void viewBookings() {
        System.out.println("\n             All Bookings:");
        for (Booking booking : bookings) {
            System.out.println("\n| Guest Name: " + booking.guestName +
                    "\n| Room Type: " + booking.getRoomType() +
                    "\n| Room Number: " + booking.roomNumber +
                    "\n| Check-in: " + booking.checkInDate +
                    "\n| Check-out: " + booking.checkOutDate +
                    "\n| Total Cost: $" + booking.totalCost );
        }
    }

    private static Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                return room;
            }
        }
        return null;
    }
    

    

}