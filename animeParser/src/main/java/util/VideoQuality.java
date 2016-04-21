package util;

public enum VideoQuality {

    CAMRIP(0,"CAMRIP"),
    TS(1,"TS"),
    DVDRIP(2,"DVDRIP"),
    SATRIP(3,"SATRIP"),
    WEBDLRIP(4,"WEBDLRIP"),
    HDTVRIP(5,"HDTVRIP"),
    HDRIP(6,"HDRIP"),
    BDRIP(7,"BDRIP");

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
