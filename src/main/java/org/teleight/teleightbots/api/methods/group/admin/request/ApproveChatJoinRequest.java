package org.teleight.teleightbots.api.methods.group.admin.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.experimental.Tolerate;
import org.jetbrains.annotations.NotNull;
import org.teleight.teleightbots.api.ApiMethodBoolean;

@Builder
public record ApproveChatJoinRequest(
        @JsonProperty(value = "chat_id", required = true)
        @NotNull
        String chatId,

        @JsonProperty(value = "user_id", required = true)
        @NotNull
        Long userId
) implements ApiMethodBoolean {
    @Override
    public @NotNull String getEndpointURL() {
        return "approveChatJoinRequest";
    }

    public static class ApproveChatJoinRequestBuilder {
        @Tolerate
        public ApproveChatJoinRequestBuilder chatId(@NotNull Long chatId) {
            this.chatId = chatId.toString();
            return this;
        }
    }
}
