package com.moat.analytics.mobile.inm;

public enum MoatAdEventType {
    AD_EVT_FIRST_QUARTILE("AdVideoFirstQuartile"),
    AD_EVT_MID_POINT("AdVideoMidpoint"),
    AD_EVT_THIRD_QUARTILE("AdVideoThirdQuartile"),
    AD_EVT_COMPLETE("AdVideoComplete"),
    AD_EVT_PAUSED("AdPaused"),
    AD_EVT_PLAYING("AdPlaying"),
    AD_EVT_START("AdVideoStart"),
    AD_EVT_STOPPED("AdStopped"),
    AD_EVT_SKIPPED("AdSkipped"),
    AD_EVT_VOLUME_CHANGE("AdVolumeChange"),
    AD_EVT_ENTER_FULLSCREEN("fullScreen"),
    AD_EVT_EXIT_FULLSCREEN("exitFullscreen");
    

    /* renamed from: a */
    private final String f33409a;

    private MoatAdEventType(String str) {
        this.f33409a = str;
    }

    public static MoatAdEventType fromString(String str) {
        MoatAdEventType[] values;
        if (str != null) {
            for (MoatAdEventType moatAdEventType : values()) {
                if (str.equalsIgnoreCase(moatAdEventType.toString())) {
                    return moatAdEventType;
                }
            }
        }
        return null;
    }

    public final String toString() {
        return this.f33409a;
    }
}
