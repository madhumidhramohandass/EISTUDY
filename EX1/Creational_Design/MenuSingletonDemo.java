import java.util.ArrayList;
import java.util.List;

class Menu {
    private static Menu instance;
    private List<String> items;

    private Menu() {
        items = new ArrayList<>();
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }
}

public class MenuSingletonDemo {
    public static void main(String[] args) {
        Menu menu = Menu.getInstance();
        menu.addItem("Espresso");
        menu.addItem("Latte");
        System.out.println("Menu items: " + menu.getItems());
    }
}
