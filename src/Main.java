import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
    private final HashMap<Integer, Table> tables;

    public Main() {
        tables = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            Table table = new Table(i);
            tables.put(i, table);
        }
    }

    public Table getFreeTable() {
        for (Table table : tables.values()) {
            if (!table.isOccupied()) {
                return table;
            }
        }
        return null;
    }

    public void addOrder(Table table, Order order) {
        table.addOrder(order);
    }

    public void displayTableOrders(Table table) {
        System.out.println("Заказы стола " + table.getTableNumber());
        for (Order order : table.getOrders()) {
            System.out.println(order.getDishName() + " " + order.getDishPrice());
        }
    }

    public void calculateTable(Table table) {
        int total = 0;
        System.out.println("Стол " + table.getTableNumber() + " заказы:");
        for (Order order : table.getOrders()) {
            System.out.println(order.getDishName() + " " + order.getDishPrice());
            total += order.getDishPrice();
        }
        System.out.println("Итог: " + total);
        table.setOccupied(false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main Main = new Main();
        HashMap<Integer, Table> tables = Main.tables;
        while (true) {
            System.out.println("1. Выбрать стол");
            System.out.println("2. Добавить заказ");
            System.out.println("3. Отображать заказ стола");
            System.out.println("4. Посчитать столик");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    Table table = Main.getFreeTable();
                    if (table == null) {
                        System.out.println("Нет свободных столов");
                    } else {
                        table.setOccupied(true);
                        System.out.println("Выбрали столик: " + table.getTableNumber());
                    }
                    break;
                }
                case 2: {
                    System.out.print("Номер стола: ");
                    int tableNumber = scanner.nextInt();
                    Table table = tables.get(tableNumber);
                    if (table == null) {
                        System.out.println("Неверный номер стола");
                        break;
                    }
                    if (!table.isOccupied()) {
                        System.out.println("Стол не занят");
                        break;
                    }
                    System.out.print("Введите название блюда: ");
                    scanner.nextLine();
                    String dishName = scanner.nextLine();
                    System.out.print("Введите цену: ");
                    int dishPrice = scanner.nextInt();
                    Order order = new Order(dishName, dishPrice);
                    Main.addOrder(table, order);
                    break;
                }
                case 3: {
                    System.out.print("Введите номер стола: ");
                    int tableNumber = scanner.nextInt();
                    Table table = tables.get(tableNumber);
                    if (table == null) {
                        System.out.println("Неверный номер стола");
                        break;
                    }
                    if (!table.isOccupied()) {
                        System.out.println("Стол не занят");
                        break;
                    }
                    Main.displayTableOrders(table);
                    break;
                }
                case 4: {
                    System.out.print("Выберите номер столика: ");
                    int tableNumber = scanner.nextInt();
                    Table table = tables.get(tableNumber);
                    if (table == null) {
                        System.out.println("Неверный номер стола");
                        break;
                    }
                    if (!table.isOccupied()) {
                        System.out.println("Стол не занят");
                        break;
                    }
                    Main.calculateTable(table);
                    break;
                }
                case 5: {
                    System.out.println("Выход...");
                    System.exit(0);
                }
                default: {
                    System.out.println("Неверный выбор");
                }
            }
        }
    }
}

class Table {
    private final int tableNumber;
    private boolean isOccupied;
    private final ArrayList<Order> orders;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        this.orders = new ArrayList<>();
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}

class Order {
    private final String dishName;
    private final int dishPrice;

    public Order(String dishName, int dishPrice) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
    }

    public String getDishName() {
        return dishName;
    }

    public int getDishPrice() {
        return dishPrice;
    }
}