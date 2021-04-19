package service.hibernate;

import application.HibernateConsoleUi;
import entities.Office;
import persistence.repository.OfficeRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OfficeService {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    OfficeRepository officeRepository = new OfficeRepository();
    List<Office> offices = new ArrayList<>();

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
                    System.out.println("Exit");
                    controllerBool = false;
                    break;
                case 1:
                    officeRepository.create(create());
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
                    officeRepository.shutdown();
                    HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                    hibernateConsoleUi.Menu();
                    controllerBool = false;
                    break;
            }

        } while (controllerBool == true);
    }


    public Office create() {

        String name;
        String adress;
        Office office = new Office();

        System.out.println("Creating new office, Name?");
        name = input.next();
        office.setName(name);
        System.out.println("Adress?");
        adress = input.next();
        office.setAdress(adress);

        return office;
    }

    public void findAll() {

        offices = officeRepository.findAll();
        for (Office office : offices) {
            System.out.println("ID: " + office.getOfficeId() + " " + office.getName() + " " + office.getAdress());
        }
        offices.clear();
    }

    public void find() {
        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        Office office = officeRepository.find(x);
        System.out.println(office.getName() + "\nfound");
    }

    public void update() {
        String name;
        String adress;
        int x = 0;
        findAll();
        System.out.println("Type id to choose office!");
        x = input.nextInt();
        Office office = officeRepository.find(x);


        System.out.println("Updating office, Name?");
        name = input.next();
        office.setName(name);
        System.out.println("Adress?");
        adress = input.next();
        office.setAdress(adress);
        officeRepository.update(office);
    }

    public void delete() {

        int x = 0;
        findAll();
        System.out.println("Type id to choose office!");
        x = input.nextInt();
        Office office = officeRepository.find(x);
        officeRepository.delete(office);

    }
}
