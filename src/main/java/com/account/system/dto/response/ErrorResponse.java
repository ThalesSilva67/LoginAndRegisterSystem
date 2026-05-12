package com.account.system.dto.response;

import java.time.Instant;

public record ErrorResponse(String message, Instant timestamp) {
}
