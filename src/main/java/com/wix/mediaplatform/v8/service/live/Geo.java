package com.wix.mediaplatform.v8.service.live;

public class Geo {

    private Coordinates coordinates;

    private String ipAddress;

    public Geo() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Geo setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Geo setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public static class Coordinates {

        private Float latitude;

        private Float longitude;

        public Coordinates() {
        }

        public Float getLatitude() {
            return latitude;
        }

        public Coordinates setLatitude(Float latitude) {
            this.latitude = latitude;
            return this;
        }

        public Float getLongitude() {
            return longitude;
        }

        public Coordinates setLongitude(Float longitude) {
            this.longitude = longitude;
            return this;
        }
    }
}
