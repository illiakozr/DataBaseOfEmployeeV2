import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);
        Logic logic = new Logic();
        EXCELReport report = new EXCELReport();

        System.out.println("Select the action you want to perform");
        System.out.println("1: add new employee");
        System.out.println("2: show data of employee");
        System.out.println("3: edit existing employee");
        System.out.println("4: remove existing employee");
        System.out.println("5: generate report");
        System.out.println("6: exit from program");

        while (true){
            int numAction;
            try {
                numAction = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("incorrect format");
                continue;
            }
            if (numAction < 1 || numAction > 6){
                System.out.println("incorrect number");
                continue;
            }
            switch (numAction){
                case 1 : logic.addNewEmployee(); break;
                case 2 : logic.viewEmployeeData(); break;
                case 3 : logic.editExistingEmployee(); break;
                case 4 : logic.deleteExistingEmployee(); break;
                case 5 : report.createReport(); break;
                case 6 : scanner.close();
                    System.exit(0);
            }
        }


    }
}
