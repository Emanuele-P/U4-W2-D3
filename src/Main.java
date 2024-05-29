import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Customer customer1 = new Customer("Cloud Strife", 2);
        Customer customer2 = new Customer("Barret Wallace", 1);
        Customer customer3 = new Customer("Aerith Gainsborough", 2);

        Product product1 = new Product("Buster Sword", "Weapon", 3000.00);
        Product product2 = new Product("Masamune", "Weapon", 5000.00);
        Product product3 = new Product("Gatling Gun", "Weapon", 3500.00);
        Product product4 = new Product("Potion", "Remedy", 50.00);
        Product product5 = new Product("Hi-Potion", "Remedy", 150.00);
        Product product6 = new Product("Ether", "Remedy", 300.00);
        Product product7 = new Product("Silver Armlet", "Accessory", 1000.00);
        Product product8 = new Product("Fire Ring", "Accessory", 1200.00);
        Product product9 = new Product("Power Wrist", "Accessory", 1100.00);

        Product[] productsArray = {product1, product2, product3, product4, product5, product6, product7, product8, product9};
        List<Product> items = new ArrayList<>(Arrays.asList(productsArray));
        List<Product> list1 = new ArrayList<>(Arrays.asList(product1, product4, product8));
        List<Product> list2 = new ArrayList<>(Arrays.asList(product3, product9, product7));
        List<Product> list3 = new ArrayList<>(Arrays.asList(product2, product5, product6));

        Order order1 = new Order("Processing order(1): ",
                LocalDate.now(), LocalDate.now().plusDays(5), list1, customer1);
        Order order2 = new Order("Completed order(2): ",
                LocalDate.parse("2024-02-02"), LocalDate.parse("2024-02-04"), list2, customer2);
        Order order3 = new Order("Completed order(3): ",
                LocalDate.parse("2024-03-12"), LocalDate.parse("2024-03-20"), list3, customer3);

        System.out.println("Available items: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        System.out.println("--------Ex1--------");
        items.stream().filter(item -> item.getCategory().equals("Weapon")
                && item.getPrice() > 3000.00).forEach(System.out::println);

        System.out.println("--------Ex2--------");
        List<Order> orders = new ArrayList<>(Arrays.asList(order1, order2, order3));
        orders.stream()
                .filter(order -> order.getProducts()
                        .stream().anyMatch(product -> product.getCategory().equals("Remedy")))
                .forEach(System.out::println);

        System.out.println("--------Ex3--------");
        items.stream().filter(item -> item.getCategory().equals("Accessory"))
                .forEach(item -> {
                    double originalPrice = item.getPrice();
                    double discountedPrice = item.calculateDiscount();
                    item.setPrice(discountedPrice);
                    System.out.println(item.getName() + ": original price was " + originalPrice +
                            ", discounted price is " + discountedPrice);
                });

        System.out.println("--------Ex4--------");
        List<Order> filteredOrders = orders.stream().filter(order -> order.getCustomer().getTier() == 2 &&
                        order.getOrderDate().isAfter(LocalDate.parse("2024-02-01")) &&
                        order.getOrderDate().isBefore(LocalDate.parse("2024-04-01")))
                .toList();

        List<Product> ex4 = new ArrayList<>();
        for (Order order : filteredOrders) {
            List<Product> products = order.getProducts();
            ex4.addAll(products);
        }

        for (Product product : ex4) {
            System.out.println(product);
        }

    }
}