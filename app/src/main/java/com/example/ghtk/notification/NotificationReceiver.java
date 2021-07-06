package com.example.ghtk.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.example.ghtk.CreateOrderActivity;
import com.example.ghtk.R;
import com.example.ghtk.Uudai1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NotificationReceiver extends BroadcastReceiver {
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {

        class NotificationHelper {

            private final Context mContext;
            private static final String NOTIFICATION_CHANNEL_ID = "10001";


            NotificationHelper(Context context) {
                mContext = context;
            }



            void createNotification()
            {
                Date now = new Date();
                long currTime = now.getTime(); //Trả về giá trị milliseconds của ngày hiện tại kể từ 1/1/1970, 00:00:00 GTM
                long dayLeft = (1628355600000L - currTime)/86400000; //1628355600000L: giá trị ms của ngày 08/08/2021 00:00:00; Kết quả nhận được sau phép tính là số ngày còn lại đến ngày 08/08/2021

                Intent intent = new Intent(mContext, CreateOrderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                        0 /* Request code */, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                //Action for button Soạn đơn in notification
                Intent intent1 = new Intent(context, CreateOrderActivity.class);
                intent1.putExtra("yes", true);
                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_ONE_SHOT);

                Intent intent2 = new Intent(context, Uudai1.class);
                intent1.putExtra("yes", true);
                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder mBuilder1 = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID);
                mBuilder1.setSmallIcon(R.drawable.ic_noti);
                mBuilder1.setContentTitle("Fast Delivery")
                        .setContentText("Còn " + dayLeft + " ngày là chương trình siêu khuyến mãi 8/8 sẽ nổ ra, các khách hàng đã chuẩn bị tinh thần soạn đơn cùng Fast Delivery chưa?")
                        .setAutoCancel(true)
                        .setColor(Color.BLUE)
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                        .addAction(R.drawable.ic_launcher_foreground, "Soạn đơn ngay", pendingIntent1)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Còn " + dayLeft + " ngày là chương trình siêu khuyến mãi 8/8 sẽ nổ ra, các khách hàng đã chuẩn bị tinh thần soạn đơn cùng Fast Delivery chưa?"))
                        .setContentIntent(resultPendingIntent);

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM");
                String strDate = dateFormat.format(date);
                NotificationCompat.Builder mBuilder2 = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID);
                mBuilder2.setSmallIcon(R.drawable.ic_noti);
                mBuilder2.setContentTitle("Khuyến mãi")
                        .setContentText("Fast Delivery tặng bạn 2 mã giảm giá vận chuyển giao hàng cực hot, duy nhất ngày " + strDate + ". Nhận mã ưu đãi ngay")
                        .setAutoCancel(true)
                        .setColor(Color.BLUE)
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                        .addAction(R.drawable.ic_launcher_foreground, "Nhận mã", pendingIntent2)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Fast Delivery tặng bạn 2 mã giảm giá vận chuyển giao hàng cực hot, duy nhất ngày " + strDate + ". Nhận mã ưu đãi ngay"))
                        .setContentIntent(resultPendingIntent);



                NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.enableVibration(true);
                    notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

                    assert mNotificationManager != null;
                    mBuilder1.setChannelId(NOTIFICATION_CHANNEL_ID);
                    mBuilder2.setChannelId(NOTIFICATION_CHANNEL_ID);
                    mNotificationManager.createNotificationChannel(notificationChannel);
                    mNotificationManager.createNotificationChannel(notificationChannel);

                }
                assert mNotificationManager != null;
                mNotificationManager.notify(0 /* Request Code */, mBuilder1.build());
                mNotificationManager.notify(1 /* Request Code */, mBuilder2.build());

            }
        }

        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.createNotification();

    }
}
