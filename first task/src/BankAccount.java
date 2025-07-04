import java.time.LocalDateTime;
import java.util.Random;

public class BankAccount {
    // Поля класса
    private String ownerName;  // имя владельца
    private int balance;       // баланс счета
    private LocalDateTime openingDate; // дата открытия счета
    private boolean isBlocked; // флаг блокировки счета
    private String accountNumber; // номер счета

    // Конструктор с параметром имя владельца
    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0; // Начальный баланс 0
        this.openingDate = LocalDateTime.now(); // Текущая дата
        this.isBlocked = false; // Счет не заблокирован
        this.accountNumber = generateAccountNumber(); // Генерация номера счета
    }

    // Метод пополнения счета
    public boolean deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false; // Если сумма отрицательная или нулевая, операция не выполнена
    }

    // Метод снятия денег
    public boolean withdraw(int amount) {
        if (!isBlocked && amount > 0 && amount <= balance) {
            this.balance -= amount;
            return true;
        }
        return false; // Операция невозможна, если счет заблокирован или недостаточно средств
    }

    // Метод перевода денег на другой счет
    public boolean transfer(BankAccount otherAccount, int amount) {
        if (this.withdraw(amount)) { // Если снятие с текущего счета прошло успешно
            return otherAccount.deposit(amount); // Попытка пополнить другой счет
        }
        return false; // Если снятие не прошло, то перевод не выполняется
    }

    // Генерация случайного номера счета
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            number.append(random.nextInt(10)); // Генерация случайной цифры
        }
        return number.toString(); // Возвращаем номер счета
    }

    // Метод toString для вывода информации о счете
    @Override
    public String toString() {
        return "BankAccount{" +
                "ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", openingDate=" + openingDate +
                ", isBlocked=" + isBlocked +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    // Метод equals для сравнения двух счетов
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankAccount that = (BankAccount) obj;
        return accountNumber.equals(that.accountNumber); // Сравнение по номеру счета
    }

    // Метод hashCode для корректной работы equals
    @Override
    public int hashCode() {
        return accountNumber.hashCode(); // Генерация хеш-кода на основе номера счета
    }

    // Геттеры и сеттеры
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}