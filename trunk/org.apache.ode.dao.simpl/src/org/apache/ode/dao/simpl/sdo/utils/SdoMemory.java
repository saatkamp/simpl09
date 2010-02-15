package org.apache.ode.dao.simpl.sdo.utils;

import org.apache.ode.dao.simpl.sdo.AbstractSDO;
import org.apache.ode.dao.simpl.sdo.ActivityRecoverySDO;
import org.apache.ode.dao.simpl.sdo.CorrelationSetSDO;
import org.apache.ode.dao.simpl.sdo.CorrelatorSDO;
import org.apache.ode.dao.simpl.sdo.EventSDO;
import org.apache.ode.dao.simpl.sdo.FaulSDO;
import org.apache.ode.dao.simpl.sdo.PartnerLinkSDO;
import org.apache.ode.dao.simpl.sdo.ProcessInstanceSDO;
import org.apache.ode.dao.simpl.sdo.ProcessSDO;
import org.apache.ode.dao.simpl.sdo.ScopeSDO;

public class SdoMemory {
	
	
    private AbstractSDO abstractSDO = null;
    
    private ActivityRecoverySDO activityRecoverySDO = null;
    
    private CorrelationSetSDO correlationSetSDO = null;
    
    private CorrelatorSDO correlatorSDO = null;
    
    private EventSDO eventSDO = null;
     
    private FaulSDO faulSDO = null;
    
    private PartnerLinkSDO linkSDO = null;
    
    private ProcessInstanceSDO instanceSDO = null;
    
    private ProcessSDO processSDO = null;
    
    private ScopeSDO scopeSDO = null;
    
    private static SdoMemory memory = null;
	
	
	private SdoMemory() {
		XsdTypeLoader.defineSIMPLTypes();
		
	}
	
	public AbstractSDO getAbstractSDO() {
		return abstractSDO;
	}

	public ActivityRecoverySDO getActivityRecoverySDO() {
		return activityRecoverySDO;
	}

	public CorrelationSetSDO getCorrelationSetSDO() {
		return correlationSetSDO;
	}

	public CorrelatorSDO getCorrelatorSDO() {
		return correlatorSDO;
	}

	public EventSDO getEventSDO() {
		return eventSDO;
	}

	public FaulSDO getFaulSDO() {
		return faulSDO;
	}

	public PartnerLinkSDO getLinkSDO() {
		return linkSDO;
	}

	public ProcessInstanceSDO getInstanceSDO() {
		return instanceSDO;
	}

	public ProcessSDO getProcessSDO() {
		if (processSDO == null) {
			processSDO = new ProcessSDO();
		}
		return processSDO;
	}

	public ScopeSDO getScopeSDO() {
		return scopeSDO;
	}

	public static SdoMemory getInstance() {
		if (memory == null) {
			memory = new SdoMemory();
			
		}
		
		return memory;
	}

	
	
	

}
