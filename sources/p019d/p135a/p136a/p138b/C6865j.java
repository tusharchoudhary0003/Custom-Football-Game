package p019d.p135a.p136a.p138b;

import android.util.Log;
import p019d.p135a.p136a.p137a.C6852h;

/* 'enum' access flag removed */
/* renamed from: d.a.a.b.j */
/* compiled from: PaymentRequestState */
class C6865j extends C6874s {
    C6865j(String str, int i) {
        super(str, i, null);
    }

    /* renamed from: a */
    public C6852h mo22283a(C6875t paymentTrigger) {
        int i = C6864i.f12572a[paymentTrigger.ordinal()];
        if (i == 1) {
            return C6874s.WAITING_FOR_PAYMENT_DATA;
        }
        if (i == 2) {
            return C6874s.ABORTED;
        }
        String str = C6852h.f12560a;
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        sb.append(" - Unknown trigger received: ");
        sb.append(paymentTrigger.toString());
        Log.d(str, sb.toString());
        return this;
    }
}
