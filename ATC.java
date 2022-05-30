class ATC implements Runnable {

    Airport airport;

    public ATC(Airport airport) {
        this.airport = airport;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("Barber started.....");
        int number = 0;
        while (number < 6) {
            airport.flyPlane();
            number++;
        }

    }

}
