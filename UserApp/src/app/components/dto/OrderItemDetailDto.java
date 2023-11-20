package app.components.dto;

public class OrderItemDetailDto {
    public class ItemDto {
        public String name;
        public Double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "ItemDto{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public Double price;
    public int qty;

    public ItemDto item;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderItemDetailDto{" +
                "price=" + price +
                ", qty=" + qty +
                ", item=" + item +
                '}';
    }
}
