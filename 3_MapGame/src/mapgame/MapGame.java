/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgame;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import mapclass.events.ExitHandler;
import mapclass.events.NewGameHandler;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.Sprite;
import mini_game.SpriteType;

/**
 *
 * @author yv
 */
public class MapGame extends MiniGame {

    public static final int FRAME_RATE = 30;
    public static final String APP_TITLE = "MapGame";
    	public static final String SPRITE_TYPES_SETUP_FILE = "./data/MapGameData.txt";
	public static final String SETUP_DELIMITER = "\\|";
	public static final Color COLOR_KEY = new Color(106, 0, 106);

    	public static final int GAME_WIDTH = 1200;
	public static final int GAME_HEIGHT = 800;
	public static final float BOUNDARY_TOP = 0.0f;
	public static final float BOUNDARY_BOTTOM = 0.0f;
	public static final float BOUNDARY_LEFT = 0.0f;
	public static final float BOUNDARY_RIGHT = 0.0f;
        
        public static final String BACKGROUND_TYPE = "BACKGROUND_TYPE";
        public static final String MAP_TYPE = "MAP_TYPE";
        public static final String NEW_TYPE = "NEW_TYPE";
        public static final String EXIT_TYPE = "EXIT_TYPE";
        
        public static final String DEFAULT_STATE = "DEFAULT_STATE";
        public static final String INVISIBLE_STATE = "INVISIBLE_STATE";

        public static final int INIT_INC = 0;
        public static final int MAX_SCORE = 10000;
        
        public static final Color GALICIA_COLOR = new Color(71,71,71);
        public static final Color ASTURIAS_COLOR = new Color(88,88,88);
        public static final Color CANTABRIA_COLOR = new Color(105,105,105);
        public static final Color BASQUE_COUNTRY_COLOR = new Color(122,122,122);
        public static final Color NAVARRE_COLOR = new Color(139,139,139);
        public static final Color CATALONIA_COLOR = new Color(156,156,156);
        public static final Color CASTILE_LEON_COLOR = new Color(173,173,173);
        public static final Color LA_RIOJA_COLOR = new Color(190,190,190);
        public static final Color ARAGON_COLOR = new Color(207,207,207);
        public static final Color EXTREMADURA_COLOR = new Color(72,72,72);
        public static final Color MADRID_COLOR = new Color(89,89,89);
        public static final Color CASTILE_LA_MANCHA_COLOR = new Color(106,106,106);
        public static final Color VALENCIA_COLOR = new Color(123,123,123);
        public static final Color BALEARIC_ISLANDS_COLOR = new Color(140,140,140);
        public static final Color ANDALUSIA_COLOR = new Color(157,157,157);
        public static final Color MURICA_COLOR = new Color(174,174,174);
        public static final Color CANARY_ISLANDS_COLOR = new Color(191,191,191);
        public static final Color CEUTA_COLOR = new Color(73,73,73);
        public static final Color MELILLA_COLOR = new Color(103,103,103);
        
        public static final String GALICIA = "Galicia";
        public static final String ASTURIAS = "Asturias";
        public static final String CANTABRIA = "Cantabria";
        public static final String BASQUE_COUNTRY = "Basque Country";
        public static final String NAVARRE= "Navarre";
        public static final String CATALONIA = "Catalonia";
        public static final String CASTILE_LEON = "Castile Leon";
        public static final String LA_RIOJA = "La Rioja";
        public static final String ARAGON = "Aragon";
        public static final String EXTREMADURA = "Extremadura";
        public static final String MADRID = "Madrid";
        public static final String CASTILE_LA_MANCHA = "Castile La Mancha";
        public static final String VALENCIA = "Valencia";
        public static final String BALEARIC_ISLANDS = "Balearic Islands";
        public static final String ANDALUSIA = "Andalusia";
        public static final String MURICA = "Murica";
        public static final String CANARY_ISLANDS = "Canary Islands";
        public static final String CEUTA = "Ceuta";
        public static final String MELILLA = "Melilla";
        
