package com.app.satgasmada;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    /* This class is a service class that runs in the background,
     * detecting every time a new notification is received*/
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage);
    }

    private void showNotification(RemoteMessage remoteMessage) {
        String channelId = "channel_new_notification";
        String channelName = "New notification";

        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, 0); // or use PendingIntent.FLAG_ONE_SHOT

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.logo_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setColor(ContextCompat.getColor(this, android.R.color.transparent))
                //.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(defaultSoundUri)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH); // You can change notification priority here
            //channel.enableVibration(true);
            //channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(channelId);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = builder.build();
        if (notificationManager != null) {
            int randomNotificationId = new Random().nextInt(); // Keep multiple notifications
            notificationManager.notify(randomNotificationId, notification);
        }
    }

    @Override
    /* There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/reinstalls the app
     * C) User clears app data*/
    public void onNewToken(@NonNull String newToken) {
        super.onNewToken(newToken);
        Log.d(TAG, "New token: " + newToken);
        sendRegistrationToServer(newToken);
    }

    // Save and update new token to database server
    public static void sendRegistrationToServer(String newToken) {
        Log.d(TAG, "sendRegistrationToServer called: " + newToken);
        // This token is required as a notification receiver
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null){
            FirebaseFirestore.getInstance().collection("users")
                    .document(firebaseUser.getUid())
                    .update("fcm_token", newToken)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) Log.d(TAG, "sendRegistrationToServer onSuccess");
                        else Log.e(TAG, "sendRegistrationToServer onFailure", task.getException());
                    });
        } else Log.w(TAG, "sendRegistrationToServer cancelled: no user logged in");
    }
}
