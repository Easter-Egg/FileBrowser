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

			if (file.exists() && file.getName().endsWith(".txt")) {
				IPath ipath = new Path(file.getAbsolutePath());
				IFileStore fs = EFS.getLocalFileSystem().getStore(ipath);
				FileStoreEditorInput fileStoreEditorInput = new FileStoreEditorInput(fs);
				
				IWorkbenchPage page = getSite().getPage();
				
				try {
					page.openEditor(fileStoreEditorInput, MyTextEditor.ID, false);
					//page.openEditor(fileStoreEditorInput, "org.eclipse.ui.DefaultTextEditor", false);
				} catch (PartInitException e) {
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

	/***** Ʈ�� ���� ��� �޼ҵ� *****/
	@Override
	public void createPartControl(Composite parent) {
		// tree viewer ����
		tree = new Tree(parent, SWT.V_SCROLL | SWT.H_SCROLL);

		// �������� ��Ÿ�� �Ӽ� ���� �޾ƿͼ� ���� - ������ ǥ���ϴ� ���� ���
		OS.SetWindowLong(tree.handle, OS.GWL_STYLE,
				OS.GetWindowLong(tree.handle, OS.GWL_STYLE) | OS.TVS_HASLINES);

		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new FileTreeContentProvider()); // Ʈ����
																		// '����'��
																		// ���� ����
		treeViewer.setLabelProvider(new FileTreeLabelProvider()); // Ʈ���� '���'��
																	// ���� ������ ����
		treeViewer.setInput(File.listRoots()); // ���� Ʈ���� ��Ʈ.

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
