package app.components;

import java.util.List;

import app.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Vendor;
import app.components.dto.VendorDto;
import app.repositories.*;

@Component
public class VendorComponent {

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private CafeteriaRepository cafRepo;

    public Vendor newVendor(VendorDto vendorDto) {
        Vendor v = new Vendor();

        v.setName(vendorDto.getName());
        v.setOwner(vendorDto.getOwner());
        v.setDescription(vendorDto.getDescription());

        v = vendorRepo.save(v);

        return v;
    }

    public List<Vendor> viewVendors() {
        return vendorRepo.findAll();
    }

    public List<Vendor> cafeteriaFilter(String name) {

        return vendorRepo.findAllByCafeteria(cafRepo.findByName(name));

    }

    public String removeVendor(int vendorId) {
        return "Deleted Vendor " + vendorRepo.deleteById(vendorId);
    }

    public List<Item> viewVendorItems(Long vendorId) {
        return vendorRepo.findById(vendorId).orElse(null).getItems();
    }

}