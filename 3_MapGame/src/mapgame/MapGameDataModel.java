/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgame;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TreeMap;
import java.util.Vector;
import mapclass.events.TikTok;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.Sprite;
import mini_game.SpriteType;

import static mapgame.MapGame.*;
import mapgame.sprites.ListBoxes;
import mapgame.sprites.RegionKey;

/**
 *
 * @author yv
 */
public class MapGameDataModel extends MiniGameDataModel {

    private int score;
    private TreeMap<String, Color> regions;
    private Vector<String> regionList;
    private Vector<RegionKey> keys;
    private int keyCount;
    private int incorrect;
    private int found;
    private int seconds;
    private Color selected;
    SimpleDateFormat time;
    Date date;
    private Color replace;
    private TikTok clock;
    private Vector<Sprite> listBoxes;
    String location;
    
    private boolean answerFound;

    public MapGameDataModel() {
        regions = new TreeMap<String, Color>();
        regionList = new Vector<String>();

        replace = FAKE_COLOR;
        time = new SimpleDateFormat("HH:mm:ss");
        keys = new Vector<RegionKey>();
        
        
    }

    private void initRegions() {
        regions = new TreeMap<String, Color>();
        regionList = new Vector<String>();
        regions.put(GALICIA, GALICIA_COLOR);
        regions.put(ASTURIAS, ASTURIAS_COLOR);
        regions.put(CANTABRIA, CANTABRIA_COLOR);
        regions.put(BASQUE_COUNTRY, BASQUE_COUNTRY_COLOR);
        regions.put(NAVARRE, NAVARRE_COLOR);
        regions.put(CATALONIA, CATALONIA_COLOR);
        regions.put(CASTILE_LEON, CASTILE_LEON_COLOR);
        regions.put(LA_RIOJA, LA_RIOJA_COLOR);
        regions.put(ARAGON, ARAGON_COLOR);
        regions.put(EXTREMADURA, EXTREMADURA_COLOR);
        regions.put(MADRID, MADRID_COLOR);
        regions.put(CASTILE_LA_MANCHA, CASTILE_LA_MANCHA_COLOR);
        regions.put(VALENCIA, VALENCIA_COLOR);
        regions.put(BALEARIC_ISLANDS, BALEARIC_ISLANDS_COLOR);
        regions.put(ANDALUSIA, ANDALUSIA_COLOR);
        regions.put(MURICA, MURICA_COLOR);
        regions.put(CANARY_ISLANDS, CANARY_ISLANDS_COLOR);
        regions.put(CEUTA, CEUTA_COLOR);
        regions.put(MELILLA, MELILLA_COLOR);

        regionList.add(GALICIA);
        regionList.add(ASTURIAS);
        regionList.add(CANTABRIA);
        regionList.add(BASQUE_COUNTRY);
        regionList.add(NAVARRE);
        regionList.add(CATALONIA);
        regionList.add(CASTILE_LEON);
        regionList.add(LA_RIOJA);
        regionList.add(ARAGON);
        regionList.add(EXTREMADURA);
        regionList.add(MADRID);
        regionList.add(CASTILE_LA_MANCHA);
        regionList.add(VALENCIA);
        regionList.add(BALEARIC_ISLANDS);
        regionList.add(ANDALUSIA);
        regionList.add(MURICA);
        regionList.add(CANARY_ISLANDS);
        regionList.add(CEUTA);
        regionList.add(MELILLA);
        
        RegionKey temp;
        String tempName;
        Color tempColor;
        for(int i=0;i<regions.size();i++)
        {
            tempName = regionList.get(i);
            tempColor = regions.get(tempName);
            temp = new RegionKey(tempName, tempColor);
            keys.add(temp);
        }

    }

    public String getHead() {
        return regionList.firstElement();
    }

