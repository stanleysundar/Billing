package com.verizon.bs;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.verizon.bs.utils.Database;
import com.verizon.bs.utils.LoadFileData;

/**
* Servlet to handle File upload request from Client
* @author Javin Paul
*/
public class FileUploadHandler extends HttpServlet {
   //private final String UPLOAD_DIRECTORY = "C:/uploads";
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	   Connection conn = null;	   
	   conn = Database.getConnection();
	   String query = "insert into sm_usage values (?,?,?,?,?,?)";
	   
       //process only if its multipart content
       if(ServletFileUpload.isMultipartContent(request)){
           try {
        	   PreparedStatement ps = conn.prepareStatement(query);
               List<FileItem> multiparts = new ServletFileUpload(
                                        new DiskFileItemFactory()).parseRequest(request);
             
               for(FileItem item : multiparts){
                   if(!item.isFormField()){
                	   
                       String name = new File(item.getName()).getName();
                       LoadFileData.readXls(new File(item.getName()),ps);
                       //item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                   }
               }
          
              //File uploaded successfully
              request.setAttribute("message", "File Uploaded Successfully");
           } catch (Exception ex) {
              request.setAttribute("message", "File Upload Failed due to " + ex);
           }          
        
       }else{
           request.setAttribute("message",
                                "Sorry this Servlet only handles file upload request");
       }
   
       request.getRequestDispatcher("/index.jsp").forward(request, response);
    
   }
 
}


