import java.util.Date;
import java.util.concurrent.TimeUnit;

class CustomerGenerator implements Runnable
{
  Bshop shop;
  public boolean closingTime = false;
  
  public CustomerGenerator(Bshop shop) {
    this.shop = shop;
  }
 
  public void run() {
    int number = 0;
    while(number < 6) {
      Customer customer = new Customer(shop);
      customer.setInTime(new Date());
      Thread thcustomer = new Thread(customer);
      customer.setName("Customer "+thcustomer.getId());
      thcustomer.start();

      try {
        TimeUnit.SECONDS.sleep((long)(Math.random()*10));
      } catch(InterruptedException iex) {
        iex.printStackTrace();
      }
      number++;
    }
  }

  public synchronized void setclosingTime() {
    closingTime = true;
  }
}