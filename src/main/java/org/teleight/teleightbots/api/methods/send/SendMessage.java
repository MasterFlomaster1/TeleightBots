package org.teleight.teleightbots.api.methods.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.experimental.Tolerate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.teleightbots.api.ApiMethodMessage;
import org.teleight.teleightbots.api.objects.entities.MessageEntity;
import org.teleight.teleightbots.api.objects.keyboard.ReplyKeyboard;
import org.teleight.teleightbots.api.utils.ParseMode;

@Builder
public record SendMessage(
        @JsonProperty(value = "chat_id", required = true)
        @NotNull
        String chatId,

        @JsonProperty(value = "message_thread_id")
        @Nullable
        Integer messageThreadId,

        @JsonProperty(value = "text", required = true)
        @NotNull
        String text,

        @JsonProperty(value = "parse_mode")
        @Nullable
        ParseMode parseMode,

        @JsonProperty(value = "entities")
        @Nullable
        MessageEntity[] entities,

        @JsonProperty(value = "disable_web_page_preview")
        @Nullable
        Boolean disableWebPagePreview,

        @JsonProperty(value = "disable_notification")
        @Nullable
        Boolean disableNotification,

        @JsonProperty(value = "protect_content")
        @Nullable
        Boolean protectContent,

        @JsonProperty(value = "reply_to_message_id")
        @Nullable
        Integer replyToMessageId,

        @JsonProperty(value = "allow_sending_without_reply")
        @Nullable
        Boolean allowSendingWithoutReply,

        @JsonProperty(value = "reply_markup")
        @Nullable
        ReplyKeyboard replyMarkup
) implements ApiMethodMessage {

    @Override
    public @NotNull String getEndpointURL() {
        return "sendMessage";
    }

    public static class SendMessageBuilder {
        @Tolerate
        public SendMessageBuilder chatId(@NotNull Long chatId) {
            this.chatId = chatId.toString();
            return this;
        }
    }

}
