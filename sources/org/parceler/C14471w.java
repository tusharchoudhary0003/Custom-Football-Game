package org.parceler;

import android.os.Parcel;
import org.parceler.NonParcelRepository.MapParcelable;
import org.parceler.p386a.C14445m;

/* renamed from: org.parceler.w */
/* compiled from: NonParcelRepository */
class C14471w extends C14445m {
    C14471w() {
    }

    /* renamed from: b */
    public void mo45543b(Object key, Parcel parcel) {
        parcel.writeParcelable(C14377B.m45944a(key), 0);
    }

    /* renamed from: c */
    public void mo45545c(Object value, Parcel parcel) {
        parcel.writeParcelable(C14377B.m45944a(value), 0);
    }

    /* renamed from: b */
    public Object mo45542b(Parcel parcel) {
        return C14377B.m45945a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
    }

    /* renamed from: c */
    public Object mo45544c(Parcel parcel) {
        return C14377B.m45945a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
    }
}
