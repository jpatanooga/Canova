package org.canova.api.records;

import java.io.IOException;

/**
 * Created by agibsonccc on 11/16/14.
 */
public abstract class BaseRecordInput implements RecordInput {
    @Override
    public byte readByte(String tag) throws IOException {
        return 0;
    }

    @Override
    public boolean readBool(String tag) throws IOException {
        return false;
    }

    @Override
    public int readInt(String tag) throws IOException {
        return 0;
    }

    @Override
    public long readLong(String tag) throws IOException {
        return 0;
    }

    @Override
    public float readFloat(String tag) throws IOException {
        return 0;
    }

    @Override
    public double readDouble(String tag) throws IOException {
        return 0;
    }

    @Override
    public String readString(String tag) throws IOException {
        return null;
    }

    @Override
    public Buffer readBuffer(String tag) throws IOException {
        return null;
    }

    @Override
    public void startRecord(String tag) throws IOException {

    }

    @Override
    public void endRecord(String tag) throws IOException {

    }

    @Override
    public Index startVector(String tag) throws IOException {
        return null;
    }

    @Override
    public void endVector(String tag) throws IOException {

    }

    @Override
    public Index startMap(String tag) throws IOException {
        return null;
    }

    @Override
    public void endMap(String tag) throws IOException {

    }
}
