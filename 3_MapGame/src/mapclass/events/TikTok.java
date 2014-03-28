/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapclass.events;


import java.util.Timer;
import java.util.TimerTask;
import mapgame.MapGameDataModel;

/**
 *
 * @author yv
 */
public class TikTok  {
    
    Timer clock;
    MapGameDataModel data;
    
    public TikTok(MapGameDataModel initData)
    {
        data = initData;
        clock = new Timer();
        clock.scheduleAtFixedRate(new OnTheClock(), 0, 1000*1);
    }
    
    class OnTheClock extends TimerTask
{
    @Override
    public void run() {
        
        try
		{
			
			 if(data.inProgress() )
                            data.updateSeconds();

		}
		finally
		{
			// RELEASE IT, SINCE THE OTHER THREAD
			// MIGHT WANT TO UPDATE STUFF IN RESPONSE
			// TO A MOUSE CLICK
			
		}
       
    }
    
}
    
}


