/**
 * <b>Purpose:</b> This class adds all {@link DataManagementActivity}s to the BPEL Designer palette.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.palette;

import org.eclipse.bpel.common.ui.palette.IPaletteProvider;
import org.eclipse.bpel.common.ui.palette.PaletteCategory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.ui.factories.DataManagementUIObjectFactory;
import org.eclipse.bpel.ui.util.BPELCreationToolEntry;
import org.eclipse.gef.palette.PaletteRoot;

public class DataManagementPaletteProvider implements IPaletteProvider {

  /*
   * (non-Javadoc)
   * @see
   * org.eclipse.bpel.common.ui.palette.IPaletteProvider#contributeItems(org.eclipse.gef
   * .palette.PaletteRoot)
   */
  @Override
  public void contributeItems(PaletteRoot paletteRoot) {
    PaletteCategory category = new PaletteCategory("DataManagement");
    category.setCategoryId("dataManagement");
    category.setOrder(40);

    category.add(new BPELCreationToolEntry("Query", "Creates a new Query Activity",
        new DataManagementUIObjectFactory(ModelPackage.eINSTANCE.getQueryActivity())));

    category.add(new BPELCreationToolEntry("Issue", "Creates a new Issue Activity",
        new DataManagementUIObjectFactory(ModelPackage.eINSTANCE.getIssueActivity())));

    category.add(new BPELCreationToolEntry("RetrieveData",
        "Creates a new RetrieveData Activity", new DataManagementUIObjectFactory(
            ModelPackage.eINSTANCE.getRetrieveDataActivity())));

    category.add(new BPELCreationToolEntry("WriteDataBack",
        "Creates a new WriteDataBack Activity", new DataManagementUIObjectFactory(
            ModelPackage.eINSTANCE.getWriteDataBackActivity())));

    category.add(new BPELCreationToolEntry("Transfer", "Creates a new Transfer Activity",
        new DataManagementUIObjectFactory(ModelPackage.eINSTANCE.getTransferActivity())));

    paletteRoot.add(category);
  }

}
