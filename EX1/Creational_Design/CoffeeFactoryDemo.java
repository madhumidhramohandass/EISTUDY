// Product Interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Products
class Espresso implements Coffee {
    @Override
    public String getDescription() {
        return "Espresso";
    }

    @Override
    public double getCost() {
        return 2.5;
    }
}

class Latte implements Coffee {
    @Override
    public String getDescription() {
        return "Latte";
    }

    @Override
    public double getCost() {
        return 3.0;
    }
}

// Creator
abstract class CoffeeFactory {
    public abstract Coffee createCoffee();
}

// Concrete Creators
class EspressoFactory extends CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new Espresso();
    }
}

class LatteFactory extends CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new Latte();
    }
}

public class CoffeeFactoryDemo {
    public static void main(String[] args) {
        CoffeeFactory coffeeFactory = new LatteFactory();
        Coffee coffee = coffeeFactory.createCoffee();
        System.out.println("Ordered: " + coffee.getDescription());
    }
}
