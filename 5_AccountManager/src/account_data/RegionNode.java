/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package account_data;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.tree.TreeNode;

/**
 *
 * @author yv
 */
public class RegionNode implements TreeNode, Serializable {

    private String id;
    private String name;
    private Vector<Score> scores;
    private Vector<TreeNode> children;
    private TreeNode parent;

    public RegionNode(String initID, String initName) {
        id = initID;
        name = initName;
        children = new Vector<TreeNode>();
        scores = new Vector<Score>();
    }

    public Vector<Score> getScores() {
        return scores;
    }
        public Iterator<Score> getScoreIterator()
    {
        return scores.iterator();
    }
     
    public void addScore(Score score)
    {
        scores.add(score);
    }
        
        public Score getScore(String scoreType)
        {
            for(int i=0;i<scores.size();i++)
            {
                if(scores.elementAt(i).getType().equalsIgnoreCase(scoreType))
                    return scores.elementAt(i);
            }
            return null;
        }

        public boolean hasScore(String scoreType)
        {
            for(int i=0;i<scores.size();i++)
            {
                if(scores.elementAt(i).getType().equalsIgnoreCase(scoreType))
                    return true;
            }
            return false;
        }
    public void setScores(Vector<Score> scores) {
        this.scores = scores;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public void setParent(TreeNode initParent) {
        parent = initParent;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.elementAt(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return (children.size() == 0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Enumeration children() {
        return children.elements();
    }

    public String toString() {
        return name;
    }
}
