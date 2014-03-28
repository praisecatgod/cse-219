/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanager;

import account_data.Account;
import account_data.RegionNode;
import account_data.RegionTreeModel;
import account_data.Score;
import static accountmanager.AccountManagerSettings.*;
import accountmanager.events.AccountsButtonHandler;
import accountmanager.events.CurrentAccountHandler;
import accountmanager.events.CurrentTreeHandler;
import accountmanager.events.NewAccountHandler;
import accountmanager.events.WelcomeHandler;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author yv
 */
public class AccountManager {

    private JFrame window;
    private JPanel managerPanel;
    private CardLayout gamePhases;
    //welcome screen//
    private JPanel welcomePanel;
    private JButton welcomeButton;
    // accounts screen //
    private JPanel accountsPanel;
    // list of accounts
    private JPanel accountSelector;
    private JLabel accountTextA;
    private JLabel accountTextB;
    private JScrollPane accountsScrollPane;
    private JList accountList;
    private DefaultListModel accountListModel;
    private JPanel accountButtonPanel;
    private JButton selectButton;
    private JButton newButton;
    private JPanel accountExitButtonPanel;
    private JButton accountsExitButton;
    // new account interface //
    private JPanel newAccountPanel;
    // account input
    private JPanel newInputPanel;
    private JLabel newAccountInfo;
    private JLabel newAccountInfoError;
    private JTextField accountTextField;
    private JPanel newAccountButtonPanel;
    private JButton okButton;
    private JButton cancelButton;
    // current account screen //
    private JPanel currentAccountPanel;
    // score viewer
    private JPanel scoreViewPanel;
    private JSplitPane splitPane;
    // // tree
    private JScrollPane worldTreeScrollPane;
    private JTree worldTree;
    // // scores
    private JPanel scorePanel;
    private JLabel regionName;
    private JLabel scoreType;
    private JLabel playedLabel;
    private JLabel scoreLabel;
    private JLabel timesLabel;
    private JLabel playedDisplayLabel;
    private JLabel scoreDisplayLabel;
    private JLabel timesDisplayLabel;
    private JPanel scoreButtonPanel;
    private JButton subregionButton;
    private JButton capitalButton;
    private JButton flagButton;
    private JButton leaderButton;
    // navigation bar
    private JPanel navigationPanel;
    private JButton accountsButton;
    private JButton currentExitButton;
    //listeners
    private WelcomeHandler welcomeHandler;
    private AccountsButtonHandler accountsButtonListener;
    private CurrentTreeHandler treeHandler;
    private CurrentAccountHandler currentAccountListener;
    //data
    private AccountFileManager fileManager;
    private File currentAccount;
    private Account selectedAccount;

    public AccountManager() {
    }

    public void init() {
        initData();
        initWindow();
        initGUI();

        window.setVisible(true);

    }

    private void initData() {
        fileManager = new AccountFileManager(this);
    }

