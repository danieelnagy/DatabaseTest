package service.hibernate;

import application.HibernateConsoleUi;
import entities.Customer;
import persistence.repository.CustomerRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class CustomerService {

        private Scanner input = new Scanner(System.in);
        private Boolean controllerBool = true;
        private int functionControll = 0;
        CustomerRepository customerRepository = new CustomerRepository();
        List<Customer> customers = new ArrayList<>();

        public void methodFactory() throws SQLException {

            System.out.println("\n****************************************");
            System.out.println("Choose a method! Press number");
            do {
                System.out.println(
                        "Function 0: Exit\nFunction 1: Create \nFunction 2: ListAll "
                                + "\nFunction 3: FindOne \nFunction 4: Update "
                                + "\nFunction 5: Delete \nFunction 6: BestCustomer");

                System.out.println("****************************************\n");
                functionControll = input.nextInt();

                switch (functionControll) {

                    case 0:
                        customerRepository.shutdown();
                        HibernateConsoleUi hibernateConsoleUi = new HibernateConsoleUi();
                        try {
                            hibernateConsoleUi.Menu();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        controllerBool = false;
                        break;
                    case 1:
                        customerRepository.create(create());
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
                        getBestCustomer();
                        controllerBool = true;
                        break;
                }

            } while (controllerBool == true);
        }


        public void getBestCustomer() throws SQLException {

            Customer apexCustomer = new Customer();
            customers = customerRepository.findAll();

            for(int i = 0; i < customers.size() - 1;i++) {
                for(int j = 0;j < customers.size() - i - 1;j++ ) {
                    if (customers.get(j).getTotalSpentMoney() < customers.get(i).getTotalSpentMoney())
                        apexCustomer = customers.get(i);
                }
            }

            System.out.println("ID: " + apexCustomer.getCustomerId() + " Name: " + apexCustomer.getName() + " Adress: "
                    + apexCustomer.getAdress() + " moneySpent: " + apexCustomer.getTotalSpentMoney());

        }

        public Customer create() {

            String name;
            String adress;
            Customer customer = new Customer();

            System.out.println("Creating new customer, Name?");
            name = input.next();
            customer.setName(name);
            System.out.println("Adress?");
            adress = input.next();
            customer.setAdress(adress);

            return customer;
        }

        public void findAll() {

            customers = customerRepository.findAll();
            for (Customer c : customers) {
                System.out.println("ID: " + c.getCustomerId() + " " + c.getName() + " " + c.getAdress());
            }
            customers.clear();
        }

        public void find() {
            int x = 0;
            findAll();
            System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
            x = input.nextInt();
            Customer c = customerRepository.find(x);
            System.out.println(c.getName() + "\nfound");
        }

        public void update() {
            String name;
            String adress;
            String email;
            int x = 0;
            findAll();
            System.out.println("Type id to choose customer!");
            x = input.nextInt();
            Customer c = customerRepository.find(x);


            System.out.println("Updating customer, Name?");
            name = input.next();
            c.setName(name);
            System.out.println("Adress?");
            adress = input.next();
            c.setAdress(adress);
            System.out.println("Email?");
            email = input.next();
            c.setEmail(email);
            customerRepository.update(c);
        }

        public void delete() {

            int x = 0;
            findAll();
            System.out.println("Type id to choose customer!");
            x = input.nextInt();
            Customer c = customerRepository.find(x);
            customerRepository.delete(c);

        }
    }
