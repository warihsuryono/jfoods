package AmandaNurOktaviani.JFood;
/**
 * Write a description of class Promo here.
 *
 * @author (Amanda)
 * @version (12/03/2020)
 */
public class Promo
{
    // instance variables
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;

    /**
     * Constructor for objects of class Promo
     */
    public Promo(int id, String code, int discount, int minPrice, boolean active)
    {
      this.id = id;
      this.code = code;
      this.discount = discount;
      this.minPrice = minPrice;
      this.active = active;
    }

    /**
     * An example of a method - replace this comment with your own
     * @return id
     */
    public int getId()
    {
        return id;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public int getDiscount()
    {
        return discount;
    }
    
    public int getMinPrice()
    {
        return minPrice;
    }
    
    public boolean getActive()
    {
        return active;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public void setDiscount(int discount)
    {
        this.discount = discount;
    }
    
    public void setMinPrice(int minPrice)
    {
        this.minPrice = minPrice;
    }
    
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    /**public void printData()
    {
        System.out.println("ID: " + id);
        System.out.println("Code: " + code);
        System.out.println("Discount: " + discount);
        System.out.println("Syarat dan Ketentuan: " + minPrice);
        System.out.println("Status: " + active);
    }*/
    
    public String toString()
    {
        return "Id = " + id + "\n" + "Code = " + code + "\n" + "Discount = " + discount + "\n" + "MinPrice = " + minPrice + "\n" + "Active status = " + active + "\n"; 
    }
}
