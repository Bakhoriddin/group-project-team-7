package sample;

public class Books {
    private int ISBN;
    private String title;
    private String author;
    private String subject;
    private String publishDate;

    public Books(int ISBN, String title, String author, String subject, String publishDate) {
        setISBN(ISBN);
        setTitle(title);
        setAuthor(author);
        setSubject(subject);
        setPublishDate(publishDate);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }
}
