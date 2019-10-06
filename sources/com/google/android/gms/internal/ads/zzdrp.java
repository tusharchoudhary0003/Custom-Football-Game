package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import org.jacoco.agent.p025rt.internal_8ff85ea.asm.Opcodes;

public final class zzdrp {

    /* renamed from: a */
    private final ByteBuffer f28153a;

    /* renamed from: b */
    private zzdni f28154b;

    /* renamed from: c */
    private int f28155c;

    private zzdrp(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private zzdrp(ByteBuffer byteBuffer) {
        this.f28153a = byteBuffer;
        this.f28153a.order(ByteOrder.LITTLE_ENDIAN);
    }

    /* renamed from: a */
    public static zzdrp m29993a(byte[] bArr) {
        return m29994a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static zzdrp m29994a(byte[] bArr, int i, int i2) {
        return new zzdrp(bArr, 0, i2);
    }

    /* renamed from: c */
    public final void mo31669c(int i, int i2) throws IOException {
        mo31668b(i, 0);
        if (i2 >= 0) {
            m30003e(i2);
        } else {
            mo31667a((long) i2);
        }
    }

    /* renamed from: a */
    public final void mo31665a(int i, boolean z) throws IOException {
        mo31668b(3, 0);
        byte b = z ? (byte) 1 : 0;
        if (this.f28153a.hasRemaining()) {
            this.f28153a.put(b);
            return;
        }
        throw new zzdrq(this.f28153a.position(), this.f28153a.limit());
    }

    /* renamed from: a */
    public final void mo31664a(int i, String str) throws IOException {
        mo31668b(i, 2);
        try {
            int c = m30001c(str.length());
            if (c == m30001c(str.length() * 3)) {
                int position = this.f28153a.position();
                if (this.f28153a.remaining() >= c) {
                    this.f28153a.position(position + c);
                    m29995a((CharSequence) str, this.f28153a);
                    int position2 = this.f28153a.position();
                    this.f28153a.position(position);
                    m30003e((position2 - position) - c);
                    this.f28153a.position(position2);
                    return;
                }
                throw new zzdrq(position + c, this.f28153a.limit());
            }
            m30003e(m29991a((CharSequence) str));
            m29995a((CharSequence) str, this.f28153a);
        } catch (BufferOverflowException e) {
            zzdrq zzdrq = new zzdrq(this.f28153a.position(), this.f28153a.limit());
            zzdrq.initCause(e);
            throw zzdrq;
        }
    }

    /* renamed from: a */
    public final void mo31663a(int i, zzdrw zzdrw) throws IOException {
        mo31668b(i, 2);
        if (zzdrw.f28166a < 0) {
            zzdrw.mo31681a();
        }
        m30003e(zzdrw.f28166a);
        zzdrw.mo31670a(this);
    }

    /* renamed from: a */
    public final void mo31662a(int i, zzdpk zzdpk) throws IOException {
        if (this.f28154b == null) {
            this.f28154b = zzdni.m29703a(this.f28153a);
            this.f28155c = this.f28153a.position();
        } else if (this.f28155c != this.f28153a.position()) {
            this.f28154b.mo31568b(this.f28153a.array(), this.f28155c, this.f28153a.position() - this.f28155c);
            this.f28155c = this.f28153a.position();
        }
        zzdni zzdni = this.f28154b;
        zzdni.mo31555a(i, zzdpk);
        zzdni.mo31545a();
        this.f28155c = this.f28153a.position();
    }

    /* renamed from: a */
    public final void mo31666a(int i, byte[] bArr) throws IOException {
        mo31668b(i, 2);
        m30003e(bArr.length);
        int length = bArr.length;
        if (this.f28153a.remaining() >= length) {
            this.f28153a.put(bArr, 0, length);
            return;
        }
        throw new zzdrq(this.f28153a.position(), this.f28153a.limit());
    }

    /* renamed from: a */
    private static int m29991a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i2);
                                throw new IllegalArgumentException(sb.toString());
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        long j = ((long) i3) + 4294967296L;
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(j);
        throw new IllegalArgumentException(sb2.toString());
    }

