package com.sample.middleware.models.Responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private Object message;
    private String code;
    private String responseTime= String.valueOf(LocalDateTime.now());
}
