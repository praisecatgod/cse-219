/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanager.events;

import account_data.RegionNode;
import accountmanager.AccountManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author yv
 */
public class CurrentTreeHandler implements TreeSelectionListener {
    
    AccountManager app;
        
    
    public CurrentTreeHandler(AccountManager initApp)
    {
       app = initApp;
    }
    @Override
    public void valueChanged(TreeSelectionEvent tse) {
       RegionNode selectedNode = 
               (RegionNode)tse.getPath().getLastPathComponent();
       
       app.display();
       // OBJECT = selectedNode.getUserObject()
       // display OBJECT
       //set to subregion
    }
    
}
