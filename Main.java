
public class Main {

	public static void main(String[] args) {

		Airport airport = new Airport();
		ATC atc = new ATC(airport);

		AirplaneGenerator ag = new AirplaneGenerator(airport, atc);
		Thread thAtc = new Thread(atc);

		Thread thAg = new Thread(ag);
		thAg.start();
		thAtc.start();

	}

}
