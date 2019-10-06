package p005cm.aptoide.p006pt;

import javax.inject.Provider;
import org.jacoco.agent.p025rt.internal_8ff85ea.Offline;
import p005cm.aptoide.p006pt.promotions.CaptchaService.ServiceInterface;
import p318e.p319a.C13181b;
import p318e.p319a.C13182c;
import retrofit2.Retrofit;

/* renamed from: cm.aptoide.pt.ApplicationModule_ProvidesCaptchaServiceInterfaceFactory */
public final class ApplicationModule_ProvidesCaptchaServiceInterfaceFactory implements C13181b<ServiceInterface> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final ApplicationModule module;
    private final Provider<Retrofit> retrofitProvider;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] probes = Offline.getProbes(-8920077174727787360L, "cm/aptoide/pt/ApplicationModule_ProvidesCaptchaServiceInterfaceFactory", 7);
        $jacocoData = probes;
        return probes;
    }

    public ApplicationModule_ProvidesCaptchaServiceInterfaceFactory(ApplicationModule module2, Provider<Retrofit> retrofitProvider2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.module = module2;
        this.retrofitProvider = retrofitProvider2;
        $jacocoInit[0] = true;
    }

    public ServiceInterface get() {
        boolean[] $jacocoInit = $jacocoInit();
        ApplicationModule applicationModule = this.module;
        Provider<Retrofit> provider = this.retrofitProvider;
        $jacocoInit[1] = true;
        ServiceInterface providesCaptchaServiceInterface = applicationModule.providesCaptchaServiceInterface((Retrofit) provider.get());
        $jacocoInit[2] = true;
        C13182c.m43111a(providesCaptchaServiceInterface, "Cannot return null from a non-@Nullable @Provides method");
        ServiceInterface serviceInterface = providesCaptchaServiceInterface;
        $jacocoInit[3] = true;
        return serviceInterface;
    }

    public static C13181b<ServiceInterface> create(ApplicationModule module2, Provider<Retrofit> retrofitProvider2) {
        boolean[] $jacocoInit = $jacocoInit();
        ApplicationModule_ProvidesCaptchaServiceInterfaceFactory applicationModule_ProvidesCaptchaServiceInterfaceFactory = new ApplicationModule_ProvidesCaptchaServiceInterfaceFactory(module2, retrofitProvider2);
        $jacocoInit[4] = true;
        return applicationModule_ProvidesCaptchaServiceInterfaceFactory;
    }

    public static ServiceInterface proxyProvidesCaptchaServiceInterface(ApplicationModule instance, Retrofit retrofit) {
        boolean[] $jacocoInit = $jacocoInit();
        ServiceInterface providesCaptchaServiceInterface = instance.providesCaptchaServiceInterface(retrofit);
        $jacocoInit[5] = true;
        return providesCaptchaServiceInterface;
    }
}
