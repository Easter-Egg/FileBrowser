package filebrowser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;

public class MyTextEditor extends EditorPart {
	public static final String ID = "FileBrowser.MyTextEditor";
	private Text text;

	public MyTextEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	//init이 createPartControl 보다 먼저 불림
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setInput(input);
		setSite(site);
		setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		text = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);

		String content = readFileContents();
		text.setText(content);
	}

	private String readFileContents() {
		FileStoreEditorInput fsInput = (FileStoreEditorInput)getEditorInput();
		URI uri = fsInput.getURI();
		File file = new File(uri);
		StringBuffer buffer = new StringBuffer();
		try( BufferedReader reader = new BufferedReader(new FileReader(file)); )
		{
			buffer.append(reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}

}
