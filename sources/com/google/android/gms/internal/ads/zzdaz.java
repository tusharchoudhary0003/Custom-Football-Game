package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzdaz implements Creator<zzday> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzday[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int b = SafeParcelReader.m21925b(parcel);
        int i = 0;
        byte[] bArr = null;
        while (parcel.dataPosition() < b) {
            int a = SafeParcelReader.m21920a(parcel);
            int a2 = SafeParcelReader.m21919a(a);
            if (a2 == 1) {
                i = SafeParcelReader.m21949w(parcel, a);
            } else if (a2 != 2) {
                SafeParcelReader.m21918C(parcel, a);
            } else {
                bArr = SafeParcelReader.m21933g(parcel, a);
            }
        }
        SafeParcelReader.m21944r(parcel, b);
        return new zzday(i, bArr);
    }
}
