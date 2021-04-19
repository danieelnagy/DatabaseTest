package service.hibernate;

import application.HibernateConsoleUi;
import entities.Customer;
import entities.Order;
import entities.Product;
import persistence.repository.CustomerRepository;
import persistence.repository.OrderRepository;
import persistence.repository.ProductRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    OrderRepository orderRepository = new OrderRepository();
    List<Order> orders = new ArrayList<>();

    public void methodFactory() throws SQLException {

        System.out.println("\n****************************************");
        System.out.println("Choose a method! Press number");
        do {
            System.out.println(
                    "Function 0: Exit\nFunction 1: Create \nFunction 2: ListAll "
                            + "\nFunction 3: FindOne \nFunction 4: Update "
                            + "\nFunction 5: Delete");

            System.out.println("****************************************\n");
            functionControll = input.nextInt();

            switch (functionControll) {

                case 0:
                    orderRepository.shutdown();
                    HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                    try {
                        hibernateConsoleUi.Menu();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    controllerBool = false;
                    break;
                case 1:
                    orderRepository.create(create());
                    controllerBool = true;
                    break;
                case 2:
                    findAll();
                    controllerBool = true;
                    break;
                case 3:
                    find();
                    controllerBool = true;
                    break;
                case 4:
                    update();
                    controllerBool = true;
                    break;
                case 5:
                    delete();
                    controllerBool = true;
                    break;
            }

        } while (controllerBool == true);
    }

    public Order create() {

        int productId;
        int customerId;
        int stock;


        ProductService productService = new ProductService();
        ProductRepository productRepository = new ProductRepository();

        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService();

        Order order = new Order();

        /* Order hantering */
        System.out.println("Creating new order, ProductId?");
        productService.findAll();
        productId = input.nextInt();
        order.setProductId(productId);
        System.out.println("Customer id?");
        customerId = input.nextInt();
        order.setCustomerId(customerId);
        System.out.println("Amount of item?");
        stock = input.nextInt();
        order.setStock(stock);

        /* Product hantering */
        Product product = productRepository.find(productId);
        System.out.println("\nproductStockBEFORE: " + "productName: " + product.getName() +  " Stock: " + product.getStock());
        product.setStock(product.getStock() - stock);
        productRepository.update(product);
        System.out.println("\nproductStockAFTER: " + "productName: " + product.getName() +  " Stock: " + product.getStock());

        /* Customer hantering */

        Customer customer = customerRepository.find(customerId);
        System.out.println("\nCustomerSPENTMONEY BEFORE: " + customer.getTotalSpentMoney() + " Name: " + customer.getName());
        customer.setTotalSpentMoney(customer.getTotalSpentMoney() + (int)product.getPrice());
        customerRepository.update(customer);
        System.out.println("\nCustomerSPENTMONEY AFTER: " + customer.getTotalSpentMoney() + " Name: " + customer.getName());


        return order;
    }

    public void findAll() {

        orders = orderRepository.findAll();
        for (Order o : orders) {
            System.out.println("ID: " + o.getOrderId() + " customerID: " + o.getCustomerId() + " stock: " + o.getStock());
        }
        orders.clear();
    }

    public void find() {
        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        Order o = orderRepository.find(x);
        System.out.println("ID: " + o.getOrderId() + " customerID: " + o.getCustomerId() + " stock: " + o.getStock());
        System.out.println("\nfound");
    }

    public void update() {
        int stock;
        int x = 0;
        findAll();
        System.out.println("Type id to choose order!");
        x = input.nextInt();
        Order o = orderRepository.find(x);


        System.out.println("Updating stock, Stock?");
        stock = input.nextInt();
        o.setStock(stock);
        orderRepository.update(o);
    }

    public void delete() {

        int x = 0;
        findAll();
        System.out.println("Type id to choose complaint!");
        x = input.nextInt();
        Order o = orderRepository.find(x);
        orderRepository.delete(o);

    }
}
