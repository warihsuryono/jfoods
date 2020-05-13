package AmandaNurOktaviani.JFood;
/**
 * Write a description of class Location here.
 *
 * @author (Amanda)
 * @version (27/02/2020)
 */
public class Location
{
    // instance variables
    private String province;
    private String description;
    private String city;

    /**
     * Constructor for objects of class Location
     * @param city, province, description
     */
    public Location(String city, String province, String description)
    {
        this.city = city;
        this.province = province;
        this.description = description;
    }

    /**
     * Methods yang terdapat pada Class Location
     * Melakukan pemberian nilai dan pengambilan nilai value
     * Pengambilan nilai menggunakan getVariable()
     * Pemberian nilai dengan setVariable()
     */
    
    /**
     * mengambil nilai dari atribut id
     * @return province
     */
    public String getProvince()
    {
        return province;
    }
    
    /**
     * mengambil nilai dari atribut city
     * @return city
     */
    public String getCity()
    {
        return city;
    }
    
    /**
     * mengambil nilai dari atribut description
     * @return description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * memberikan nilai pada atribut province
     * @param province - instance variabel yang digunakan untuk assign nilai province
     */
    public void setProvince (String province)
    {
        this.province = province;
    }
    
    /**
     * memberikan nilai pada atribut city
     * @param city - instance variabel yang digunakan untuk assign nilai city
     */
    public void setCity (String city)
    {
        this.city = city;
    }
    
    /**
     * memberikan nilai pada atribut description
     * @param description - instance variabel yang digunakan untuk assign nilai description
     */
    public void setDescription (String description)
    {
        this.description = description;
    }
    
    /**
     * menampilkan isi nilai atribut province
     * @params name - instance variabel yang digunakan untuk assign nilai province
     */
    /**public void printData()
    {
        System.out.println(province);
    }*/
    
    public String toString()
    {
        return "Province = " + province + "\n" + "City = " + city + "\n" + "Description = " + description + "\n";        
    }
}
