package UI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class excelOperation {
    public excelOperation(){}

    /*public static void main(String[] args) {
        //try {
            System.out.println(readData()[0][0]);


    }*/
    public static String[][] readData()  {
        try {
            String[][] opj = new String[15][15];

            File file = new File("C:\\demo\\test.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();
            //iterating over excel file
            int rowindex=0;
            while (itr.hasNext())
            {
                int cullomindex=0;

                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                while (cellIterator.hasNext())
                {

                    Cell cell = cellIterator.next();
                    switch (cell.getCellType())
                    {

                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                           // System.out.print(cell.getStringCellValue() + "\t\t\t");
                            //System.out.print(rowindex+ "\t\t\t" + cullomindex);

                            opj[rowindex][cullomindex]=cell.getStringCellValue();

                            break;
                        case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                            //System.out.print(cell.getNumericCellValue() + "\t\t\t");
                            opj[rowindex][cullomindex]=cell.getNumericCellValue()+"";
                            break;
                        default:


                    }
                    cullomindex++;
                }
                ///System.out.println("");
                 rowindex++;
            }
            return opj;
        }
        catch(Exception e)   {
            e.printStackTrace();
        }
        return null;


    }
}
