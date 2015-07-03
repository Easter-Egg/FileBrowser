package filebrowser;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return (File[]) inputElement; 					// 파일 목록
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		File file = (File) parentElement;
		return file.listFiles(); 						// 하위 파일 목록
	}

	@Override
	public Object getParent(Object element) {
		File file = (File) element;
		return file.getParentFile(); 					// 상위 파일
	}

	@Override
	public boolean hasChildren(Object element) {
		File file = (File) element;
		
		if (file.isDirectory())							// 하위 파일이 있는지 체크
			return true;
		
		else
			return false;
	}
}
