
public class Main {
	public static void main(String[] args) {
		ATC atc = new ATC();

		AirplanGenerator ag = new AirplanGenerator(atc);
		Dock dock1 = new Dock(atc, ag, 1);
		Dock dock2 = new Dock(atc, ag, 2);
		Thread thdock1 = new Thread(dock1);
		Thread thdock2 = new Thread(dock2);
		Thread thag = new Thread(ag);
		thag.start();
		thdock1.start();
		thdock2.start();
	}

}
