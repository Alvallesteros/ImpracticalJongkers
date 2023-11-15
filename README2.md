# AJMA (Atenean Junction for Meal Assistance)

## Ports
Each app runs on the following ports:

1. UserApp - 9999
2. VendorApp - 9998
3. OrderApp - 9997

## Functions / Microservices

### UserApp
This is where most of the user-side interactivity takes place. **Placing orders, creating new
customer accounts, viewing order history** can be found here.   

### VendorApp
VendorApp is the "admin-side" of the entire system. **New cafeterias, vendors, items, and more
can be found here.** It contains most of the logic found in organizing the orders.

### OrderApp
The middleman between the two, OrderApp sends text messages to customers to notify them of any
changes to their orders.

## API Endpoints

### UserApp

#### Orders
- `/order/new`
    - Takes in a **ParamsDto** object.
    - Returns an **OrderReceivedDto**.
- `/order/add`
    - Takes in **APPLICATION_FORM_URLENCODED**.
    - FormParameters: `orderCode`, `itemId`, `qty`. 
    - Returns an **OrderReceivedDto**.
- `/order/edit`
    - Takes in **APPLICATION_FORM_URLENCODED**.
    - FormParameters: `orderCode`, `status`
    - Edits the status of the order.

#### Customers
- `/customer/register`
    - Takes in a **CustomerDto** object.
    - Returns **Customer**.
- `/{customerId}/orders`
    - Takes in PathParam `customerId`.
    - Returns an **array** of orders with `order_customerId = {customerId}`.
