class LuxRoomServiceImpl implements LuxRoomService {

    @Override
    public void clean(LuxRoom room) {
        System.out.println("Люкс-комната " + room.roomNumber + " очищена.");
    }

    @Override
    public void reserve(LuxRoom room) {
        room.reserve();
        System.out.println("Люкс-комната " + room.roomNumber + " забронирована.");
    }

    @Override
    public void free(LuxRoom room) {
        room.free();
        System.out.println("Люкс-комната " + room.roomNumber + " освобождена.");
    }

    @Override
    public void foodDelivery(LuxRoom room) {
        System.out.println("Доставка еды для люкс-комнаты " + room.roomNumber + " выполнена.");
    }
}