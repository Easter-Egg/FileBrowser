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

	/***** 트리 구조 출력 메소드 *****/
	@Override
	public void createPartControl(Composite parent) {
		// tree viewer 생성
		tree = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tree.setContentProvider(new FileTreeContentProvider()); // 트리의 '내용'에 대한
																// 정보를 제공
		tree.setLabelProvider(new FileTreeLabelProvider()); // 트리의 '모양'에 대한 정보를
															// 제공
		tree.setInput(File.listRoots()); // 파일 트리의 루트
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