    private void initWindow() {
        window = new JFrame();

        window.setTitle(APP_NAME);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setResizable(WINDOW_IS_RESIZABLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerWindow();
    }

    private void initGUI() {
        gamePhases = new CardLayout();
        managerPanel = new JPanel(gamePhases);

        initWelcomeScreen();
        initAccountsScreen();
        initNewAccountScreen();
        //initCurrentAccountScreen();

        window.add(managerPanel);

    }

    private void initWelcomeScreen() {
        welcomePanel = new JPanel(new BorderLayout());

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(WELCOME_FILE);
        ImageIcon ii = new ImageIcon(img);

        welcomeButton = new JButton(ii);
        welcomeButton.setToolTipText("Click to continue");
        welcomeButton.setActionCommand("Click to continue");
        welcomeHandler = new WelcomeHandler(this);
        welcomeButton.addActionListener(welcomeHandler);
        welcomePanel.add(welcomeButton, BorderLayout.CENTER);

        managerPanel.add(welcomePanel, WELCOME_PANEL);
    }

    private void initAccountsScreen() {
        accountsPanel = new JPanel(new BorderLayout());
        accountSelector = new JPanel(new GridBagLayout());
        accountsButtonListener = new AccountsButtonHandler(this);
        initAccountTextLabels();
        initAccountList();
        initAccountListButtons();
        accountsPanel.add(accountSelector, BorderLayout.CENTER);
        managerPanel.add(accountsPanel, ACCOUNTS_PANEL);
    }

    private void initAccountTextLabels() {
        GridBagConstraints c = new GridBagConstraints();

        accountTextA = new JLabel(ACCOUNTS_TEXT_A);
        accountTextB = new JLabel(ACCOUNTS_TEXT_B);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        accountSelector.add(accountTextA, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        accountSelector.add(accountTextB, c);
    }

    private void initAccountList() {
        GridBagConstraints c = new GridBagConstraints();

        accountListModel = new DefaultListModel();
        accountList = new JList(accountListModel);
        accountList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        accountList.setLayoutOrientation(JList.VERTICAL);
        accountList.setVisibleRowCount(-1);
        accountList.setSelectedIndex(0);

        for (int i = 0; i < fileManager.getFiles().size(); i++) {
            accountListModel.add(i, fileManager.getFiles().get(i).getName());
        }
        accountsScrollPane = new JScrollPane(accountList);
        accountsScrollPane.setPreferredSize(new Dimension(100, 80));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        accountSelector.add(accountsScrollPane, c);
    }

    private void initAccountListButtons() {
        GridBagConstraints c = new GridBagConstraints();

        accountButtonPanel = new JPanel();
        selectButton = new JButton("Select");
        selectButton.setToolTipText("Select Account");
        selectButton.setActionCommand("Select Account");
        selectButton.addActionListener(accountsButtonListener);
        accountButtonPanel.add(selectButton, c);


        newButton = new JButton("New");
        newButton.setToolTipText("Create New Account");
        newButton.setActionCommand("Create New Account");
        newButton.addActionListener(accountsButtonListener);
        accountButtonPanel.add(newButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        accountSelector.add(accountButtonPanel, c);

        accountExitButtonPanel = new JPanel();
        accountsExitButton = new JButton("Exit");
        accountsExitButton.setToolTipText("Exit Game");
        accountsExitButton.setActionCommand("Exit Game");
        accountsExitButton.addActionListener(accountsButtonListener);

        accountExitButtonPanel.add(accountsExitButton);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        accountSelector.add(accountExitButtonPanel, c);
    }

    private void initNewAccountScreen() {
        newAccountPanel = new JPanel(new BorderLayout());

        // input panel //
        newInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        NewAccountHandler newAccountListener = new NewAccountHandler(this);

        // // text // //
        newAccountInfo = new JLabel(NEW_ACCOUNT_TEXT_A);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        newInputPanel.add(newAccountInfo, c);

        newAccountInfoError = new JLabel(NEW_ACCOUNT_TEXT_B);
        newAccountInfoError.setForeground(Color.red);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        newInputPanel.add(newAccountInfoError, c);
        newAccountInfoError.setVisible(false);

        // // text field // //
        accountTextField = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        newInputPanel.add(accountTextField, c);

        // // buttons // //
        newAccountButtonPanel = new JPanel();
        okButton = new JButton("OK");
        okButton.setToolTipText("Create Account");
        okButton.setActionCommand("Create Account");
        okButton.addActionListener(newAccountListener);
        newAccountButtonPanel.add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setToolTipText("Return To List");
        cancelButton.setActionCommand("Return To List");
        cancelButton.addActionListener(newAccountListener);
        newAccountButtonPanel.add(cancelButton);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        newInputPanel.add(newAccountButtonPanel, c);

        newAccountPanel.add(newInputPanel, BorderLayout.CENTER);

        managerPanel.add(newAccountPanel, NEW_PANEL);

    }

    private void initCurrentAccountScreen() {
        currentAccountPanel = new JPanel(new BorderLayout());

        currentAccountListener = new CurrentAccountHandler(this);
        treeHandler = new CurrentTreeHandler(this);

        initCurrentNavigationBar();
        initSplitPane();

        managerPanel.add(currentAccountPanel, CURRENT_PANEL);

    }

    private void initCurrentNavigationBar() {
        navigationPanel = new JPanel();
        accountsButton = new JButton("Accounts");
        accountsButton.setToolTipText("Return To Account List");
        accountsButton.setActionCommand("Return To Account List");
        accountsButton.addActionListener(currentAccountListener);
        navigationPanel.add(accountsButton);

        currentExitButton = new JButton("Exit");
        currentExitButton.setToolTipText("Exit Game");
        currentExitButton.setActionCommand("Exit Game");
        currentExitButton.addActionListener(currentAccountListener);
        navigationPanel.add(currentExitButton);

        currentAccountPanel.add(navigationPanel, BorderLayout.NORTH);
    }

    private void initSplitPane() {
        initTree();
        initScorePanel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                worldTreeScrollPane,
                scoreViewPanel);
        splitPane.setDividerLocation(200);

        currentAccountPanel.add(splitPane, BorderLayout.CENTER);
    }

    private void initScorePanel() {
        scoreViewPanel = new JPanel(new BorderLayout());
        initScoreStats();
        initScoreButtons();

    }

    private void initScoreStats() {

        scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        regionName = new JLabel("REGION");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        scorePanel.add(regionName, c);
        scoreType = new JLabel("TYPE");
        c.gridx = 0;
        c.gridy = 1;
        scorePanel.add(scoreType, c);

        playedLabel = new JLabel("Times Played: ");
        c.gridx = 0;
        c.gridy = 2;
        scorePanel.add(playedLabel, c);
        playedDisplayLabel = new JLabel("");
        c.gridx = 1;
        c.gridy = 2;
        scorePanel.add(playedDisplayLabel, c);

        scoreLabel = new JLabel("High Score: ");
        c.gridx = 0;
        c.gridy = 3;
        scorePanel.add(scoreLabel, c);
        scoreDisplayLabel = new JLabel("");
        c.gridx = 1;
        c.gridy = 3;
        scorePanel.add(scoreDisplayLabel, c);

        timesLabel = new JLabel("Fastest Time: ");
        c.gridx = 0;
        c.gridy = 4;
        scorePanel.add(timesLabel, c);
        timesDisplayLabel = new JLabel("");
        c.gridx = 1;
        c.gridy = 4;
        scorePanel.add(timesDisplayLabel, c);


        scoreViewPanel.add(scorePanel, BorderLayout.CENTER);
    }

    private void initScoreButtons() {
        scoreButtonPanel = new JPanel();
        subregionButton = new JButton("subregion");
        subregionButton.setToolTipText("Subregion Mode");
        subregionButton.setActionCommand("SUBREGION");
        subregionButton.addActionListener(currentAccountListener);
        scoreButtonPanel.add(subregionButton);

        capitalButton = new JButton("capital");
        capitalButton.setToolTipText("Capital Mode");
        capitalButton.setActionCommand("CAPITAL");
        capitalButton.addActionListener(currentAccountListener);
        scoreButtonPanel.add(capitalButton);

        flagButton = new JButton("flag");
        flagButton.setToolTipText("Flag Mode");
        flagButton.setActionCommand("FLAG");
        flagButton.addActionListener(currentAccountListener);
        scoreButtonPanel.add(flagButton);

        leaderButton = new JButton("leader");
        leaderButton.setToolTipText("Leader Mode");
        leaderButton.setActionCommand("LEADER");
        leaderButton.addActionListener(currentAccountListener);
        scoreButtonPanel.add(leaderButton);

        scoreViewPanel.add(scoreButtonPanel, BorderLayout.SOUTH);
    }

    private void initTree() {
        TreeModel model = new RegionTreeModel(selectedAccount.getTreeRoot());
        worldTree = new JTree(model);
        worldTree.addTreeSelectionListener(treeHandler);
        worldTreeScrollPane = new JScrollPane(worldTree);
        
        


    }

    /// CHANGE GAME PHASE ///
    public void goToAccountList() {
        //upd8Accounts();
        selectButton.setVisible(!accountListModel.isEmpty());
        if(!accountListModel.isEmpty())
            accountList.setSelectedIndex(0);
        gamePhases.show(managerPanel, ACCOUNTS_PANEL);

    }

    public void upd8Accounts()
    {
        fileManager.loadAccounts();
        accountListModel.clear();
        for (int i = 0; i < fileManager.getFiles().size(); i++) {
            accountListModel.add(i, fileManager.getFiles().get(i).getName());
        }  
    }
    public void goToNewAccount() {
        accountTextField.setText("");
        newAccountInfoError.setVisible(false);
        gamePhases.show(managerPanel, NEW_PANEL);

    }

    public void goToCurrentAccount() {
        initCurrentAccountScreen();
        createTree();
            worldTree.setSelectionRow(0);

        gamePhases.show(managerPanel, CURRENT_PANEL);

    }

    public void goToWelcomeScreen() {
        gamePhases.show(managerPanel, WELCOME_PANEL);
    }

    public void exitGame() {
        System.exit(0);
    }

    // DATA MANAGEMENT //
    public void createNewAccount() {
        if (accountListModel.contains(accountTextField.getText())
                || accountTextField.getText().isEmpty()) {
            newAccountInfoError.setVisible(true);
            accountTextField.setText("");
        } else {
            makeNewAccount(accountTextField.getText());
            accountTextField.setText("");
        }
    }

    public String getNewAccountInput() {
        return accountTextField.getText();
    }

    public void makeNewAccount(String name) {
        selectedAccount = fileManager.newFile(name);
            accountListModel.add(accountListModel.getSize(), name);


        goToCurrentAccount();
    }

    public void getListSelection() {
        int index = accountList.getSelectedIndex();
        fileManager.loadAccounts();
        currentAccount = fileManager.getFiles().get(index);

        try {
            FileInputStream fileIn =
                    new FileInputStream(currentAccount);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            selectedAccount = (Account) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("ding dong you're wrong");
            c.printStackTrace();
            return;
        }
        
        goToCurrentAccount();

    }

    public RegionNode getSelectedRegion() {
        // GET THE PATH TO THE NODE
        TreePath selectedPath = worldTree.getSelectionPath();
        if (selectedPath != null) {
            // AND GET THE LAST ONE, WHICH IS THE SELECTED ONE
            RegionNode selectedNode = (RegionNode) selectedPath.getLastPathComponent();
            return selectedNode;
        }
        return null;
    }
    /// tree methods ///

    public void createTree() {
    }

    public void display() {
        regionName.setText(getSelectedRegion().getName());
        
        checkScores();
    }
    
    public void checkScores()
    {
      subregionButton.setEnabled(getSelectedRegion().hasScore("SUBREGION"));
      capitalButton.setEnabled(getSelectedRegion().hasScore("CAPITAL"));
      flagButton.setEnabled(getSelectedRegion().hasScore("FLAG"));
      leaderButton.setEnabled(getSelectedRegion().hasScore("LEADER"));

    }

    public void displayScore(String type)
    {
        if(getSelectedRegion().hasScore(type))
        {
        scoreType.setText(type);
        playedDisplayLabel.setText(
                ""+getSelectedRegion().getScore(type).getTimesPlayed()+"" );
        scoreDisplayLabel.setText(
                ""+getSelectedRegion().getScore(type).getHighScore()+"" );
        timesDisplayLabel.setText(
                ""+getSelectedRegion().getScore(type).getFastTime()+"" );
        }
        
    }
    /// HELPER METHODS ///
    public void centerWindow() {

        Toolkit singletonToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = singletonToolkit.getScreenSize();
        int halfWayX = screenSize.width / 2;
        int halfWayY = screenSize.height / 2;
        int halfWindowWidth = window.getWidth() / 2;
        int halfWindowHeight = window.getHeight() / 2;
        int windowX = halfWayX - halfWindowWidth;
        int windowY = halfWayY - halfWindowHeight;
        window.setLocation(windowX, windowY);
    }

    public static void main(String[] args) {
        AccountManager app = new AccountManager();
        app.init();

    }
}
