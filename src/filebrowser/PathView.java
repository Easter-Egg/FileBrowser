package filebrowser;

import java.io.File;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class PathView extends ViewPart {
	public PathView() {

	}

	public static final String ID = "FileBrowser.pathView";
	private Text textBox;

	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {
			if (!(sel instanceof IStructuredSelection))
				return;
			IStructuredSelection ss = (IStructuredSelection) sel;
			Object o = ss.getFirstElement();
			String path = o.toString();

			textBox.setText(path);
			File file = new File(path);
			
			boolean match = false;
			IEditorReference[] er = getSite().getPage().getEditorReferences();
			
			for (int i = 0 ; i < er.length; i++) {
				if (er[i].getTitle().equals(file.getName())) {
					match = true;
					System.out.println("find same file in open files list.");
					break;
				}
			}
			
			if (!match) {
				if (!file.isDirectory()) {
					if (file.getName().endsWith(".txt")) {
						try {
							System.out.println("open Editor");
							getSite().getPage().openEditor(
									new TextEditorInput(),
									"FileBrowser.textEditor", false);
						} catch (PartInitException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	};

	@Override
	public void createPartControl(Composite parent) {
		textBox = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		textBox.setLayoutData(new GridData(GridData.FILL_BOTH));
		getSite().getPage().addSelectionListener(listener);
	}

	@Override
	public void setFocus() {

	}

	public void dispose() {
		getSite().getPage().removeSelectionListener(listener);
	}
}