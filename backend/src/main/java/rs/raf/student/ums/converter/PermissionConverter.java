package rs.raf.student.ums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import rs.raf.student.ums.type.Permissions;

@Converter
public class PermissionConverter implements AttributeConverter<Permissions, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Permissions attribute) {
        return attribute.flags();
    }

    @Override
    public Permissions convertToEntityAttribute(Integer data) {
        return Permissions.of(data);
    }

}
