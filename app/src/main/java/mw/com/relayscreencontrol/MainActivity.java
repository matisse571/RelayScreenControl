package mw.com.relayscreencontrol;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;

import java.util.List;

public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start();

        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);


        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        startService(new Intent(MainActivity.this.getApplicationContext(), FileModificationService.class));
       // startService(new Intent(FileModificationMonitor.this.getApplicationContext(), FileModificationService.class));



    }

    public void start () {

        PendingIntent pendingIntent;
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 30000, pendingIntent);
    }




    protected void moveToFront() {


        if (Build.VERSION.SDK_INT >= 11) { // honeycomb
            final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
           // final List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
            final List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(5);
            Log.d("Executed app","mmmmm");
            //activityManager.moveTaskToFront(2, 0);
            for (int i = 0; i < recentTasks.size(); i++)
            {



                Log.d("Executed app", "Application executed : "
                        +recentTasks.get(i).baseActivity.toShortString()
                        + "\t\t ID: "+recentTasks.get(i).id+"");
                activityManager.moveTaskToFront(63, 0);


               // ActivityManager am = (ActivityManager) mContext
                       // .getSystemService(Activity.ACTIVITY_SERVICE);


               // String packageName = am.getRunningTasks(1).get(0).topActivity
                String packageName = activityManager.getRunningTasks(1).get(0).topActivity
                        .getPackageName();

                Log.d("Executed app?",packageName);

               // String packageName2 = activityManager.getRunningTasks(1).get(0).topActivity;

                // bring to front
                if (recentTasks.get(i).baseActivity.toShortString().indexOf("myapplication") > -1) {
                    activityManager.moveTaskToFront(recentTasks.get(i).id, ActivityManager.MOVE_TASK_WITH_HOME);
                    Log.d("Executed app","fffff");
                    activityManager.moveTaskToFront(2, 0);
                }
            }
        }



    }


}
