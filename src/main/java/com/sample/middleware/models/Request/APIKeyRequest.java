package com.sample.middleware.models.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIKeyRequest {
    private String name;
    private String value;
}
