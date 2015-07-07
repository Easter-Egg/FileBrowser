package filebrowser;

import java.io.File;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

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

		// getViewSite().setSelectionProvider(tree);

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				String path = event.getSelection().toString();  		// "[���]"
				String loc = path.substring(1, path.length() - 1);		// "���" �߰�ȣ ����

				((PathView) getViewSite().getPage().findView(			// PathView�� �ؽ�Ʈ �ڽ��� ��� ǥ��
						"FileBrowser.pathView")).setLocation(loc);

				try {
					File file = new File(loc);
					if (!file.isDirectory()) {							// ����� ������ ���丮�� �ƴ� ��쿡��
						IEditorInput input = new TextEditorInput();		// �����ͷ� ����
						getViewSite().getPage().openEditor(input,
								"FileBrowser.textEditor");
					}

				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public TreeViewer getTree() {
		return treeViewer;
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
