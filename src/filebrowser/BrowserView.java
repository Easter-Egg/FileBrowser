package filebrowser;

import java.io.File;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;

@SuppressWarnings("restriction")
public class BrowserView extends ViewPart {
	public BrowserView() {
	}

	private TreeViewer treeViewer;
	private Tree tree;
	public static final String ID = "FileBrowser.browserView";

	/***** Ʈ�� ���� ��� �޼ҵ� *****/
	@Override
	public void createPartControl(Composite parent) {
		// tree viewer ����
		tree = new Tree(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		
		// �������� ��Ÿ�� �Ӽ� ���� �޾ƿͼ� ���� - ������ ǥ���ϴ� ���� ���
		OS.SetWindowLong(tree.handle, OS.GWL_STYLE, OS.GetWindowLong(tree.handle, OS.GWL_STYLE) | OS.TVS_HASLINES);
		
		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new FileTreeContentProvider()); 	// Ʈ���� '����'�� ���� ����
		treeViewer.setLabelProvider(new FileTreeLabelProvider()); 		// Ʈ���� '���'�� ���� ������ ����
		treeViewer.setInput(File.listRoots()); 							// ���� Ʈ���� ��Ʈ.

		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}


