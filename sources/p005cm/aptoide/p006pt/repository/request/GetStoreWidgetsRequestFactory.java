package p005cm.aptoide.p006pt.repository.request;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.view.WindowManager;
import okhttp3.OkHttpClient;
import org.jacoco.agent.p025rt.internal_8ff85ea.Offline;
import p005cm.aptoide.accountmanager.AptoideAccountManager;
import p005cm.aptoide.p006pt.dataprovider.interfaces.TokenInvalidator;
import p005cm.aptoide.p006pt.dataprovider.p010ws.BodyInterceptor;
import p005cm.aptoide.p006pt.dataprovider.p010ws.p011v2.aptwords.AdsApplicationVersionCodeProvider;
import p005cm.aptoide.p006pt.dataprovider.p010ws.p013v7.BaseBody;
import p005cm.aptoide.p006pt.dataprovider.p010ws.p013v7.BaseRequestWithStore.StoreCredentials;
import p005cm.aptoide.p006pt.dataprovider.p010ws.p013v7.store.GetStoreWidgetsRequest;
import p005cm.aptoide.p006pt.store.StoreCredentialsProvider;
import p026rx.C0120S;
import p026rx.Single;
import p026rx.p401g.C14959b;
import retrofit2.Converter.Factory;

/* renamed from: cm.aptoide.pt.repository.request.GetStoreWidgetsRequestFactory */
class GetStoreWidgetsRequestFactory {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final AptoideAccountManager accountManager;
    private final BodyInterceptor<BaseBody> bodyInterceptor;
    private final String clientUniqueId;
    private final Factory converterFactory;
    private final String filters;
    private final OkHttpClient httpClient;
    private final String partnerId;
    private final Resources resources;
    private final SharedPreferences sharedPreferences;
    private final StoreCredentialsProvider storeCredentialsProvider;
    private final ConnectivityManager systemService;
    private final TokenInvalidator tokenInvalidator;
    private final AdsApplicationVersionCodeProvider versionCodeProvider;
    private final WindowManager windowManager;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] probes = Offline.getProbes(-8384319159386646291L, "cm/aptoide/pt/repository/request/GetStoreWidgetsRequestFactory", 7);
        $jacocoData = probes;
        return probes;
    }

    public GetStoreWidgetsRequestFactory(StoreCredentialsProvider storeCredentialsProvider2, BodyInterceptor<BaseBody> bodyInterceptor2, OkHttpClient httpClient2, Factory converterFactory2, TokenInvalidator tokenInvalidator2, SharedPreferences sharedPreferences2, Resources resources2, WindowManager windowManager2, String clientUniqueId2, String partnerId2, AptoideAccountManager accountManager2, String filters2, ConnectivityManager systemService2, AdsApplicationVersionCodeProvider versionCodeProvider2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.storeCredentialsProvider = storeCredentialsProvider2;
        this.bodyInterceptor = bodyInterceptor2;
        this.httpClient = httpClient2;
        this.converterFactory = converterFactory2;
        this.tokenInvalidator = tokenInvalidator2;
        this.sharedPreferences = sharedPreferences2;
        this.resources = resources2;
        this.windowManager = windowManager2;
        this.clientUniqueId = clientUniqueId2;
        this.partnerId = partnerId2;
        this.accountManager = accountManager2;
        this.filters = filters2;
        this.systemService = systemService2;
        this.versionCodeProvider = versionCodeProvider2;
        $jacocoInit[0] = true;
    }

    public GetStoreWidgetsRequest newStoreWidgets(String url, boolean googlePlayServicesAvailable) {
        boolean[] $jacocoInit = $jacocoInit();
        C0120S enabled = this.accountManager.enabled();
        $jacocoInit[1] = true;
        C0120S d = enabled.mo3647d();
        $jacocoInit[2] = true;
        Single n = d.mo3678n();
        $jacocoInit[3] = true;
        C14959b a = n.mo3689a();
        $jacocoInit[4] = true;
        boolean adultContentEnabled = ((Boolean) a.mo46175a()).booleanValue();
        $jacocoInit[5] = true;
        StoreCredentials fromUrl = this.storeCredentialsProvider.fromUrl(url);
        BodyInterceptor<BaseBody> bodyInterceptor2 = this.bodyInterceptor;
        OkHttpClient okHttpClient = this.httpClient;
        Factory factory = this.converterFactory;
        TokenInvalidator tokenInvalidator2 = this.tokenInvalidator;
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        Resources resources2 = this.resources;
        WindowManager windowManager2 = this.windowManager;
        String str = this.clientUniqueId;
        String str2 = this.partnerId;
        String str3 = this.filters;
        boolean[] zArr = $jacocoInit;
        String str4 = str3;
        String str5 = str2;
        GetStoreWidgetsRequest ofAction = GetStoreWidgetsRequest.ofAction(url, fromUrl, bodyInterceptor2, okHttpClient, factory, tokenInvalidator2, sharedPreferences2, resources2, windowManager2, str, googlePlayServicesAvailable, str5, adultContentEnabled, str4, this.systemService, this.versionCodeProvider);
        zArr[6] = true;
        return ofAction;
    }
}
