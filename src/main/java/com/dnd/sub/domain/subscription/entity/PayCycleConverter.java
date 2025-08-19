package com.dnd.sub.domain.subscription.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PayCycleConverter implements AttributeConverter<PayCycle, String> {

    @Override
    public String convertToDatabaseColumn(PayCycle attribute) {
        if (attribute == null) return null;
        return attribute.getInterval() + attribute.getUnit().name();
    }

    @Override
    public PayCycle convertToEntityAttribute(String data) {
        if (data == null || data.isEmpty()) return null;
        String num = data.replaceAll("[^0-9]", "");
        String unit = data.replaceAll("[0-9]", "");
        return PayCycle.of(Integer.parseInt(num), PayCycleUnitType.valueOf(unit));
    }
}
