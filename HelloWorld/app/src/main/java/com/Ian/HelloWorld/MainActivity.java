package com.Ian.HelloWorld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String afDevKey = "REaW4oazBFH6zuBNihvAWE";
        AppsFlyerLib appsflyer = AppsFlyerLib.getInstance();
        appsflyer.setLogLevel(AFLogger.LogLevel.VERBOSE);
        appsflyer.setDebugLog(true);

        appsflyer.init(afDevKey, null, this);
        appsflyer.start(this);

        Map<String, Object> eventValues = new HashMap<String, Object>();
        eventValues.put(AFInAppEventParameterName.REVENUE, 1234.56);

        AppsFlyerLib.getInstance().logEvent(getApplicationContext(),
                AFInAppEventType.PURCHASE, eventValues,
                new AppsFlyerRequestListener() {
                    @Override
                    public void onSuccess() {
                        Log.d("Success", "Event sent successfully");
                    }
                    @Override
                    public void onError(int i, @NonNull String s) {
                        Log.d("Fail", "Event failed to be sent:\n" +
                                "Error code: " + i + "\n"
                                + "Error description: " + s);
                    }
                });

        setContentView(R.layout.activity_main);
    }
}