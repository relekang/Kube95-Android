package com.rolflekang.kube95;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetProvider extends AppWidgetProvider {
	
	public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
	
	private HttpConnector httpCon;
	private Pantekassa pantList;
	
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        
        pantList = new Pantekassa();        
    	httpCon = new HttpConnector("10.0.1.3");
    	pantList.parseStrings(httpCon.getList(httpCon.PANT));
        CleanGuy cleaner = new CleanGuy();

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent launchIntent = new Intent(context, PantListActivity.class);
            PendingIntent pendingLaunchIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            
            Intent updateIntent = new Intent(context, WidgetProvider.class);
            updateIntent.setAction(ACTION_WIDGET_RECEIVER);
            PendingIntent pendingUpdateIntent = PendingIntent.getBroadcast(context,0, updateIntent,0);

                    
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.widgetpantbtn, pendingLaunchIntent);
            views.setOnClickPendingIntent(R.id.widgetsumholder,pendingUpdateIntent);
            
            views.setTextViewText(R.id.widgetcurrentcleaner, cleaner.getCleaner());
            views.setTextViewText(R.id.widgetnextcleaner, cleaner.getNextCleaner());
            views.setTextViewText(R.id.widgetpantsum, ""+pantList.getSum()+"");

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {    	
    	if(intent.getAction().equals(ACTION_WIDGET_RECEIVER)) {
    		
    		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
    	    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
    	    if (appWidgetIds.length > 0) {
    	        new WidgetProvider().onUpdate(context, appWidgetManager, appWidgetIds);
        		Toast.makeText(context, "Oppdaterte pantesum", Toast.LENGTH_SHORT).show();
    	    }    	    
    	}
    	
    	super.onReceive(context, intent);
    }
}
