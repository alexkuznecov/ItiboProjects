package domain;

public class Anime {

    private String id;

    private String name;

    private String newSeries;

    private String publicationDate;

    private String image;

    private String site;

    public void setName(String name) {
        this.name = name;
    }

    public void setNewSeries(String newSeries) {
        this.newSeries = newSeries;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getNewSeries() {
        return newSeries;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
