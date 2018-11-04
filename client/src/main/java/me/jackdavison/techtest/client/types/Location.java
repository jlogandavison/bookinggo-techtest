package me.jackdavison.techtest.client.types;

public final class Location {

    public final double latitude, longitude;

    public Location(double lat, double lng) {
        this.latitude = lat;
        this.longitude = lng;
    }

    public String toString() {
        return String.format("%f,%f", this.latitude, this.longitude);
    }

    public static Location parseLocation(String input)
            throws LocationFormatException {

        if(input == null) {
            throw new LocationFormatException();
        }

        String parts[] = input.split(",");
        if(parts.length != 2) {
            throw new LocationFormatException();
        }

        try {
            return new Location(
                Double.parseDouble(parts[0]),
                Double.parseDouble(parts[1])
            );
        } catch(NumberFormatException exception) {
            throw new LocationFormatException(exception);
        }
    }

    public static class LocationFormatException extends IllegalArgumentException {

        public static String message = "Location string must be decimal"
                + " latitude and longitude values separated by a comma"
                + " (eg: \"53.4811312,-2.2424384\")";

        protected LocationFormatException() {
            super(message);
        }

        protected LocationFormatException(Throwable cause) {
            super(message, cause);
        }
    }
}
