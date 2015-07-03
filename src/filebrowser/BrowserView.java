package filebrowser;

import java.io.File;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
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
		tree.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				Object selected_file = selection.getFirstElement();

				try {
					((PathView) getViewSite().getPage().findView(PathView.ID))
							.setPath(selected_file.toString());
				} catch (Exception e) {
				}
			}
		});
	}

	@Override
	public void setFocus() {
		tree.getControl().setFocus();
	}
}
