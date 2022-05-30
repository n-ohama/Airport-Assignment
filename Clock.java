public class Clock extends Thread {

    private ATC atc;

    public Clock(ATC atc) {

        this.atc = atc;
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

        atc.setclosingTime();
    }

}
