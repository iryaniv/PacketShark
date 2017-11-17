package PacketShark;

public class LibpcapPacket extends Packet {

    public int timeStampSeconds;
    public int timeStampMicroSeconds;
    public int frameLength;
    public int frameActualLength;
    public byte[] data;
    public LibpcapPacket nextLevel;

    public LibpcapPacket(int timeStampSeconds, int timeStampMicroSeconds, int frameLength, int frameActualLength, byte[] data) {
        this.timeStampSeconds = timeStampSeconds;
        this.timeStampMicroSeconds = timeStampMicroSeconds;
        this.frameLength = frameLength;
        this.frameActualLength = frameActualLength;
        this.data = data;
    }
}
