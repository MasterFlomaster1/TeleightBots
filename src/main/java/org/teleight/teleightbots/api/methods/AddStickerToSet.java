package org.teleight.teleightbots.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import org.jetbrains.annotations.NotNull;
import org.teleight.teleightbots.api.MultiPartApiMethodBoolean;
import org.teleight.teleightbots.api.objects.InputFile;

import java.util.HashMap;
import java.util.Map;

@Builder(builderClassName = "Builder", toBuilder = true, builderMethodName = "ofBuilder")
@Jacksonized
public record AddStickerToSet(
        @JsonProperty(value = "user_id", required = true)
        long userId,

        @JsonProperty(value = "name", required = true)
        @NotNull
        String name,

        @JsonProperty(value = "sticker", required = true)
        @NotNull
        InputFile sticker
) implements MultiPartApiMethodBoolean {

    public static @NotNull Builder ofBuilder(long userId, String name, InputFile sticker) {
        return new AddStickerToSet.Builder().userId(userId).name(name).sticker(sticker);
    }

    @Override
    public @NotNull String getEndpointURL() {
        return "addStickerToSet";
    }

    @Override
    public @NotNull Map<String, Object> getParameters() {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", userId);
        parameters.put("name", name);
        parameters.put("sticker", sticker);
        return parameters;
    }
}
