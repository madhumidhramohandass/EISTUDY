// Strategy interface
interface PaymentMethod {
    void pay(double amount);
}

// Concrete Strategy A
class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// Concrete Strategy B
class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

// Context
class PaymentContext {
    private PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void pay(double amount) {
        paymentMethod.pay(amount);
    }
}

public class PaymentStrategyDemo {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setPaymentMethod(new CreditCardPayment());
        paymentContext.pay(5.0);

        paymentContext.setPaymentMethod(new PayPalPayment());
        paymentContext.pay(10.0);
    }
}
