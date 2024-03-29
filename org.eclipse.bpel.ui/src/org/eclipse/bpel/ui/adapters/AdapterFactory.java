package org.eclipse.bpel.ui.adapters;


import org.eclipse.bpel.model.adapters.AdapterProvider;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IStatus;


/**
 * An adapter factory.  
 *  
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Sep 19, 2006
 *
 */

@SuppressWarnings({"boxing","nls","restriction"})

public class AdapterFactory implements IAdapterFactory {

	/** We adapt to these types ... */	
	
	static Class<?> [] adapterList = { 
			ILabeledElement.class ,
			IStatus.class
	};
	
	AdapterProvider provider = new AdapterProvider();
	
	/**
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	
	public Class<?>[] getAdapterList() {
		return adapterList;
	}

	
	/**
	 * Get the adapter for the requested adaptable object.
	 * 
	 * @param adaptableObject adaptable object.
	 * @param adapterType the adapter type
	 * @return the adapter or null 
	 */
	
	@SuppressWarnings("unchecked")
	public Object getAdapter ( Object adaptableObject, Class adapterType ) {
		
		if (adapterType == ILabeledElement.class) {								
			if (adaptableObject instanceof Throwable) {
				return provider.getAdapter( JavaThrowableAdapter.class , adaptableObject );				
			}			
		} else if (adapterType == IStatus.class) {
			if (adaptableObject instanceof IMarker) {
				return provider.getAdapter( MarkerAdapter.class, adaptableObject );
			}
		}
			
		
		return null;
	}


	
	

}
