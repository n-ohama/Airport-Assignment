
public class Main {

	public static void main(String[] args) {

		Airport airport = new Airport();

		AirplaneGenerator ag = new AirplaneGenerator(airport);

		Thread thAg = new Thread(ag);
		thAg.start();

	}

}
