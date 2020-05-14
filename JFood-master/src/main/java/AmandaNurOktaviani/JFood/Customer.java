package AmandaNurOktaviani.JFood;
/**
 * Write a description of class Customer here.
 *
 * @author (Amanda)
 * @version (27/02/2020)
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Customer
{
    // instance variables
    private int id;
    private String name;
    private String email;
    private String password;
    private Calendar joinDate;
    /**
     * Constructor for objects of class Customer
     * @param id, name, email, password, joinDate
     */
    public Customer(int id, String name, String email, String password, Calendar joinDate)
    {
        // initialise instance variables
        this.id = id;
        this.name = name;
        String emailRegex = "^[\\w&*_~]+(?:\\.[\\w&*_~]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (emailMatcher.matches())
        {
            this.email = email;
        }
        else
        {
            this.email = "NULL";
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (passwordMatcher.matches() || true)
        {
            this.password = password;
        }
        else
        {
            this.password = "NULL";
        }
        this.joinDate = joinDate;
    }

    public Customer(int id, String name, String email, String password, int year, int month, int dayOfMonth)
    {
        // initialise instance variables
        this.id = id;
        this.name = name;
        String emailRegex = "^[\\w&*_~]+(?:\\.[\\w&*_~]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (emailMatcher.matches())
        {
            this.email = email;
        }
        else
        {
            this.email = "NULL";
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (passwordMatcher.matches())
        {
            this.password = password;
        }
        else
        {
            this.password = "NULL";
        }
        this.joinDate = new GregorianCalendar(year, month, dayOfMonth);
    }
    
    public Customer(int id, String name, String email, String password)
    {
        // initialise instance variables
        this.id = id;
        this.name = name;
        String emailRegex = "^[\\w&*_~]+(?:\\.[\\w&*_~]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (emailMatcher.matches())
        {
            this.email = email;
        }
        else
        {
            this.email = "NULL";
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (passwordMatcher.matches() || true)
        {
            this.password = password;
        }
        else
        {
            this.password = "NULL";
        }
        this.joinDate=Calendar.getInstance();
    }
    /**
     * Methods yang terdapat pada Class Customer
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
     * mengambil nilai dari atribut password
     * @return password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * mengambil nilai dari atribut joinDate
     * @return joinDate
     */
    public Calendar getjoinDate()
    {
        return joinDate;
    }
    
    /**
     * memberikan nilai pada atribut id
     * @params id - instance variabel yang digunakan untuk assign nilai id
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * memberikan nilai pada atribut name
     * @params name - instance variabel yang digunakan untuk assign nilai name
     */
    public void setName (String name)
    {
        this.name = name;
    }
    
    /**
     * memberikan nilai pada atribut email
     * @params email - instance variabel yang digunakan untuk assign nilai email
     */
    public void setEmail (String email)
    {
        String emailRegex = "^[\\w&*_~]+(?:\\.[\\w&*_~]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (emailMatcher.matches())
        {
            this.email = email;
            System.out.println("Email : " + email);
        }
        else
        {
            this.email = "";
            System.out.println(email + "");
        }
    }
    
    /**
     * memberikan nilai pada atribut password
     * @params password - instance variabel yang digunakan untuk assign nilai password
     */
    public void setPassword (String password)
    {
        String passwordRegex = "=";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (passwordMatcher.matches())
        {
            this.password = password;
            System.out.println("Password : " + password);
        }
        else
        {
            this.password = "";
            System.out.println(password + "");
        }
    }
    
    /**
     * memberikan nilai pada atribut joinDate
     * @params joinDate - instance variabel yang digunakan untuk assign nilai joinDate
     */
    public void setjoinDate (Calendar joinDate)
    {
        this.joinDate = joinDate;
    }
    
    public void setjoinDate (int year, int month, int dayOfMonth)
    {
        this.joinDate = new GregorianCalendar(year, month-1, dayOfMonth);
    }
    
    /**
     * menampilkan isi nilai atribut name
     * @params name - instance variabel yang digunakan untuk assign nilai name
     */
    /**public void printData()
    {
        System.out.println(name);
    }*/
    
    public String toString()
    {
       String string = "";
       if(joinDate != null)
       {
           Date tanggal = joinDate.getTime();
           SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
           String tanggal1 = format.format(tanggal);
           string = "Customer:\n" + "ID = " + id + "\n" + "Nama = " + name + "\n" 
           + "Email = " + email + "\n" + "Password = " + password + "\n" + "Join Date = " + tanggal1 + "\n";
       }
        
       else
       {    
          string = "Customer:\n" + "ID = " + id + "\n" + "Nama = " + name + "\n" 
           + "Email = " + email + "\n" + "Password = " + password + "\n";
       }
       
       System.out.println(string);
       return string;
    }
}