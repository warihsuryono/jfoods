package AmandaNurOktaviani.JFood;
import java.util.ArrayList;
/**
 * Write a description of class DatabasePromo here.
 *
 * @author (Amanda)
 * @version (12/03/2020)
 */
public class DatabasePromo
{
    // instance variables
    private static final ArrayList<Promo> PROMO_DATABASE = new ArrayList<Promo>();
    private static int lastId = 0;

    public static ArrayList<Promo> getPromoDatabase()
    {
        return PROMO_DATABASE;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static Promo getPromoById(int id) throws PromoNotFoundException
    {
        for(Promo promo:PROMO_DATABASE)
        {
            if(promo.getId()==id)
            {
                return promo;
            }
        }
        throw new PromoNotFoundException(id);
    }

    public static Promo getPromoByCode(String code)
    {
        for(Promo promo:PROMO_DATABASE)
        {
            if(promo.getCode()==code)
            {
                return promo;
            }
        }
        return null;
    }

    public static boolean addPromo(Promo promo) throws PromoCodeAlreadyExistsException
    {
        for (Promo _promo:PROMO_DATABASE)
        {
            if (_promo.getCode().equals(promo.getCode()))
            {
                throw new PromoCodeAlreadyExistsException(promo);
            }
        }
        PROMO_DATABASE.add(promo);
        lastId=promo.getId()+1;
        return true;
    }

    public static boolean activatePromo(int id)
    {
        for(Promo promo:PROMO_DATABASE)
        {
            if(promo.getId()==id)
            {
                promo.setActive(true);
                return true;
            }
        }
        return false;
    }

    public static boolean deactivatePromo(int id)
    {
        for(Promo promo:PROMO_DATABASE)
        {
            if(promo.getId()==id)
            {
                promo.setActive(false);
                return false;
            }
        }
        return true;
    }

    public static boolean removePromo(int id) throws PromoNotFoundException
    {
        for(int i=0; i<PROMO_DATABASE.size(); i++)
        {
            Promo promo = PROMO_DATABASE.get(i);
            if(promo.getId()==id)
            {
                PROMO_DATABASE.remove(i);
                return true;
            }
        }
        throw new PromoNotFoundException(id);
    }
}
