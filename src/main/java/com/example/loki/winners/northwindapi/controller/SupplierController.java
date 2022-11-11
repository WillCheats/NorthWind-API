package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.model.entity.Supplier;
import com.example.loki.winners.northwindapi.model.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SupplierController {

    public static SupplierController controller;
    private SupplierRepository repository;

    @Autowired
    public SupplierController(SupplierRepository repository) {
        this.repository = repository;
        controller = WebMvcLinkBuilder.methodOn(this.getClass());
    }

    @GetMapping("/supplier/all")
    public List<Supplier> getAllSupplier() {

        return repository.findAll();
    }

    @GetMapping("/supplier/{id}")
    public EntityModel<Supplier> getSupplierById(@PathVariable int id) {
        Supplier supplier = repository.findById(id).get();
        EntityModel<Supplier> entityModel = EntityModel.of(supplier);
        WebMvcLinkBuilder prev = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getSupplierById(id - 1));
        WebMvcLinkBuilder next = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getSupplierById(id + 1));
        WebMvcLinkBuilder all = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllSupplier());
        entityModel.add(prev.withRel("previous-supplier"));
        entityModel.add(next.withRel("next-supplier"));
        entityModel.add(all.withRel("all-suppliers"));
        return entityModel;
    }

    @GetMapping("/supplier/country/{country}")
    public List<Supplier> getSupplierByCountry(@PathVariable String country) {

        return repository.getAllByCountry(country);
    }

    @GetMapping("/supplier/company_name/{companyName}")
    public List<Supplier> getSupplierByCompanyName(@PathVariable String companyName) {

        return repository.getAllByCompanyName(companyName);
    }

    @GetMapping("/supplier/contact_name/{ContactName}")
    public List<Supplier> getSupplierByContactName(@PathVariable String contactName) {

        return repository.getAllByContactName(contactName);
    }

    @GetMapping("/supplier/contact_title/{ContactTitle}")
    public List<Supplier> getSupplierByContactTitle(@PathVariable String contactTitle) {

        return repository.getAllByContactTitle(contactTitle);
    }

    @GetMapping("/supplier/address/{Address}")
    public List<Supplier> getSupplierByAddress(@PathVariable String address) {

        return repository.getAllByAddress(address);
    }

    @GetMapping("/supplier/city/{City}")
    public List<Supplier> getSupplierByCity(@PathVariable String city) {

        return repository.getAllByCity(city);
    }

    @GetMapping("/supplier/region/{Region}")
    public List<Supplier> getSupplierByRegion(@PathVariable String region) {

        return repository.getAllByRegion(region);
    }

    @GetMapping("/supplier/postal_code/{PostalCode}")
    public List<Supplier> getSupplierByPostalCode(@PathVariable String postalCode) {

        return repository.getAllByPostalCode(postalCode);
    }

    @GetMapping("/supplier/phone/{phone}")
    public List<Supplier> getSupplierByPhone(@PathVariable String phone) {

        return repository.getAllByPhone(phone);
    }

    @GetMapping("/supplier/fax/{fax}")
    public List<Supplier> getSupplierByFax(@PathVariable String fax) {

        return repository.getAllByFax(fax);
    }

    @PatchMapping("/supplier/edit/contact_name/{id}")
    public void editSupplierContactName(@PathVariable int id, String newName) {

        repository.findById(id).get().setContactName(newName);
    }

    @PatchMapping("/supplier/edit/contact_title/{id}")
    public void editSupplierContactTitle(@PathVariable int id, String newTitle) {

        repository.findById(id).get().setContactTitle(newTitle);
    }

    @PostMapping("/supplier/new")
    public String createNewSupplier (@RequestBody Supplier supplier) {

        if (!supplierExists(supplier.getId())) {
            repository.save(supplier);
            return HttpStatus.OK.toString();
        }
        return HttpStatus.BAD_REQUEST.toString();
    }

    public boolean supplierExists(int id) {

        return repository.existsById(id);
    }
}
