package p005cm.aptoide.p006pt;

import org.jacoco.agent.p025rt.internal_8ff85ea.Offline;
import p318e.p319a.C13181b;
import p318e.p319a.C13182c;

/* renamed from: cm.aptoide.pt.ApplicationModule_ProvidesAutoUpdateBaseHostFactory */
public final class ApplicationModule_ProvidesAutoUpdateBaseHostFactory implements C13181b<String> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final ApplicationModule module;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] probes = Offline.getProbes(8471585175829799462L, "cm/aptoide/pt/ApplicationModule_ProvidesAutoUpdateBaseHostFactory", 7);
        $jacocoData = probes;
        return probes;
    }

    public ApplicationModule_ProvidesAutoUpdateBaseHostFactory(ApplicationModule module2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.module = module2;
        $jacocoInit[0] = true;
    }

    public String get() {
        boolean[] $jacocoInit = $jacocoInit();
        ApplicationModule applicationModule = this.module;
        $jacocoInit[1] = true;
        String providesAutoUpdateBaseHost = applicationModule.providesAutoUpdateBaseHost();
        $jacocoInit[2] = true;
        C13182c.m43111a(providesAutoUpdateBaseHost, "Cannot return null from a non-@Nullable @Provides method");
        String str = providesAutoUpdateBaseHost;
        $jacocoInit[3] = true;
        return str;
    }

    public static C13181b<String> create(ApplicationModule module2) {
        boolean[] $jacocoInit = $jacocoInit();
        ApplicationModule_ProvidesAutoUpdateBaseHostFactory applicationModule_ProvidesAutoUpdateBaseHostFactory = new ApplicationModule_ProvidesAutoUpdateBaseHostFactory(module2);
        $jacocoInit[4] = true;
        return applicationModule_ProvidesAutoUpdateBaseHostFactory;
    }

    public static String proxyProvidesAutoUpdateBaseHost(ApplicationModule instance) {
        boolean[] $jacocoInit = $jacocoInit();
        String providesAutoUpdateBaseHost = instance.providesAutoUpdateBaseHost();
        $jacocoInit[5] = true;
        return providesAutoUpdateBaseHost;
    }
}
