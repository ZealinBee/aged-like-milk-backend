package com.alm.dtos.auth;

public class TokensDTO {
    public String accessToken;

    public TokensDTO() {
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TokensDTO)) return false;
        final TokensDTO other = (TokensDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accessToken = this.getAccessToken();
        final Object other$accessToken = other.getAccessToken();
        if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TokensDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accessToken = this.getAccessToken();
        result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
        return result;
    }

    public String toString() {
        return "TokensDTO(accessToken=" + this.getAccessToken() + ")";
    }
}
