package filebrowser;

import java.io.File;

import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class SelectedFileInfo {
	private static SelectedFileInfo instance = new SelectedFileInfo();

	private SelectedFileInfo() {

	}

	public static SelectedFileInfo getInstance() {
		if (instance == null) {
			synchronized (SelectedFileInfo.class) {
				if (instance == null) {
					instance = new SelectedFileInfo();
				}
			}
		}
		return instance;
	}

	private static String path;
	
				
			/*
			
				/*PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().openEditor(input,
						"FileBrowser.textEditor");*/
				/*IEditorInput input = new TextEditorInput();		// 에디터로 오픈
				
					if(file.getName() != fileName.getTitle()){
						if(file.getName().endsWith(".txt")){*/
				
	public String getPath() {
		return path;
	}

	public void setPath(String loc){
		path = loc;
		boolean match = false;
		((PathView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("FileBrowser.pathView")).getTb()
				.setText(path);
		
		IEditorInput input = new TextEditorInput();
		File file = new File(path);
		
		for(IEditorPart Editor : PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getEditors()){
			if(Editor.getTitle().equals(file.getName())){
				match = true;
				Editor.getEditorSite().getWorkbenchWindow().getPages()[0].activate(Editor);
				break;
			}
		}
		
		if(!match){
		
			if(!file.isDirectory()) {
				if(file.getName().endsWith(".txt")){
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().openEditor(input,
								"FileBrowser.textEditor");
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(file.getName().endsWith(".jpg") || file.getName().endsWith(".png")){
					/*((ImageView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().findView("FileBrowser.imageView")).openImage(path);*/
				}
			}
		}
		
		/*
		if(Editor.getTitle() != file.getName()){
			if(!file.isDirectory()) {
				if(file.getName().endsWith(".txt")){
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().openEditor(input,
							"FileBrowser.textEditor");
				}
				
				else if(file.getName().endsWith(".png") || file.getName().endsWith(".jpg")){
					
				}
			}
		
		else{
			
			}*/
	}

}
