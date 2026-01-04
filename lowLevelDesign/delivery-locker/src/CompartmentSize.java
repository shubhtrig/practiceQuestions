public enum CompartmentSize {
    BIG,
    MEDIUM,
    SMALL;

    public static CompartmentSize getSize(String size) {
        if (size.equalsIgnoreCase("SMALL")) {
            return SMALL;
        } else if (size.equalsIgnoreCase("MEDIUM")) {
            return MEDIUM;
        } else if (size.equalsIgnoreCase("BIG")) {
            return BIG;
        } else {
            throw new IllegalArgumentException("Invalid compartment size: " + size);
        }
    }
}
