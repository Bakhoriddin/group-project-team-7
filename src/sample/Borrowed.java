package sample;

public class Borrowed {

    private String user;
    private String title;
    private String isbn;
    private String taken;
    private String returned;
    private boolean status;

    public Borrowed(String user, String title, String isbn, String taken, String returned, boolean status) {
        this.user = user;
        this.title = title;
        this.isbn = isbn;
        this.taken = taken;
        this.returned = returned;
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTaken() {
        return taken;
    }

    public String getReturned() {
        return returned;
    }

    public boolean isStatus() {
        return status;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
