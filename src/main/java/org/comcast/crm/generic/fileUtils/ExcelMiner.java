package org.comcast.crm.generic.fileUtils;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.*;

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

public class ExcelMiner implements Closeable {
    private final Workbook workbook;
    private Sheet sheet;
    private final String path;

    /**
     * Constructs an ExcelMiner object and loads the Excel workbook from the given file path.
     *
     * @param path the full path to the Excel (.xlsx or .xls) file
     */
    public ExcelMiner(String path) throws IOException {
        this.path = path;

        try (FileInputStream fis = new FileInputStream(path)) {
            workbook = WorkbookFactory.create(fis);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Loads a specific sheet from the Excel workbook by its name.
     *
     * @param sheetName the name of the sheet to load
     * @throws SheetNotAvailableException if {@code sheetName} does not exist.
     */
    public void loadSheet(String sheetName) {
        sheetName = sheetName.trim();
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
        {
            throw new SheetNotAvailableException(sheetName + " is not present in workbook.");
        }
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

    /**
     *
     * @param row the row number in which you wish to write the data
     * @param col the column number in which you wish to write the data
     * @param data the data you want to insert into the cell
     * @throws IOException if the file doesn't exist
     */

    public void insertData(int row, int col, String data) throws IOException {
        insertData(this.sheet.getSheetName(), row, col, data);
    }


    /**
     *
     * @param sheetName name of the sheet in which you want to insert data
     * @param row the row number in which you wish to write the data
     * @param col the column number in which you wish to write the data
     * @param data the data you want to insert into the cell
     * @throws IOException if the file doesn't exist
     */
    public void insertData(String sheetName, int row, int col, String data) throws IOException {
        Sheet sheet = workbook.getSheet(sheetName);
        Row r = sheet.getRow(row);
        if (r == null) {
            r = sheet.createRow(row);
        }
        Cell c = r.getCell(col);
        if(c == null) {
            c = r.createCell(col);
        }
        c.setCellValue(data);
        try (FileOutputStream fos = new FileOutputStream(path)) {
            workbook.write(fos);
        }
    }

    @Override
    public void close() throws IOException {
        workbook.close();
    }
}


/**
 * Exception thrown when a requested sheet is not found in the Excel workbook.
 * <p>
 * This exception is typically used in scenarios where an attempt is made to load or access
 * a sheet by name, but the sheet does not exist in the workbook. It extends {@link RuntimeException},
 * so it is unchecked and does not require mandatory handling.
 * </p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>{@code
 * ExcelMiner excel = new ExcelMiner("data.xlsx");
 * excel.loadSheet("NonExistentSheet"); // throws SheetNotAvailableException
 * }</pre>
 *
 * @author Siddharth Malviya
 * @since 1.0
 */
class SheetNotAvailableException extends RuntimeException {
    public SheetNotAvailableException(String message) {
        super(message);
    }
}