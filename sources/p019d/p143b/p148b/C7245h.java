package p019d.p143b.p148b;

/* renamed from: d.b.b.h */
public enum C7245h {
    ADAPTER_CONFIGURATION_ERROR(-5100),
    ADAPTER_NOT_FOUND(-5101),
    NETWORK_UNSPECIFIED(-5200),
    NETWORK_INVALID_STATE(-5201),
    NETWORK_INVALID_CONFIGURATION(-5202),
    NETWORK_BAD_REQUEST(-5203),
    NETWORK_TIMEOUT(-5002),
    NETWORK_NO_CONNECTION(-5003),
    NETWORK_NO_FILL(-5204),
    NETWORK_AD_NOT_READY(-5205),
    NETWORK_SERVER_ERROR(-5206),
    NETWORK_INTERNAL_ERROR(-5206),
    NETWORK_DISPLAY_UNSPECIFIED(-5300),
    NETWORK_DISPLAY_INVALID_STATE(-5301),
    INTERNAL_ERROR(-5500),
    INTERNAL_AD_TYPE_NOT_SUPPORTED(-5501),
    INTERNAL_AD_SIZE_NOT_SUPPORTED(-5502);
    

    /* renamed from: s */
    private final int f14093s;

    private C7245h(int i) {
        this.f14093s = i;
    }

    /* renamed from: e */
    public int mo23471e() {
        return this.f14093s;
    }
}
