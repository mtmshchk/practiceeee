public class Room {

    int roomNumber;
    int maxPeople;
    double pricePerNight;
    boolean isReserved;

    // Конструктор класса Room
    public Room(int roomNumber, int maxPeople, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
        this.pricePerNight = pricePerNight;
        this.isReserved = false; // По умолчанию комната не забронирована
    }

    // Метод для изменения статуса бронирования комнаты
    public void reserve() {
        if (isReserved) {
            throw new RoomAlreadyReservedException("Комната уже забронирована");
        } else {
            this.isReserved = true;
        }
    }

    // Метод для освобождения комнаты
    public void free() {
        if (!isReserved) {
            throw new RoomNotReservedException("Комната не была забронирована");
        } else {
            this.isReserved = false;
        }
    }
}