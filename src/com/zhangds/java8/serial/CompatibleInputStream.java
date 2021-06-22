package com.zhangds.java8.serial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

/**
 * 处理序列化不一致，继承 ObjectInputStream，重写 readClassDescriptor 方法
 * 当遇到目标数据 Class 版本号和本地 Class 版本号不一致时，默认使用本地版本的 Class
 * @author: zhangds
 * @date: 2021/6/18 11:17
 */
public class CompatibleInputStream extends ObjectInputStream {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("tmp");
        ObjectInputStream s = new CompatibleInputStream(in);
        String today = (String)s.readObject();
        Date date = (Date)s.readObject();
    }

    Logger logger = LoggerFactory.getLogger(CompatibleInputStream.class);

    public CompatibleInputStream(InputStream in) throws IOException {
        super(in);
    }

    protected CompatibleInputStream() throws IOException, SecurityException {
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass resultClassDescriptor = super.readClassDescriptor(); // initially streams descriptor
        Class localClass; // the class in the local JVM that this descriptor represents.
        try {
            localClass = Class.forName(resultClassDescriptor.getName());
        } catch (ClassNotFoundException e) {
            logger.error("No local class for " + resultClassDescriptor.getName(), e);
            return resultClassDescriptor;
        }
        ObjectStreamClass localClassDescriptor = ObjectStreamClass.lookup(localClass);
        if (localClassDescriptor != null) { // only if class implements serializable
            final long localSUID = localClassDescriptor.getSerialVersionUID();
            final long streamSUID = resultClassDescriptor.getSerialVersionUID();
            if (streamSUID != localSUID) { // check for serialVersionUID mismatch.
                final StringBuffer s = new StringBuffer("Overriding serialized class version mismatch: ");
                s.append("local serialVersionUID = ").append(localSUID);
                s.append(" stream serialVersionUID = ").append(streamSUID);
                Exception e = new InvalidClassException(s.toString());
                logger.error("Potentially Fatal Deserialization Operation.", e);
                resultClassDescriptor = localClassDescriptor; // Use local class descriptor for deserialization
            }
        }
        return resultClassDescriptor;
    }
}
