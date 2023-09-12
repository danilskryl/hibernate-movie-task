package org.danilskryl.javarush.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getRating();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        Rating[] ratingArray = Rating.values();

        for (Rating rating : ratingArray) {
            if (rating.getRating().equals(s)) {
                return rating;
            }
        }
        return null;
    }
}
