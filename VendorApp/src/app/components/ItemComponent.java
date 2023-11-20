package app.components;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Vendor;
import app.entities.Item;
import app.rest.controllers.ItemDto;
import app.repositories.*;

@Component
public class ItemComponent {

    @Autowired
    private ItemRepository itemRepo;
    
    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    public Item newItem(ItemDto itemDto) {
        Item i = new Item();
        i.setName(itemDto.getName());
        i.setPrice(itemDto.getPrice());
        i.setDescription(itemDto.getDescription());
        Vendor v = vendorRepo.getOne(itemDto.getVendorId());
        i.setVendor(v);

        i = itemRepo.save(i);

        return i;
    }

    public String toggleItemStock(Long itemId) {
        Item item = itemRepo.findById(itemId).orElse(null);
        item.setInStock(!item.isInStock());
        itemRepo.save(item);

        return "Item " + itemId + " stock toggled";
    }
    
    public List<Item> viewItems(Long vendorId)
    {
    	Vendor v = vendorRepo.getOne(vendorId);
    	return itemRepo.findByVendor(v);
    }
    
    public Item editItem(Long itemId, String itemName, double itemPrice, String itemDescription)
    {
    	Item i = itemRepo.getOne(itemId);
    	i.setName(itemName);
    	i.setDescription(itemDescription);
    	i.setPrice(itemPrice);
    	i = itemRepo.save(i);
    	return i;
    }

}