package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.AddCommentDto;
import by.bsu.eventfood.controller.dto.CommentDto;
import by.bsu.eventfood.model.Comment;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper(uses = DataMapper.class)
@Component
public interface CommentMapper {

    @Mappings({@Mapping(target = "place", source = "placeId")})
    Comment map(AddCommentDto dto);

    @AfterMapping
    default void initDates(@MappingTarget Comment comment) {
        Date currentDate = new Date();

        comment.setCreated(currentDate);
        comment.setModified(currentDate);
    }


    @Mappings({
            @Mapping(target = "comment", source = "text"),
            @Mapping(target = "clientId", source = "client.id"),
            @Mapping(target = "userName", source = "client.name")})
    CommentDto mapCommentDto(Comment comment);
}
