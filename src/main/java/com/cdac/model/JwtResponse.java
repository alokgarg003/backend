package com.cdac.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String jwtToken;
}