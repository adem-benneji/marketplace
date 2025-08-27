package com.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   // ✅ gives a constructor with all fields
@NoArgsConstructor
public class AuthResponse {
    private String token;
}
