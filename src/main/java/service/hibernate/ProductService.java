package service.hibernate;

import application.HibernateConsoleUi;
import entities.Customer;
import entities.Product;
import persistence.repository.ProductRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    ProductRepository productRepository = new ProductRepository();
    List<Product> products = new ArrayList<>();

    public void methodFactory() throws SQLException {

        System.out.println("\n****************************************");
        System.out.println("Choose a method! Press number");
        do {
            System.out.println(
                    "Function 0: Back\nFunction 1: Create \nFunction 2: ListAll "
                            + "\nFunction 3: FindOne \nFunction 4: Update "
                            + "\nFunction 5: Delete"
                            + "\nFunction 6: ShowLowStock");

            System.out.println("****************************************\n");
            functionControll = input.nextInt();

            switch (functionControll) {

                case 0:
                    productRepository.shutdown();
                    HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                    hibernateConsoleUi.Menu();
                    controllerBool = false;
                    break;
                case 1:
                    productRepository.create(Create());
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
                case 6:
                    getLowStock();
                    controllerBool = true;
                    break;
            }

        } while (controllerBool == true);
    }


    public void getLowStock() {

        List<Product> lowStockProducts = new ArrayList<>();
        products = productRepository.findAll();
        for(Product product : products) {
            if(product.getStock() <= 5) {
                lowStockProducts.add(product);
            }
        }
        for(Product p : lowStockProducts) {
            System.out.println("ID: " + p.getProductId() + " Name: " + p.getName() + " Price: " + p.getPrice() + " Stock: " + p.getStock());
        }


    }

    public Product Create() {

        String name;
        double price;
        int stock;
        int warehouseId;
        int positionId;
        Product product = new Product();

        System.out.println("Creating new product, Name?");
        name = input.next();
        product.setName(name);
        System.out.println("Price?");
        price = input.nextDouble();
        product.setPrice(price);
        System.out.println("Stock?");
        stock = input.nextInt();
        product.setStock(stock);
        System.out.println("Warehouseid?");
        warehouseId = input.nextInt();
        product.setWareHouseId(warehouseId);
        System.out.println("Positionid?");
        positionId = input.nextInt();
        product.setPositionId(positionId);

        return product;
    }

    public void findAll() {

        products = productRepository.findAll();
        for (Product product : products) {
            System.out.println("ID: " + product.getProductId() + " " + product.getName() + " " + product.getPrice());
        }
        products.clear();
    }

    public void find() {
        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        Product product = productRepository.find(x);
        System.out.println(product.getName() + "\nfound");
    }

    public void update() {
        String name;
        double price;
        int stock;
        int x = 0;
        findAll();
        System.out.println("Type id to choose product!");
        x = input.nextInt();
        Product product = productRepository.find(x);

        System.out.println("Updating product, Name?");
        name = input.next();
        product.setName(name);
        System.out.println("Price?");
        price = input.nextDouble();
        product.setPrice(price);
        System.out.println("Stock?");
        stock = input.nextInt();
        product.setStock(stock);

        productRepository.update(product);
    }

    public void delete() {

        int x = 0;
        findAll();
        System.out.println("Type id to choose office!");
        x = input.nextInt();
        Product product = productRepository.find(x);
        productRepository.delete(product);

    }

}
