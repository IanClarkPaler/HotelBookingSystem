package Main;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isOccupied;
    String occupiedUntil;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isOccupied = false;
        this.occupiedUntil = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}