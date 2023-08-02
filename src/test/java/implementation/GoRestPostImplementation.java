package implementation;

import logger.Log;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import utils.GoRestUtils;

import java.io.FileInputStream;
import java.util.LinkedHashMap;

public class GoRestPostImplementation {
    public static void getDataFromXlsxFileAndPost() {
//        Try block to handle exceptions
        try {
//          Storing user data from userdata.xlsx in maps
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/userdata.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sh = wb.getSheet("Sheet1");

            LinkedHashMap<String, String> firstUserMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> secondUserMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> thirdUserMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> fourthUserMap = new LinkedHashMap<>();

            for (int i = 0; i < sh.getLastRowNum(); i++) {
                String key = sh.getRow(0).getCell(i).getStringCellValue();
                String value = sh.getRow(1).getCell(i).getStringCellValue();
                firstUserMap.put(key, value);
            }
            for (int i = 0; i < sh.getLastRowNum(); i++) {
                String key = sh.getRow(0).getCell(i).getStringCellValue();
                String value = sh.getRow(2).getCell(i).getStringCellValue();
                secondUserMap.put(key, value);
            }
            for (int i = 0; i < sh.getLastRowNum(); i++) {
                String key = sh.getRow(0).getCell(i).getStringCellValue();
                String value = sh.getRow(3).getCell(i).getStringCellValue();
                thirdUserMap.put(key, value);
            }
            for (int i = 0; i < sh.getLastRowNum(); i++) {
                String key = sh.getRow(0).getCell(i).getStringCellValue();
                String value = sh.getRow(4).getCell(i).getStringCellValue();
                fourthUserMap.put(key, value);
            }
//          Posting the payload using GoRestApi post request
            GoRestUtils.postResponse("/public/v2/users", firstUserMap);
            GoRestUtils.postResponse("/public/v2/users", secondUserMap);
            GoRestUtils.postResponse("/public/v2/users", thirdUserMap);
            GoRestUtils.postResponse("/public/v2/users", fourthUserMap);
            Log.info("Successfully Posted!");
        }
        // Catch block to handle exceptions
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
