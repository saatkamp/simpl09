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

public class DataManagementPaletteProvider implements IPaletteProvider{

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.common.ui.palette.IPaletteProvider#contributeItems(org.eclipse.gef.palette.PaletteRoot)
	 */
	@Override
	public void contributeItems(PaletteRoot paletteRoot) {
		// TODO Auto-generated method stub
		PaletteCategory category = new PaletteCategory("DataManagement");
		category.setCategoryId("dataManagement");
		category.setOrder(40);
		
		category.add(new BPELCreationToolEntry("Query Activity",
				"Creates a new Query Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getQueryActivity())));
		
		category.add(new BPELCreationToolEntry("Insert Activity",
				"Creates a new Insert Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getInsertActivity())));
		
		category.add(new BPELCreationToolEntry("Update Activity",
				"Creates a new Update Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getUpdateActivity())));
		
		category.add(new BPELCreationToolEntry("Delete Activity",
				"Creates a new Delete Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getDeleteActivity())));
		
		category.add(new BPELCreationToolEntry("Create Activity",
				"Creates a new Create Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getCreateActivity())));
		
		category.add(new BPELCreationToolEntry("Drop Activity",
				"Creates a new Drop Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getDropActivity())));
		
		category.add(new BPELCreationToolEntry("Call Activity",
				"Creates a new Call Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getCallActivity())));
		
		category.add(new BPELCreationToolEntry("RetrieveData Activity",
				"Creates a new RetrieveData Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getRetrieveDataActivity())));
		
		category.add(new BPELCreationToolEntry("Transfer Activity",
				"Creates a new Transfer Activity",
				new DataManagementUIObjectFactory(ModelPackage.eINSTANCE
						.getTransferActivity())));

		paletteRoot.add(category);
	}

}
