package by.domain;

public class FilmTemplate {

    private Integer id;

    private String filmName;

    private Integer userId;

    private int found;

    public FilmTemplate() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getFound() {
        return found;
    }

    public void setFound(int found) {
        this.found = found;
    }
}
