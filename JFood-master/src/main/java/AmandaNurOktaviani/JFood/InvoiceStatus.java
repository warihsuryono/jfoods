package AmandaNurOktaviani.JFood;
/**
 * Enumeration class InvoiceStatus - write a description of the enum class here
 *
 * @author (Amanda)
 * @version (06/03/2020)
 */
public enum InvoiceStatus
{
    Ongoing ("Ongoing"),
    Finished ("Finished"),
    Cancelled ("Cancelled");
    
    private String name;
    
    private InvoiceStatus(String name)
    {
        this.name = name;
    }
    
    public String toString()
    {
        return name;
    }
}
