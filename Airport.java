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

        if(waitingList.size() > 0) {
          airplane = (Airplane) ((LinkedList<?>) waitingList).poll();
          ((LinkedList<Airplane>) docks).offer(airplane);
          break;
        }

        System.out.println("ATC is waiting for airplane. ");

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
    System.out.println(airplane.getName() + " landing request " + airplane.getDate());

    synchronized (docks) {
      if (docks.size() < nDock) {
        // If dock is available, the waiting airplane can be land and dock the dock.
        if (waitingList.size() > 0) {
          waitingAirplane = (Airplane) ((LinkedList<?>) waitingList).poll();
          System.out.println(waitingAirplane.getName() + " Waiting Airplane → docks");
          ((LinkedList<Airplane>) docks).offer(waitingAirplane);

        } else {
          ((LinkedList<Airplane>) docks).offer(airplane);
          System.out.println(airplane.getName() + " can land directly into docks.");
        }
        
      } else {
        ((LinkedList<Airplane>) waitingList).offer(airplane);
        System.out.println(airplane.getName() + " can land into waitingList");
      }

      if (docks.size() == 1) {
        docks.notify();
      }
    }
  }
}
