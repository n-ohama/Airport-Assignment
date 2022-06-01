class Barber implements Runnable {
  Bshop shop;
  CustomerGenerator customerGenerator;

  public Barber(Bshop shop, CustomerGenerator customerGenerator) {
    this.shop = shop;
    this.customerGenerator = customerGenerator;
  }

  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException iex) {
      iex.printStackTrace();
    }
    System.out.println("Barber started.....");
    while (!customerGenerator.closingTime) {
      shop.cutHair();
    }
    if (customerGenerator.closingTime) {
      while(shop.listCustomer.size() > 0) {
        shop.cutHair();
      }
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return;
    }
  }
}