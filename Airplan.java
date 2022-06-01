import java.util.Date;

class Airplan implements Runnable {
    String name;
    Date inTime;
    ATC atc;

    public Airplan(ATC atc) {
        this.atc = atc;
    }

    public String getName() {
        return name;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public void run() {
        goForHairCut();

    }

    private synchronized void goForHairCut() {
        atc.add(this);
    }
}