package com.gentlemonster.DTO.Request.User;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserMutiDeleteRequest {
    private List<String> id;
}

