import java.util.stream.Collectors;

enum CarType {
    SEDAN, SUV, ELECTRIC, TRUCK;
}

public class CarDealership {
    private Set<Car> cars = new HashSet<>();

    // Добавить машину в автоцентр
    public void addCar(Car car) {
        if (cars.stream().noneMatch(c -> c.getVin().equals(car.getVin()))) {
            cars.add(car);
        } else {
            System.out.println("Машина с таким VIN уже существует.");
        }
    }

    // Найти машины по производителю
    public List<Car> findCarsByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(car -> car.getManufacturer().equals(manufacturer))
                .collect(Collectors.toList());
    }

    // Вычисление средней цены для машин определённого типа
    public double averagePriceByType(CarType type) {
        return cars.stream()
                .filter(car -> car.getModel().contains(type.name()))
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
    }

    // Сортировка машин по году выпуска
    public List<Car> getCarsSortedByYear() {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getYear).reversed())
                .collect(Collectors.toList());
    }

    // Статистика по типам машин и самой старой/новой машине
    public void displayStatistics() {
        // Статистика по типам машин
        Map<CarType, Long> typeCount = cars.stream()
                .collect(Collectors.groupingBy(car -> {
                    if (car.getModel().contains("SUV")) return CarType.SUV;
                    if (car.getModel().contains("ELECTRIC")) return CarType.ELECTRIC;
                    return CarType.SEDAN;
                }, Collectors.counting()));

        System.out.println("Статистика по типам машин:");
        typeCount.forEach((type, count) -> System.out.println(type + ": " + count));

        // Самая старая и самая новая машина
        Car oldestCar = cars.stream().min(Comparator.comparingInt(Car::getYear)).orElse(null);
        Car newestCar = cars.stream().max(Comparator.comparingInt(Car::getYear)).orElse(null);

        System.out.println("\nСамая старая машина: " + (oldestCar != null ? oldestCar.getModel() : "Не найдено"));
        System.out.println("Самая новая машина: " + (newestCar != null ? newestCar.getModel() : "Не найдено"));
    }

    // Меню с выбором
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarDealership dealership = new CarDealership();

        dealership.addCar(new Car("1", "Tesla Model S", "Tesla", 2022, 5000, 75000, CarType.ELECTRIC));
        dealership.addCar(new Car("2", "BMW X5", "BMW", 2020, 30000, 50000, CarType.SUV));

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
                // Добавление машины
                break;
            case 2:
                // Поиск машин
                break;
            case 3:
                // Средняя цена машин
                break;
            case 4:
                // Машины по году выпуска
                break;
            case 5:
                dealership.displayStatistics();
                break;
        }
        scanner.close();
    }
}