package filebrowser;

import java.io.File;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class BrowserView extends ViewPart {
	public BrowserView() {
	}

	private TreeViewer tree;
	public static final String ID = "FileBrowser.browserView";

	/***** Ʈ�� ���� ��� �޼ҵ� *****/
	@Override
	public void createPartControl(Composite parent) {
		// tree viewer ����
		tree = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tree.setContentProvider(new FileTreeContentProvider()); // Ʈ���� '����'�� ����
																// ������ ����
		tree.setLabelProvider(new FileTreeLabelProvider()); // Ʈ���� '���'�� ���� ������
															// ����
		tree.setInput(File.listRoots()); // ���� Ʈ���� ��Ʈ

		getViewSite().setSelectionProvider(tree);

		try {
			getViewSite().getPage().openEditor(new TextEditorInput(),
					"FileBrowser.textEditor");

		} catch (PartInitException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void setFocus() {
		tree.getControl().setFocus();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
