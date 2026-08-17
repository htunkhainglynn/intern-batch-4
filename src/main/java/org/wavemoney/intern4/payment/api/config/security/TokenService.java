package org.wavemoney.intern4.payment.api.config.security;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    private final Map<String, String> tokenStatus = new HashMap<>();

    public void markTokenAsActive(String phone, long expirationMs) {
        tokenStatus.put(phone, "ACTIVE");
    }

    public void markTokenAsLoggedOut(String phone) {
        tokenStatus.put(phone, "LOGGED_OUT");
    }

    public String getTokenStatus(String phone) {
        String status = tokenStatus.get(phone);
        if (status == null) {
            return "UNKNOWN";
        }
        return status;
    }

    public boolean isTokenActive(String phone) {
        return "ACTIVE".equals(getTokenStatus(phone));
    }
}