    /* renamed from: a */
    private static void m29995a(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        if (!byteBuffer.isReadOnly()) {
            String str = "Unpaired surrogate at index ";
            int i2 = 0;
            if (byteBuffer.hasArray()) {
                try {
                    byte[] array = byteBuffer.array();
                    int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                    int remaining = byteBuffer.remaining();
                    int length = charSequence.length();
                    int i3 = remaining + arrayOffset;
                    while (i2 < length) {
                        int i4 = i2 + arrayOffset;
                        if (i4 >= i3) {
                            break;
                        }
                        char charAt = charSequence.charAt(i2);
                        if (charAt >= 128) {
                            break;
                        }
                        array[i4] = (byte) charAt;
                        i2++;
                    }
                    if (i2 == length) {
                        i = arrayOffset + length;
                    } else {
                        i = arrayOffset + i2;
                        while (i2 < length) {
                            char charAt2 = charSequence.charAt(i2);
                            if (charAt2 < 128 && i < i3) {
                                int i5 = i + 1;
                                array[i] = (byte) charAt2;
                                i = i5;
                            } else if (charAt2 < 2048 && i <= i3 - 2) {
                                int i6 = i + 1;
                                array[i] = (byte) ((charAt2 >>> 6) | 960);
                                i = i6 + 1;
                                array[i6] = (byte) ((charAt2 & '?') | 128);
                            } else if ((charAt2 < 55296 || 57343 < charAt2) && i <= i3 - 3) {
                                int i7 = i + 1;
                                array[i] = (byte) ((charAt2 >>> 12) | 480);
                                int i8 = i7 + 1;
                                array[i7] = (byte) (((charAt2 >>> 6) & 63) | 128);
                                int i9 = i8 + 1;
                                array[i8] = (byte) ((charAt2 & '?') | 128);
                                i = i9;
                            } else if (i <= i3 - 4) {
                                int i10 = i2 + 1;
                                if (i10 != charSequence.length()) {
                                    char charAt3 = charSequence.charAt(i10);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        int i11 = i + 1;
                                        array[i] = (byte) ((codePoint >>> 18) | 240);
                                        int i12 = i11 + 1;
                                        array[i11] = (byte) (((codePoint >>> 12) & 63) | 128);
                                        int i13 = i12 + 1;
                                        array[i12] = (byte) (((codePoint >>> 6) & 63) | 128);
                                        i = i13 + 1;
                                        array[i13] = (byte) ((codePoint & 63) | 128);
                                        i2 = i10;
                                    } else {
                                        i2 = i10;
                                    }
                                }
                                int i14 = i2 - 1;
                                StringBuilder sb = new StringBuilder(39);
                                sb.append(str);
                                sb.append(i14);
                                throw new IllegalArgumentException(sb.toString());
                            } else {
                                StringBuilder sb2 = new StringBuilder(37);
                                sb2.append("Failed writing ");
                                sb2.append(charAt2);
                                sb2.append(" at index ");
                                sb2.append(i);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            }
                            i2++;
                        }
                    }
                    byteBuffer.position(i - byteBuffer.arrayOffset());
                } catch (ArrayIndexOutOfBoundsException e) {
                    BufferOverflowException bufferOverflowException = new BufferOverflowException();
                    bufferOverflowException.initCause(e);
                    throw bufferOverflowException;
                }
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt4 = charSequence.charAt(i2);
                    if (charAt4 < 128) {
                        byteBuffer.put((byte) charAt4);
                    } else if (charAt4 < 2048) {
                        byteBuffer.put((byte) ((charAt4 >>> 6) | 960));
                        byteBuffer.put((byte) ((charAt4 & '?') | 128));
                    } else if (charAt4 < 55296 || 57343 < charAt4) {
                        byteBuffer.put((byte) ((charAt4 >>> 12) | 480));
                        byteBuffer.put((byte) (((charAt4 >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((charAt4 & '?') | 128));
                    } else {
                        int i15 = i2 + 1;
                        if (i15 != charSequence.length()) {
                            char charAt5 = charSequence.charAt(i15);
                            if (Character.isSurrogatePair(charAt4, charAt5)) {
                                int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                                byteBuffer.put((byte) ((codePoint2 >>> 18) | 240));
                                byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                                byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                                byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                                i2 = i15;
                            } else {
                                i2 = i15;
                            }
                        }
                        int i16 = i2 - 1;
                        StringBuilder sb3 = new StringBuilder(39);
                        sb3.append(str);
                        sb3.append(i16);
                        throw new IllegalArgumentException(sb3.toString());
                    }
                    i2++;
                }
            }
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    /* renamed from: a */
    public static int m29990a(int i, int i2) {
        return m29989a(i) + m29996b(i2);
    }

    /* renamed from: b */
    public static int m29998b(int i, String str) {
        return m29989a(i) + m29992a(str);
    }

    /* renamed from: b */
    public static int m29997b(int i, zzdrw zzdrw) {
        int a = m29989a(i);
        int a2 = zzdrw.mo31681a();
        return a + m30001c(a2) + a2;
    }

    /* renamed from: b */
    public static int m29999b(int i, byte[] bArr) {
        return m29989a(i) + m30001c(bArr.length) + bArr.length;
    }

    /* renamed from: b */
    public static int m29996b(int i) {
        if (i >= 0) {
            return m30001c(i);
        }
        return 10;
    }

    /* renamed from: a */
    public static int m29992a(String str) {
        int a = m29991a((CharSequence) str);
        return m30001c(a) + a;
    }

    /* renamed from: a */
    public final void mo31661a() {
        if (this.f28153a.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.f28153a.remaining())}));
        }
    }

    /* renamed from: d */
    private final void m30002d(int i) throws IOException {
        byte b = (byte) i;
        if (this.f28153a.hasRemaining()) {
            this.f28153a.put(b);
            return;
        }
        throw new zzdrq(this.f28153a.position(), this.f28153a.limit());
    }

    /* renamed from: b */
    public final void mo31668b(int i, int i2) throws IOException {
        m30003e((i << 3) | i2);
    }

    /* renamed from: a */
    public static int m29989a(int i) {
        return m30001c(i << 3);
    }

    /* renamed from: e */
    private final void m30003e(int i) throws IOException {
        while ((i & -128) != 0) {
            m30002d((i & Opcodes.LAND) | 128);
            i >>>= 7;
        }
        m30002d(i);
    }

    /* renamed from: c */
    private static int m30001c(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((i & -268435456) == 0) {
            return 4;
        }
        return 5;
    }

    /* renamed from: a */
    public final void mo31667a(long j) throws IOException {
        while ((-128 & j) != 0) {
            m30002d((((int) j) & Opcodes.LAND) | 128);
            j >>>= 7;
        }
        m30002d((int) j);
    }

    /* renamed from: b */
    public static int m30000b(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((j & Long.MIN_VALUE) == 0) {
            return 9;
        }
        return 10;
    }
}
