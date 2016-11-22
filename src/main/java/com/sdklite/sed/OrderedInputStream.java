package com.sdklite.sed;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/**
 * Represents an ordered input stream
 * 
 * @author johnsonlee
 *
 */
public class OrderedInputStream extends FilterInputStream {

    private final ByteOrder byteOrder;

    /**
     * Instantialized with the specified input stream and little-endian byte
     * order
     * 
     * @param in
     *            The input stream
     */
    public OrderedInputStream(final InputStream in) {
        this(in, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * Instantialized with the specified input stream and byte order
     * 
     * @param in
     *            The input stream
     * @param byteOrder
     *            The byte order
     */
    public OrderedInputStream(final InputStream in, final ByteOrder byteOrder) {
        super(in);
        this.byteOrder = byteOrder;
    }

    /**
     * Reads the next byte
     * 
     * @return The next byte
     * @throws IOException
     *             If I/O error occurred
     */
    public byte readByte() throws IOException {
        return (byte) read();
    }

    /**
     * Reads the next char
     * 
     * @return The next char
     * @throws IOException
     *             If I/O error occurred
     */
    public char readChar() throws IOException {
        return this.byteOrder == ByteOrder.BIG_ENDIAN ? readCharB() : readCharL();
    }

    /**
     * Reads the next short
     * 
     * @return The next short
     * @throws IOException
     *             If I/O error occurred
     */
    public short readShort() throws IOException {
        return this.byteOrder == ByteOrder.BIG_ENDIAN ? readShortB() : readShortL();
    }

    /**
     * Reads the next unsigned short
     * 
     * @return The next unsigned short
     * @throws IOException
     *             If I/O error occurred
     */
    public int readUnsignedShort() throws IOException {
        return this.byteOrder == ByteOrder.BIG_ENDIAN ? readUnsignedShortB() : readUnsignedShortL();
    }

    /**
     * Reads the next int
     * 
     * @return The next int
     * @throws IOException
     *             If I/O error occurred
     */
    public int readInt() throws IOException {
        return this.byteOrder == ByteOrder.BIG_ENDIAN ? readIntB() : readIntL();
    }

    /**
     * Reads the next float
     * 
     * @return The next float
     * @throws IOException
     *             If I/O error occurred
     */
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    /**
     * Reads the next long
     * 
     * @return The next long
     * @throws IOException
     *             If I/O error occurred
     */
    public long readLong() throws IOException {
        return this.byteOrder == ByteOrder.BIG_ENDIAN ? readLongB() : readLongL();
    }

    /**
     * Reads the next double
     * 
     * @return The next double
     * @throws IOException
     */
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    private char readCharB() throws IOException {
        final int b0 = read();
        final int b1 = read();
        return (char) ((b0 << 8) | b1);
    }

    private char readCharL() throws IOException {
        final int b0 = read();
        final int b1 = read();
        return (char) ((b1 << 8) | b0);
    }

    private short readShortB() throws IOException {
        final int b0 = read();
        final int b1 = read();
        return (short) ((b0 << 8) | b1);
    }

    private short readShortL() throws IOException {
        final int b1 = read();
        final int b2 = read();
        return (short) ((b2 << 8) | b1);
    }

    private int readUnsignedShortB() throws IOException {
        final int b0 = read();
        final int b1 = read();
        return ((b0 << 8) | b1);
    }

    private int readUnsignedShortL() throws IOException {
        final int b0 = read();
        final int b1 = read();
        return ((b1 << 8) | b0);
    }

    private int readIntB() throws IOException {
        final int b0 = read();
        final int b1 = read();
        final int b2 = read();
        final int b3 = read();
        return ((b0 << 24) | (b1 << 16) | (b2 << 8) | (b3));
    }

    private int readIntL() throws IOException {
        final int b0 = read();
        final int b1 = read();
        final int b2 = read();
        final int b3 = read();
        return ((b3 << 24) | (b2 << 16) | (b1 << 8) | (b0));
    }

    private long readLongB() throws IOException {
        final int b0 = read();
        final int b1 = read();
        final int b2 = read();
        final int b3 = read();
        final int b4 = read();
        final int b5 = read();
        final int b6 = read();
        final int b7 = read();

        return ((long) (b0) << 56)
             | ((long) (b1 & 0xff) << 48)
             | ((long) (b2 & 0xff) << 40)
             | ((long) (b3 & 0xff) << 32)
             | ((long) (b4 & 0xff) << 24)
             | ((long) (b5 & 0xff) << 16)
             | ((long) (b6 & 0xff) <<  8)
             | ((long) (b7 & 0xff) <<  0);
    }

    private long readLongL() throws IOException {
        final int b0 = read();
        final int b1 = read();
        final int b2 = read();
        final int b3 = read();
        final int b4 = read();
        final int b5 = read();
        final int b6 = read();
        final int b7 = read();

        return ((long) (b7) << 56)
             | ((long) (b6 & 0xff) << 48)
             | ((long) (b5 & 0xff) << 40)
             | ((long) (b4 & 0xff) << 32)
             | ((long) (b3 & 0xff) << 24)
             | ((long) (b2 & 0xff) << 16)
             | ((long) (b1 & 0xff) <<  8)
             | ((long) (b0 & 0xff) <<  0);
    }

}
