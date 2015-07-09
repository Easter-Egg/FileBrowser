package filebrowser;


import java.io.File;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class ImageView extends ViewPart{
	
	public ImageView() {
	
	}
	
	public static final String ID = "FileBrowser.imageView";
	public String ImagePath = "C:\\Users\\Taewoo\\Pictures\\si.png";
	public Canvas canvas;
	
	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {
			if ((!(sel instanceof IStructuredSelection))  && (part != ImageView.this))
				return;
			
			IStructuredSelection ss = (IStructuredSelection) sel;
			String path = ss.getFirstElement().toString();

			if(path.endsWith(".jpg") || path.endsWith(".png"))
				ImagePath = path;
			
			canvas.redraw();
		}
	};
	
	@Override
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(listener);
		
		canvas = new Canvas(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		
		canvas.addPaintListener(new PaintListener() { 
	        public void paintControl(PaintEvent e) { 
	            File file = new File(ImagePath);
	            setPartName(file.getName());
	            Image image = new Image(parent.getDisplay(), ImagePath);
	            Rectangle clientArea = canvas.getClientArea();
	            e.gc.drawImage(image, clientArea.width/2-image.getBounds().width/2, clientArea.height/2-image.getBounds().height/2);
	            }
	    });
		
		
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(listener);
	}

}
