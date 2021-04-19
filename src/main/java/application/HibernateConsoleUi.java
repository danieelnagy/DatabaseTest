package application;

import persistence.SessionHibernate;
import service.hibernate.*;

import java.sql.SQLException;
import java.util.Scanner;

public class HibernateConsoleUi {

    private Scanner input = new Scanner(System.in);
    private Boolean controllerBool = true;
    private int functionControll = 0;

    public void Menu() throws SQLException {

        System.out.println("\n****************************************");
        System.out.println("Choose data! Press number");
        do {

            System.out.println(
                    "Function 0: Exit\nFunction 1: Offices \nFunction 2: Employees \nFunction 3: Customers \nFunction 4: Products \nFunction 5: Warehouses"
                            + "\nFunction 6: Orders \nFunction 7: Complaints \nFunction 8: Positions");
            System.out.println("\n****************************************");

            functionControll = input.nextInt();

            switch (functionControll) {

                case 0:
                    System.out.println("Exit");
                    SessionHibernate sessionHibernate = new SessionHibernate();
                    sessionHibernate.Exit();
                    DbMenu menu = new DbMenu();
                    menu.Menu();
                    controllerBool = false;
                    break;
                case 1:
                    OfficeService officeService = new OfficeService();
                    officeService.methodFactory();
                    controllerBool = false;
                    break;
                case 2:
                    EmployeeService employeeService = new EmployeeService();
                    employeeService.methodFactory();
                    controllerBool = false;
                    break;
                case 3:
                    CustomerService customerService = new CustomerService();
                    customerService.methodFactory();
                    controllerBool = false;
                    break;
                case 4:
                    ProductService productService = new ProductService();
                    productService.methodFactory();
                    controllerBool = false;
                    break;
                case 5:
                    WarehouseService warehouseService = new WarehouseService();
                    warehouseService.methodFactory();
                    controllerBool = false;
                    break;
                case 6:
                    OrderService orderService = new OrderService();
                    orderService.methodFactory();
                    controllerBool = false;
                    break;
                case 7:
                    ComplaintService complaintService = new ComplaintService();
                    complaintService.methodFactory();
                    controllerBool = false;
                    break;

                case 8:
                    PositionService positionService = new PositionService();
                    positionService.methodFactory();
                    controllerBool = false;
                    break;
            }
        } while (controllerBool == true);
    }

}
