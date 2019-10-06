package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import org.jacoco.agent.p025rt.internal_8ff85ea.asm.Opcodes;

/* renamed from: com.google.android.gms.internal.ads.Xp */
abstract class C9241Xp {
    C9241Xp() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract int mo28699a(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract int mo28700a(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract void mo28701a(CharSequence charSequence, ByteBuffer byteBuffer);

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public abstract String mo28703b(byte[] bArr, int i, int i2) throws zzdok;

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo28702a(byte[] bArr, int i, int i2) {
        return mo28699a(0, bArr, i, i2) == 0;
    }

    /* renamed from: b */
    static void m23223b(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i2 = 0;
        while (i2 < length) {
            try {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i2, (byte) charAt);
                i2++;
            } catch (IndexOutOfBoundsException e) {
                int position2 = byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1);
                char charAt2 = charSequence.charAt(i2);
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(charAt2);
                sb.append(" at index ");
                sb.append(position2);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
        }
        if (i2 == length) {
            byteBuffer.position(position + i2);
            return;
        }
        position += i2;
        while (i2 < length) {
            char charAt3 = charSequence.charAt(i2);
            if (charAt3 < 128) {
                byteBuffer.put(position, (byte) charAt3);
            } else if (charAt3 < 2048) {
                i = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt3 >>> 6) | Opcodes.CHECKCAST));
                    byteBuffer.put(i, (byte) ((charAt3 & '?') | 128));
                    position = i;
                } catch (IndexOutOfBoundsException e2) {
                    position = i;
                    int position22 = byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1);
                    char charAt22 = charSequence.charAt(i2);
                    StringBuilder sb2 = new StringBuilder(37);
                    sb2.append("Failed writing ");
                    sb2.append(charAt22);
                    sb2.append(" at index ");
                    sb2.append(position22);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else if (charAt3 < 55296 || 57343 < charAt3) {
                i = position + 1;
                byteBuffer.put(position, (byte) ((charAt3 >>> 12) | 224));
                position = i + 1;
                byteBuffer.put(i, (byte) (((charAt3 >>> 6) & 63) | 128));
                byteBuffer.put(position, (byte) ((charAt3 & '?') | 128));
            } else {
                int i3 = i2 + 1;
                if (i3 != length) {
                    try {
                        char charAt4 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            int i4 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((codePoint >>> 18) | 240));
                                position = i4 + 1;
                                byteBuffer.put(i4, (byte) (((codePoint >>> 12) & 63) | 128));
                                i4 = position + 1;
                                byteBuffer.put(position, (byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put(i4, (byte) ((codePoint & 63) | 128));
                                position = i4;
                                i2 = i3;
                            } catch (IndexOutOfBoundsException e3) {
                                position = i4;
                                i2 = i3;
                                int position222 = byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1);
                                char charAt222 = charSequence.charAt(i2);
                                StringBuilder sb22 = new StringBuilder(37);
                                sb22.append("Failed writing ");
                                sb22.append(charAt222);
                                sb22.append(" at index ");
                                sb22.append(position222);
                                throw new ArrayIndexOutOfBoundsException(sb22.toString());
                            }
                        } else {
                            i2 = i3;
                        }
                    } catch (IndexOutOfBoundsException e4) {
                        i2 = i3;
                        int position2222 = byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1);
                        char charAt2222 = charSequence.charAt(i2);
                        StringBuilder sb222 = new StringBuilder(37);
                        sb222.append("Failed writing ");
                        sb222.append(charAt2222);
                        sb222.append(" at index ");
                        sb222.append(position2222);
                        throw new ArrayIndexOutOfBoundsException(sb222.toString());
                    }
                }
                throw new C9283Zp(i2, length);
            }
            i2++;
            position++;
        }
        byteBuffer.position(position);
    }
}