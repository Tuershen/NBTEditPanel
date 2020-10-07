package pers.tuershen.nbtedit.compoundlibrary.nms.io;


import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;

import java.io.*;
import java.util.Base64;

public class SerializableStream {

    private static final Base64.Encoder encoder = Base64.getEncoder() ;

    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static ByteArrayOutputStream byteArrayOutputStream;

    protected static String encode(byte[] bytes) { return encoder.encodeToString(bytes); }

    protected static byte[] decode(String encodedText) {
        return decoder.decode(encodedText);
    }

    /**
     * 序列化
     * @param serializableFormat
     * @return
     */
    public static String getByteStream(pers.tuershen.nbtedit.compoundlibrary.nms.io.SerializableFormat serializableFormat) {
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objStream = new ObjectOutputStream(byteArrayOutputStream);
            objStream.writeObject(serializableFormat);
            byte[] data = byteArrayOutputStream.toByteArray();
            return encode(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     * @param data
     * @return
     */
    public static pers.tuershen.nbtedit.compoundlibrary.nms.io.SerializableFormat deserializeObj(String data) {
        ByteArrayInputStream byteStream = decodeByteInputStream(data);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(byteStream);
            pers.tuershen.nbtedit.compoundlibrary.nms.io.SerializableFormat obj = (SerializableFormat)ois.readObject();
            return obj;
        }
        catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends TagBase> String getTagBaseByteStream(T t){
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objStream = new ObjectOutputStream(byteArrayOutputStream);
            objStream.writeObject(t);
            byte[] data = byteArrayOutputStream.toByteArray();
            return encode(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends TagBase> T deserializeTagBase(String data){
        ByteArrayInputStream byteStream = decodeByteInputStream(data);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(byteStream);
            T obj = (T)ois.readObject();
            return obj;
        }
        catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ByteArrayInputStream decodeByteInputStream(String data){
        return new ByteArrayInputStream(decode(data));
    }







}
