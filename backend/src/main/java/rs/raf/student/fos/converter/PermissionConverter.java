package rs.raf.student.fos.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import rs.raf.student.fos.type.Permissions;

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
