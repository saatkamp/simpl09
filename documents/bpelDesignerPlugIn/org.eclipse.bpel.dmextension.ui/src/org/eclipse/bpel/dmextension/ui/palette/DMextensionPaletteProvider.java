package org.eclipse.bpel.dmextension.ui.palette;

import org.eclipse.bpel.common.ui.palette.IPaletteProvider;
import org.eclipse.bpel.common.ui.palette.PaletteCategory;
import org.eclipse.bpel.dmextension.model.ModelPackage;
import org.eclipse.bpel.dmextension.ui.factories.DMextensionUIObjectFactory;
import org.eclipse.bpel.ui.util.BPELCreationToolEntry;
import org.eclipse.gef.palette.PaletteRoot;

public class DMextensionPaletteProvider implements IPaletteProvider{

	@Override
	public void contributeItems(PaletteRoot paletteRoot) {
		// TODO Auto-generated method stub
		PaletteCategory category = new PaletteCategory("DataManagement");
		category.setCategoryId("dataManagement");
		category.setOrder(30);

		category.add(new BPELCreationToolEntry("Data Management Activity",
				"Creates a new Data Management Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getDataManagementActivity())));
		
		category.add(new BPELCreationToolEntry("Create Activity",
				"Creates a new Create Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getCreateActivity())));
		
		category.add(new BPELCreationToolEntry("Select Activity",
				"Creates a new Select Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getSelectActivity())));
		
		category.add(new BPELCreationToolEntry("Insert Activity",
				"Creates a new Insert Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getInsertActivity())));
		
		category.add(new BPELCreationToolEntry("Update Activity",
				"Creates a new Update Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getUpdateActivity())));
		
		category.add(new BPELCreationToolEntry("Delete Activity",
				"Creates a new Delete Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getDeleteActivity())));
		
		category.add(new BPELCreationToolEntry("CallProcedure Activity",
				"Creates a new CallProcedure Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getCallProcedureActivity())));
		
		category.add(new BPELCreationToolEntry("RetrieveSet Activity",
				"Creates a new RetrieveSet Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getRetrieveSetActivity())));
		
		category.add(new BPELCreationToolEntry("WriteBack Activity",
				"Creates a new WriteBack Activity",
				new DMextensionUIObjectFactory(ModelPackage.eINSTANCE
						.getWriteBackActivity())));
		

		paletteRoot.add(category);
	}

}
