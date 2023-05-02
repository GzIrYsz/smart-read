package fr.cyu.smartread.spellchecking.dictionary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This class represents an exception thrown if an unsupported file type is trying to be loaded.
 *
 * @author Thomas REMY
 */
public class FiletypeNotSupportedException extends IllegalArgumentException {
    private File file;
    private String filetype;

    /**
     * One of the constructors available.
     *
     * @param file The file not supported.
     * @throws IOException If bad things happens during the read.
     */
    public FiletypeNotSupportedException(File file) throws IOException {
        setFile(file);
        updateCorrespondingFiletype();
    }

    /**
     * One of the constructors available.
     *
     * @param file The file not supported.
     * @param message The message explaining the exception.
     * @throws IOException If bad things happens during the read.
     */
    public FiletypeNotSupportedException(File file, String message) throws IOException {
        super(message);
        setFile(file);
        updateCorrespondingFiletype();
    }

    /**
     * One of the constructors available.
     *
     * @param file The file not supported.
     * @param cause The cause.
     * @throws IOException If bad things happens during the read.
     */
    public FiletypeNotSupportedException(File file, Throwable cause) throws IOException {
        super(cause);
        setFile(file);
        updateCorrespondingFiletype();
    }

    /**
     * One of the constructors available.
     *
     * @param file The file not supported.
     * @param message The detailed message.
     * @param cause The cause.
     * @throws IOException If bad things happens during the read.
     */
    public FiletypeNotSupportedException(File file, String message, Throwable cause) throws IOException {
        super(message, cause);
        setFile(file);
        updateCorrespondingFiletype();
    }

    private FiletypeNotSupportedException updateCorrespondingFiletype() throws IOException {
        setFiletype(Files.probeContentType(this.file.toPath()));
        return this;
    }

    /**
     * @return The file not supported.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * @return The MIME type of the unsupported file.
     */
    public String getFiletype() {
        return this.filetype;
    }

    private void setFile(File file) {
        this.file = file;
    }

    private void setFiletype(String filetype) {
        this.filetype = filetype;
    }
}
