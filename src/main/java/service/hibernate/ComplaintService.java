package service.hibernate;

import application.HibernateConsoleUi;
import entities.Complaint;
import persistence.repository.ComplaintRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComplaintService {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    ComplaintRepository complaintRepository = new ComplaintRepository();
    List<Complaint> complaints = new ArrayList<>();

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
                    complaintRepository.shutdown();
                    HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                    hibernateConsoleUi.Menu();
                    controllerBool = false;
                    break;
                case 1:
                    complaintRepository.create(create());
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


    public Complaint create() {

        String describe;
        int productId;
        int customerId;
        int employeeId;

        Complaint complaint = new Complaint();

        System.out.println("Creating new complaint, ProductId?");
        productId = input.nextInt();
        complaint.setProduct_id(productId);
        System.out.println("Your(customer) id?");
        customerId = input.nextInt();
        complaint.setCustomer_id(customerId);
        System.out.println("Employee?");
        employeeId = input.nextInt();
        complaint.setEmployee_id(employeeId);
        System.out.println("Describe?");
        describe = input.next();
        complaint.setDescribe(describe);

        return complaint;
    }

    public void findAll() {

        complaints = complaintRepository.findAll();
        for (Complaint c : complaints) {
            System.out.println("ID: " + c.getComplaint_id() + " CustomerID: " + c.getCustomer_id() + " Date: " + c.getDate());
        }
        complaints.clear();
    }

    public void find() {
        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        Complaint c = complaintRepository.find(x);
        System.out.println("ID: " + c.getComplaint_id() + " CustomerID: " + c.getCustomer_id() + " Date: " + c.getDate());
        System.out.println("\nfound");
    }

    public void update() {
        String describe;
        int x = 0;
        findAll();
        System.out.println("Type id to choose complaint!");
        x = input.nextInt();
        Complaint c = complaintRepository.find(x);


        System.out.println("Updating description, Name?");
        describe = input.next();
        c.setDescribe(describe);
        complaintRepository.update(c);
    }

    public void delete() {

        int x = 0;
        findAll();
        System.out.println("Type id to choose complaint!");
        x = input.nextInt();
        Complaint c = complaintRepository.find(x);
        complaintRepository.delete(c);

    }

}
