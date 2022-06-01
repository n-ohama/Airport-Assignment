import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class ATC {
    int nchair = 5;

    List<Airplan> listAirplan;

    public ATC() {

        listAirplan = new LinkedList<Airplan>();
    }

    public void cutHair(int unique) {
        Airplan airplan;
        synchronized (listAirplan) {

            while (listAirplan.size() == 0) {
                System.out.println("Dock" + unique + " is waiting for airplan.");
                try {
                    listAirplan.wait();
                } catch (InterruptedException iex) {
                    iex.printStackTrace();
                }
            }
            System.out.println("Dock" + unique + " found a airplan in the queue.");
            airplan = (Airplan) ((LinkedList<?>) listAirplan).poll();
        }
        latePrint("Docked Airplan : " + airplan.getName());

        System.out.println("ATC : OK!");
        System.out.println("Airplan " + airplan.getName() + " Fly...");
    }

    public void latePrint(String word) {
        long duration = 0;
        try {
            System.out.println(word);
            duration = (long) (Math.random() * 40);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }

    public void add(Airplan airplan) {
        System.out.println("Airplan : " + airplan.getName() + " entering the shop at " + airplan.getInTime());

        synchronized (listAirplan) {
            if (listAirplan.size() == nchair) {

                System.out.println("No chair available for customer " + airplan.getName());
                System.out.println("Airplan " + airplan.getName() + "Exits...");
                return;
            }

            ((LinkedList<Airplan>) listAirplan).offer(airplan);
            System.out.println("Airplan : " + airplan.getName() + " added waiting list.");

            if (listAirplan.size() == 1)
                listAirplan.notify();
        }
    }
}