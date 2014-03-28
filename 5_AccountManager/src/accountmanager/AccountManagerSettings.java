/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanager;


public class AccountManagerSettings {
    
    // window settings //
    public static final String APP_NAME = "Ramble On: Demo";
    public static final int     WINDOW_WIDTH        = 900;
    public static final int     WINDOW_HEIGHT       = 600;
    public static final boolean WINDOW_IS_RESIZABLE = false;
    
    // gui/graphics ///
    public static final String WELCOME_FILE = "data/gui/welcome.png";
    
    // account management //
    public static final String ACCOUNT_FILE_PREFIX = "data/accounts/";
    public static final String ACCOUNT_FILE_SUFFIX = ".xml"; 
    
    // CardLayout names //
    public static final String WELCOME_PANEL = "WELCOME_PANEL";
    public static final String ACCOUNTS_PANEL = "ACCOUNTS_PANEL";
    public static final String NEW_PANEL = "NEW_PANEL";
    public static final String CURRENT_PANEL = "CURRENT_PANEL";
    
    // ScoreType names //
    private static final String SUBREGION_TYPE = "Subregion Mode";
    private static final String CAPITAL_TYPE = "Capital Mode";
    private static final String FLAG_TYPE = "Flag Mode";
    private static final String LEADER_TYPE = "Leader Mode";
    
    // ScoreDisplay //
    private static final String PLAYED_LABEL = "Times Played: ";
    private static final String SCORE_LABEL = "High Score: ";
    private static final String TIME_LABEL = "Fastest Time: ";
    
    // button tooltips //
    private static final String WELCOME_TOOLTIP = "Click to continue";
    
    private static final String ACCOUNTS_SELECT_TOOLTIP = "Select Account";
    private static final String ACCOUNTS_NEW_TOOLTIP = "Create New Account";
    private static final String ACCOUNTS_EXIT_TOOLTIP = "Exit Game";
    
    private static final String NEW_OK_TOOLTIP = "Create Account";
    private static final String NEW_CANCEL_TOOLTIP = "Return To List";
    
    
    private static final String CURRENT_SUBREGION_TOOLTIP = "Subregion Mode";
    private static final String CURRENT_CAPITAL_TOOLTIP = "Capital Mode";
    private static final String CURRENT_FLAG_TOOLTIP = "Flag Mode";
    private static final String CURRENT_LEADER_TOOLTIP = "Leader Mode";
    
    private static final String CURRENT_ACCOUNTS_TOOLTIP = "Return To Account List";
    private static final String CURRENT_EXIT_TOOLTIP = "Exit Game";
    
    
    


    
    // display text //
    public static final String ACCOUNTS_TEXT_A = 
            "Please select your name from the list below and press SELECT.\n";
    public static final String ACCOUNTS_TEXT_B =
            "\nOr press NEW if you wish to make a new account.";
    public static final String NEW_ACCOUNT_TEXT_A =
            "Please enter a unique name for your account\n";
    public static final String NEW_ACCOUNT_TEXT_B =
            "\nInvalid input. Account name blank or already in use!";



    
}