    public void evaluateClick(Color clickedColor) {
        selected = clickedColor;
        
        if (clickedColor.equals( regions.get(getHead()) )) 
        {
            answerFound = true;
            regionList.remove(0);
            found++;
        } 
        
        else if (regions.containsValue(clickedColor)) 
        {
            answerFound = false;
            incorrect++;

        } 
        else 
        {
            selected = FAKE_COLOR;
        }

    }

    public int getScore() {
        calculateScore();
        return score;
    }

    public int getUnfound() {
        return regionList.size();
    }

    public int getFound() {
        return found;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public Vector<String> getRegions() {
        return regionList;
    }
    
    public String getName(int i)
    {
        return regionList.get(i);
    }

    public void calculateScore() {
        score = MAX_SCORE - (incorrect * 10) - seconds;
    }
    
    public void updateSeconds()
    {
        seconds++;
    }
    public String getTime()
    {
        int m = (seconds/60);
        int s = (seconds%60);
        if(m < 10 && s < 10)
            return "0"+m+":0"+s;
        else if(m < 10 && s > 10)
            return "0"+m+":"+s;
        else if(m>10 && s<10)
            return m+":0"+s;
        else
            return m+":"+s;


    }

    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y) {
        MapGame mGame = (MapGame) game;

        Sprite bg = mGame.getGUIDecor().get(MAP_TYPE);
        SpriteType bgST = bg.getSpriteType();
        BufferedImage img = bgST.getStateImage(bg.getState()) ;
        int rgb = img.getRGB(x, y);
        Color c = new Color(rgb);
        evaluateClick(c);

    }

    private void regionFound(Color yes, MapGame game) {
       
        Sprite bg = game.getGUIDecor().get(MAP_TYPE);
        SpriteType bgST = bg.getSpriteType();
        int choose = 0;
        for(int i=0;i<keys.size();i++)
        {
            if( keys.get(i).getColor().equals(yes) )
            {
                choose = i;
            }
                
        }
        keys.get(choose).greenPixels(bgST.getStateImage(bg.getState()));
        resetIncorrect(game);

    }
    
        private void regionIncorrect(Color wrongColor, MapGame game) {
        Sprite bg = game.getGUIDecor().get(MAP_TYPE);
        SpriteType bgST = bg.getSpriteType();
                int choose = 0;
        for(int i=0;i<keys.size();i++)
        {
            if( keys.get(i).getColor().equals(wrongColor) )
                choose = i;
        }
            keys.get(choose).redPixels(bgST.getStateImage(bg.getState()));

    }
        
        private void resetIncorrect(MapGame game) {
        Sprite bg = game.getGUIDecor().get(MAP_TYPE);
        SpriteType bgST = bg.getSpriteType();
        BufferedImage img = bgST.getStateImage(bg.getState());
        for(int i=0;i<keys.size();i++)
        {
            keys.get(i).canCorrect(img);
        }
    }

    @Override
    public void reset(MiniGame game) {
        selected = FAKE_COLOR;
        seconds = INIT_INC;
        answerFound = false;
        regionList.clear();
        regions.clear();
        score = MAX_SCORE;
        incorrect = INIT_INC;
        found = INIT_INC;
        initRegions();
        
       clock = new TikTok(this);
        


        Sprite bg = game.getGUIDecor().get(MAP_TYPE);
        SpriteType bgST = bg.getSpriteType();
       BufferedImage img = bgST.getStateImage(bg.getState());
        for(int i=0;i<keys.size();i++)
            keys.elementAt(i).setPixels(img);

        beginGame();
    }

    @Override
    public void updateAll(MiniGame game) {

        if (regionList.isEmpty()) 
        {
            endGameAsWin();
        } 
        else 
        {
            if(answerFound)
            {
                regionFound(selected, (MapGame) game);
            }
            else if(!selected.equals(FAKE_COLOR))
            {
                regionIncorrect(selected, (MapGame) game);
            }

        }
        
    }

    @Override
    public void updateDebugText(MiniGame game) {
    }

    private void updateAll(MiniGame game, Vector<Sprite> sprites) {
        for (Sprite s : sprites) {
            s.update(game);
        }
    }
}
