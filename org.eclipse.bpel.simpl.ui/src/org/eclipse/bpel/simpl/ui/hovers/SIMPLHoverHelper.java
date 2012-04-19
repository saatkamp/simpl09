package org.eclipse.bpel.simpl.ui.hovers;

import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.IHoverHelper;
import org.eclipse.bpel.ui.adapters.IAnnotatedElement;
import org.eclipse.bpel.ui.adapters.ILabeledElement;
import org.eclipse.bpel.ui.factories.AbstractUIObjectFactory;
import org.eclipse.bpel.ui.factories.UIObjectFactoryProvider;
import org.eclipse.core.resources.IMarker;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */

/*
 * This class is based on a 1:1 copy of the
 * org.eclipse.bpel.ui.hovers.DefaultHoverHelper class. By implementing the
 * extension point for hover help for this plug-in the existing implementation
 * of the BPEL Designer wouldn't be executed at all. In this case we copied the
 * code and extended it with new functionality for this plug-in.
 * 
 * This is also a really bad low-level implementation and should be changed as
 * soon as possible.
 */
public class SIMPLHoverHelper implements IHoverHelper {

  public IFigure getHoverFigure(EObject modelObject) {

    Figure panel = new Figure();
    panel.setLayoutManager(new GridLayout(2, false));

    if (modelObject instanceof DataManagementActivity) {
      DataManagementActivity dm = (DataManagementActivity) modelObject;
      String name = dm.getName();
      String type = getUIObjectFactory(modelObject).getTypeLabel();

      if (name == null || type == null) {
        return null;
      }

      Label title = new BoldLabel();
      title.setIcon(getUIObjectFactory(modelObject).getSmallImage());
      title.setIconAlignment(PositionConstants.TOP);
      title.setText(type);

      panel.add(title);

      Label label = new Label();
      if (!name.equals(type)) {
        label.setText(name);
      }
      panel.add(label);
      
      if (modelObject instanceof DataManagementActivity) {
        if (((DataManagementActivity) modelObject).getDataResource() != null) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getDataManagementActivity_DataResource().getName() + " :"));
          panel.add(new Label(((DataManagementActivity) modelObject)
              .getDataResource()));
        }
        if (((DataManagementActivity) modelObject).getDmCommand() != null) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getDataManagementActivity_DmCommand().getName() + " :"));
          panel.add(new Label(((DataManagementActivity) modelObject)
              .getDmCommand()));
        }
      }
      if (modelObject instanceof QueryDataActivity) {
        if (!((QueryDataActivity) modelObject).getTargetContainer().isEmpty()) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getQueryDataActivity_TargetContainer().getName() + " :"));
          panel.add(new Label(((QueryDataActivity) modelObject)
              .getTargetContainer()));
        }
      }
      if (modelObject instanceof RetrieveDataActivity) {
        if (((RetrieveDataActivity) modelObject).getTargetVariable() != null
            && !((RetrieveDataActivity) modelObject).getTargetVariable()
                .getName().isEmpty()) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getRetrieveDataActivity_TargetVariable().getName() + " :"));
          panel.add(new Label(((RetrieveDataActivity) modelObject)
              .getTargetVariable().getName()));
        }
      }
      if (modelObject instanceof WriteDataBackActivity) {
        if (((WriteDataBackActivity) modelObject).getDataVariable() != null
            && !((WriteDataBackActivity) modelObject).getDataVariable()
                .getName().isEmpty()) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getWriteDataBackActivity_DataVariable().getName() + " :"));
          panel.add(new Label(((WriteDataBackActivity) modelObject)
              .getDataVariable().getName()));
          panel.add(new Label(ModelPackage.eINSTANCE
              .getWriteDataBackActivity_TargetContainer().getName() + " :"));
          panel.add(new Label(((WriteDataBackActivity) modelObject)
              .getTargetContainer()));
        }
      }
      if (modelObject instanceof TransferDataActivity) {
        if (!((TransferDataActivity) modelObject).getDataSource().isEmpty()) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getTransferDataActivity_DataSource().getName() + " :"));
          panel.add(new Label(((TransferDataActivity) modelObject)
              .getDataSource()));
        }
        if (!((TransferDataActivity) modelObject).getDataSourceCommand()
            .isEmpty()) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getTransferDataActivity_DataSourceCommand().getName() + " :"));
          panel.add(new Label(((TransferDataActivity) modelObject)
              .getDataSourceCommand()));
        }
        if (!((TransferDataActivity) modelObject).getDataSink().isEmpty()) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getTransferDataActivity_DataSink().getName() + " :"));
          panel.add(new Label(((TransferDataActivity) modelObject)
              .getDataSink()));
        }
        if (!((TransferDataActivity) modelObject).getDataSinkContainer()
            .isEmpty()) {
          panel.add(new Label(ModelPackage.eINSTANCE
              .getTransferDataActivity_DataSinkContainer().getName() + " :"));
          panel.add(new Label(((TransferDataActivity) modelObject)
              .getDataSinkContainer()));
        }
      }
    } else {
      ILabeledElement e = getLabeledElement(modelObject);
      if (e == null) {
        return null;
      }
      String name = e.getLabel(modelObject);
      String type = e.getTypeLabel(modelObject);

      Label title = new BoldLabel();
      title.setIcon(e.getSmallImage(modelObject));
      title.setIconAlignment(PositionConstants.TOP);
      title.setText(type);

      panel.add(title);

      Label label = new Label();
      if (!name.equals(type)) {
        label.setText(name);
      }
      panel.add(label);

      if (e instanceof IAnnotatedElement) {
        String annotation[] = ((IAnnotatedElement) e)
            .getAnnotation(modelObject);
        if (annotation != null) {
          for (int i = 0; i < annotation.length; i += 2) {
            if (annotation[i + 1] != null && annotation[i + 1].length() > 0) {
              panel.add(new Label(annotation[i] + " :"));
              panel.add(new Label(annotation[i + 1]));
            }
          }
        }
      }
    }

    return panel;
  }

  public String getHoverHelp(IMarker marker) {
    return null;
    // return BPELUtil.getObjectFromMarker(marker, ref) marker.toString();
  }

  protected ILabeledElement getLabeledElement(EObject modelObject) {
    if (modelObject == null) {
      return null;
    }
    for (Adapter adapter : modelObject.eAdapters()) {
      if (adapter instanceof ILabeledElement) {
        return (ILabeledElement) adapter;
      }
    }
    return null;
  }

  private static class BoldLabel extends Label {
    @Override
    public void addNotify() {
      super.addNotify();

      Font font = getFont();
      if (font == null) {
        return;
      }
      FontData[] fontData = font.getFontData();
      for (FontData fd : fontData) {
        fd.setStyle(fd.getStyle() | SWT.BOLD);
      }
      Font bold = new Font(font.getDevice(), fontData);
      setFont(bold);
    }
  }

  /**
   * Helper method for getting an AbstractUIObjectFactory from a model object.
   */
  protected AbstractUIObjectFactory getUIObjectFactory(Object modelObject) {
    if (modelObject instanceof EObject) {
      return UIObjectFactoryProvider.getInstance().getFactoryFor(
          ((EObject) modelObject).eClass());
    }
    return null;
  }
}