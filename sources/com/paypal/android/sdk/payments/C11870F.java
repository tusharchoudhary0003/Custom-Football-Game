package com.paypal.android.sdk.payments;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: com.paypal.android.sdk.payments.F */
final class C11870F implements TextWatcher {

    /* renamed from: a */
    private /* synthetic */ LoginActivity f37304a;

    C11870F(LoginActivity loginActivity) {
        this.f37304a = loginActivity;
    }

    public final void afterTextChanged(Editable editable) {
        this.f37304a.m39288i();
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
