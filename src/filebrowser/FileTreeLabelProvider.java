package filebrowser;

import java.io.File;
import java.net.URL;

import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class FileTreeLabelProvider extends StyledCellLabelProvider {
	private Image icon;
	
	@Override
	public void update(ViewerCell cell) {
		// TODO Auto-generated method stub
		super.update(cell);
		
		createImage(); 								// 아이콘 이미지 생성
		
		Object element = cell.getElement();
		StyledString text = new StyledString();
		File file = (File) element;
		
		if(file.isDirectory()){						// 해당 파일이 디렉토리라면
			text.append(getFileName(file));			// 파일이름 출력
			cell.setImage(icon);					// 폴더 아이콘 출력
			String[] files = file.list();			// 폴더 내의 파일 수 출력
			if(files != null){
				text.append(" (" + files.length + ") ", StyledString.COUNTER_STYLER);
			}
		}
		
		else
			text.append(getFileName(file));

		cell.setText(text.toString());
		cell.setStyleRanges(text.getStyleRanges());
	}
	
	/***** 아이콘 이미지 생성 메소드 *****/
	private void createImage(){
		Bundle bundle = FrameworkUtil.getBundle(FileTreeLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/folder.png"), null);
		ImageDescriptor imageDcr = ImageDescriptor.createFromURL(url);
		this.icon = imageDcr.createImage();
	}
	
	private String getFileName(File file){
		String name = file.getName();
		return name.isEmpty() ? file.getPath() : name;
	}
	
	@PreDestroy
	public void dispose(){
		icon.dispose();
	}
	
}
