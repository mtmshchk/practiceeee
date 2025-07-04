public class ExampleClass {

    // Метод с аннотацией @DeprecatedEx
    @DeprecatedEx(message = "Используйте новый метод 'newMethod'")
    public void oldMethod() {
        System.out.println("Старый метод");
    }

    // Вложенный класс с аннотацией @DeprecatedEx
    @DeprecatedEx(message = "Используйте новый класс 'NewClass'")
    public static class OldClass {
        public void display() {
            System.out.println("Старый класс");
        }
    }
