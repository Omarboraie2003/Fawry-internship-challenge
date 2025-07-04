public class Customer {

   
    private String customerName;
    private double balance;
    
    public Customer( String customerNmae,double balance){
       
        
        this.customerName=customerNmae;
        this.balance=balance;
   }  
   
     public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }


     public void withdraw(double amount) {
        if (amount < balance) {
             balance -= amount;
        }
        else{
            throw new IllegalArgumentException("Insufficient balance.");
        }

    }

}
