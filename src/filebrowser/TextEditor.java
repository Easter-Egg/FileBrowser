package filebrowser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		
		
		Label label = new Label(parent, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 26);
		fd_label.top = new FormAttachment(0, 5);
		fd_label.left = new FormAttachment(0, 5);
		label.setLayoutData(fd_label);
		label.setText("파일명");
		Text text = new Text(parent, SWT.BORDER | SWT.MULTI);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, -5);
		fd_text.top = new FormAttachment(0, 5);
		fd_text.left = new FormAttachment(0, 46);
		text.setLayoutData(fd_text);
		text.setText("파일명");
		
		Label label_1 = new Label (parent, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.bottom = new FormAttachment(0, 52);
		fd_label_1.right = new FormAttachment(0, 41);
		fd_label_1.top = new FormAttachment(0, 31);
		fd_label_1.left = new FormAttachment(0, 5);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("내용");
		Text text2 = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		FormData fd_text2 = new FormData();
		fd_text2.bottom = new FormAttachment(100);
		fd_text2.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text2.top = new FormAttachment(0, 31);
		fd_text2.left = new FormAttachment(0, 46);
		text2.setLayoutData(fd_text2);
		text2.setText("내용");
		
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener((part, selection)->{
			text2.setText("");
			try {
				String loc = ((IStructuredSelection) selection).getFirstElement().toString();
				File file = new File(loc);
				BufferedReader br = new BufferedReader(new FileReader(loc));
				
				if(file.isFile()){
					text.setText(file.getName());
					
					if(file.canRead()){
						String line = "";
						String document = "";
						while((line = br.readLine()) != null){
							document += (line + "\n");
						}
						text2.setText(document);
						br.close();
					}
				}
				
				System.out.printf("%s, %s\n", part, selection);
			} catch (Exception e) {
				
			}
		});
	}

	@Override
	public void setFocus() {
		// Set the focus
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
		if(!(input instanceof TextEditorInput)){
			throw new RuntimeException("Wrong input");
		}
		
		this.input = (TextEditorInput) input;
		setSite(site);
		setInput(input);
		setPartName("파일명");
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
