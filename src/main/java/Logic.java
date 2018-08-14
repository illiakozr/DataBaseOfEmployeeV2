
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Logic {

      public Connection createConnection() {
          String userName = "root";
          String password = "1234";
          String connectionURL = "jdbc:mysql://localhost:3306/database_of_employee";
          Connection connection = null;
          try {
              Class.forName("com.mysql.jdbc.Driver");
              connection = DriverManager.getConnection(connectionURL, userName, password);
             // System.out.println("we are connect");
          } catch (SQLException | ClassNotFoundException e){
              System.out.println("can not connect to database");
          }
          return connection;
      }

    public void addNewEmployee() {
        Scanner scannerWR = new Scanner(System.in);
        System.out.println("Input data in format: id, last-name; name; surname; Birthday(yyyy-MM-dd); position; sub-division; " +
                "room number; officialTelefon; eMail; salary(####.##); date of hiring(yyyy-MM-dd); notes");
        System.out.println("Input 'break' to finish");

        Connection connection = createConnection();
        String s;
        while (!(s = scannerWR.nextLine()).equals("break")) {
            try {
                String[] parts = s.split("; ");
                if (parts.length < 13) {
                    System.out.println("incorrect input");
                    continue;
                }
                PreparedStatement preparedStatement = connection.prepareStatement("insert into " +
                        "database_of_employee.employees values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, Integer.parseInt(parts[0]));
                preparedStatement.setString(2,parts[1]);
                preparedStatement.setString(3,parts[2]);
                preparedStatement.setString(4,parts[3]);
                preparedStatement.setString(5, parts[4]);
                preparedStatement.setString(6,parts[5]);
                preparedStatement.setString(7,parts[6]);
                preparedStatement.setInt(8,Integer.parseInt(parts[7]));
                preparedStatement.setInt(9,Integer.parseInt(parts[8]));
                preparedStatement.setString(10,parts[9]);
                preparedStatement.setBigDecimal(11,BigDecimal.valueOf(Double.parseDouble(parts[10])));
                preparedStatement.setString(12,parts[11]);
                preparedStatement.setString(13,parts[12]);

                preparedStatement.executeUpdate();
                System.out.println("has been added successfully");

            } catch (Exception e) {
                System.out.println("incorrect input format");
                e.printStackTrace();
            }
        }
    }

    public ResultSet readEmployeeFromDB(String[] parts){

          boolean searchFlag = false;

        try{
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("select * from database_of_employee.employees " +
                    "" + "where lastName = ?  and name = ? and surname = ?",ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            statement.setString(1,parts[0]);
            statement.setString(2,parts[1]);
            statement.setString(3,parts[2]);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                searchFlag = true;
                System.out.println(resultSet.getInt(1) + "; " + resultSet.getString(2) + "; "
                        + resultSet.getString(3) + "; " + resultSet.getString(4) + "; "
                        + resultSet.getString(5) + "; " + resultSet.getString(6) + "; "
                        + resultSet.getString(7) + "; " + resultSet.getInt(8) + "; "
                        + resultSet.getInt(9) + "; " + resultSet.getString(10) + "; "
                        + resultSet.getBigDecimal(11) + resultSet.getString(12) + "; "
                        + resultSet.getString(13));
            }
            if (!searchFlag){
                System.out.println("didn't find this employee");
            }
           return resultSet;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("can't create a connection");
        }
        return null;
    }

    public void viewEmployeeData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input full name of employee (last-name; name; surname) to which you want see a data");
        System.out.println("Input 'break' to finish");

        String s;
        while (!(s = scanner.nextLine()).equals("break")) {
            if (s.endsWith(";")) {
                String result = s.substring(s.lastIndexOf(";"));
            }
                String[] parts = s.split("; ");
            if (parts.length < 3){
                System.out.println("incorrect input");
                continue;
            }

            ResultSet resultSet = readEmployeeFromDB(parts);
        }
    }

    public void editExistingEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("input full name of employee (last-name; name; surname) to which you want to change a data");
        System.out.println("Input 'break' to finish");
        // пропустити це можна через флаг true false в параметрі метода

        String s;
        while (!(s = scanner.nextLine()).equals("break")) {

            String[] parts = s.split("; ");
            if (parts.length < 3) {
                System.out.println("incorrect input");
                continue;
            }
            ResultSet resultSet = readEmployeeFromDB(parts);

            try {
                if (!resultSet.first())
                    continue;
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            CachedRowSet cachedRowSet = (CachedRowSet) resultSet.cancelRowUpdates();
//            if (cachedRowSet.size() == 0){
//                continue;
//            }

                System.out.println("what field do you wanna change? (type number)");
                System.out.println("1: ID");
                System.out.println("2: last-name");
                System.out.println("3: name");
                System.out.println("4: surname");
                System.out.println("5: Birthday");
                System.out.println("6: position");
                System.out.println("7: sub-division");
                System.out.println("8: room number");
                System.out.println("9: official Telefon");
                System.out.println("10: eMail");
                System.out.println("11: salary");
                System.out.println("12: date of hiring");
                System.out.println("13: notes");
                System.out.println("14: <exit>");

                try {
                    while (true) {
                        int numberOfField = Integer.parseInt(scanner.nextLine());
                        if (numberOfField < 1 || numberOfField > 14){
                            System.out.println("incorrect number");
                            continue;
                        }
                        if (numberOfField == 14) {
                            System.out.println("changes commit");
                            break;
                        }
                        resultSet.last();
                        switch (numberOfField) {
                            case 1:
                                System.out.println("set ID ");
                                resultSet.updateInt(1, Integer.parseInt(scanner.nextLine()));
                                resultSet.updateRow();
                                break;
                            case 2:
                                System.out.println("Set last-name");
                                resultSet.updateString(2, (scanner.nextLine()));
                                resultSet.updateRow();
                                break;
                            case 3:
                                System.out.println("Set name");
                                resultSet.updateString(3, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                            case 4:
                                System.out.println("Set surname");
                                resultSet.updateString(4, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                            case 5:
                                System.out.println("Set Birthday");
                                resultSet.updateString(5, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                            case 6:
                                System.out.println("Set position");
                                resultSet.updateString(6, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                            case 7:
                                System.out.println("set sub-division");
                                resultSet.updateString(7, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                            case 8:
                                System.out.println("set room number");
                                resultSet.updateInt(8, Integer.parseInt(scanner.nextLine()));
                                resultSet.updateRow();
                                break;
                            case 9:
                                System.out.println("set official Telefon");
                                resultSet.updateInt(9, Integer.parseInt(scanner.nextLine()));
                                resultSet.updateRow();
                                break;
                            case 10:
                                System.out.println("set eMali");
                                resultSet.updateString(10, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                            case 11:
                                System.out.println("set salary");
                                resultSet.updateBigDecimal(11,
                                        BigDecimal.valueOf(Double.parseDouble(scanner.nextLine())));
                                resultSet.updateRow();
                                break;
                            case 12:
                                System.out.println("set date of hiring");
                                resultSet.updateString(12, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                            case 13:
                                System.out.println("set notes");
                                resultSet.updateString(13, scanner.nextLine());
                                resultSet.updateRow();
                                break;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }

    public void deleteExistingEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("input full name of employee (last-name; name; surname) to which you want to delete");
        System.out.println("Input 'break' to finish");
        // пропустити це можна через флаг true false в параметрі метода

        String s;
        while (!(s = scanner.nextLine()).equals("break")) {

            String[] parts = s.split("; ");
            if (parts.length < 3) {
                System.out.println("incorrect input");
                continue;
            }

            ResultSet resultSet = readEmployeeFromDB(parts);

            try {
                if (!resultSet.first())
                    continue;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Are You sure you want to remove this employee?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            int answer = Integer.parseInt(scanner.nextLine());
            switch (answer){
                case 1:
                    try {
                        resultSet.last();
                        resultSet.deleteRow();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("can't delete row");
                    }
                    System.out.println("employee has been removed");
                break;
                case 2 : break;
            }
        }
    }
}
