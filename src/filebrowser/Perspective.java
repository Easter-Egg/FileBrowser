package filebrowser;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		layout.addStandaloneView(BrowserView.ID, false, IPageLayout.LEFT,
				0.9f, layout.getEditorArea());
		layout.addStandaloneView(PathView.ID, false, IPageLayout.TOP, 0.1f, BrowserView.ID);
	}
}
