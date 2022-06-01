
public class Main {
	public static void main(String[] args) {
		Bshop shop = new Bshop();

		CustomerGenerator cg = new CustomerGenerator(shop);
		Barber barber1 = new Barber(shop, cg, 1);
		Barber barber2 = new Barber(shop, cg, 2);
		Thread thbarber1 = new Thread(barber1);
		Thread thbarber2 = new Thread(barber2);
		Thread thcg = new Thread(cg);
		thcg.start();
		thbarber1.start();
		thbarber2.start();
	}

}
