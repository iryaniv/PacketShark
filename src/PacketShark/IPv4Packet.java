package PacketShark;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class IPv4Packet extends EthernetPacket {
    // TODO: Need to fix fragmentOffset
    // TODO: Add IpOptions headers.

    private final static int IPV4_ETHR_TYPE = 2048;
    public int version;
    public int ihl;
    public int dscp;
    public int ecn;
    public int identification;
    public int ipLength;
    public List<String> flags;
    public int fragmentOffset;
    public int ttl;
    public int protocol;
    public byte[] checksum;
    public String sourceIp;
    public String destinationIp;

    public IPv4Packet(LibpcapPacket packet) {
        super(packet);
        BitSet firstByteBitSet = BitSet.valueOf(Arrays.copyOfRange(this.data, 0,1));
        BitSet secondByteBitSet = BitSet.valueOf(Arrays.copyOfRange(this.data, 1,2));
        BitSet flagsByteBitSet = BitSet.valueOf(Arrays.copyOfRange(this.data, 6,7));
        BitSet offsetByteBitSet = BitSet.valueOf(Arrays.copyOfRange(this.data, 6,8));
        this.version = BytesUtils.bitsToInt(firstByteBitSet.get(4,8));
        this.ihl = BytesUtils.bitsToInt(firstByteBitSet.get(0,4));
        this.ecn = BytesUtils.bitsToInt(secondByteBitSet.get(0,2));
        this.dscp = BytesUtils.bitsToInt(secondByteBitSet.get(2,8));
        this.ipLength = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 2, 4));
        getFlags(flagsByteBitSet);
        this.identification = BytesUtils.bytesToIntBig(Arrays.copyOfRange(this.data, 4, 6));
        this.fragmentOffset = BytesUtils.bitsToInt(offsetByteBitSet.get(0,13));
        this.ttl = (int)this.data[8] & 0xff;
        this.protocol = (int)this.data[9];
        this.checksum = Arrays.copyOfRange(this.data, 10, 12);
        this.sourceIp = BytesUtils.bytesToIpStr(this.data,12);
        this.destinationIp = BytesUtils.bytesToIpStr(this.data,16);
        if (this.ihl > 5)
            this.data = Arrays.copyOfRange(this.data, 32, data.length);
        else
            this.data = Arrays.copyOfRange(this.data, 20, data.length);

    }

    private void getFlags(BitSet flagsBitSet){
        this.flags = new ArrayList<>();
        if (flagsBitSet.get(6)){
            this.flags.add("DF");
        }
        if(flagsBitSet.get(7)) {
            this.flags.add("MF");
        }

    }
    @Override
    public String toString() {
        return String.format("%s\nIP.Version: %d IP.IHL: %d IP.DSCP: %d IP.ECN: %d IP.Length: %d  IP.ID: %d IP.Flags: %s IP.FragmentOffset: %d IP.TTL: %d IP.Protcol: %d IP.Source: %s IP.Destination: %s",
                super.toString(), this.version, this.ihl, this.dscp, this.ecn, this.ipLength, this.identification,
                this.flags.toString(), this.fragmentOffset, this.ttl, this.protocol, this.sourceIp, this.destinationIp);
    }
}
