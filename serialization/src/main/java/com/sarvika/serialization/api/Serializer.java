package com.sarvika.serialization.api;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import com.sarvika.serialization.encryption.BASE64Encoder;

/**
 * @author J.Godara
 * 
 *         For serialization and deserialization. This interface may have many
 *         implementations such as {@link BASE64Encoder}. Also, this could be
 *         implemented to make a custom serializer.
 * 
 *         When using this to serialize and deserialize Objects across
 *         applications, make sure that the Object which is to be serialized has
 *         the same serialVersionUID at both the places.
 */
public interface Serializer {

	/**
	 * Serializes an Object into a String. This string could be stored in file
	 * or transmitted over network as a String or as an array of char. This
	 * String or char[] could be desrialized to retain the true state of the
	 * serialized Object.
	 * 
	 * <b>Transient members of the Object are never serialized and would be
	 * returned as empty or null in the deserilazed object.</b>
	 * 
	 * @param object
	 *            The Object to serialize.
	 * @return The String representing the Serialized Object.
	 * @throws IOException
	 */
	public String serialize(Serializable object) throws IOException;

	/**
	 * Deserializes a String representing a serialized Object. When deserialing,
	 * the true state of object is returned in the newly created object.
	 * 
	 * <b>This will throw a {@link StreamCorruptedException} if an invalid
	 * String is supplied</b>
	 * 
	 * @param serial
	 *            The serialized String.
	 * @return The deserialized Object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object deSerialize(String serial) throws IOException,
			ClassNotFoundException;

	/**
	 * Deserializes an array of characters representing a serialized Object.
	 * When deserialing, the true state of object is returned in the newly
	 * created object.
	 * 
	 * <b>This will throw a {@link StreamCorruptedException} if an invalid
	 * char[] is supplied</b>
	 * 
	 * @param serial
	 *            The serialized char[].
	 * @return The deserialized Object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object deSerialize(char[] serial) throws IOException,
			ClassNotFoundException;

}
