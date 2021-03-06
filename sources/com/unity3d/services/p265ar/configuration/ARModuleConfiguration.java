package com.unity3d.services.p265ar.configuration;

import com.unity3d.services.ads.configuration.IAdsModuleConfiguration;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.p265ar.ARCheck;
import com.unity3d.services.p265ar.ARUtils;
import com.unity3d.services.p265ar.api.C12601AR;
import com.unity3d.services.p265ar.view.ARViewHandler;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.unity3d.services.ar.configuration.ARModuleConfiguration */
public class ARModuleConfiguration implements IAdsModuleConfiguration {
    public Class[] getWebAppApiClassList() {
        return new Class[]{C12601AR.class};
    }

    public boolean resetState(Configuration configuration) {
        return true;
    }

    public boolean initModuleState(Configuration configuration) {
        if (ARCheck.isFrameworkPresent()) {
            ARUtils.isSupported(ClientProperties.getApplicationContext());
        }
        return true;
    }

    public boolean initErrorState(Configuration configuration, String state, String message) {
        return true;
    }

    public boolean initCompleteState(Configuration configuration) {
        return true;
    }

    public Map<String, Class> getAdUnitViewHandlers() {
        Map<String, Class> handlers = new HashMap<>();
        handlers.put("arview", ARViewHandler.class);
        return handlers;
    }
}
