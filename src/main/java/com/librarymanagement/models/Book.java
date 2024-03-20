package com.librarymanagement.models;

public class Book {
    private String name;
    private int id;
    private String author;
    private String publication;
    private String edition;
    private String journal;
    private int availableCount;
    private int volume;

    public Book(String name, int id, String author, String publication, String edition, String journal, int availableCount, int volume) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.publication = publication;
        this.edition = edition;
        this.journal = journal;
        this.availableCount = availableCount;
        this.volume = volume;
    }

    public Book() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getEdition() {
        return edition;
    }



    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", author='" + author + '\'' +
                ", publication='" + publication + '\'' +
                ", edition='" + edition + '\'' +
                ", journal='" + journal + '\'' +
                ", availableCount=" + availableCount +
                ", volume=" + volume +
                '}';
    }
}
