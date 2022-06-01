
import java.util.Date;
import java.util.concurrent.TimeUnit;

class AirplaneGenerator implements Runnable {
    Airport airport;
    ATC atc;
    public boolean closingTime = false;

    public AirplaneGenerator(Airport airport, ATC atc) {
        this.airport = airport;
        this.atc = atc;
    }

    public void run() {
        int number = 0;
        while (number < 6) {
            Airplane airplane = new Airplane(airport);
            airplane.setDate(new Date());
            Thread thairplane = new Thread(airplane);
            airplane.setName("Airplane " + thairplane.getId());
            thairplane.start();

            try {
                TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
            number++;
        }
        atc.setclosingTime();

    }

}