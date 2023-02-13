package fr.cyu.smartread.spellchecking.dictionary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FiletypeNotSupportedException extends IllegalArgumentException {
    private File file;
    private String filetype;

    public FiletypeNotSupportedException(File file) throws IOException {
        setFile(file);
        updateCorrespondingFiletype();
    }

    public FiletypeNotSupportedException(File file, String message) throws IOException {
        super(message);
        setFile(file);
        updateCorrespondingFiletype();
    }

    public FiletypeNotSupportedException(File file, Throwable cause) throws IOException {
        super(cause);
        setFile(file);
        updateCorrespondingFiletype();
    }

    public FiletypeNotSupportedException(File file, String message, Throwable cause) throws IOException {
        super(message, cause);
        setFile(file);
        updateCorrespondingFiletype();
    }

    private FiletypeNotSupportedException updateCorrespondingFiletype() throws IOException {
        setFiletype(Files.probeContentType(this.file.toPath()));
        return this;
    }

    public File getFile() {
        return this.file;
    }

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
