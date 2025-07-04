public class CarModels {
    public static void main(String[] args) {
        // Создание списка с названиями моделей машин
        List<String> models = new ArrayList<>(Arrays.asList(
                "Toyota Camry", "BMW X5", "Tesla Model S", "Tesla Model 3", "BMW X5", "Audi Q7"
        ));

        // Удаление дубликатов и сортировка в обратном алфавитном порядке
        Set<String> uniqueModels = new HashSet<>(models);
        List<String> sortedModels = new ArrayList<>(uniqueModels);
        Collections.sort(sortedModels, Collections.reverseOrder());

        // Вывод отсортированного списка
        System.out.println("Модели после удаления дубликатов и сортировки:");
        sortedModels.forEach(System.out::println);

        // Замена "Tesla" на "ELECTRO_CAR"
        for (int i = 0; i < sortedModels.size(); i++) {
            if (sortedModels.get(i).contains("Tesla")) {
                sortedModels.set(i, "ELECTRO_CAR");
            }
        }

        // Вывод после замены
        System.out.println("\nМодели после замены 'Tesla' на 'ELECTRO_CAR':");
        sortedModels.forEach(System.out::println);
    }
}