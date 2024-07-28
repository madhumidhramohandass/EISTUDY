import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String promotion);
}

// Concrete Observer
class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String promotion) {
        System.out.println("Dear " + name + ", there's a new promotion: " + promotion);
    }
}

// Subject class
class CoffeeShop {
    private List<Observer> customers = new ArrayList<>();
    
    public void addCustomer(Observer customer) {
        customers.add(customer);
    }
    
    public void removeCustomer(Observer customer) {
        customers.remove(customer);
    }
    
    public void notifyCustomers(String promotion) {
        for (Observer customer : customers) {
            customer.update(promotion);
        }
    }
}

public class CoffeeShopObserverDemo {
    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();
        Customer customer1 = new Customer("Alice");
        Customer customer2 = new Customer("Bob");
        
        shop.addCustomer(customer1);
        shop.addCustomer(customer2);
        
        shop.notifyCustomers("Buy one get one free!");
    }
}
