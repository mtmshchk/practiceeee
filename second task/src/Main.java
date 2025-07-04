public class Main {
    public static void main(String[] args) {
        // Создаем объект автоцентра
        CarDealership dealership = new CarDealership();

        // Пример добавления машин
        dealership.addCar(new Car("1", "Tesla Model S", "Tesla", 2022, 5000, 75000, Car.CarType.ELECTRIC));
        dealership.addCar(new Car("2", "BMW X5", "BMW", 2020, 30000, 50000, Car.CarType.SUV));

        Scanner scanner = new Scanner(System.in);

        // Меню
        System.out.println("Выберите опцию:");
        System.out.println("1 - Добавить машину в автоцентр");
        System.out.println("2 - Найти машины по производителю");
        System.out.println("3 - Средняя цена машин по типу");
        System.out.println("4 - Машины, отсортированные по году выпуска");
        System.out.println("5 - Статистика");

        int option = scanner.nextInt();
        scanner.nextLine();  // Чтобы избежать проблем с новой строкой после nextInt()

        switch (option) {
            case 1:
                // Логика добавления машины в автоцентр
                break;
            case 2:
                System.out.println("Введите производителя:");
                String manufacturer = scanner.nextLine();
                dealership.findCarsByManufacturer(manufacturer).forEach(car -> System.out.println(car.getModel()));
                break;
            case 3:
                System.out.println("Введите тип машин (SEDAN, SUV, ELECTRIC, TRUCK):");
                String typeStr = scanner.nextLine();
                Car.CarType type = Car.CarType.valueOf(typeStr.toUpperCase());
                System.out.println("Средняя цена для типа " + type + ": " + dealership.averagePriceByType(type));
                break;
            case 4:
                dealership.getCarsSortedByYear().forEach(car -> System.out.println(car.getModel()));
                break;
            case 5:
                dealership.displayStatistics();
                break;
            default:
                System.out.println("Некорректный выбор.");
        }

        scanner.close();
    }
}