import java.util.stream.Collectors;

public class CarAnalysis {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("1", "Toyota Camry", "Toyota", 2019, 40000, 25000),
                new Car("2", "BMW X5", "BMW", 2017, 30000, 35000),
                new Car("3", "Tesla Model S", "Tesla", 2021, 10000, 70000),
                new Car("4", "Audi Q7", "Audi", 2018, 20000, 45000),
                new Car("5", "BMW X5", "BMW", 2020, 15000, 38000)
        );

        // Фильтрация по пробегу и сортировка по цене
        List<Car> filteredAndSorted = cars.stream()
                .filter(car -> car.getMileage() < 50000)
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .collect(Collectors.toList());

        // Вывод топ-3 самых дорогих машин
        System.out.println("Топ-3 самых дорогих машины:");
        filteredAndSorted.stream().limit(3).forEach(car -> System.out.println(car.getModel() + " - $" + car.getPrice()));

        // Средний пробег всех машин
        double averageMileage = cars.stream().mapToInt(Car::getMileage).average().orElse(0);
        System.out.println("\nСредний пробег всех машин: " + averageMileage);

        // Группировка по производителю
        Map<String, List<Car>> carsByManufacturer = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        // Вывод сгруппированных машин
        System.out.println("\nМашины по производителю:");
        carsByManufacturer.forEach((manufacturer, carList) -> {
            System.out.println(manufacturer + ":");
            carList.forEach(car -> System.out.println("  " + car.getModel()));
        });
    }
}