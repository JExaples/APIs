package com.sarvika.serialization.api.impl;

import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.sarvika.serialization.api.BaseSerializer;
import com.sarvika.serialization.api.Serializer;
import com.sarvika.serialization.encryption.BASE64Encoder;

/**
 * @author J.Godara
 * 
 *         Class which serializes/deserializes an object based on Base64
 *         Encryption Scheme
 */
public class Base64Serializer extends BaseSerializer implements Serializer {

	/**
	 * Takes a Serializable Object and converts it into a String encoded on
	 * Base64 Format.
	 * 
	 * @param object
	 *            The Object to serialize
	 */
	public String serialize(Serializable object) throws IOException {
		
		logger.info("Using "+Base64Serializer.class+" as serializer.");
		
		byte[] data = serializeToByteArray(object);
		
		return new String(BASE64Encoder.encode(data));
	}

	/**
	 * Deserializes a Base64 encoded serialized String and returns the Object's
	 * original state. This Object can be class-casted to the type which was
	 * serialized.
	 * 
	 * @param serial
	 *            The serialized String
	 */
	public Object deSerialize(String serial) throws IOException,
			ClassNotFoundException {
		
		logger.info("Using "+Base64Serializer.class+" as deserializer.");
		
		byte[] data = BASE64Encoder.decode(serial);

		return deSerializeBytes(data);
	}

	/**
	 * Deserializes a Base64 encoded serialized Character Array and returns the
	 * Object's original state. This Object can be class-casted to the type
	 * which was serialized.
	 * 
	 * @param serial
	 *            The serialized Character Array
	 */
	public Object deSerialize(char[] serial) throws ClassNotFoundException,
			IOException {
		
		logger.info("Using "+Base64Serializer.class+" as deserializer.");
		
		byte[] data = BASE64Encoder.decode(serial);

		return deSerializeBytes(data);
	}

}
