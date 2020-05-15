package AmandaNurOktaviani.JFood.controller;

import AmandaNurOktaviani.JFood.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;

@RequestMapping("/promo")
@RestController

public class PromoController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ArrayList<Promo> getAllPromo() {
        ArrayList<Promo> promos = new ArrayList<>();
        promos = DatabasePromoPostgre.getPromoDatabase();
        return promos;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public Promo getPromoByCode(@PathVariable String code)
    {
        Promo promos = null;
        promos = DatabasePromoPostgre.getPromoByCode(code);
        return promos;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Promo addPromo(@RequestParam(value = "code") String code,
                            @RequestParam(value = "discount") int discount,
                            @RequestParam(value = "minPrice") int minPrice,
                            @RequestParam(value = "status") Boolean active)
    {
        try {
            Promo promos = new Promo(DatabasePromoPostgre.getLastId() + 1, code, discount, minPrice, active);
            if (DatabasePromoPostgre.addPromo(promos)) {
                return promos;
            }
        }
        catch (PromoCodeAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
