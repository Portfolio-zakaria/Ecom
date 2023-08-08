package com.zakaria.ecom.dtos;

import com.zakaria.ecom.models.Role;
import com.zakaria.ecom.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Collection<Role> roles;

    public static UserDto fromDao(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
      return dto;
    }
    public User toDao() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setRoles(this.getRoles());

        return user;
    }
}
