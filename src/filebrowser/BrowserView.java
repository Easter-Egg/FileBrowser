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

			Object firstElement = ss.getFirstElement();
			if(firstElement == null)
				return;
			String path = firstElement.toString();
			//			System.out.println(path);
			File file = new File(path);
			String fileName = file.getName();
			IPath ipath = new Path(file.getAbsolutePath());
			IFileStore fs = EFS.getLocalFileSystem().getStore(ipath);
			FileStoreEditorInput fileStoreEditorInput = new FileStoreEditorInput(fs);
			IWorkbenchPage page = getSite().getPage();

			try {
				if (fileName.endsWith(".txt")) {
					page.openEditor(fileStoreEditorInput, MyTextEditor.ID, false);
					//page.openEditor(fileStoreEditorInput, "org.eclipse.ui.DefaultTextEditor", false);
				}
				else if(fileName.endsWith(".png")){
					page.openEditor(fileStoreEditorInput, ImageEditor.ID, false);
				}
			} catch (PartInitException e) {
				e.printStackTrace();
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

	/***** 트占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쌨소듸옙 *****/
	@Override
	public void createPartControl(Composite parent) {
		// tree viewer 占쏙옙占쏙옙
		tree = new Tree(parent, SWT.V_SCROLL | SWT.H_SCROLL);

		// 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙타占쏙옙 占쌈쇽옙 占쏙옙占쏙옙 占쌨아와쇽옙 占쏙옙占쏙옙 - 占쏙옙占쏙옙占쏙옙 표占쏙옙占싹댐옙 占쏙옙占쏙옙 占쏙옙占�
		OS.SetWindowLong(tree.handle, OS.GWL_STYLE,
				OS.GetWindowLong(tree.handle, OS.GWL_STYLE) | OS.TVS_HASLINES);

		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new FileTreeContentProvider()); // 트占쏙옙占쏙옙
		// '占쏙옙占쏙옙'占쏙옙
		// 占쏙옙占쏙옙 占쏙옙占쏙옙
		treeViewer.setLabelProvider(new FileTreeLabelProvider()); // 트占쏙옙占쏙옙 '占쏙옙占�'占쏙옙
		// 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
		treeViewer.setInput(File.listRoots()); // 占쏙옙占쏙옙 트占쏙옙占쏙옙 占쏙옙트.

		getSite().setSelectionProvider(treeViewer);
		getSite().getPage().addPostSelectionListener(listener);
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
