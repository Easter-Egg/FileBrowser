package filebrowser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class TextEditor extends EditorPart {

	public static final String ID = "FileBrowser.textEditor"; //$NON-NLS-1$

	public TextEditor() {
	}

	private TextEditorInput input;
	private Text text2;
	private String path;

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		ISelection sel = getSite().getPage().getSelection("FileBrowser.browserView");
		path = sel.toString();
		String loc = path.substring(1, path.length()-1);
		
		parent.setLayout(new FormLayout());
		text2 = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		FormData fd_text2 = new FormData();
		fd_text2.left = new FormAttachment(0);
		fd_text2.right = new FormAttachment(100);
		fd_text2.bottom = new FormAttachment(100);
		fd_text2.top = new FormAttachment(0);
		text2.setLayoutData(fd_text2);
		text2.setText("");

		File file = new File(loc);
		setPartName(file.getName());

		try {
			BufferedReader br = new BufferedReader(new FileReader(loc));
			String line = "";
			String document = "";
			while ((line = br.readLine()) != null) {
				document += (line + "\n");
			}
			text2.setText(document);
			br.close();
		} catch (Exception e) {
		}
	}
	
	@Override
	public void setFocus() {
		// Set the focus
		text2.forceFocus();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		if (!(input instanceof TextEditorInput)) {
			throw new RuntimeException("Wrong input");
		}

		this.input = (TextEditorInput) input;
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}