package filebrowser;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		//layout.setEditorAreaVisible(true);
		layout.setFixed(true);
		layout.addStandaloneView(BrowserView.ID, false, IPageLayout.LEFT,
				0.35f, IPageLayout.ID_EDITOR_AREA);
		layout.addStandaloneView(PathView.ID, false, IPageLayout.TOP, 0.05f, BrowserView.ID);
		
	}
}
