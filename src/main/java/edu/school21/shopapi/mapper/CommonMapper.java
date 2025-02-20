package edu.school21.shopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CommonMapper {

    default OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
    }

    default String uuidToString(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }
}
