package app.components;

import java.util.List;

import app.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Vendor;
import app.entities.Item;
import app.rest.controllers.ItemDto;
import app.repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

    public String removeItem(Long itemId) {
        List<OrderItem> orderItems = orderItemRepo.findByItemId(itemId);
//    	itemRepo.deleteById(itemId);
        return "Deleted Item " + itemId + orderItems;
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