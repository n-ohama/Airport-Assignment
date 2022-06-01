import java.util.Date;
import java.util.concurrent.TimeUnit;

class AirplanGenerator implements Runnable {
    ATC atc;
    public boolean closingTime = false;

    public AirplanGenerator(ATC atc) {
        this.atc = atc;
    }

    public void run() {
        int number = 0;
        while (number < 6) {
            Airplan airplan = new Airplan(atc);
            airplan.setInTime(new Date());
            Thread thairplan = new Thread(airplan);
            airplan.setName("Airplan " + thairplan.getId());
            thairplan.start();

            try {
                TimeUnit.SECONDS.sleep((long) (3));
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
            number++;
        }
        closingTime = true;
    }
}