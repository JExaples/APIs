package com.sarvika.serialization.api;

import org.apache.log4j.Logger;

import com.sarvika.serialization.api.impl.Base64Serializer;
import com.sarvika.serialization.api.impl.BasicJasyptSerializer;

/**
 * @author J.Godara
 *
 *         This class creates and holds a single instance of each of the basic
 *         {@link Serializer} instances. These instances could be accessed
 *         anywhere during the runtime by calling any of the factory methods.
 */
public class SerializerFactory {
	
	private static final Logger logger = Logger.getLogger(SerializerFactory.class);

	/**
	 * A {@link Base64Serializer}.
	 */
	private static Base64Serializer base64Serializer = null;

	/**
	 * A {@link BasicJasyptSerializer}.
	 */
	private static BasicJasyptSerializer jasyptSerializer = null;

	/**
	 * The passkey to be used by the JASYPT Serializes.
	 */
	private static String jasyptPasskey = null;

	/**
	 * Returns a factory instance of {@link Base64Serializer}. If an instance
	 * has not been created yet, creates it.
	 * 
	 * @return The {@link Base64Serializer} instance.
	 */
	public static Base64Serializer getBase64Serializer() {

		if (base64Serializer == null) {
			base64Serializer = new Base64Serializer();
		}

		return base64Serializer;
	}

	/**
	 * Returns a factory instance of {@link BasicJasyptSerializer}. If an
	 * instance has not been created yet, creates it.
	 * 
	 * @return The {@link BasicJasyptSerializer} instance.
	 */
	public static BasicJasyptSerializer getBasicJasyptSerializer() {

		if (jasyptSerializer == null) {

			// If the JASYPT Key is null, then create an instance with the
			// default key.
			if (jasyptPasskey == null) {
				jasyptSerializer = new BasicJasyptSerializer();
			} else {
				jasyptSerializer = new BasicJasyptSerializer(jasyptPasskey);
			}
		}

		return jasyptSerializer;
	}

	/**
	 * Sets the passkey to be used by JASYPT Serializers
	 * 
	 * @param passkey
	 *            The passkey to set.
	 */
	public static void setJasyptPasskey(String passkey) {
		if (jasyptSerializer != null) {
			logger.warn("Method call is not effective, a BasicJasyptSerializer oject is already bound in the factory.");
			return;
		}
		jasyptPasskey = passkey;
	}

}
