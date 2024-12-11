package com.gentlemonster.DTO.Response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private T data;
    private List<String> message;
}
