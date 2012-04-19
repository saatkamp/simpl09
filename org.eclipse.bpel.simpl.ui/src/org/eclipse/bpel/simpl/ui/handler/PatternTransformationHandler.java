package org.eclipse.bpel.simpl.ui.handler;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Sequence;
import org.eclipse.bpel.ui.BPELMultipageEditorPart;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class PatternTransformationHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchPart iWorkbenchPart = HandlerUtil.getActivePart(event);
    BPELMultipageEditorPart bpelMultipageEditorPart = (BPELMultipageEditorPart) iWorkbenchPart;
    Process process = bpelMultipageEditorPart.getProcess();
    if (process.getActivity() instanceof Sequence) {
      System.out.println("HUHU!!!");
    }
    return null;
  }
}
