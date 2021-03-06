package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.paypal.android.sdk.C11756Pb;
import com.paypal.android.sdk.C11761Rb;
import com.paypal.android.sdk.C11833ic;
import com.paypal.android.sdk.C11845lc;
import com.paypal.android.sdk.C11849mc;
import com.paypal.android.sdk.C11853nc;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;

public final class PayPalProfileSharingActivity extends Activity {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f37403a = PayPalProfileSharingActivity.class.getSimpleName();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Date f37404b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Timer f37405c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public PayPalService f37406d;

    /* renamed from: e */
    private final ServiceConnection f37407e = new C11903W(this);

    /* renamed from: f */
    private boolean f37408f;

    /* renamed from: a */
    static /* synthetic */ C11937ha m39359a(PayPalProfileSharingActivity payPalProfileSharingActivity) {
        return new C11905X(payPalProfileSharingActivity);
    }

    public final void finish() {
        super.finish();
        StringBuilder sb = new StringBuilder();
        sb.append(PayPalProfileSharingActivity.class.getSimpleName());
        sb.append(".finish");
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        String str;
        String str2;
        super.onActivityResult(i, i2, intent);
        StringBuilder sb = new StringBuilder();
        sb.append(PayPalProfileSharingActivity.class.getSimpleName());
        sb.append(".onActivityResult");
        if (i == 1) {
            if (i2 == -1) {
                if (intent != null) {
                    String str3 = "com.paypal.android.sdk.authorization";
                    PayPalAuthorization payPalAuthorization = (PayPalAuthorization) intent.getParcelableExtra(str3);
                    if (payPalAuthorization != null) {
                        Intent intent2 = new Intent();
                        intent2.putExtra(str3, payPalAuthorization);
                        setResult(-1, intent2);
                    } else {
                        str2 = f37403a;
                        str = "result was OK, have data, but no authorization state in bundle, oops";
                    }
                } else {
                    str2 = f37403a;
                    str = "result was OK, no intent data, oops";
                }
                Log.e(str2, str);
            } else if (i2 != 0) {
                String str4 = f37403a;
                StringBuilder sb2 = new StringBuilder("unexpected request code ");
                sb2.append(i);
                sb2.append(" call it a cancel");
                Log.wtf(str4, sb2.toString());
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StringBuilder sb = new StringBuilder();
        sb.append(PayPalProfileSharingActivity.class.getSimpleName());
        sb.append(".onCreate");
        new C11853nc(this).mo38825a();
        new C11849mc(this).mo38822a();
        new C11845lc(this).mo38821a(Arrays.asList(new String[]{PayPalProfileSharingActivity.class.getName(), LoginActivity.class.getName(), FuturePaymentInfoActivity.class.getName(), ProfileSharingConsentActivity.class.getName()}));
        this.f37408f = bindService(C11867Da.m39245b((Activity) this), this.f37407e, 1);
        setTheme(16973934);
        requestWindowFeature(8);
        C11833ic icVar = new C11833ic(this);
        setContentView(icVar.f37234a);
        icVar.f37236c.setText(C11756Pb.m38949a(C11761Rb.CHECKING_DEVICE));
        C11867Da.m39239a((Activity) this, (TextView) null, C11761Rb.CHECKING_DEVICE);
    }

    /* access modifiers changed from: protected */
    public final Dialog onCreateDialog(int i, Bundle bundle) {
        return i != 2 ? i != 3 ? C11867Da.m39229a((Activity) this, C11761Rb.UNAUTHORIZED_DEVICE_TITLE, bundle, i) : C11867Da.m39229a((Activity) this, C11761Rb.UNAUTHORIZED_MERCHANT_TITLE, bundle, i) : C11867Da.m39227a((Activity) this, (OnClickListener) new C11901V(this));
    }

    /* access modifiers changed from: protected */
    public final void onDestroy() {
        StringBuilder sb = new StringBuilder();
        sb.append(PayPalProfileSharingActivity.class.getSimpleName());
        sb.append(".onDestroy");
        PayPalService payPalService = this.f37406d;
        if (payPalService != null) {
            payPalService.mo38990o();
            this.f37406d.mo39002u();
        }
        if (this.f37408f) {
            unbindService(this.f37407e);
            this.f37408f = false;
        }
        super.onDestroy();
    }
}
