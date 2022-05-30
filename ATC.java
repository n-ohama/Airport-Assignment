class ATC implements Runnable {

    Airport airport;
    public boolean closingTime = false;

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

        if (closingTime) {
            while (airport.waitingList.size() > 0) {// check if there are any customers in the shop
                System.out.println("Looks like there's " + airport.waitingList.size() + "Airplanes left. Next!");
                airport.flyPlane();

            }

            while (airport.docks.size() > 0) {// check if there are any customers in the shop
                System.out.println("Looks like there's " + airport.docks.size() + "Airplanes left. Next!");
                airport.flyPlane();

            }

            try {
                Thread.sleep(5000);
                System.out
                        .println(
                                "Looks like there's " + airport.waitingList.size() + "Airplanes left. Going home now.");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

    }

    public synchronized void setclosingTime() {// 6台作ったら終了
        closingTime = true;
        System.out.println("Closing? OK.");
    }

}
