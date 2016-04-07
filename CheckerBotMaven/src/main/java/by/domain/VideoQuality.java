package by.domain;

public enum VideoQuality {

    CAMRIP(0,"CAMRIP"),
    TS(1,"TS"),
    DVDRIP(2,"DVDRIP"),
    SATRIP(3,"SATRIP"),
    WEBDLRIP(4,"WEBDLRIP"),
    HDRIP(5,"HDRIP"),
    BDRIP(6,"BDRIP");

    private final Integer value;

    private final String name;

    VideoQuality(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
