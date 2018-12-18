package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.TableTypeDto;
import by.bsu.eventfood.model.TableType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(uses = DataMapper.class)
@Component
public interface TableTypeMapper {
    TableType map(TableTypeDto dto);

    TableTypeDto map(TableType dto);
}
