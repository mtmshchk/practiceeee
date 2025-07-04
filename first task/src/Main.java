public class Main {
    public static void main(String[] args) {
        // Создание объектов счетов
        BankAccount account1 = new BankAccount("Иван Иванов");
        BankAccount account2 = new BankAccount("Петр Петров");

        // Вывод информации о счетах до операций
        System.out.println("До операций:");
        System.out.println(account1);
        System.out.println(account2);

        // Пополнение счета
        account1.deposit(1000);
        account2.deposit(500);

        // Снятие денег
        account1.withdraw(200);

        // Перевод денег
        account1.transfer(account2, 300);

        // Вывод информации о счетах после операций
        System.out.println("\nПосле операций:");
        System.out.println(account1);
        System.out.println(account2);

        // Сравнение счетов
        System.out.println("\nСравнение счетов:");
        System.out.println("Счета одинаковы: " + account1.equals(account2));
    }
}