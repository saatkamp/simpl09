package org.simpl.resource.management.webinterface;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * <b>Purpose:</b>Offers operations for multipart forms that handle data in another
 * way.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class MultipartForm {
  @SuppressWarnings("rawtypes")
  public static HashMap<String, String> getParameters(HttpServletRequest request) {
    HashMap<String, String> parameters = new HashMap<String, String>();
    DiskFileItemFactory fileItemFactory = null;
    ServletFileUpload uploadHandler = null;
    File tmpDir = new File(System.getProperty("java.io.tmpdir"));

    try {
      fileItemFactory = new DiskFileItemFactory();
      fileItemFactory.setRepository(tmpDir);

      uploadHandler = new ServletFileUpload(fileItemFactory);

      // parse request
      List formItems = uploadHandler.parseRequest(request);
      Iterator itr = formItems.iterator();

      while (itr.hasNext()) {
        FileItem item = (FileItem) itr.next();

        if (item.isFormField()) {
          // handle form field
          parameters.put(item.getFieldName(), item.getString());
        } else {
          // handle policy file
          byte[] buffer = new byte[(int) item.getSize()];
          BufferedInputStream f = new BufferedInputStream(item.getInputStream());
          f.read(buffer);
          parameters.put(item.getFieldName(), new String(buffer));
        }
      }
    } catch (FileUploadException e) {
      // TODO
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return parameters;
  }
}