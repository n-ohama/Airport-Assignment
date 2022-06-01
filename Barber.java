class Barber implements Runnable {
    Bshop shop;
    CustomerGenerator customerGenerator;
    int unique;

    public Barber(Bshop shop, CustomerGenerator customerGenerator, int unique) {
        this.shop = shop;
        this.customerGenerator = customerGenerator;
        this.unique = unique;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("Barber started.....");
        while (!customerGenerator.closingTime) {
            shop.cutHair();
        }
        if (customerGenerator.closingTime) {
            if (unique == 1) {
                while (shop.listCustomer.size() > 0) {
                    shop.cutHair();
                }
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