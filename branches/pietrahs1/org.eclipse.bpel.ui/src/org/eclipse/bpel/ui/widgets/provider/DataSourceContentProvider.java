package org.eclipse.bpel.ui.widgets.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.simpl.resource.management.data.DataSource;

public class DataSourceContentProvider implements IStructuredContentProvider {

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof java.util.List<?>) {
      @SuppressWarnings("unchecked")
      List<DataSource> references = (List<DataSource>) inputElement;

      return references.toArray();
    } else if (inputElement instanceof ExtendedStringArray) {

      return new Object[] { inputElement };
    }
    return null;
  }

  @Override
  public void dispose() {
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }
}
