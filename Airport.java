import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

class Airport {
  public int nDock = 2;

  List<Airplane> docks;

  List<Airplane> waitingList;

  public Airport() {
    docks = new LinkedList<Airplane>();
    waitingList = new LinkedList<Airplane>();
  }

  public void flyPlane() {
    Airplane airplane;

    synchronized (docks) {
      while (docks.size() == 0) {

        System.out.println("ATC is waiting for airplane.");
        try {
          docks.wait();
        } catch (InterruptedException iex) {
          iex.printStackTrace();
        }
      }
      System.out.println("ATC found a airplane in the air.");
      airplane = (Airplane) ((LinkedList<?>) docks).poll();
    }

    long duration = 0;
    try {
      System.out.println("Passengers is getting off from: " + airplane.getName());
      duration = (long) (Math.random() * 10);
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException iex) {
      iex.printStackTrace();
    }

    try {
      System.out.println("Completed getting off from: " + airplane.getName() + " in " + duration + " seconds.");
      System.out.println("Cleaning is started in: " + airplane.getName());
      duration = (long) (Math.random() * 10);
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException iex) {
      iex.printStackTrace();
    }

    System.out.println("Completed cleaning in: " + airplane.getName() + " in " + duration + " seconds.");
    System.out.println("Airplane " + airplane.getName() + " left...");
  }

  public void putInAirport(Airplane airplane) {
    Airplane waitingAirplane;
    System.out.println("Airplane : " + airplane.getName() + "Willing to be entering the dock at " + airplane.getDate());

    synchronized (docks) {
      if (docks.size() < nDock) {
        // If dock is available, the waiting airplane can be land and dock the dock.
        if (waitingList.size() > 0) {
          waitingAirplane = (Airplane) ((LinkedList<?>) waitingList).poll();
          System.out.println("Next waiting airplane can land the airport.");
          ((LinkedList<Airplane>) docks).offer(waitingAirplane);

        } else {
          ((LinkedList<Airplane>) docks).offer(airplane);
          System.out.println("There is empty dock, " + airplane.getName() + "can land directly.");
        }

      } else {
        ((LinkedList<Airplane>) waitingList).offer(airplane);
      }

      if (docks.size() == 1) {
        docks.notify();
      }
    }
  }
}
