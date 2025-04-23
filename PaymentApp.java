interface Secure {
    boolean authenticate(String credentials);
}
abstract class PaymentMethod implements Secure {
    protected String user;

    public PaymentMethod(String user) {
        this.user = user;
    }
    abstract void processPayment(double amount);
    void generateReceipt(double amount) {
        System.out.println("Receipt: ₹" + amount + " paid by " + user);
    }
}
class UPIPayment extends PaymentMethod {
    private String upiPin;
    public UPIPayment(String user, String upiPin) {
        super(user);
        this.upiPin = upiPin;
    }
    @Override
    public boolean authenticate(String enteredPin) {
        return upiPin.equals(enteredPin);
    }
    @Override
    void processPayment(double amount) {
        System.out.println("Processing UPI Payment of ₹" + amount);
        generateReceipt(amount);
    }
}
class CreditCardPayment extends PaymentMethod {
    private String cvv;

    public CreditCardPayment(String user, String cvv) {
        super(user);
        this.cvv = cvv;
    }
    @Override
    public boolean authenticate(String enteredCvv) {
        return cvv.equals(enteredCvv);
    }
    @Override
    void processPayment(double amount) {
        System.out.println("Processing Credit Card Payment of ₹" + amount);
        generateReceipt(amount);
    }
}
public class PaymentApp {
    public static void main(String[] args) {
        PaymentMethod upi = new UPIPayment("Sushobhit", "1234");
        if (upi.authenticate("1234")) {
            upi.processPayment(500.00);
        } else {
            System.out.println("UPI Authentication Failed!");
        }

        PaymentMethod card = new CreditCardPayment("Sushobhit", "999");
        if (card.authenticate("999")) {
            card.processPayment(1500.00);
        } else {
            System.out.println("Credit Card Authentication Failed!");
        }
    }
}
