/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapclass.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mapgame.MapGame;

/**
 *
 * @author yv
 */
public class NewGameHandler implements ActionListener {
    
    private MapGame game;
    
    public NewGameHandler (MapGame initGame)
    {
        game = initGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.reset();
    }
    
}
