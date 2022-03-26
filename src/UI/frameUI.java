package UI;

import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
public class frameUI extends JFrame {

    java.lang.String[][] ss = readData();
    //////////////////////left
    private final JPanel leftPanel = new JPanel();
     private java.lang.String[] tablecols = {"","","","",""};
    private final JPanel downPanel1 = new JPanel();
    private final java.lang.String[][] tableDate = ss;

    private final JTable invoiceTable = new JTable(tableDate,tablecols);
    private final JButton createInvoice = new JButton("Create New Invoice");
    private final JButton deleteInvoice = new JButton("Delete Invoice");


    /////////////////////////////////right
    private final JPanel rightPanel = new JPanel();
    private final JPanel tablePanel = new JPanel();
    private final JPanel labelPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JLabel invoiceNumber = new JLabel("Invoice Item");
    private final JLabel invoiceDate = new JLabel("Invoice Date");
    private final JLabel CustomerName = new JLabel("Customer Name");
    private final JLabel invoiceTotal = new JLabel("Invoice Total");

    private final JLabel invoiceNumberText = new JLabel(" gggggggggg ");
    private final JTextField invoiceDateText = new JTextField(10);
    private final JTextField customerNameText = new JTextField(10);
    private final JLabel invoiceTotalText = new JLabel("aaaaaaaaaaaa  ");


    private java.lang.String [] itemHeader = {"NO.","Item Name","Item Price","Count","Item Total"};
    private final String[][] itemData = new String[2][5];

    private final JTable InvoiceItem = new JTable(itemData, itemHeader);

    private final JButton saveButton = new JButton("Save");
    private final JButton cancelButton = new JButton("Cancel");

    // menu creation

    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenuItem load;
    private final JMenuItem save;
///////////////////////////////


    public frameUI() {
        super("Sales Invoice");
        setLayout(new GridLayout(1, 2));
        //create fileMenu
        menuBar = new JMenuBar();
        load = new JMenuItem("Load", 'o');
        load.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.ALT_DOWN_MASK));
        save = new JMenuItem("Save", 's');
        save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.ALT_DOWN_MASK));
        fileMenu = new JMenu("File");
        fileMenu.add(load);
        fileMenu.add(save);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        /////////////////////////////////////////////////////
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.setSize(300, 600);
        leftPanel.add(new JScrollPane(invoiceTable));

        downPanel1.add(createInvoice);
        downPanel1.add(deleteInvoice);
        leftPanel.add(downPanel1);


        ////////////////////////////////right
        rightPanel.setLayout(new GridLayout(3, 1));
        rightPanel.setSize(350, 600);
        rightPanel.setLocation(300, 5);
        labelPanel.setSize(250, 50);
        labelPanel.setLayout(new GridLayout(4, 2));
        labelPanel.add(invoiceNumber);
        labelPanel.add(invoiceNumberText);
        labelPanel.add(invoiceDate);
        labelPanel.add(invoiceDateText);
        labelPanel.add(CustomerName);
        labelPanel.add(customerNameText);
        labelPanel.add(invoiceTotal);
        labelPanel.add(invoiceTotalText);

        rightPanel.add(labelPanel);

        tablePanel.add(new JScrollPane(InvoiceItem));
        rightPanel.add(tablePanel);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        rightPanel.add(buttonPanel);
        add(leftPanel);
        add(rightPanel);

        //////////////////////////////////
        setSize(800, 600);
        setLocation(300, 250);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(java.lang.String[] args) {
        frameUI f = new frameUI();
    }


    public static java.lang.String[][] readData() {
        try {
           java.lang.String[][] opj = new java.lang.String[15][15];

            File file = new File("C:\\demo\\test.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();
            //iterating over excel file
            int rowindex = 0;
            while (itr.hasNext()) {
                int cullomindex = 0;

                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                            // System.out.print(cell.getStringCellValue() + "\t\t\t");
                            //System.out.print(rowindex+ "\t\t\t" + cullomindex);

                            opj[rowindex][cullomindex] = cell.getStringCellValue();

                            break;
                        case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                            //System.out.print(cell.getNumericCellValue() + "\t\t\t");
                            opj[rowindex][cullomindex] = cell.getNumericCellValue() + "";
                            break;
                        default:


                    }
                    cullomindex++;
                }
                ///System.out.println("");
                rowindex++;
            }
            return opj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public void export(JTable table, File file){
        try
        {
            TableModel m = table.getModel();
            FileWriter fw = new FileWriter(file);
            for(int i = 0; i < m.getColumnCount(); i++){
                fw.write(m.getColumnName(i) + "\t");
            }
            fw.write("\n");
            for(int i=0; i < m.getRowCount(); i++) {
                for(int j=0; j < m.getColumnCount(); j++) {
                    fw.write(m.getValueAt(i,j).toString()+"\t");
                }
                fw.write("\n");
            }
            fw.close();
        }
        catch( IOException e){ System.out.println(e); }
    }
}
