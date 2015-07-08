package filebrowser;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ImageView extends ViewPart{
	
	public ImageView() {
	
	}
	
	public static final String ID = "FileBrowser.imageView";
	public String ImagePath = "C:\\Users\\Taewoo\\Pictures\\si.png";
	public Canvas canvas;
	
	@Override
	public void createPartControl(Composite parent) {
		
		canvas = new Canvas(parent, SWT.BORDER | SWT.NO_REDRAW_RESIZE);
		
		canvas.addPaintListener(new PaintListener() { 
	        public void paintControl(PaintEvent e) { 
	            Rectangle clientArea = canvas.getClientArea(); 
	            Image image = new Image(parent.getDisplay(), ImagePath);
	            e.gc.drawImage(image, clientArea.width/2-image.getBounds().width/2, clientArea.height/2-image.getBounds().height/2);
	            }
	    });
		
        /*
		Rectangle bounds = image.getBounds();
		GC gc = new GC(image);
		gc.drawImage(image, 0, 0);
		//gc.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		//gc.drawRectangle(0,0,image.getBounds().width, image.getBounds().height);
		//canvas.print(gc);
*/
		setPartName("ImageViewer");
		
	}
	
	public void openImage(String path){/*
		Display device = getViewSite().getShell().getDisplay();
		canvas.addPaintListener(new PaintListener() { 
	        public void paintControl(PaintEvent e) { 
	            Rectangle clientArea = canvas.getClientArea(); 
	            Image image = new Image(device, path);
	            e.gc.drawImage(image, clientArea.width/2-image.getBounds().width/2, clientArea.height/2-image.getBounds().height/2);
	            }
	    });*/
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
