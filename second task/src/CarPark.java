public class CarPark {
    public static void main(String[] args) {
        // Создаем массив для годов выпуска машин (от 2000 до 2025)
        int[] years = new int[50];
        Random random = new Random();

        // Заполнение массива случайными годами
        for (int i = 0; i < years.length; i++) {
            years[i] = random.nextInt(2025 - 2000 + 1) + 2000;
        }

        // Вывод машин, выпущенных после 2015 года
        System.out.println("Машины, выпущенные после 2015 года:");
        for (int year : years) {
            if (year > 2015) {
                System.out.println(year);
            }
        }

        // Подсчет среднего возраста авто
        int currentYear = 2025;
        int totalAge = 0;
        for (int year : years) {
            totalAge += currentYear - year;
        }
        double averageAge = totalAge / (double) years.length;
        System.out.println("\nСредний возраст автомобилей: " + averageAge);
    }
}
