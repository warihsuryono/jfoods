package AmandaNurOktaviani.JFood;
/**
 * Write a description of class Seller here.
 *
 * @author (Amanda)
 * @version (27/02/2020)
 */
public class Seller
{
    // instance variables
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

    /**
     * Constructor for objects of class Seller
     * @param id, name, email, phoneNumber, location
     */
    public Seller(int id, String name, String email, String phoneNumber, Location location)
    {
        this.id  = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }
    
    /**
     * Methods yang terdapat pada Class Seller
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
     * mengambil nilai dari atribut email
     * @return email
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * mengambil nilai dari atribut phoneNumber
     * @return phoneNumber
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    /**
     * mengambil nilai dari atribut location
     * @return location
     */
    public Location getLocation()
    {
        return location;
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
     * memberikan nilai pada atribut email
     * @param email - instance variabel yang digunakan untuk assign nilai email
     */
    public void setEmail (String email)
    {
        this.email = email;
    }
    
    /**
     * memberikan nilai pada atribut password
     * @param phoneNumber - instance variabel yang digunakan untuk assign nilai password
     */
    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * memberikan nilai pada atribut location
     * @param location - instance variabel yang digunakan untuk assign nilai location
     */
    public void setLocation (Location location)
    {
        this.location = location;
    }
    
    /**
     * menampilkan isi nilai atribut name
     * @params name - instance variabel yang digunakan untuk assign nilai name
     */
    /**public void printData()
    {
        System.out.println(name);
    }
    */
   
    public String toString()
    {
        return "Id = " + id + "\n" + "Name = " + name + "\n" + "Email = " + email + "\n" + "PhoneNumber = " + phoneNumber + "\n" + "Location = " + location + "\n";
    }
}
