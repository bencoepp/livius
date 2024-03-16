package bencoepp.livius.security.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response containing a message.
 */
@Getter
@Setter
public class MessageResponse {
    private String message;
    public MessageResponse(String message) {
        this.message = message;
    }
}
