package com.app.service.dto;


import com.app.entities.Role;
import com.app.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 11.05.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class UserDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String login;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Email
    @NotBlank
    @Size(min = 5, max = 254)
    private String email;

    private Date created;

    private Set<String>roles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.created = user.getCreated();
        this.roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", created=").append(created);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
