package PacketShark;

import java.util.BitSet;

public class BytesUtils {
    public static int bytesToInt(byte[] bytes) {
        int ret = 0;
        for (int i=0; i<4 && i<bytes.length; i++) {
            ret <<= 8;
            ret |= (int)bytes[i] & 0xFF;
        }
        return (ret&0xff)<<24 | (ret&0xff00)<<8 | (ret&0xff0000)>>8 | (ret>>24)&0xff;
    }
    public static int bytesToIntBig(byte[] bytes) {
        int ret = 0;
        for (int i=0; i<4 && i<bytes.length; i++) {
            ret <<= 8;
            ret |= (int)bytes[i] & 0xFF;
        }
        return ret;
     }

    public static Long bytesToLongBig(byte[] bytes) {
        Long ret = 0L;
        for (int i=0; i<4 && i<bytes.length; i++) {
            ret <<= 8;
            ret |= (long)bytes[i] & 0xFF;
        }
        return ret;
    }

    public static int bytesToIntBigFast(byte[] bytes, int offset, int size) {
        int ret = 0;
        for (int i=offset; i < 4 && i + size<bytes.length; i++) {
            ret <<= 8;
            ret |= (int)bytes[i] & 0xFF;
        }
        return ret;
    }

    public static void printBytes(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString());
    }
    public static String bytesToMacStr(byte[] bytes, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i=offset; i < offset+6; i++) {
            if (i+1 < offset+6)
                sb.append(String.format("%02X:", bytes[i]));
            else
                sb.append(String.format("%02X", bytes[i]));
        }
        return sb.toString();
    }
    public static String bytesToIpStr(byte[] bytes, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset + 4; i++) {
            if (i + 1 < offset + 4)
                sb.append(String.format("%d.", bytes[i] & 0xFF));
            else
                sb.append(String.format("%d", bytes[i] &  0xFF));
        }
        return sb.toString();
    }

    public static int bitsToInt(BitSet bits) {
        int value = 0;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1 << i) : 0;
        }
        return value;
    }

}
