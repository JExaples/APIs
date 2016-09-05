package com.sarvika.serialization.api.impl;

import java.io.IOException;
import java.io.Serializable;

import org.jasypt.util.text.BasicTextEncryptor;

import com.sarvika.serialization.api.BaseSerializer;
import com.sarvika.serialization.api.Serializer;
import com.sarvika.serialization.api.SerializerFactory;

/**
 * @author J.Godara
 *
 *         Class which serializes and deserializes Objects based on a passkey.
 *         There is no setter method for the passkey, so it could only be
 *         changed while constructing a new instance of
 *         {@link BasicJasyptSerializer}, or by invoking setJasyptKey(...) and
 *         then getting an instance of this class by invoking
 *         getBasicJaysptSerializer(...) static methods on
 *         {@link SerializerFactory}.
 * 
 *         <b>If custom passkey is to be used for this class, then
 *         setJasyptKey(...) MUST be called before getBasicJasyptSerializer(...)
 *         is invoked from anywhere.</b>
 */
public class BasicJasyptSerializer extends BaseSerializer implements Serializer {

	/**
	 * The encryptor/decryptor Object.
	 */
	private BasicTextEncryptor encryptor;

	/**
	 * Constructs a {@link BasicJasyptSerializer} with the supplied passkey.
	 * 
	 * @param passkey
	 *            The passkey which is used in creation of an instance of this
	 *            class.
	 */
	public BasicJasyptSerializer(String passkey) {
		
		logger.info("Constructing "+BasicJasyptSerializer.class+" with passkey: "+passkey+";");

		encryptor = new BasicTextEncryptor();
		encryptor.setPassword(passkey);

	}

	/**
	 * Constructs a {@link BasicJasyptSerializer} object with the default
	 * passkey.
	 */
	public BasicJasyptSerializer() {
		
		logger.info("Constructing "+BasicJasyptSerializer.class+" with passkey: default-passkey;");

		// Default Passkey
		String passkey = "NR#BQqq-P:QsrF>R\"C";

		encryptor = new BasicTextEncryptor();
		encryptor.setPassword(passkey);

	}

	/**
	 * Takes a Serializable Object and converts it into a String using the
	 * JASYPT method with the supplied passkey.
	 * 
	 * @param object
	 *            The Object to serialize
	 */
	public String serialize(Serializable object) throws IOException {
		
		logger.info("Using "+BasicJasyptSerializer.class+" as serializer.");

		byte[] data = serializeToByteArray(object);
		String serialString = new String(data);

		return encryptor.encrypt(serialString);
	}

	/**
	 * Deserializes a String serialized by this class and returns the Object's
	 * original state. This Object can be class-casted to the type which was
	 * serialized.
	 * 
	 * @param serial
	 *            The serialized String
	 */
	public Object deSerialize(String serial) throws IOException,
			ClassNotFoundException {
		
		logger.info("Using "+BasicJasyptSerializer.class+" as deserializer.");

		byte[] data = encryptor.decrypt(serial).getBytes();

		return deSerializeBytes(data);
	}

	/**
	 * Deserializes a Character Array serialized by this class and returns the
	 * Object's original state. This Object can be class-casted to the type
	 * which was serialized.
	 * 
	 * @param serial
	 *            The serialized Character Array
	 */
	public Object deSerialize(char[] serial) throws IOException,
			ClassNotFoundException {
		
		logger.info("Using "+BasicJasyptSerializer.class+" as deserializer.");

		byte[] data = new String(serial).getBytes();

		return deSerializeBytes(data);
	}

}
