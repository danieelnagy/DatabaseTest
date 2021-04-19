package service.hibernate;

import application.HibernateConsoleUi;
import entities.Positions;
import persistence.repository.PositionRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PositionService {


    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    PositionRepository positionRepository = new PositionRepository();
    List<Positions> positions = new ArrayList<>();

    public void methodFactory() throws SQLException {

        System.out.println("\n****************************************");
        System.out.println("Choose a method! Press number");
        do {
            System.out.println(
                    "Function 0: Back\nFunction 1: Create \nFunction 2: ListAll "
                            + "\nFunction 3: FindOne \nFunction 4: Update "
                            + "\nFunction 5: Delete");

            System.out.println("****************************************\n");
            functionControll = input.nextInt();

            switch (functionControll) {

                case 0:
                    positionRepository.shutdown();
                    HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                    hibernateConsoleUi.Menu();
                    controllerBool = false;
                    break;
                case 1:
                    positionRepository.create(Create());
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


    public Positions Create() {

        String positionName;
        Positions position = new Positions();

        System.out.println("Creating new position, position (only 4 chr)?");
        positionName = input.next();
        position.setPosition(positionName);

        return position;
    }

    public void findAll() {

        positions = positionRepository.findAll();
        for (Positions position : positions) {
            System.out.println("ID: " + position.getPositionId() + " " + position.getPosition());
        }
        positions.clear();
    }

    public void find() {
        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        Positions position = positionRepository.find(x);
        System.out.println(position.getPosition() + "\nfound");
    }

    public void update() {


        String positionName;
        int x = 0;
        findAll();
        System.out.println("Type id to choose position!");
        x = input.nextInt();
        Positions position = positionRepository.find(x);

        System.out.println("Updating position, positionName?");
        positionName = input.next();
        position.setPosition(positionName);

        positionRepository.update(position);
    }

    public void delete() {

        int x = 0;
        findAll();
        System.out.println("Type id to choose office!");
        x = input.nextInt();
        Positions position = positionRepository.find(x);
        positionRepository.delete(position);

    }
}
