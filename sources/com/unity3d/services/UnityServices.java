package com.unity3d.services;

import android.app.Activity;
import android.os.Build.VERSION;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.configuration.EnvironmentCheck;
import com.unity3d.services.core.configuration.InitializeThread;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;

public class UnityServices {
    private static boolean _configurationInitialized = false;

    public enum UnityServicesError {
        INVALID_ARGUMENT,
        INIT_SANITY_CHECK_FAIL
    }

    public static void initialize(Activity activity, String gameId, IUnityServicesListener listener) {
        initialize(activity, gameId, listener, false);
    }

    public static void initialize(Activity activity, String gameId, IUnityServicesListener listener, boolean testMode) {
        DeviceLog.entered();
        if (_configurationInitialized) {
            if (ClientProperties.getGameId() != null && !ClientProperties.getGameId().equals(gameId)) {
                DeviceLog.warning("You are trying to re-initialize with a different gameId");
            }
            return;
        }
        _configurationInitialized = true;
        if (!isSupported()) {
            DeviceLog.error("Error while initializing Unity Services: device is not supported");
            return;
        }
        SdkProperties.setInitializationTime(System.currentTimeMillis());
        if (gameId == null || gameId.length() == 0) {
            DeviceLog.error("Error while initializing Unity Services: empty game ID, halting Unity Ads init");
            if (listener != null) {
                listener.onUnityServicesError(UnityServicesError.INVALID_ARGUMENT, "Empty game ID");
            }
        } else if (activity == null) {
            DeviceLog.error("Error while initializing Unity Services: null activity, halting Unity Ads init");
            if (listener != null) {
                listener.onUnityServicesError(UnityServicesError.INVALID_ARGUMENT, "Null activity");
            }
        } else {
            String str = ") with game id ";
            String str2 = " (";
            String str3 = "Initializing Unity Services ";
            if (testMode) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(SdkProperties.getVersionName());
                sb.append(str2);
                sb.append(SdkProperties.getVersionCode());
                sb.append(str);
                sb.append(gameId);
                sb.append(" in test mode");
                DeviceLog.info(sb.toString());
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str3);
                sb2.append(SdkProperties.getVersionName());
                sb2.append(str2);
                sb2.append(SdkProperties.getVersionCode());
                sb2.append(str);
                sb2.append(gameId);
                sb2.append(" in production mode");
                DeviceLog.info(sb2.toString());
            }
            SdkProperties.setDebugMode(SdkProperties.getDebugMode());
            SdkProperties.setListener(listener);
            ClientProperties.setGameId(gameId);
            ClientProperties.setApplicationContext(activity.getApplicationContext());
            ClientProperties.setApplication(activity.getApplication());
            SdkProperties.setTestMode(testMode);
            if (EnvironmentCheck.isEnvironmentOk()) {
                DeviceLog.info("Unity Services environment check OK");
                InitializeThread.initialize(new Configuration());
                return;
            }
            DeviceLog.error("Error during Unity Services environment check, halting Unity Services init");
            if (listener != null) {
                listener.onUnityServicesError(UnityServicesError.INIT_SANITY_CHECK_FAIL, "Unity Services init environment check failed");
            }
        }
    }

    public static boolean isSupported() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean isInitialized() {
        return SdkProperties.isInitialized();
    }

    public static String getVersion() {
        return SdkProperties.getVersionName();
    }

    public static void setDebugMode(boolean debugMode) {
        SdkProperties.setDebugMode(debugMode);
    }

    public static boolean getDebugMode() {
        return SdkProperties.getDebugMode();
    }
}
