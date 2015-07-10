package filebrowser;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    
    private IWorkbenchAction exitAction;
    private FileOpenAction fileOpenAction;
    private StatusLineContributionItem statusItem;

    protected void makeActions(IWorkbenchWindow window) {
    	exitAction = ActionFactory.QUIT.create(window);
    	register(exitAction);
    	
    	fileOpenAction = fileOpenAction.getInstance();
    	fileOpenAction.setText("file open in browser");
    	register(fileOpenAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager menu = new MenuManager("File", IWorkbenchActionConstants.M_FILE);
    	menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    	menu.add(exitAction);
    	menu.add(fileOpenAction);
    	menuBar.add(menu);
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
    }
    
    @Override
    protected void fillStatusLine(IStatusLineManager statusLine){
    	statusItem = new StatusLineContributionItem("Size");
    	statusItem.setText("File size");
    	statusLine.add(statusItem);
    }
}
