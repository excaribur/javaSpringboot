package com.bob.backend.mapper;

import com.bob.backend.entity.User;
import com.bob.backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MRegisterResponse toRegisterResponse(User user);
}
