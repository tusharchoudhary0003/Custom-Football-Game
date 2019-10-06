package com.fyber.inneractive.sdk.p165f;

import okhttp3.internal.http.StatusLine;

/* renamed from: com.fyber.inneractive.sdk.f.f */
public enum C7737f {
    VAST_ERROR_INVALID_RESPONSE(301),
    VAST_ERROR_TOO_MANY_WRAPPERS(302),
    VAST_ERROR_NO_MEDIA_FILES(303),
    VAST_ERROR_NO_COMPATIBLE_MEDIA_FILE(304),
    VAST_ERROR_PRE_BUFFER_TIMEOUT(306),
    VAST_ERROR_BUFFER_TIMEOUT(StatusLine.HTTP_TEMP_REDIRECT),
    VAST_ERROR_FAILED_PLAYING_MEDIA_FILE(StatusLine.HTTP_PERM_REDIRECT),
    VAST_ERROR_FAILED_PLAYING_ALL_MEDIA_FILES(311),
    VAST_ERROR_UNSECURE_URL(312),
    MRAID_ERROR_UNSECURE_CONTENT(313),
    VPAID_ERROR_UNSECURE_CONTENT(314),
    VAST_UNKNOWN_PLAYER_ERROR(399),
    NATIVE_ERROR_INVALID_NATIVE_RESPONSE(401),
    NATIVE_ERROR_MISSING_PUB_REQUIRED_FIELD(402),
    NATIVE_ERROR_MISMATCH_REQUIRED_ASSET_RESPONSE(404),
    NATIVE_ERROR_INVALID_REQUIRED_ASSET_DATA(405),
    NATIVE_ERROR_INVALID_OPTIONAL_ASSET_DATA(406),
    NATIVE_ERROR_INVALID_LINK_WEB_URL(407),
    NATIVE_ERROR_FAILED_LOAD_ASSET(410),
    NATIVE_ERROR_MISMATCH_OPTIONAL_ASSET_RESPONSE(411),
    NATIVE_ERROR_MISSING_PROTOCOL_REQUIRED_FIELD(412),
    NATIVE_ERROR_RECEIVED_VIDEO_BUT_NOT_SUPPORTED(413),
    NATIVE_ERROR_NO_VALID_URLS_FOR_CLICK(414),
    NATIVE_ERROR_SOME_INVALID_URLS_FOR_PROCESSED_CLICK(415),
    MRAID_AUTO_ACTION_DETECTED(501),
    INTERNAL_CONFIG_MISMATCH(601),
    FATAL_CONFIGURATION_ERROR(701),
    MRAID_VIDEO_HAS_NOT_STARTED_PLAYING_IN_A_TIMELY_FASHION(390);
    

    /* renamed from: C */
    int f15484C;

    private C7737f(int i) {
        this.f15484C = i;
    }
}