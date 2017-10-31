package com.github.aekrylov.itis.sem05.selenium;

import java.util.ArrayList;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 10/31/17 10:49 PM
 */
public class Gist {

    String description;
    List<GistFile> files = new ArrayList<>();

    public Gist() {
    }

    public Gist(String description, List<GistFile> files) {
        this.description = description;
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GistFile> getFiles() {
        return files;
    }

    public void setFiles(List<GistFile> files) {
        this.files = files;
    }

    static class GistFile {
        String filename;
        String fileContents;

        public GistFile() {
        }

        public GistFile(String filename, String fileContents) {
            this.filename = filename;
            this.fileContents = fileContents;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getFileContents() {
            return fileContents;
        }

        public void setFileContents(String fileContents) {
            this.fileContents = fileContents;
        }
    }
}
