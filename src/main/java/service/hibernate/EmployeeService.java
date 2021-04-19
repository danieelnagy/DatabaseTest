package service.hibernate;

import application.HibernateConsoleUi;
import entities.Employee;
import entities.Office;
import persistence.repository.EmployeeRepository;
import persistence.repository.OfficeRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;
    EmployeeRepository employeeRepository = new EmployeeRepository();
    List<Employee> employees = new ArrayList<>();

    public void methodFactory() throws SQLException {

        System.out.println("\n****************************************");
        System.out.println("Choose a method! Press number");
        do {
            System.out.println(
                    "Function 0: Exit\nFunction 1: Create \nFunction 2: ListAll "
                            + "\nFunction 3: FindOne \nFunction 4: Update "
                            + "\nFunction 5: Delete"
                            + "\nFunction 6: Back");

            System.out.println("\n****************************************\n");
            functionControll = input.nextInt();

            switch (functionControll) {

                case 0:
                    System.out.println("Exit");
                    controllerBool = false;
                    break;
                case 1:
                    employeeRepository.create(Create());
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
                    employeeRepository.shutdown();
                    HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                    hibernateConsoleUi.Menu();
                    controllerBool = false;
                    break;
            }

        } while (controllerBool == true);
    }


    public Employee Create() {

        List<Office> offices = new ArrayList<Office>();
        String name;
        String adress;
        int officeId;
        Employee employee = new Employee();

        System.out.println("Creating new employee, Name?");
        name = input.next();
        employee.setName(name);
        System.out.println("Adress?");
        adress = input.next();
        employee.setAdress(adress);

        OfficeService officeService = new OfficeService();
        OfficeRepository officeRepository = new OfficeRepository();
        System.out.println("Office(enter id)?");
        officeService.findAll();

        officeId = input.nextInt();

        Office office = officeRepository.find(officeId);

        employee.setOfficeId(office.getOfficeId());

        return employee;
    }

    public void findAll() {

        employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getEmployeeId() + " " + employee.getName() + " " + employee.getAdress());
        }
        employees.clear();
    }

    public void find() {
        int x = 0;
        findAll();
        System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
        x = input.nextInt();
        Employee employee = employeeRepository.find(x);
        System.out.println(employee.getName() + "\nfound");
    }

    public void update() {
        String name;
        String adress;
        int x = 0;
        findAll();
        System.out.println("Type id to choose employee!");
        x = input.nextInt();
        Employee employee = employeeRepository.find(x);


        System.out.println("Updating employee, Name?");
        name = input.next();
        employee.setName(name);
        System.out.println("Adress?");
        adress = input.next();
        employee.setAdress(adress);
        employeeRepository.update(employee);
    }

    public void delete() {

        int x = 0;
        findAll();
        System.out.println("Type id to choose employee!");
        x = input.nextInt();
        Employee employee = employeeRepository.find(x);
        employeeRepository.delete(employee);

    }
}
