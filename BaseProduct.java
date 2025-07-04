abstract class BaseProduct {
    

    private String name;
    private double price;
    private int quantity;

    //constructor
    public BaseProduct(String name, double price, int quantity){

        if (price<0) throw new IllegalArgumentException("Invalid product price");
        if(quantity<0) throw new IllegalArgumentException("invalid product price");

        this.name=name;
        this.price=price;
        this.quantity = quantity;
    }

    //getters
    public String getProductName(){
        return name;
    }
    
    public double getProductPrice(){
        return price;
    }

    public double getProductQuantity(){
        return quantity;
    }

    //setters
     public void setProductPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("please enter a valid price");
        this.price = price;
    }

     public void setProductQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("please enter a valid product quantity.");
        this.quantity = quantity;
    }

      public void reduceProductQantity(int orderAmount){
        if(orderAmount<quantity){
            this.quantity=this.quantity-orderAmount;
        }
        else{
            throw new IllegalArgumentException("Not enough quantity in stock.");
        }
      }

     public boolean isAvailable(int orderAmount){
           return  this.quantity >= orderAmount && !isExpired();
     }


      public boolean isExpired() {
        return false; // By default it is non expirable
    }
     public boolean isShippable() {
        return false; // by default it is not shippable
    }
}
