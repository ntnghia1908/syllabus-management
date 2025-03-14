// UserDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private boolean enabled;
    private Set<String> roles = new HashSet<>();
    
    // Static method to convert Entity to DTO
    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());
        dto.setRoles(user.getRoles());
        return dto;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<UserDTO> fromEntities(List<User> users) {
        return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
}