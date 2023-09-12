package org.danilskryl.javarush.entity;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String feature;

    Feature(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    public static Feature getFeatureByValue(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        Feature[] features = Feature.values();
        for (Feature feature : features) {
            if (feature.feature.equals(value)) {
                return feature;
            }
        }

        return null;
    }
}
