/**
 * <b>Purpose:</b> This class adds all {@link DataManagementActivity}s to the BPEL Designer palette.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataManagementPaletteProvider.java 1755 2011-01-17 16:16:42Z michael.schneidt@arcor.de $ <br>
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

    category.add(new BPELCreationToolEntry("QueryData", "Creates a new QueryData Activity",
        new DataManagementUIObjectFactory(ModelPackage.eINSTANCE.getQueryDataActivity())));

    category.add(new BPELCreationToolEntry("IssueCommand", "Creates a new IssueCommand Activity",
        new DataManagementUIObjectFactory(ModelPackage.eINSTANCE.getIssueCommandActivity())));

    category.add(new BPELCreationToolEntry("RetrieveData",
        "Creates a new RetrieveData Activity", new DataManagementUIObjectFactory(
            ModelPackage.eINSTANCE.getRetrieveDataActivity())));

    category.add(new BPELCreationToolEntry("WriteDataBack",
        "Creates a new WriteDataBack Activity", new DataManagementUIObjectFactory(
            ModelPackage.eINSTANCE.getWriteDataBackActivity())));

    category.add(new BPELCreationToolEntry("TransferData", "Creates a new TransferData Activity",
        new DataManagementUIObjectFactory(ModelPackage.eINSTANCE.getTransferDataActivity())));

    paletteRoot.add(category);
  }

}
