package domain;

import util.VideoQuality;

public class Film {

    private Integer id;

    private String name;

    private Integer year;

    private VideoQuality videoQuality;

    private String sound;

    private String site;

    private User users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public VideoQuality getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(VideoQuality videoQuality) {
        this.videoQuality = videoQuality;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
