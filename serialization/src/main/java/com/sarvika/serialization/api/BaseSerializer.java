package com.sarvika.serialization.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * @author J.Godara
 *
 *         Basic implementation for serialization / deserialization. This class
 *         could be inherit and its methods could be used to easily serialize a
 *         {@link Serializable} object to a stream of bytes and vice-versa.
 */
public class BaseSerializer {

	protected final static Logger logger = Logger.getLogger(BaseSerializer.class);
	
	/**
	 * Converts a {@link Serializable} object into stream of bytes.
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
	protected byte[] serializeToByteArray(Serializable object)
			throws IOException {
		
		logger.info("Serializing "+object.getClass().getName());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

		objectOutputStream.writeObject(object);
		
		logger.info(object.getClass().getName()+" serialized.");

		return byteArrayOutputStream.toByteArray();

	}

	/**
	 * Internal method which takes a serialized object as stream of bytes and
	 * returns it true state.
	 * 
	 * @param data
	 *            The object serialized as byte[]
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected Object deSerializeBytes(byte[] data) throws IOException,
			ClassNotFoundException {
		
		logger.info("Deserializing bytes...");

		ByteArrayInputStream dataByteArrayInputStream = new ByteArrayInputStream(data);		
		ObjectInputStream objectInputStream = new ObjectInputStream(dataByteArrayInputStream);

		Object deSerialzed = objectInputStream.readObject();
		objectInputStream.close();
		
		logger.info("Bytes deserialized as: "+deSerialzed.getClass().getName());

		return deSerialzed;

	}

}
