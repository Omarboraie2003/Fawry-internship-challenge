import java.sql.Date;
import java.time.LocalDate;
public class ExpirableProduct extends BaseProduct{
    
    private Date expDate;
      public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate){
        
        super(name, price, quantity);
        this.expDate=Date.valueOf(expiryDate);
       
        if (expDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null.");
        }
    }

        public Date getExpDate(){
            return expDate;
        }
       
        @Override
        public boolean isExpired() {
        // Get the current system date (today)
        Date today = new Date(System.currentTimeMillis());
        return expDate.before(today);
    }




    }
    
  