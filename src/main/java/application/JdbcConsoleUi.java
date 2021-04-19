package application;
import java.sql.SQLException;
import java.util.Scanner;
import service.ComplaintsView;
import service.CustomerView;
import service.EmployeeView;
import service.OfficeView;
import service.OrderView;
import service.PositionsView;
import service.ProductsView;
import service.WareHouseView;


public class JdbcConsoleUi {

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
				DbMenu menu = new DbMenu();
				menu.Menu();
					controllerBool = false;
						break;
			case 1:
				OfficeView officeView = new OfficeView();
					officeView.MethodFactory();
						controllerBool = false;
							break;
			case 2:
				EmployeeView employeView = new EmployeeView();
					employeView.MethodFactory();
						controllerBool = false;
							break;
			case 3:
				CustomerView cView = new CustomerView();
					cView.MethodFactory();
						controllerBool = false;
							break;
			case 4:
				ProductsView productsView = new ProductsView();
					productsView.MethodFactory();
						controllerBool = false;
							break;
			case 5:
				WareHouseView warehouseView = new WareHouseView();
					warehouseView.MethodFactory();
						controllerBool = false;
							break;
			case 6:
				OrderView orderView = new OrderView();
					orderView.MethodFactory();
						controllerBool = false;
							break;
			case 7:
				ComplaintsView complainView = new ComplaintsView();
					complainView.MethodFactory();
						controllerBool = false;
							break;
			
			case 8: 
				PositionsView pView = new PositionsView();
					pView.MethodFactory();
						controllerBool = false;
						 break;				
			}
		} while (controllerBool == true);
	}

}
