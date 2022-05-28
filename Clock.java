public class Clock extends Thread {
  private CustomerGenerator cg;
  private Barber b;

  public Clock(CustomerGenerator cg, Barber b) {
      this.cg = cg;
      this.b = b;
  }

  public void run() {
      try {
          Thread.sleep(15000);
          NotifyClosed();
      } catch (InterruptedException ex) {
          ex.printStackTrace();
      }
  }

  public void NotifyClosed() {
      System.out.println("Click: Tick Tock.. It's closing time!");
      cg.setclosingTime();
      b.setclosingTime();
  }

}