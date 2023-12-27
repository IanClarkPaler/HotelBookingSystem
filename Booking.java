package Main;

class Booking {
	
    String guestName;
    int roomNumber;
    String checkInDate;
    String checkOutDate;
    double totalCost;
    String roomType;
   
    
    

    public Booking(String guestName, int roomNumber, String checkInDate, String checkOutDate, double totalCost, String roomType) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.roomType = roomType;
        
        
    }
    public String getRoomType() {
        return roomType;
    }
    
    
}
