package implementation;

import io.restassured.response.Response;
import logger.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import utils.GoRestUtils;

import java.io.*;
import java.util.ArrayList;

public class GoRestGetImplementation {
    public static void getDataAndAddToXlsxFile() {
//        Try block to handle exceptions
        try {
//          Storing user data fetched from GoRestApi
            Response res = GoRestUtils.getResponse("/public/v2/users");
            ArrayList<Object> userDataList = res.path("");
            ArrayList<String> idList = new ArrayList<>();
            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> emailList = new ArrayList<>();

//          Storing id,name and email data from userDataList
            for (int i = userDataList.size() - 1; i >= 0; i--) {
                idList.add(userDataList.get(i).toString().split(",")[0].split("=")[1]);
                nameList.add(userDataList.get(i).toString().split(",")[1].split("=")[1]);
                emailList.add(userDataList.get(i).toString().split(",")[2].split("=")[1]);
            }
//          Setting up excel workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" User Data ");

//          Setting up row and column vars along with headers of name,id and email
            Row row;
            Cell cell;

            row = spreadsheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("id");

            cell = row.createCell(1);
            cell.setCellValue("name");

            cell = row.createCell(2);
            cell.setCellValue("email");

//          Writing data in excel file
            int rowId = 1;
            for (int i = 0; i < idList.size(); i++) {
                row = spreadsheet.createRow(rowId++);
                cell = row.createCell(0);
                cell.setCellValue(idList.get(i));
                cell = row.createCell(1);
                cell.setCellValue(nameList.get(i));
                cell = row.createCell(2);
                cell.setCellValue(emailList.get(i));

            }
//          Writing data to workbook and providing its save path
            FileOutputStream out = new FileOutputStream((System.getProperty("user.dir") + "/src/test/resources/ApiTest2.xlsx"));
            workbook.write(out);
            out.close();

            Assert.assertEquals(res.getStatusCode(), 200);
            Log.info("Booking IDs fetched successfully!");

            // Catch block to handle exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
