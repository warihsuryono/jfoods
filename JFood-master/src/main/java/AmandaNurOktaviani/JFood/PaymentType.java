package AmandaNurOktaviani.JFood;
/**
 * Enumeration class PaymentType - write a description of the enum class here
 *
 * @author (Amanda)
 * @version (05/03/2020)
 */
public enum PaymentType
{
    Cashless ("Cashless"),
    Cash ("Cash");
    
    private String name;
    
    private PaymentType(String name)
    {
        this.name = name;
    }
    
    public String toString()
    {
        return name();
    }
}
