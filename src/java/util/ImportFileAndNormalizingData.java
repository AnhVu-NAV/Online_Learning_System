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
public class ImportFileAndNormalizingData {

    private final static Logger logger = LogManager.getLogger(ImportFileAndNormalizingData.class);

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
                        case STRING ->
                            string += cell.getStringCellValue() + " ";
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

    public static String getOption(String option, int count) throws Exception {
        if (option.matches("[A-Za-z]")) {
            return option;
        }
        throw new Exception("Input must be a single alphabetic character." + "Error in " + count + "line.");
    }

    public static String getContent(String content, int count) throws Exception {
        if (!content.isEmpty()) {
            return content;
        }
        throw new Exception("Content must not be empty" + "Error in " + count + "line.");
    }

    public static String getExplaination(String ex, int count) throws Exception {
        if (!ex.isEmpty()) {
            return ex;
        }
        throw new Exception("Explaination should not be empty\n" + "Error in " + count + "line.");
    }

    public static int getLevelOfQuestion(String level, int count) throws Exception {
        String error;
        if (level.toLowerCase().equals("difficult")) {
            return 2;
        } else if (level.toLowerCase().equals("easy")) {
            return 0;
        } else if (level.toLowerCase().equals("normal")) {
            return 1;
        } else if (level.isEmpty()) {
            error = "Level of question should not be empty\n" + "Error in " + count + "line.";
        } else {
            error = "Level of question should be Difficult, Easy or Normal\n" + "Error in " + count + "line.";
        }
        throw new Exception(error);
    }

    public static int getTypeOfQuestion(String type, int count) throws Exception {
        String error;
        if (type.toLowerCase().equals("essay question")) {
            return 1;
        } else if (type.toLowerCase().equals("multiple choice question")) {
            return 2;
        } else if (type.isEmpty()) {
            error = "Type of question should not be empty\n" + "Error in " + count + "line.";
        } else {
            error = "Type of question should be Essay Question or Multiple Choice Question\n" + "Error in " + count + "line.";
        }
        throw new Exception(error);
    }
    
}
