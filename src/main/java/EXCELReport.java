import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EXCELReport {
    Logic logic = new Logic();

    public void createReport(){

        ResultSet resultSet = null;
        int rowCount = 0;
        try {
            Statement statement = logic.createConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
           resultSet = statement.executeQuery("select * from database_of_employee.employees");
            while (resultSet.next()){
                rowCount++;
            }
            resultSet.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("can't create a connection");
        }

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("report");

        CellStyle styleHead = wb.createCellStyle();
        styleHead.setBorderBottom(BorderStyle.DASH_DOT);
        styleHead.setBorderLeft(BorderStyle.DASH_DOT);
        styleHead.setBorderRight(BorderStyle.DASH_DOT);
        styleHead.setBorderTop(BorderStyle.DASH_DOT);

        Font fontHeader = wb.createFont();
        fontHeader.setBold(true);
        fontHeader.setFontHeightInPoints((short) 12);

        styleHead.setFont(fontHeader);

        //CachedRowSet cachedRowSet = (CachedRowSet) resultSet;


        for (int row_i = 0; row_i <= rowCount; row_i++) {

            Row row = sheet.createRow(row_i);
            if (row_i == 0) {
                Cell head0 = row.createCell(0);
                head0.setCellValue("ID");
                head0.setCellStyle(styleHead);

                Cell head1 = row.createCell(1);
                head1.setCellValue("Last-name");
                head1.setCellStyle(styleHead);

                Cell head2 = row.createCell(2);
                head2.setCellValue("Name");
                head2.setCellStyle(styleHead);

                Cell head3 = row.createCell(3);
                head3.setCellValue("Surname");
                head3.setCellStyle(styleHead);

                Cell head4 = row.createCell(4);
                head4.setCellValue("Birthday");
                head4.setCellStyle(styleHead);

                Cell head5 = row.createCell(5);
                head5.setCellValue("Position");
                head5.setCellStyle(styleHead);

                Cell head6 = row.createCell(6);
                head6.setCellValue("Sub-division");
                head6.setCellStyle(styleHead);

                Cell head7 = row.createCell(7);
                head7.setCellValue("Room number");
                head7.setCellStyle(styleHead);

                Cell head8 = row.createCell(8);
                head8.setCellValue("Official Telefon");
                head8.setCellStyle(styleHead);

                Cell head9 = row.createCell(9);
                head9.setCellValue("eMail");
                head9.setCellStyle(styleHead);

                Cell head10 = row.createCell(10);
                head10.setCellValue("Salary");
                head10.setCellStyle(styleHead);

                Cell head11 = row.createCell(11);
                head11.setCellValue("Date of hiring");
                head11.setCellStyle(styleHead);

                Cell head12 = row.createCell(12);
                head12.setCellValue("Notes");
                head12.setCellStyle(styleHead);

            } else {
               try {
                   resultSet.next();

                   for (int column = 0; column < 13; column++) {
                       Cell cell = row.createCell(column);
                       switch (column) {
                           case 0:
                               cell.setCellValue(resultSet.getInt(1));
                               break;
                           case 1:
                               cell.setCellValue(resultSet.getString(2));
                               break;
                           case 2:
                               cell.setCellValue(resultSet.getString(3));
                               break;
                           case 3:
                               cell.setCellValue(resultSet.getString(4));
                               break;
                           case 4:
                               cell.setCellValue(resultSet.getString(5));
                               break;
                           case 5:
                               cell.setCellValue(resultSet.getString(6));
                               break;
                           case 6:
                               cell.setCellValue(resultSet.getString(7));
                               break;
                           case 7:
                               cell.setCellValue(resultSet.getInt(8));
                               break;
                           case 8:
                               cell.setCellValue(resultSet.getInt(9));
                               break;
                           case 9:
                               cell.setCellValue(resultSet.getString(10));
                               break;
                           case 10:
                               cell.setCellValue((resultSet.getBigDecimal(11).doubleValue()));
                               break;
                           case 11:
                               cell.setCellValue(resultSet.getString(12));
                               break;
                           case 12:
                               cell.setCellValue(resultSet.getString(13));
                               break;
                       }
                       sheet.autoSizeColumn(column);
                   }

               }catch (SQLException e){
                   e.printStackTrace();
               }
            }
        }
        try {
            OutputStream fos = new FileOutputStream("E:/employee.xls");
            ((HSSFWorkbook) wb).write(fos);
            fos.close();
            wb.close();
            System.out.println("report has been created in directory E:/employee.xls");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can't create a report");
        }
    }
}
