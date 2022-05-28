import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Bshop
{
    public int nchair = 5;
    
    List<Customer> listCustomer;
 
    public Bshop()
    {
       
        listCustomer = new LinkedList<Customer>();
    }
 
    public void cutHair()
    {
        Customer customer;
        synchronized (listCustomer)
        {
 
            while(listCustomer.size()==0)
            {
                System.out.println("Barber is waiting for customer.");
                try
                {
                    listCustomer.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
            System.out.println("Barber found a customer in the queue.");
            customer = (Customer)((LinkedList<?>)listCustomer).poll();
        }
        long duration=0;
        try
        {    
            System.out.println("Cutting hair of Customer : "+customer.getName());
            duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        System.out.println("Completed cutting hair of Customer : "+customer.getName() + " in "+duration+ " seconds.");
        System.out.println("Customer "+customer.getName()+" exits...");
    }
 
    public void add(Customer customer)
    {
        System.out.println("Customer : "+customer.getName()+ " entering the shop at "+customer.getInTime());
 
        synchronized (listCustomer)
        {
            if(listCustomer.size() == nchair)
            {
            	
            	System.out.println("No chair available for customer "+customer.getName());
                System.out.println("Customer "+customer.getName()+"Exits...");
                return ;
            }
 
            ((LinkedList<Customer>)listCustomer).offer(customer);
            System.out.println("Customer : "+customer.getName()+ " got the chair.");
           
            if(listCustomer.size()==1) {
                listCustomer.notify();
            }
        }
    }
}