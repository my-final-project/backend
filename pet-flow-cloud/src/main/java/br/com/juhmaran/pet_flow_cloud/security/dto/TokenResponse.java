package br.com.juhmaran.pet_flow_cloud.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("tokent_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("purpose")
    private String purpose;

    private String username;

    private String email;

    private List<String> roles;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
