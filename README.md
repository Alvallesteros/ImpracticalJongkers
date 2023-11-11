ok so i said stuff in the gc but basically my vision rn is

# UserApp:
- Customer, Order, Item, OrderItem
- updates all of those entities
- retrofit for communicating order to orderapp -> vendorapp
- retrofit for getting all items associated with a vendor

# VendorApp:
- Vendor, Cafeteria, Item(?)
- Item is here to link Item to Vendor
- retrofit for updating order status

# OrderApp:
- in-between for the two apps
- retrofit central

# TODO:
- all of orderapp
- figure out significance of communicating order thru retrofit
- decide if double item