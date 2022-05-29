import java.util.Date;

class Airplane implements Runnable {
  String name;
  Date date;
  Airport airport;

  public Airplane(Airport airport)
  {
    this.airport = airport;
  }

  public String getName() {
    return name;
  }

  public Date getDate() {
    return date;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void run()
  {
    landing();
  }

  private synchronized void landing()
  {
    airport.putInAirport(this);
  }
}
