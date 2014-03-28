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
public class CurrentAccountHandler implements ActionListener {
    
    private AccountManager app;
    
    public CurrentAccountHandler(AccountManager initApp)
    {
        app = initApp;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
       String command = ae.getActionCommand();
        
        switch (command)
        {
            case "Return To Account List":
                app.goToAccountList();
                break;
            case "Exit Game":
                app.exitGame();
                break;
            default:
                app.displayScore(command);
                break;
  
        }
    }
    
}
