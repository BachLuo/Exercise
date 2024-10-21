package core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUltils {
	public static Object[][] testReadCellsByColumnNames(String filePath, String sheetName, String[] columnNames, int rowIndex) {
        Map<String, String> cellValues = readCellsByColumnNames(filePath, sheetName, columnNames, rowIndex);
        String[][] table = new String[1][columnNames.length];
        
        // Lưu dữ liệu vào String[] từ Map
        for (int i = 0; i < columnNames.length; i++) {
            String columnName = columnNames[i];
            table[0][i] = cellValues.get(columnName); // Lấy giá trị từ Map
        }
        return table;
    }

    private static Map<String, String> readCellsByColumnNames(String filePath, String sheetName, String[] columnNames, int rowIndex) {
        Map<String, String> cellValues = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0); // Vì header luôn là dòng thứ 0
            HashMap<String, Integer> columnIndexMap = new HashMap<>();

            // Build map dựa trên chỉ số cột
            for (Cell cell : headerRow) {
                columnIndexMap.put(cell.getStringCellValue(), cell.getColumnIndex());
            }

            // Đọc data với tên cột và dòng tương ứng
            Row targetRow = sheet.getRow(rowIndex);
            if (targetRow != null) {
                for (String columnName : columnNames) {
                    Integer columnIndex = columnIndexMap.get(columnName);
                    if (columnIndex != null) {
                        Cell cell = targetRow.getCell(columnIndex);
                        if (cell != null) {
                            cellValues.put(columnName, cell.toString());
                        } else {
                            cellValues.put(columnName, ""); // Handle null cell
                        }
                    } else {
                        System.out.println("Column not found: " + columnName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cellValues;
    }
}
