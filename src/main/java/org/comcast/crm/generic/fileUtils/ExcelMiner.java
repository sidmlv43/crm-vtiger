package org.comcast.crm.generic.fileUtils;

import java.io.FileInputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Utility class for reading data from Excel files using Apache POI.
 * <p>
 * This class allows loading an Excel workbook and reading data from specific sheets and cells.
 * It supports retrieving string cell data and row counts from a sheet.
 * </p>
 * <p>
 * Example usage:
 * <pre>{@code
 * ExcelMiner excel = new ExcelMiner("data.xlsx");
 * excel.loadSheet("Sheet1");
 * String value = excel.getCellData(0, 1);
 * int rowCount = excel.getRowCount();
 * }</pre>
 *
 * @author [Siddharth Malviya]
 * @version 1.0
 */

public class ExcelMiner {
    private Workbook workbook;
    private Sheet sheet;

    /**
     * Constructs an ExcelMiner object and loads the Excel workbook from the given file path.
     *
     * @param path the full path to the Excel (.xlsx or .xls) file
     */
    public ExcelMiner(String path) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a specific sheet from the Excel workbook by its name.
     *
     * @param sheetName the name of the sheet to load
     */
    public void loadSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    /**
     * Retrieves the string value from a cell in the currently loaded sheet.
     *
     * @param row the zero-based row index
     * @param col the zero-based column index
     * @return the string value of the specified cell
     * @throws NullPointerException if the row or cell does not exist
     */
    public String getCellData(int row, int col) {
        Row r = sheet.getRow(row);
        return r.getCell(col).getStringCellValue();
    }


    /**
     * Returns the index of the last row number in the currently loaded sheet.
     * Row indexing starts from 0.
     *
     * @return the index of the last row in the loaded sheet
     */

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    /**
     * Returns the index of the last row number in a specific sheet.
     *
     * @param sheetName the name of the sheet to check
     * @return the index of the last row in the given sheet
     */
    public int getRowCount(String sheetName) {
        return this.workbook.getSheet(sheetName).getLastRowNum();
    }


}
