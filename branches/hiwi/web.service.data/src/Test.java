import web.service.data.WebServiceDataService;
import de.uni_stuttgart.dwarf.www.biif.BiifType;

/**
 * <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 *
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Test {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    WebServiceDataService service = new WebServiceDataService();
    try {
      BiifType data = service.getData(50000);
      System.out.println("DataDesc:" + data.getDescription());
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
