package fr.cyu.smartread.app.util.serialization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * This class contains static methods to serialize and deserialize objects.
 *
 * @author Thomas REMY
 */
public class SerializationUtil {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Serialize the object given in parameter to a specific file.
     *
     * @param file The file where to serialize the object.
     * @param object The object to serialize.
     * @throws InvalidClassException If an error occurs during serialization.
     * @throws NotSerializableException If the class of an object is not serializable.
     * @throws IOException If something bad happen.
     */
    public static void serialize(File file, Object object) throws InvalidClassException, NotSerializableException, IOException {
        FileOutputStream fos;
        ObjectOutputStream ois;
        try {
            fos = new FileOutputStream(file);
            ois = new ObjectOutputStream(fos);
            logger.trace("Start of serialization!");
            ois.writeObject(object);
            logger.trace("Object of class " + object.getClass().getName() + " serialized!");
            ois.close();
            fos.close();
        } catch (InvalidClassException e) {
            logger.error("An error occurred when trying to serialize the class " + object.getClass().getName());
            throw new InvalidClassException(object.getClass().getName(), "An error occurred when trying to serialize this class!")
        } catch (NotSerializableException e) {
            logger.error("The class " + object.getClass().getName() + " is not serializable!");
            throw new NotSerializableException(object.getClass().getName());
        } catch (IOException e) {
            logger.error("An error occurred while serializing an object of the class " + object.getClass().getName());
            throw new IOException("Something bad happen during serialization!", e);
        }
    }

    /**
     * Deserialize the file given.
     *
     * @param file The file to deserialize.
     * @return The object deserialized.
     * @throws IOException If something bad happen.
     * @throws ClassNotFoundException If the deserialized object's class is not found.
     */
    public static Object deserialize(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis;
        ObjectInputStream ois;
        Object obj;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            logger.trace("Start of deserialization!");
            obj = ois.readObject();
            logger.trace("Object of class " + obj.getClass().getName() + " deserialized!");
            ois.close();
            fis.close();
        } catch (IOException e) {
            logger.error("An error occurred while reading the serialized file: " + file.getCanonicalPath());
            throw new IOException("Something bad happen during deserialization!", e);
        } catch (ClassNotFoundException e) {
            logger.error("An error occurred while deserializing the objects: class can't be found!");
            throw new ClassNotFoundException("Serialized class can't be found!", e);
        }
        return obj;
    }
}
