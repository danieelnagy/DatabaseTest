package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.JdbcConsoleUi;
import entities.Product;
import persistence.dao.ProductDao;

public class ProductsView {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    private ProductDao productDao = new ProductDao();
    List<Product> products = new ArrayList<>();

    public void MethodFactory() throws SQLException {

        System.out.println("\n****************************************");
        System.out.println("Choose a method! Press number");
        do {
            System.out.println(
                    "Function 0: Back\nFunction 1: Create \nFunction 2: ListAll "
                            + "\nFunction 3: FindOne \nFunction 4: Update "
                            + "\nFunction 5: Delete"
                            + "\nFunction 6: LowStock");

            System.out.println("****************************************\n");
            functionControll = input.nextInt();

            switch (functionControll) {

                case 0:
                    JdbcConsoleUi jdbcConsole = new JdbcConsoleUi();
                    jdbcConsole.Menu();
                    controllerBool = false;
                    break;
                case 1:
                    productDao.create(create());
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
                    controllerBool = false;
                    break;
            }

        } while (controllerBool == true);
    }


    public Product create() {

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

    public void findAll() throws SQLException {
        products = productDao.findAll();
        for (Product p : products) {
            System.out.println("ID: " + p.getProductId() + " Name: " + p.getName() + " Price: " + p.getPrice());
        }
        products.clear();
    }

    public void find() throws SQLException {

        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        Product p = productDao.find(x);
        System.out.println("ID: " + p.getProductId() + " Name: " + p.getName() + " Price: " + p.getPrice());
        System.out.println("\nfound");
    }


    public void update() throws SQLException {

        String name;
        double price;
        int stock;
        int x = 0;
        findAll();
        System.out.println("Type id to choose product!");
        x = input.nextInt();
        Product p = productDao.find(x);


        System.out.println("Updating product, Name?");
        name = input.next();
        p.setName(name);
        System.out.println("Price?");
        price = input.nextDouble();
        p.setPrice(price);
        System.out.println("Stock?");
        stock = input.nextInt();
        p.setStock(stock);
        productDao.update(p);
    }

    public void delete() throws SQLException {

        int x = 0;
        findAll();
        System.out.println("Type id to choose product!");
        x = input.nextInt();
        Product p = productDao.find(x);
        productDao.delete(p);
    }

    public void getLowStock() throws SQLException {
        products = productDao.getLowStock();
        for (Product p : products) {
            System.out.println("ID: " + p.getProductId() + " Name: " + p.getName() + " Price: " + p.getPrice() + " Stock: " + p.getStock());
        }
        products.clear();
    }


}
