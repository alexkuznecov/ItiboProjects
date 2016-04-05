package by.domain;

/**
 * Created by alek on 20.2.16.
 */
public class Anime {

    private String id;

    private String name;

    private String newSeries;

    private String publicationDate;

    private String site;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewSeries() {
        return newSeries;
    }

    public void setNewSeries(String newSeries) {
        if (!newSeries.equals("")) {
            this.newSeries = newSeries;
        } else {
            this.newSeries = "0";
        }
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
