/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.*;
import java.util.*;
import org.apache.logging.log4j.*;
import org.apache.poi.ss.usermodel.*;
import static org.apache.poi.ss.usermodel.CellType.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author ADMIN
 */
public class ImportFile {

    private final static Logger logger = LogManager.getLogger(ImportFile.class);

    //test done
    public static List<String[]> readXlsx(String filePath) {
        List<String> list = new ArrayList<>();
        List<String[]> listData = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath)) {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                String string = "";
                for (Cell cell : sheet.getRow(i)) {
                    switch (formula.evaluate(cell).getCellType()) {
                        case STRING -> string += cell.getStringCellValue() + " ";
                        case NUMERIC -> {
                            int value = (int) cell.getNumericCellValue();
                            string += value + " ";
                        }
                    }
                }
                list.add(string);
            }

            for (String s : list) {
                String[] components = s.split(" ");
                listData.add(components);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return listData;
    }

    //test done
    public static List<String[]> readCSV(String filePath) {
        List<String> list = new ArrayList<>();
        List<String[]> listData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            for (String string : list) {
                String[] components = string.split(";");
                listData.add(components);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return listData;
    }
}
