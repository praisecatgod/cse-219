/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgame.sprites;

import mapgame.MapGameDataModel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;

/**
 *
 * @author yv
 */
public class ListBoxes extends Sprite {
    
    private String name;
    
    
    public ListBoxes(SpriteType initSpriteType, float initX, float initY,
			float initVx, float initVy, String initState) 
    {
	super(initSpriteType, initX, initY, initVx, initVy, initState);   
    }
    
    public String getName()
    { return name;}
    
    public void setName(String initName)
    { name = initName; }
    
   //private void moveDown()

    
    
    public void update(MiniGame game)
	{
           if (y > game.getBoundaryBottom())
           {
               vX = 0f;
               vY = -200f;
           }
            
            /*if (y < game.getBoundaryTop())
			y = game.getBoundaryTop();
		else if ((y + spriteType.getHeight() > game.getBoundaryBottom()))
			y = game.getBoundaryBottom() - spriteType.getHeight();*/
            
            
        }
    
}
