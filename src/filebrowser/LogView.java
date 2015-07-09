package filebrowser;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import java.util.Calendar;

public class LogView extends ViewPart {
	public LogView() {
	}

	private String date;
	private Text log;
	public static final String ID = "FileBrowser.logView";

	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {
			if (!(sel instanceof IStructuredSelection))
				return;
			System.out.println(part + ", " + sel);
			IStructuredSelection ss = (IStructuredSelection) sel;
			Object o = ss.getFirstElement();
			String path = o.toString();
			Calendar calendar = Calendar.getInstance();// 현재 시간 정보 받기
			date = calendar.get(Calendar.YEAR) + "-"
					+ (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE) + " "
					+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
					+ calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + "."
					+ calendar.get(Calendar.MILLISECOND);
			log.append(date + " -- " + "[" + path + "]" + "\n");
		}
	};

	@Override
	public void createPartControl(Composite parent) {
		log = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.H_SCROLL);
		log.setLayoutData(new GridData(GridData.FILL_BOTH));
		getSite().getPage().addSelectionListener(listener);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(listener);
	}

}