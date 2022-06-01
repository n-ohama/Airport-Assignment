class Dock implements Runnable {
    ATC atc;
    AirplanGenerator airplanGenerator;
    int unique;

    public Dock(ATC atc, AirplanGenerator airplanGenerator, int unique) {
        this.atc = atc;
        this.airplanGenerator = airplanGenerator;
        this.unique = unique;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("Start Simulation!!!");
        while (!airplanGenerator.closingTime) {
            atc.cutHair(unique);
        }
        if (airplanGenerator.closingTime) {
            while (atc.listAirplan.size() > 0) {
                atc.cutHair(unique);
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