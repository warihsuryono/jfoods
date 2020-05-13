package AmandaNurOktaviani.JFood;
public class PriceCalculator implements Runnable
{
    private Invoice invoice;

    public PriceCalculator(Invoice invoice)
    {
        this.invoice=invoice;
    }

    @Override
    public void run()
    {
        System.out.print("Calculating Invoice ID: " + invoice.getId());
        invoice.setTotalPrice();
        System.out.println("\nFinish Calculating Invoice ID: " + invoice.getId());
    }
}
