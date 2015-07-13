package filebrowser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;

public class MyTextEditor extends EditorPart {
	public static final String ID = "FileBrowser.MyTextEditor";
	private TextViewer textViewer;
	private int firstLineLength = -1;

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
		textViewer = new TextViewer(parent, SWT.MULTI | SWT.V_SCROLL);

		String content = readFileContents();
		Document document = new Document(content);
		textViewer.setDocument(document);

		if(firstLineLength > 0){
			TextPresentation style = new TextPresentation();
			Color red = new Color(null, 255, 0, 0);
			style.addStyleRange(new StyleRange(0, firstLineLength, red, null, SWT.BOLD));
			textViewer.changeTextPresentation(style, true);
		}
	}

	private String readFileContents() {
		FileStoreEditorInput fsInput = (FileStoreEditorInput)getEditorInput();
		URI uri = fsInput.getURI();
		File file = new File(uri);
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try( BufferedReader reader = new BufferedReader(new FileReader(file)); )
		{
			while((line = reader.readLine()) != null){
				buffer.append(line + "\n");
				if(firstLineLength < 0)
					firstLineLength = line.length();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public void setFocus() {
		textViewer.getControl().setFocus();
	}

}
