package com.security.dao.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SighnUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
