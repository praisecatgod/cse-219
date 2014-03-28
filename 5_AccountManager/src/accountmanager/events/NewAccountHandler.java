/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanager.events;

import accountmanager.AccountManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author yv
 */
public class NewAccountHandler implements ActionListener {
    
    private AccountManager app;
    
    public NewAccountHandler(AccountManager initApp)
    {
        app = initApp;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
       String command = ae.getActionCommand();
        
        switch (command)
        {
            case "Create Account":
                app.createNewAccount();
                break;
            case "Return To List":
                app.goToAccountList();
                break;
  
        }
    }
    
}
