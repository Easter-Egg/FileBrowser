package filebrowser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class PathView extends ViewPart {
	public static final String ID = "FileBrowser.pathView";
	private Text textBox;
	
	@Override
	public void createPartControl(Composite parent) {
		textBox = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		textBox.setLayoutData(new GridData(GridData.FILL_BOTH));
		textBox.setText("°æ·Î");
	}
	
	public void setPath(String str){
		textBox.setText(str);
	}

	@Override
	public void setFocus() {
		
	}

}
