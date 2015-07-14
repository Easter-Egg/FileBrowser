package filebrowser;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class FileInfoHandler extends AbstractHandler {

	private Canvas srcCanvas;
	private Image srcImage;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		ImageEditor ie = (ImageEditor) page.getActiveEditor();
		srcCanvas = ((ImageEditor) page.getActiveEditor()).getCanvas();
		srcImage = ((ImageEditor) page.getActiveEditor()).getImage();

		if (srcImage == null)
			return null;

		
		System.out.println(">>>>> File name : " + ie.getTitle() + " width : " + srcImage.getBounds().width + ", height : " + srcImage.getBounds().height + " (canvas : " + srcCanvas.getClientArea().width + "x" + srcCanvas.getClientArea().height +")");
		return null;
		/*double dx = ((double) w) / 2;
		double dy = ((double) h) / 2;
		centerZoom(dx, dy, ZOOMIN_RATE, transform);
		return null;*/
	}

	/*public void centerZoom(double dx, double dy, double scale,
			AffineTransform af) {
		af.preConcatenate(AffineTransform.getTranslateInstance(-dx, -dy));
		af.preConcatenate(AffineTransform.getScaleInstance(scale, scale));
		af.preConcatenate(AffineTransform.getTranslateInstance(dx, dy));
		transform = af;
		syncScrollBars();
	}*/

	/*public void syncScrollBars() {
		if (srcImage == null) {
			srcCanvas.redraw();
			return;
		}
		AffineTransform af = transform;
		double sx = af.getScaleX(), sy = af.getScaleY();
		double tx = af.getTranslateX(), ty = af.getTranslateY();
		if (tx > 0)
			tx = 0;
		if (ty > 0)
			ty = 0;

		Rectangle imageBound = srcImage.getBounds();
		int cw = srcCanvas.getClientArea().width, ch = srcCanvas.getClientArea().height;

		ScrollBar horizontal = srcCanvas.getHorizontalBar();
		horizontal.setIncrement((int) (srcCanvas.getClientArea().width / 100));
		horizontal.setPageIncrement(srcCanvas.getClientArea().width);

		if (imageBound.width * sx > cw) { /* image is wider than client area 
			horizontal.setMaximum((int) (imageBound.width * sx));
			horizontal.setEnabled(true);
			if (((int) -tx) > horizontal.getMaximum() - cw) {
				tx = -horizontal.getMaximum() + cw;
			
		} else { /* image is narrower than client area 
			horizontal.setEnabled(false);
			tx = (cw - imageBound.width * sx) / 2;
		}
		horizontal.setSelection((int) (-tx));
		horizontal.setThumb((int) (srcCanvas.getClientArea().width));

		/* update vertical scrollbar, same as above. 
		ScrollBar vertical = srcCanvas.getVerticalBar();
		vertical.setIncrement((int) (srcCanvas.getClientArea().height / 100));
		vertical.setPageIncrement(srcCanvas.getClientArea().height);
		
		if(imageBound.height * sy > ch){
			vertical.setMaximum((int) (imageBound.height * sy));
			vertical.setEnabled(true);
			if(((int) -ty) > vertical.getMaximum() - ch){
				ty = -vertical.getMaximum() + ch;
				
			}
		} else {
			vertical.setEnabled(false);
			ty = (ch - imageBound.height * sy) / 2;
		}
		vertical.setSelection((int) (-ty));
		vertical.setThumb((int) (srcCanvas.getClientArea().height));

		/* update transform. 
		af = AffineTransform.getScaleInstance(sx, sy);
		af.preConcatenate(AffineTransform.getTranslateInstance(tx, ty));
		transform = af;

		srcCanvas.redraw();
	}*/
}
