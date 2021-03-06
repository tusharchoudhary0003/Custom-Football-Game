package com.integralads.avid.library.mopub.session.internal.jsbridge;

import android.webkit.WebView;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidJavascriptInterface.AvidJavascriptInterfaceCallback;
import com.integralads.avid.library.mopub.weakreference.AvidWebView;

public class AvidWebViewManager implements AvidJavascriptInterfaceCallback {
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private final AvidBridgeManager avidBridgeManager;
    private final AvidWebView avidWebView = new AvidWebView(null);
    private AvidJavascriptInterface javascriptInterface;

    public AvidWebViewManager(InternalAvidAdSessionContext avidAdSessionContext2, AvidBridgeManager avidBridgeManager2) {
        this.avidAdSessionContext = avidAdSessionContext2;
        this.avidBridgeManager = avidBridgeManager2;
    }

    public void setWebView(WebView webView) {
        if (this.avidWebView.get() != webView) {
            this.avidBridgeManager.setWebView(null);
            clearJavascriptInterface();
            this.avidWebView.set(webView);
            if (webView != null) {
                this.javascriptInterface = new AvidJavascriptInterface(this.avidAdSessionContext);
                this.javascriptInterface.setCallback(this);
                webView.addJavascriptInterface(this.javascriptInterface, "avid");
            }
        }
    }

    public void destroy() {
        setWebView(null);
    }

    private void clearJavascriptInterface() {
        AvidJavascriptInterface avidJavascriptInterface = this.javascriptInterface;
        if (avidJavascriptInterface != null) {
            avidJavascriptInterface.setCallback(null);
            this.javascriptInterface = null;
        }
    }

    public void onAvidAdSessionContextInvoked() {
        this.avidBridgeManager.setWebView((WebView) this.avidWebView.get());
    }

    /* access modifiers changed from: 0000 */
    public AvidJavascriptInterface getJavascriptInterface() {
        return this.javascriptInterface;
    }
}
