package service.hibernate;

import application.HibernateConsoleUi;
import entities.WareHouse;
import persistence.repository.WarehouseRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarehouseService {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    WarehouseRepository warehouseRepository = new WarehouseRepository();
    List<WareHouse> wareHouses  = new ArrayList<>();

    public void methodFactory() throws SQLException {

        System.out.println("\n****************************************");
        System.out.println("Choose a method! Press number");
        do {
            System.out.println(
                    "Function 0: Exit\nFunction 1: Create \nFunction 2: ListAll "
                            + "\nFunction 3: FindOne \nFunction 4: Update "
                            + "\nFunction 5: Delete"
                            + "\nFunction 6: Back");

            System.out.println("****************************************\n");
            functionControll = input.nextInt();

            switch (functionControll) {

                case 0:
                    warehouseRepository.shutdown();
                    HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                    hibernateConsoleUi.Menu();
                    controllerBool = false;
                    break;
                case 1:
                    warehouseRepository.create(Create());
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


    public WareHouse Create() {

        String name;
        String adress;
        WareHouse wareHouse = new WareHouse();

        System.out.println("Creating new warehouse, Name?");
        name = input.next();
        wareHouse.setName(name);
        System.out.println("Adress?");
        adress = input.next();
        wareHouse.setAdress(adress);

        return wareHouse;
    }

    public void findAll() {

        wareHouses = warehouseRepository.findAll();
        for (WareHouse wareHouse : wareHouses) {
            System.out.println("ID: " + wareHouse.getWareHouseId() + " " + wareHouse.getName() + " " + wareHouse.getAdress());
        }
        wareHouses.clear();
    }

    public void find() {
        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        WareHouse wareHouse = warehouseRepository.find(x);
        System.out.println(wareHouse.getName() + "\nfound");
    }

    public void update() {
        String name;
        String adress;
        int x = 0;
        findAll();
        System.out.println("Type id to choose warehouse!");
        x = input.nextInt();
        WareHouse wareHouse = warehouseRepository.find(x);


        System.out.println("Updating warehouse, Name?");
        name = input.next();
        wareHouse.setName(name);
        System.out.println("Adress?");
        adress = input.next();
        wareHouse.setAdress(adress);
        warehouseRepository.update(wareHouse);
    }

    public void delete() {

        int x = 0;
        findAll();
        System.out.println("Type id to choose office!");
        x = input.nextInt();
        WareHouse wareHouse = warehouseRepository.find(x);
        warehouseRepository.delete(wareHouse);

    }
}
