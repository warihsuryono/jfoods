package AmandaNurOktaviani.JFood.controller;

import AmandaNurOktaviani.JFood.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;

@RequestMapping("/invoice")
@RestController

public class InvoiceController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ArrayList<Invoice> getAllInvoice() {
        ArrayList<Invoice> invoices = new ArrayList<>();
        invoices = DatabaseInvoicePostgre.getInvoiceDatabase();
        return invoices;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id) {
        Invoice invoices = null;
        try {
            invoices = DatabaseInvoicePostgre.getInvoiceById(id);
        } catch (InvoiceNotFoundException e) {
            e.getMessage();
            return null;
        }
        return invoices;
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ArrayList<Invoice> getInvoiceByCustomer(@PathVariable int customerId) {
        ArrayList<Invoice> invoices = new ArrayList<>();
        try {
            invoices = DatabaseInvoicePostgre.getInvoiceByCustomer(customerId);
        } catch (CustomerNotFoundException e) {
            e.getMessage();
        }
        return invoices;
    }

    @RequestMapping(value = "/invoiceStatus/{id}", method = RequestMethod.PUT)
    public Invoice changeInvoiceStatus(@RequestParam(value = "ID") int id,
                                       @RequestParam(value = "Invoice") InvoiceStatus status)
    {
        Invoice invoice= null;
        if (invoice.getId() == id && invoice.getInvoiceStatus() == InvoiceStatus.Ongoing) {
            DatabaseInvoicePostgre.changeInvoiceStatus(id, status);
            return invoice;
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Invoice removeInvoice(@PathVariable int id)
    {
        Invoice invoice = null;
        try
        {
            invoice = DatabaseInvoicePostgre.getInvoiceById(id);
            DatabaseInvoicePostgre.removeInvoice(id);
        }
        catch(InvoiceNotFoundException i)
        {
            i.getMessage();
            return null;
        }
        return invoice;
    }

    @RequestMapping(value = "/createCashInvoice", method = RequestMethod.POST)
    public Invoice addCashInvoice(@RequestParam(value="foodId") ArrayList<Integer> foodIdList,
                                  @RequestParam(value="customerId") int customerId,
                                  @RequestParam(value="deliveryFee") int deliveryFee)
    {
        ArrayList<Food> foods = new ArrayList<>();
        for (int food : foodIdList) {
            try {
                foods.add(DatabaseFoodPostgre.getFoodById(food));
            } catch (FoodNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Invoice invoice = new CashInvoice(DatabaseInvoicePostgre.getLastId()+1, foods,
                    DatabaseCustomerPostgre.getCustomerById(customerId), deliveryFee);
            DatabaseInvoicePostgre.addInvoice(invoice);
            return invoice;
        } catch (CustomerNotFoundException | OngoingInvoiceAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/createCashlessInvoice", method = RequestMethod.POST)
    public Invoice addCashlessInvoice(@RequestParam(value="foodId") ArrayList<Integer> foodIdList,
                                      @RequestParam(value="customerId") int customerId,
                                      @RequestParam(value="promoCode") String promoCode)
    {
        ArrayList<Food> foods = new ArrayList<>();
        for (int food : foodIdList) {
            try {
                foods.add(DatabaseFoodPostgre.getFoodById(food));
            } catch (FoodNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Invoice invoice = new CashlessInvoice(DatabaseInvoicePostgre.getLastId()+1, foods,
                    DatabaseCustomerPostgre.getCustomerById(customerId), DatabasePromo.getPromoByCode(promoCode));
            DatabaseInvoicePostgre.addInvoice(invoice);
            return invoice;
        } catch (CustomerNotFoundException | OngoingInvoiceAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
