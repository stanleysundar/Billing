package com.verizon.bs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import com.google.gson.JsonObject;
 
public class ExcelOrderFileUpload extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    private static final String TMP_DIR_PATH = "/MyTempFiles";
    private File tmpDir;
    private static final String DESTINATION_DIR_PATH ="/MySavedFiles";
    private File destinationDir;
 
    public ExcelOrderFileUpload() {
        super();
    }
 
    public void init(ServletConfig config) throws ServletException {
 
        super.init(config);
        String realPath = getServletContext().getRealPath(TMP_DIR_PATH);
        tmpDir = new File(realPath);
        if(!tmpDir.isDirectory()) {
            throw new ServletException(TMP_DIR_PATH + " is not a directory");
        }
 
        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
        destinationDir = new File(realPath);
        if(!destinationDir.isDirectory()) {
            throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
        }
 
    }
 
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        //PrintWriter to send the JSON response back
        PrintWriter out = response.getWriter();
 
        //set content type and header attributes
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
 
        //Set the size threshold, above which content will be stored on disk.
        fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
 
        //Set the temporary directory to store the uploaded files of size above threshold.
        fileItemFactory.setRepository(tmpDir);
 
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        JsonObject myObj = new JsonObject();
 
        String fileName = null;
        String fullName = null;
        File file = null;
 
        try {
 
            //Parse the request
            List items = uploadHandler.parseRequest(request);
            Iterator iterator = items.iterator();
            while(iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
 
                //Handle Form Fields
                if(item.isFormField()) {
                    System.out.println("Field Name = " + item.getFieldName() + ", Value = " + item.getString());
                    if(item.getFieldName().trim().equalsIgnoreCase("filename")){
                        fileName = item.getString().trim();
                    }
                } 
 
                //Handle Uploaded files.
                else {
                    System.out.println("Field Name = " + item.getFieldName()+
                            ", File Name = "+ item.getName()+
                            ", Content type = "+item.getContentType()+
                            ", File Size = "+item.getSize());
                    fullName = item.getName().trim();
 
                    //Write file to the ultimate location.
                    file = new File(destinationDir,item.getName());
                    item.write(file);
                }
 
 
 
            }
 
            int count = 0;
            String extension = FilenameUtils.getExtension(fullName);
            if(extension.trim().equalsIgnoreCase("xlsx")){
                count = processExcelFile(file);
            }
            else if(extension.trim().equalsIgnoreCase("xls")){
                //process your binary excel file
            }
            if(extension.trim().equalsIgnoreCase("csv")){
                //process your CSV file
            }
 
            myObj.addProperty("success", true);
            myObj.addProperty("message", count + " item(s) were processed for file " + fileName);
            out.println(myObj.toString());
 
        }
        catch(FileUploadException ex) {
            log("Error encountered while parsing the request",ex);
            myObj.addProperty("success", false);
            out.println(myObj.toString());
        } catch(Exception ex) {
            log("Error encountered while uploading file",ex);
            myObj.addProperty("success", false);
            out.println(myObj.toString());
        }
 
        out.close();
 
    }
 
    private int processExcelFile(File file){
 
 
        int count = 0;
 
        try{
            // Creating Input Stream 
            FileInputStream myInput = new FileInputStream(file);
 
            // Create a workbook using the File System 
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
 
            // Get the first sheet from workbook 
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
 
            /** We now need something to iterate through the cells.**/
            Iterator<Row> rowIter = mySheet.rowIterator();
            while(rowIter.hasNext()){
 
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                while(cellIter.hasNext()){
 
                    XSSFCell myCell = (XSSFCell) cellIter.next();
                    //get cell index
                    System.out.println("Cell column index: " + myCell.getColumnIndex());
                    //get cell type
                    System.out.println("Cell Type: " + myCell.getCellType());
 
                    //get cell value
                    switch (myCell.getCellType()) {
                    case XSSFCell.CELL_TYPE_NUMERIC :
                        System.out.println("Cell Value: " + myCell.getNumericCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_STRING:   
                        System.out.println("Cell Value: " + myCell.getStringCellValue());
                        break;
                    default:   
                        System.out.println("Cell Value: " + myCell.getRawValue());
                        break;   
                    }
                    System.out.println("---");
 
                    if(myCell.getColumnIndex() == 0 && 
                            !myCell.getStringCellValue().trim().equals("") &&
                            !myCell.getStringCellValue().trim().equals("Item Number")){
                        count++;
                    }
 
                }
 
            }
        }
        catch (Exception e){
            e.printStackTrace(); 
        }
 
        return count;
 
    }
    
}

