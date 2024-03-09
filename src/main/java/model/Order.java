package model;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements ItemManager {
    private final String orderID;
    private final ArrayList<MenuItem> orderItemList = new ArrayList<>();
    private final static double SERVICE_CHARGE = 0.1;
    private final static double GST = 0.09;
    public Order() {;
        this.orderID = "ORDER" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Override
    public void add(MenuItem item) {
        this.orderItemList.add(item);
    }

    @Override
    public void remove(int index) {
        try {
            this.orderItemList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }

    /**
     * Remove all items from the order list by its ID
     * @param ID the ID of the item to be removed
     */
    @Override
    public void remove(String ID) {
        this.orderItemList.removeIf(x -> x.getID().equals(ID));
    }

    public String getID() {
        return orderID;
    }

    public double getTotalPrice() {
        return Double.parseDouble(String.format("%.2f",
                this.orderItemList.stream().mapToDouble(Item::getPrice).sum() * (1+SERVICE_CHARGE+GST)));
    }

    //TODO: Implement getReceipt method
    public String getReceipt() {
        return null;
    }

    //TODO: Implement getReceipt method with discount
    public String getReceipt(double discount) {
        return null;
    }

    @Override
    public String toString() {
        return this.orderID + "\n" +
                IntStream.range(0, this.orderItemList.size())
                        .mapToObj(x -> (x + 1) + ". " + this.orderItemList.get(x))
                        .collect(Collectors.joining("\n"));
    }
}
