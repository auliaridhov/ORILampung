package com.arba.orilampung;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetid : appWidgetIds){
            Intent intent  = new Intent(context, BuatAjuanActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);
            views.setOnClickPendingIntent(R.id.btn_widget, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetid, views);
        }
    }
}
