public class Main {

        public static void main(String[] args) {
                // Создание объектов комнат
                Room room1 = new EconomyRoom(101, 2, Prices.ECONOMY.getPrice());
                Room room2 = new LuxRoom(201, 2, Prices.LUX.getPrice());

                // Создание сервисов
                RoomService<Room> roomService = new RoomServiceImpl();
                LuxRoomService luxRoomService = new LuxRoomServiceImpl();

                // Работы с комнатами
                try {
                        roomService.reserve(room1); // Резервируем обычную комнату
                        luxRoomService.reserve((LuxRoom) room2); // Резервируем люксовую комнату
                        luxRoomService.foodDelivery((LuxRoom) room2); // Доставка еды в люксовую комнату

                        // Очистка комнат
                        roomService.clean(room1);
                        luxRoomService.clean((LuxRoom) room2);

                        // Освобождение комнат
                        roomService.free(room1);
                        luxRoomService.free((LuxRoom) room2);

                } catch (RoomAlreadyReservedException | RoomNotReservedException e) {
                        System.out.println(e.getMessage());
                }
        }
}