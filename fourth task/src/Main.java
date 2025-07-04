import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.lang.annotation.*;
import java.lang.reflect.*;

// 1. Интерфейс Printable с лямбда-выражением
@FunctionalInterface
interface Printable {
    void print();
}

// 2. Класс HeavyBox для демонстрации с Consumer
class HeavyBox {
    int weight;

    public HeavyBox(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}

// 3. Кастомная аннотация @DeprecatedEx
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface DeprecatedEx {
    String message();
}

// 4. Кастомная аннотация @JsonField
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

// 5. Класс Person для демонстрации @JsonField
class Person {
    @JsonField(name = "first_name")
    private String firstName;

    @JsonField(name = "last_name")
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

public class Main {

    public static void main(String[] args) {
        // 1. Лямбда выражение для интерфейса Printable
        Printable printable = () -> System.out.println("Это лямбда-выражение для Printable");
        printable.print();

        // 2. Проверка пустой строки
        checkEmptyString();

        // 3. Проверка строки, которая начинается с J или N и заканчивается A
        checkStringStartsAndEnds();

        // 4. Лямбда-выражение для HeavyBox
        heavyBoxConsumer();

        // 5. Лямбда для Function
        checkNumber();

        // 6. Лямбда для Supplier
        generateRandomNumber();

        // 7. Обработчик кастомной аннотации
        handleAnnotations();

        // 8. Сериализация в JSON
        Person person = new Person("John", "Doe");
        System.out.println(toJson(person));  // {"first_name": "John", "last_name": "Doe"}
    }

    // 2.1 Лямбда выражение для проверки строки на null (используем Predicate)
    public static void checkEmptyString() {
        Predicate<String> isNotNull = s -> s != null;
        System.out.println(isNotNull.test("Hello"));  // true
        System.out.println(isNotNull.test(null));     // false
    }

    // 2.2 Лямбда выражение для проверки, что строка не пуста (используем Predicate)
    public static void checkStringStartsAndEnds() {
        Predicate<String> startsWithJorN = s -> s.startsWith("J") || s.startsWith("N");
        Predicate<String> endsWithA = s -> s.endsWith("A");
        Predicate<String> checkString = startsWithJorN.and(endsWithA);

        System.out.println(checkString.test("Java"));  // true
        System.out.println(checkString.test("Node"));  // true
        System.out.println(checkString.test("Python")); // false
    }

    // 4. Лямбда-выражение для HeavyBox (Consumer)
    public static void heavyBoxConsumer() {
        HeavyBox box = new HeavyBox(10);
        Consumer<HeavyBox> boxProcessor = b -> System.out.println("Отгрузили ящик с весом " + b.getWeight() + " кг");
        Consumer<HeavyBox> boxSender = b -> System.out.println("Отправляем ящик с весом " + b.getWeight() + " кг");

        boxProcessor.andThen(boxSender).accept(box);
    }

    // 5. Лямбда для Function
    public static void checkNumber() {
        Function<Integer, String> numberChecker = num -> {
            if (num > 0) {
                return "Положительное число";
            } else if (num < 0) {
                return "Отрицательное число";
            } else {
                return "Ноль";
            }
        };

        System.out.println(numberChecker.apply(5));   // Положительное число
        System.out.println(numberChecker.apply(-3));  // Отрицательное число
        System.out.println(numberChecker.apply(0));   // Ноль
    }

    // 6. Лямбда для Supplier
    public static void generateRandomNumber() {
        Supplier<Integer> randomNumberSupplier = () -> (int) (Math.random() * 11);
        System.out.println(randomNumberSupplier.get());  // Случайное число от 0 до 10
    }

    // 7. Обработчик кастомной аннотации @DeprecatedEx
    public static void handleAnnotations() {
        try {
            Class<?> clazz = ExampleClass.class;

            // Проверка для методов
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(DeprecatedEx.class)) {
                    DeprecatedEx annotation = method.getAnnotation(DeprecatedEx.class);
                    System.out.println("Внимание: метод '" + method.getName() + "' устарел. Альтернатива: '" + annotation.message() + "'");
                }
            }

            // Проверка для классов
            for (Class<?> innerClass : clazz.getDeclaredClasses()) {
                if (innerClass.isAnnotationPresent(DeprecatedEx.class)) {
                    DeprecatedEx annotation = innerClass.getAnnotation(DeprecatedEx.class);
                    System.out.println("Внимание: класс '" + innerClass.getName() + "' устарел. Альтернатива: '" + annotation.message() + "'");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 8. Сериализация в JSON
    public static String toJson(Object obj) {
        StringBuilder json = new StringBuilder("{");

        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField annotation = field.getAnnotation(JsonField.class);
                field.setAccessible(true);
                try {
                    json.append("\"").append(annotation.name()).append("\": \"")
                            .append(field.get(obj)).append("\", ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        json.setLength(json.length() - 2); // Убираем последнюю запятую
        json.append("}");
        return json.toString();
    }
}
