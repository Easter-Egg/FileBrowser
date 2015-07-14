package filebrowser;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

public class OutlineView extends PageBookView {
	public OutlineView() {
	}
	public final static String ID = "FileBrowser.outLineView";

	@Override
	protected IPage createDefaultPage(PageBook book) {
		// TODO Auto-generated method stub
		MessagePage messagePage = new MessagePage();
		initPage(messagePage);
		messagePage.setMessage("No interested in this part");
		messagePage.createControl(book);
		return messagePage;
	}

	@Override
	protected PageRec doCreatePage(IWorkbenchPart part) {
		MessagePage messagePage = new MessagePage();
		initPage(messagePage);
		messagePage.setMessage("Page for "+part.getTitle());
		messagePage.createControl(getPageBook());
		return new PageRec(part, messagePage);
	}

	@Override
	protected void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
		pageRecord.page.dispose();
	}

	@Override
	protected IWorkbenchPart getBootstrapPart() {
		IWorkbenchPage page = getSite().getPage();
		  if(page != null) {
		   // check whether the active part is important to us
		   IWorkbenchPart activePart = page.getActivePart();
		   return isImportant(activePart)?activePart:null;
		  }
		  return null;
	}

	@Override
	protected boolean isImportant(IWorkbenchPart part) {
		return part.getSite().getPluginId().startsWith("org.eclipse.ui");
	}

}