        public static final Color INCORRECT_COLOR = new Color(255,0,0);
        public static final Color CORRECT_COLOR = new Color(46,215,58);
        public static final Color FAKE_COLOR = new Color(6,6,0,0);
        
        
        
    public MapGame(){
        super(APP_TITLE, FRAME_RATE);
    }


    @Override
    public void initData() {
        data = new MapGameDataModel();
        data.setGameDimensions(GAME_WIDTH, GAME_HEIGHT);
        
        boundaryLeft 	= BOUNDARY_LEFT;
	boundaryRight 	= BOUNDARY_RIGHT;
	boundaryTop 	= BOUNDARY_TOP;
	boundaryBottom 	= BOUNDARY_BOTTOM;
        
        this.window.setBounds(0, 0, 1200, 800);
        
    }
    

    @Override
    public void initGUIControls() {
        canvas = new MapGamePanel(this, (MapGameDataModel) data);
        
        
        
        BufferedImage img;
	float x, y;
	SpriteType sT;
	Sprite s;
       
        sT = new SpriteType(BACKGROUND_TYPE);
	img = loadImage("./data/background.png");
	sT.addState(DEFAULT_STATE, img);
	x = 0;
	y = 0;
	s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
	guiDecor.put(BACKGROUND_TYPE, s);
        
        sT = new SpriteType(NEW_TYPE);
	img = loadImage("./data/new.png");
	sT.addState(DEFAULT_STATE, img);
	x = 970;
	y = 210;
	s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
	guiButtons.put(NEW_TYPE, s);
        
        sT = new SpriteType(EXIT_TYPE);
	img = loadImage("./data/exit.png");
	sT.addState(DEFAULT_STATE, img);
	x = 1070;
	y = 210;
	s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
	guiButtons.put(EXIT_TYPE, s);
        
        sT = new SpriteType(MAP_TYPE);
	img = loadImageWithColorKey("./data/map.png", COLOR_KEY);
	sT.addState(DEFAULT_STATE, img);
	x = 0;
	y = 0;
	s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
	guiDecor.put(MAP_TYPE, s); 
        
        sT = new SpriteType("WIN_DISPLAY_TYPE");
		img = loadImageWithColorKey("./data/win.png", COLOR_KEY);
		sT.addState("VISIBLE_STATE", img);
		x = 50;
		y = 50;
		img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
		sT.addState(INVISIBLE_STATE, img);
		s = new Sprite(sT, x, y, 0, 0, INVISIBLE_STATE);
		guiDecor.put("WIN_DISPLAY_TYPE", s);
    }
     

    @Override
    public void initGUIHandlers() {
        
        NewGameHandler ngh = new NewGameHandler(this);
        guiButtons.get(NEW_TYPE).setActionListener(ngh);
        
        ExitHandler egh = new ExitHandler(this);
        guiButtons.get(EXIT_TYPE).setActionListener(egh);

    }

    @Override
    public void reset() {
        BufferedImage img;
	float x, y;
	SpriteType sT;
	Sprite s;
        
        sT = new SpriteType(MAP_TYPE);
	img = loadImageWithColorKey("./data/map.png", COLOR_KEY);
	sT.addState(DEFAULT_STATE, img);
	x = 0;
	y = 0;
	s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
	guiDecor.put(MAP_TYPE, s); 
        
        sT = new SpriteType("WIN_DISPLAY_TYPE");
		img = loadImageWithColorKey("./data/win.png", COLOR_KEY);
		sT.addState("VISIBLE_STATE", img);
		x = 50;
		y = 50;
		img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
		sT.addState(INVISIBLE_STATE, img);
		s = new Sprite(sT, x, y, 0, 0, INVISIBLE_STATE);
		guiDecor.put("WIN_DISPLAY_TYPE", s);


        data.reset(this);
    }

    @Override
    public void updateGUI() {
        if(data.won())
        { 
           guiDecor.get("WIN_DISPLAY_TYPE").setState("VISIBLE_STATE");
           //canvas.

           
        }

    }

    public static void main(String[] args) {
        MapGame game = new MapGame();
        game.startGame();
    }
}
