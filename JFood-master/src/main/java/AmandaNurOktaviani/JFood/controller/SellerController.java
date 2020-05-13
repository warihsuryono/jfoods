package AmandaNurOktaviani.JFood.controller;

import AmandaNurOktaviani.JFood.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/seller")
@RestController

public class SellerController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ArrayList<Seller> getAllSeller() {
        ArrayList<Seller> sellers = new ArrayList<>();
        sellers = DatabaseSeller.getSellerDatabase();
        return sellers;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Seller getSellerById(@PathVariable int id) {
        Seller sellers = null;
        try {
            sellers = DatabaseSeller.getSellerById(id);
        } catch (SellerNotFoundException e) {
            e.getMessage();
            return null;
        }
        return sellers;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Seller addSeller(@RequestParam(value = "name") String name,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "phoneNumber") String phoneNumber,
                            @RequestParam(value = "province") String province,
                            @RequestParam(value = "description") String description,
                            @RequestParam(value = "city") String city)
    {
        Seller seller = new Seller(DatabaseSeller.getLastId()+1, name, email, phoneNumber, new Location(province, city, description));
        if (DatabaseSeller.addSeller(seller))
        {
            return seller;
        }
        return null;
    }
}
