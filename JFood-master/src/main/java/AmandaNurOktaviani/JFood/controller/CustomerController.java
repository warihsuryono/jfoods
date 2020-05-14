package AmandaNurOktaviani.JFood.controller;

import AmandaNurOktaviani.JFood.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @RequestMapping("")
    public String indexPage(@RequestParam(value="name", defaultValue="world") String name) {
        return "Hello " + name;
    }

    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        Customer customer = null;
        try {
            customer = DatabaseCustomerPostgre.getCustomerById(id);
        } catch (CustomerNotFoundException e) {
            e.getMessage();
            return null;
        }
        return customer;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Customer registerCustomer(@RequestParam(value="name") String name,
                                @RequestParam(value="email") String email,
                                @RequestParam(value="password") String password)
    {
        Customer customer = new Customer(DatabaseCustomerPostgre.getLastId()+1, name, email, password);
        try {
//            DatabaseCustomer.addCustomer(customer);
            if(!DatabaseCustomerPostgre.insertCustomer(customer))
                throw new EmailAlreadyExistsException(customer);
        } catch (EmailAlreadyExistsException e) {
            e.getMessage();
            return null;
        }
        return customer;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Customer loginCustomer(@RequestParam(value="email") String email,
                                  @RequestParam(value="password") String password)
    {
        Customer customer;
        customer = DatabaseCustomerPostgre.getCustomerLogin(email, password);
        return customer;
    }
}