/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanager.events;
import static accountmanager.AccountManagerSettings.*;
import accountmanager.AccountManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeHandler implements ActionListener {
    
    private AccountManager app;
    
    public WelcomeHandler(AccountManager initApp)
    {
        app = initApp;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        app.goToAccountList();
    }
    
}
