// ToDo.java
public class ToDo {
    private String title;
    private boolean status;

    public ToDo() {
    }

    public ToDo(String title) {
        this.title = title;
        this.status = false;
    }

    public ToDo(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
