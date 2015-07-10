package filebrowser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbenchPage;
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

	private ISelectionChangedListener l = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			String path = event.getSelection().toString();
			String loc = path.substring(1, path.length() - 1);
			File file = new File(loc);
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IPath ipath = new Path(file.getAbsolutePath());
			IFileStore fs = EFS.getLocalFileSystem().getStore(ipath);
			FileStoreEditorInput fileStoreEditorInput = new FileStoreEditorInput(
					fs);

			try {
				if (file.getName().endsWith(".txt")) {
					page.openEditor(fileStoreEditorInput, MyTextEditor.ID, false);
				}

				else if ((file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))) {
					page.openEditor(fileStoreEditorInput, ImageEditor.ID, false);
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
			
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss:SSS a"); 
			System.out.println(sdf.format(dt).toString() +  " " + path);
		}
	};

	@Override
	public void createPartControl(Composite parent) {
		tree = new Tree(parent, SWT.V_SCROLL | SWT.H_SCROLL);

		OS.SetWindowLong(tree.handle, OS.GWL_STYLE,
				OS.GetWindowLong(tree.handle, OS.GWL_STYLE) | OS.TVS_HASLINES);

		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new FileTreeContentProvider());
		treeViewer.setLabelProvider(new FileTreeLabelProvider());
		treeViewer.setInput(File.listRoots());

		getSite().setSelectionProvider(treeViewer);
		treeViewer.addPostSelectionChangedListener(l);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	@Override
	public void dispose() {
		super.dispose();
		treeViewer.removePostSelectionChangedListener(l);
	}
}
