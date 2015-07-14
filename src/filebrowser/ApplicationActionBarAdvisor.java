package filebrowser;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private StatusLineContributionItem statusItem;
	private IWorkbenchAction aboutAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		 aboutAction = ActionFactory.ABOUT.create(window);
	        register(aboutAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager helpmenu = new MenuManager();
		helpmenu.add(aboutAction);
	}

	protected void fillCoolBar(ICoolBarManager coolBar) {
	}

	@Override
	protected void fillStatusLine(IStatusLineManager statusLine) {
		statusItem = new StatusLineContributionItem("Size");
		statusItem.setText("File size");
		statusLine.add(statusItem);
	}
}
