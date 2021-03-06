package p005cm.aptoide.p006pt.dataprovider.p010ws.p012v3;

import p005cm.aptoide.p006pt.dataprovider.util.HashMapNotNull;

/* renamed from: cm.aptoide.pt.dataprovider.ws.v3.BaseBody */
public class BaseBody extends HashMapNotNull<String, Object> {
    public void setAndroidVersion(int version) {
        put("android_version", Integer.valueOf(version));
    }

    public void setAptoideMd5sum(String aptoideMd5sum) {
        put("aptoide_md5sum", aptoideMd5sum);
    }

    public void setAptoidePackage(String aptoidePackage) {
        put("aptoide_package", aptoidePackage);
    }

    public void setResponseMode(String mode) {
        put("mode", mode);
    }

    public void setAuthMode(String authMode) {
        put("authMode", authMode);
    }

    public void setAccessToken(String accessToken) {
        put("access_token", accessToken);
        put("oauthToken", accessToken);
    }

    public void setAptoideUid(String aptoideUid) {
        put("aptoide_uid", aptoideUid);
    }

    public void setQ(String q) {
        put("q", q);
    }

    public void setSimCountryISOCode(String countryCode) {
        put("simcc", countryCode);
    }

    public void setMobileNetworkCode(String mobileNetworkCode) {
        put("mnc", mobileNetworkCode);
    }

    public void setMobileCountryCode(String mobileCountryCode) {
        put("mcc", mobileCountryCode);
    }
}
