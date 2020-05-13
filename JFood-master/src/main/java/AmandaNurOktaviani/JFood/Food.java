package AmandaNurOktaviani.JFood;
/**
 * Write a description of class Food here.
 *
 * @author (Amanda)
 * @version (27/02/2020)
 */
public class Food
{
    // instance variables
    private int id;
    private String name;
    private Seller seller;
    private int price;
    private FoodCategory category;

    /**
     * Constructor for objects of class Food
     * param id, name, seller, price, category
     */
    public Food(int id, String name, Seller seller, int price, FoodCategory category)
    {
        this.id = id;
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.category = category;
    }

    /**
     * Methods yang terdapat pada Class Food
     * Melakukan pemberian nilai dan pengambilan nilai value
     * Pengambilan nilai menggunakan getVariable()
     * Pemberian nilai dengan setVariable()
     */
    
    /**
     * mengambil nilai dari atribut id
     * @return id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * mengambil nilai dari atribut name
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * mengambil nilai dari atribut seller
     * @return seller
     */
    public Seller getSeller()
    {
        return seller;
    }
    
    /**
     * mengambil nilai dari atribut price
     * @return price
     */
    public int getPrice()
    {
        return price;
    }
    
    /**
     * mengambil nilai dari atribut category
     * @return category
     */
    public FoodCategory getCategory()
    {
        return category;
    }
    
    /**
     * memberikan nilai pada atribut id
     * @param id - instance variabel yang digunakan untuk assign nilai id
     */
    public void setId (int id)
    {
        this.id = id;
    }
    
    /**
     * memberikan nilai pada atribut name
     * @param name - instance variabel yang digunakan untuk assign nilai name
     */
    public void setName (String name)
    {
        this.name = name;
    }
    
    /**
     * memberikan nilai pada atribut seller
     * @param seller - instance variabel yang digunakan untuk assign nilai seller
     */
    public void setSeller (Seller seller)
    {
        this.seller = seller;
    }
    
    /**
     * memberikan nilai pada atribut price
     * @param price - instance variabel yang digunakan untuk assign nilai price
     */
    public void setPrice (int price)
    {
        this.price = price;
    }
    
    /**
     * memberikan nilai pada atribut category
     * @param category - instance variabel yang digunakan untuk assign nilai category
     */
    public void setCategory (FoodCategory category)
    {
        this.category = category;
    }
    
    /**
     * fungsi untuk mengeprint data
     */
    //public void printData()
    //{
      //  System.out.println("==========FOOD==========");
      //  System.out.println("ID: " + id);
      //  System.out.println("Name: " + name);
      //  System.out.println("Seller: " + seller.getName());
      //  System.out.println("City: " + seller.getLocation().getCity());
      //  System.out.println("Price: " + price);
      //  System.out.println("Category: " + category);
    //}
    
    public String toString()
    {
        return "Id = " + id + "\n" + "Name = " + name + "\n" + "Seller = " + seller.getName() + "\n" + "City = " + seller.getLocation().getCity() + "\n" +  "Price = " + price + "\n" + "Category = " + category + "\n";
    }
}
