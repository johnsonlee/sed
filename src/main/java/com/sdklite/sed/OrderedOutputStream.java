package com.sdklite.sed;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;

/**
 * Represents an ordered output stream
 * 
 * @author johnsonlee
 *
 */
public class OrderedOutputStream extends FilterOutputStream {

    private final ByteOrder byteOrder;

    /**
     * Instantialized with the specified output stream and little-endian byte
     * order
     * 
     * @param out
     *            The output stream
     */
    public OrderedOutputStream(final OutputStream out) {
        this(out, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * Instantialized with the specified output stream and byte order
     * 
     * @param out
     *            The output stream
     * @param byteOrder
     *            The byte order
     */
    public OrderedOutputStream(final OutputStream out, final ByteOrder byteOrder) {
        super(out);
        this.byteOrder = byteOrder;
    }

    /**
     * Writes the specified byte
     * 
     * @param v
     *            The byte value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeByte(final byte v) throws IOException {
        this.write(v);
    }

    /**
     * Writes the specified unsigned byte
     * 
     * @param v
     *            The unsigned byte value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeUnsignedByte(final int v) throws IOException {
        this.write(v);
    }

    /**
     * Writes the specified char
     * 
     * @param v
     *            The char value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeChar(final char v) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            writeCharB(v);
        } else {
            writeCharL(v);
        }
    }

    /**
     * Writes the specified short
     * 
     * @param v
     *            The short value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeShort(final short v) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            writeShortB(v);
        } else {
            writeShortL(v);
        }
    }

    /**
     * Writes the specified unsigned short
     * 
     * @param v
     *            The unsigned short value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeUnsignedShort(final int v) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            writeUnsignedShortB(v);
        } else {
            writeUnsignedShortL(v);
        }
    }

    /**
     * Writes the specified int
     * 
     * @param v
     *            The int value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeInt(final int v) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            writeIntB(v);
        } else {
            writeIntL(v);
        }
    }

    /**
     * Writes the specified float
     * 
     * @param v
     *            The float value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeFloat(final float v) throws IOException {
        writeInt(Float.floatToIntBits(v));
    }

    /**
     * Writes the specified long
     * 
     * @param v
     *            The long value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeLong(final long v) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            writeLongB(v);
        } else {
            writeLongL(v);
        }
    }

    /**
     * Writes the specified double
     * 
     * @param v
     *            The double value to be written
     * @throws IOException
     *             If I/O error occurred
     */
    public void writeDouble(final double v) throws IOException {
        writeLong(Double.doubleToLongBits(v));
    }

    private void writeCharB(final char v) throws IOException {
        write((v >> 8) & 0xff);
        write((v >> 0) & 0xff);
    }

    private void writeCharL(final char v) throws IOException {
        write((v >> 0) & 0xff);
        write((v >> 8) & 0xff);
    }

    private void writeShortB(final short v) throws IOException {
        write((v >> 8) & 0xff);
        write((v >> 0) & 0xff);
    }

    private void writeShortL(final short v) throws IOException {
        write((v >> 0) & 0xff);
        write((v >> 8) & 0xff);
    }

    private void writeUnsignedShortB(final int v) throws IOException {
        write((v >> 8) & 0xff);
        write((v >> 0) & 0xff);
    }

    private void writeUnsignedShortL(final int v) throws IOException {
        write((v >> 0) & 0xff);
        write((v >> 8) & 0xff);
    }

    private void writeIntB(final int v) throws IOException {
        write((v >> 24) & 0xff);
        write((v >> 16) & 0xff);
        write((v >>  8) & 0xff);
        write((v >>  0) & 0xff);
    }

    private void writeIntL(final int v) throws IOException {
        write((v >>  0) & 0xff);
        write((v >>  8) & 0xff);
        write((v >> 16) & 0xff);
        write((v >> 24) & 0xff);
    }

    private void writeLongB(final long v) throws IOException {
        write((int) (v >> 56) & 0xff);
        write((int) (v >> 48) & 0xff);
        write((int) (v >> 40) & 0xff);
        write((int) (v >> 32) & 0xff);
        write((int) (v >> 24) & 0xff);
        write((int) (v >> 16) & 0xff);
        write((int) (v >>  8) & 0xff);
        write((int) (v >>  0) & 0xff);
    }

    private void writeLongL(final long v) throws IOException {
        write((int) (v >>  0) & 0xff);
        write((int) (v >>  8) & 0xff);
        write((int) (v >> 16) & 0xff);
        write((int) (v >> 24) & 0xff);
        write((int) (v >> 32) & 0xff);
        write((int) (v >> 40) & 0xff);
        write((int) (v >> 48) & 0xff);
        write((int) (v >> 56) & 0xff);
    }
}
