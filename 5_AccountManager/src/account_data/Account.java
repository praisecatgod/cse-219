/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package account_data;

import java.io.Serializable;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author yv
 */
public class Account implements Serializable {

    private String name;
    private RegionNode treeRoot;

    public Account(String initName) {
        name = initName;
        treeRoot = new RegionNode("WORLD", "World");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegionNode getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(RegionNode treeRoot) {
        this.treeRoot = treeRoot;
    }
}
