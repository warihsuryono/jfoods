package AmandaNurOktaviani.JFood;
/**
 * Enumeration class FoodCategory - write a description of the enum class here
 *
 * @author (Amanda)
 * @version (05/03/2020)
 */
public enum FoodCategory
{
    Beverages ("Beverages"), 
    Coffee ("Coffee"), 
    Western ("Western"), 
    Snacks ("Snacks"), 
    Rice ("Rice"), 
    Noodles ("Noodles"), 
    Bakery ("Bakery"), 
    Japanese ("Japanese");
    
    private String name;
    
    private FoodCategory(String name)
    {
        this.name = name;
    }
    
    public String toString(){
        return name();
    }
}
