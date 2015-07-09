package filebrowser;

import java.io.File;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.ViewPart;

@SuppressWarnings("restriction")
public class BrowserView extends ViewPart {
	public BrowserView() {
	}

	private TreeViewer treeViewer;
	private Tree tree;
	public static final String ID = "FileBrowser.browserView";

	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {
			if (!(sel instanceof IStructuredSelection))
				return;

			IStructuredSelection ss = (IStructuredSelection) sel;

			String path = ss.getFirstElement().toString();
			System.out.println(path);
			File file = new File(path);
			
			

			if (file.exists() && file.getName().endsWith(".txt")) {
				IPath ipath = new Path(file.getAbsolutePath());
				IFileStore fs = EFS.getLocalFileSystem().getStore(ipath);
				FileStoreEditorInput fileStoreEditorInput = new FileStoreEditorInput(fs);
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				
				try {
					page.openEditor(fileStoreEditorInput, "org.eclipse.ui.DefaultTextEditor", false);
					
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			ss = null;

			/*
			 * File file = new File(path); if (!file.isDirectory()) { if
			 * (file.getName().endsWith(".txt")) { try {
			 * PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			 * .getActivePage() .openEditor(new TextEditorInput(),
			 * "FileBrowser.textEditor", false); } catch (PartInitException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); } } } }
			 */
		}
	};

	/***** 트리 구조 출력 메소드 *****/
	@Override
	public void createPartControl(Composite parent) {
		// tree viewer 생성
		tree = new Tree(parent, SWT.V_SCROLL | SWT.H_SCROLL);

		// 윈도우의 스타일 속성 값을 받아와서 변경 - 계층을 표현하는 점선 출력
		OS.SetWindowLong(tree.handle, OS.GWL_STYLE,
				OS.GetWindowLong(tree.handle, OS.GWL_STYLE) | OS.TVS_HASLINES);

		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new FileTreeContentProvider()); // 트리의
																		// '내용'에
																		// 대한 정보
		treeViewer.setLabelProvider(new FileTreeLabelProvider()); // 트리의 '모양'에
																	// 대한 정보를 제공
		treeViewer.setInput(File.listRoots()); // 파일 트리의 루트.

		getSite().setSelectionProvider(treeViewer);
		getSite().getPage().addSelectionListener(listener);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		getSite().getPage().removeSelectionListener(listener);
	}
}
