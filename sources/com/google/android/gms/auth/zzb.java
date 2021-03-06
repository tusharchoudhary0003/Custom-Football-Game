package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Creator<AccountChangeEventsRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new AccountChangeEventsRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int b = SafeParcelReader.m21925b(parcel);
        int i = 0;
        String str = null;
        Account account = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = SafeParcelReader.m21920a(parcel);
            int a2 = SafeParcelReader.m21919a(a);
            if (a2 == 1) {
                i = SafeParcelReader.m21949w(parcel, a);
            } else if (a2 == 2) {
                i2 = SafeParcelReader.m21949w(parcel, a);
            } else if (a2 == 3) {
                str = SafeParcelReader.m21941o(parcel, a);
            } else if (a2 != 4) {
                SafeParcelReader.m21918C(parcel, a);
            } else {
                account = (Account) SafeParcelReader.m21921a(parcel, a, Account.CREATOR);
            }
        }
        SafeParcelReader.m21944r(parcel, b);
        return new AccountChangeEventsRequest(i, i2, str, account);
    }
}
