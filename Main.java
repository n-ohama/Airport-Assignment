
public class Main {
	public static void main(String[] args) {
        Bshop shop = new Bshop();
        
        CustomerGenerator cg = new CustomerGenerator(shop);
        Barber barber1 = new Barber(shop, cg);
        
        Thread thbarber1 = new Thread(barber1);
        Thread thcg = new Thread(cg);
        thcg.start();
        thbarber1.start();

        // Clock clock = new Clock(cg, barber);
        // clock.start();
	}

}
