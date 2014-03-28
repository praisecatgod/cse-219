/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanager.events;
import static accountmanager.AccountManagerSettings.*;

import accountmanager.AccountManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author yv
 */
public class AccountsButtonHandler implements ActionListener {

        private AccountManager app;
    
    public AccountsButtonHandler(AccountManager initApp)
    {
        app = initApp;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    String command = ae.getActionCommand();
        
        // WHICH BUTTON WAS CLICKED?
        switch (command)
        {
            case "Select Account":
                app.getListSelection();
                break;
            case "Create New Account":
                app.goToNewAccount();
                break;
            case "Exit Game":
                app.exitGame();
                break;
  
        }
                
    }
    
}
