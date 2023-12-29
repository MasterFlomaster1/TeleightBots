package org.teleight.teleightbots.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.teleight.teleightbots.api.ApiMethod;
import org.teleight.teleightbots.api.objects.File;
import org.teleight.teleightbots.exception.exceptions.TelegramRequestException;

public record GetFile(
        @JsonProperty(value = "file_id", required = true)
        @NotNull
        String fileId
) implements ApiMethod<File> {
    @Override
    public @NotNull File deserializeResponse(@NotNull String answer) throws TelegramRequestException {
        return deserializeResponse(answer, File.class);
    }

    @Override
    public @NotNull String getEndpointURL() {
        return "getFile";
    }
}
