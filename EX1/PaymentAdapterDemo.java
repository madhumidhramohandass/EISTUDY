// Existing Interface
interface PaymentGateway {
    void processPayment(double amount);
}

// Adaptee
class NewPaymentGateway {
    public void newProcessPayment(double amount) {
        System.out.println("Processing payment through New Payment Gateway: " + amount);
    }
}

// Adapter
class PaymentAdapter implements PaymentMethod {
    private NewPaymentGateway newPaymentGateway;

    public PaymentAdapter(NewPaymentGateway newPaymentGateway) {
        this.newPaymentGateway = newPaymentGateway;
    }

    @Override
    public void pay(double amount) {
        newPaymentGateway.newProcessPayment(amount);
    }
}

public class PaymentAdapterDemo {
    public static void main(String[] args) {
        NewPaymentGateway newPaymentGateway = new NewPaymentGateway();
        PaymentAdapter paymentAdapter = new PaymentAdapter(newPaymentGateway);
        paymentAdapter.pay(15.0);
    }
}
