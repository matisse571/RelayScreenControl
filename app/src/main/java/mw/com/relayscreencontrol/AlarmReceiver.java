package mw.com.relayscreencontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;
/**
 * Created by Mark on 3/6/17.
 */



public class AlarmReceiver extends BroadcastReceiver {



    public AlarmReceiver() {



        // Log.d("LOG", "Alarm Receiver" );
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String ret = "";
        //String ret = "111111111111111";

       ret = new ReadSensor().readSensor();


        String subString = ret.substring(10,11);
        int i = Integer.parseInt(subString);
      //  i = 4;
        //Log.d("TAG observer",subString);
        //new WriteGpio().WriteGPIO("30", "1");

        if(i<3) {
            //Log.d("TAG observer","before1");
            new WriteGpio().WriteGPIO("30", "0");
          //  Log.d("TAG observer","before2");
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

           // Log.d("TAG observer","after");


        }
        if(i>3) {

            new WriteGpio().WriteGPIO("30", "1");



        }
    }
}