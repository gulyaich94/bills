package com.gulyaich.bills.user.model.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gulyaich.bills.user.model.dto.UserDTO;
import com.gulyaich.bills.user.model.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final ObjectMapper objectMapper;

    public UserMapper(@Qualifier("entityObjectMapper") final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public UserDTO mapToDTO(final User user) {
        return objectMapper.convertValue(user, UserDTO.class);
    }

    public List<UserDTO> mapToDTO(final List<User> users) {
        return objectMapper.convertValue(users, new TypeReference<List<UserDTO>>(){});
    }
}
