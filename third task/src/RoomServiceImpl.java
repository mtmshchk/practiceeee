class RoomServiceImpl implements RoomService<Room> {

    @Override
    public void clean(Room room) {
        System.out.println("Комната " + room.roomNumber + " очищена.");
    }

    @Override
    public void reserve(Room room) {
        room.reserve();
        System.out.println("Комната " + room.roomNumber + " забронирована.");
    }

    @Override
    public void free(Room room) {
        room.free();
        System.out.println("Комната " + room.roomNumber + " освобождена.");
    }
}