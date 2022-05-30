import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

class Airport {
  public int nDock = 2;
  
  List<Airplane> docks;

  List<Airplane> waitingList;

  public Airport()
  {
    docks = new LinkedList<Airplane>();
    waitingList = new LinkedList<Airplane>();
  }

  public void flyPlane() {
    Airplane airplane;
    Airplane waitingAirplane;
    synchronized (docks)
    {
      while(docks.size()==0)
      {
        if(waitingList.size() != 0) {
          waitingAirplane = (Airplane)((LinkedList<?>)waitingList).poll();
          ((LinkedList<Airplane>) docks).offer(waitingAirplane);
          break;
        }
        System.out.println("ATC is waiting for airplane.");
        try {
          docks.wait();
        } catch(InterruptedException iex) {
          iex.printStackTrace();
        }
      }
      System.out.println("ATC found a airplane in the air.");
      airplane = (Airplane)((LinkedList<?>)docks).poll();
    }

    long duration = 0;
    try {
      System.out.println("Passengers is getting off from: " + airplane.getName());
      duration = (long)(Math.random()*10);
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException iex) {
      iex.printStackTrace();
    }

    try {
      System.out.println("Completed getting off from: " + airplane.getName() + " in " + duration + " seconds.");
      System.out.println("Cleaning is started in: " + airplane.getName());
      duration = (long)(Math.random()*10);
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException iex) {
      iex.printStackTrace();
    }

    System.out.println("Completed cleaning in: " + airplane.getName() + " in " + duration + " seconds.");
    System.out.println("Airplane " + airplane.getName() + " left...");
  }

  public void putInAirport(Airplane airplane)
  {
    Airplane waitingAirplane; 
    System.out.println("Airplane : " + airplane.getName() + "Willing to be entering the dock at " + airplane.getDate());

    synchronized(docks)
    {
      if(docks.size() == nDock) {
        System.out.println("No dock available for airplane " + airplane.getName());
        System.out.println("Airplane " + airplane.getName() + "Exits...");
        ((LinkedList<Airplane>) waitingList).offer(airplane);

        return;
      }

      if(waitingList.size() > 0) {
        waitingAirplane = (Airplane)((LinkedList<?>)waitingList).poll();
        ((LinkedList<Airplane>) docks).offer(waitingAirplane);
      } else {
        ((LinkedList<Airplane>) docks).offer(airplane);
      }

      if(docks.size()==1) {
        docks.notify();
      }
    }
  }
}
