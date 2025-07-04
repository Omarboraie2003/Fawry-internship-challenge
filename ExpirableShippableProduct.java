import java.sql.Date;
import java.time.LocalDate;

public class ExpirableShippableProduct extends ShippableProducts {
    private Date expDate;

    public ExpirableShippableProduct(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity, weight);

        if (expiryDate == null) {
            throw new IllegalArgumentException("Product creation failed: Expiry date cannot be null.");
        }

        this.expDate = Date.valueOf(expiryDate); 

        if (isExpired()) {
            System.out.println("⚠️ Note: " + getProductName() + " is expired (Expiry: " + expDate + ")");
        }
    }

    public Date getExpDate() {
        return expDate;
    }

    @Override
    public boolean isExpired() {
        if (expDate == null) {
            
            throw new IllegalStateException("Cannot check expiry: expiry date is not set.");
        }

        Date today = new Date(System.currentTimeMillis());
        return expDate.before(today);
    }
}

