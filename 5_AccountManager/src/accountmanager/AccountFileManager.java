/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanager;
import account_data.Account;
import static accountmanager.AccountManagerSettings.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author yv
 */
public class AccountFileManager {
    
    private AccountManager app;
    File directory;
    private ArrayList<File> accountFiles;
    private ArrayList<String> accountNames;
    private File currentFile;
    private String currentFileName;
    
    public AccountFileManager(AccountManager initApp)
    {
       app = initApp;
       accountFiles = new ArrayList();
       accountNames = new ArrayList();
       directory = new File(ACCOUNT_FILE_PREFIX);

       loadAccounts();
    }
    
    public File getDirectory()
    {
        return directory;
    }
    
    public void loadAccounts()
    {
        File[] folder = directory.listFiles();
        for(int i=0;i<directory.listFiles().length;i++)
        {
            accountFiles.add(folder[i]);


        }
    }
           
    public ArrayList<File> getFiles()
    {
        return accountFiles;
    }
    
    
    public Account newFile(String name)
    {
        Account newAccount = new Account(name);
        
         try
      {
         FileOutputStream fileOut =
         new FileOutputStream(ACCOUNT_FILE_PREFIX+name);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(newAccount);
         out.close();
          fileOut.close();
      }catch(IOException i)
      {
          i.printStackTrace();
      }
      
         return newAccount;
    }
    
    public void loadFile(File file)
    {
        file.listFiles();
        
    }
}
