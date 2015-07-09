package filebrowser;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		//layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		layout.addStandaloneView(BrowserView.ID, false, IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
		//layout.addStandaloneView(ImageView.ID, true, IPageLayout.RIGHT, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addStandaloneView(PathView.ID, false, IPageLayout.TOP, 0.05f, BrowserView.ID);
		layout.addStandaloneView(LogView.ID, false, IPageLayout.BOTTOM, 0.87f,BrowserView.ID);  
	}
}
