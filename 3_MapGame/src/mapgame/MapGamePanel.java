/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JPanel;
import mini_game.Sprite;
import mini_game.SpriteType;

import static mapgame.MapGame.*;
import mapgame.sprites.ListBoxes;

/**
 *
 * @author yv
 */
public class MapGamePanel extends JPanel {

    private MapGame game;
    private MapGameDataModel data;

    public MapGamePanel(MapGame initGame, MapGameDataModel initData) {
        game = initGame;
        data = initData;
    }

    public void paintComponent(Graphics g) {
        renderToGraphicsContext(g);
    }

    public void renderToGraphicsContext(Graphics g) {
        renderBackground(g);

        // AND THE SUNS, BRAINS, AND ZOMBIES
        renderMap(g);


        // AND THE BUTTONS AND DECOR
        renderGUIControls(g);

        // NOW THE CHANGING NUMBER IN THE GUI
        renderStats(g);

        renderTitle(g);

        renderBoxes(g);
        
        if(data.won())
        {
            finalScore(g);
        }
    }

    public void renderBackground(Graphics g) {
        Sprite bg = game.getGUIDecor().get(BACKGROUND_TYPE);
        renderSprite(g, bg);
    }

    public void renderTitle(Graphics g) {
        String titleString = "MAP GAME: Spain";
        g.setFont( new Font("Serif", Font.BOLD, 30));
        g.setColor(new Color(102,0,0));
        g.drawString(titleString, 60, 125);
    }

    public void renderMap(Graphics g) {
        Sprite map = game.getGUIDecor().get(MAP_TYPE);
        renderSprite(g, map);
    }

    public void renderBoxes(Graphics g) {


        
       
        if (game.getDataModel().inProgress() )
        {       g.setFont( new Font("Serif", Font.PLAIN, 20));
        g.setColor(new Color(0,225,0));
          int placement = 760;
          g.drawString(data.getRegions().get(0), 970, placement);
          placement -=25;
          for(int i=1;i<data.getRegions().size();i++)
          {
                        g.setColor(Color.BLACK);

              g.drawString(data.getRegions().get(i), 970, placement);
              placement -=25;
          }
        }
        
    }

    public void renderSprites(Graphics g, Iterator<Sprite> spritesIt) {
        while (spritesIt.hasNext()) {
            Sprite spriteToRender = spritesIt.next();
            renderSprite(g, spriteToRender);
        }
    }

    public void renderGUIControls(Graphics g) {
        // GET EACH DECOR IMAGE ONE AT A TIME
        Collection<Sprite> decorSprites = game.getGUIDecor().values();
        for (Sprite s : decorSprites) {
            // MAKE SURE NOT TO RENDERING THE BACKGROUND
            // ON TOP OF EVERYTHING ELSE
            if (!s.getSpriteType().getSpriteTypeID().equals(BACKGROUND_TYPE)) {
                renderSprite(g, s);
            }
        }

        // AND NOW RENDERING THE BUTTONS
        Collection<Sprite> buttonSprites = game.getGUIButtons().values();
        for (Sprite s : buttonSprites) {
            renderSprite(g, s);
        }
    }

    public void renderStats(Graphics g) {
        g.setFont( new Font("Serif", Font.PLAIN, 20));
        g.setColor(new Color(102,0,0));
        String unfoundString = "Unfound: " + data.getUnfound();
        String foundString = "Found: " + data.getFound();
        String incString = "Incorrect: " + data.getIncorrect();
        String timeString = "Time: " + data.getTime();
        g.drawString(unfoundString
                + " " + foundString + " " + incString
                + " " + timeString, 50, 750);
    }

        public void finalScore(Graphics g) {
        g.setColor(Color.BLACK);
                g.setFont( new Font("Serif", Font.PLAIN, 50));
        String scoreString = "Score: " + data.getScore();
       
        g.drawString(scoreString, 400, 400);
    }
    public void renderSprite(Graphics g, Sprite s) {
        if (!s.getState().equals(INVISIBLE_STATE)) {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int) s.getX(), (int) s.getY(), bgST.getWidth(), bgST.getHeight(), null);
        }
    }
}
