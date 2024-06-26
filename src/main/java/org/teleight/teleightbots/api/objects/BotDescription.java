package org.teleight.teleightbots.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.teleight.teleightbots.api.ApiResult;

public record BotDescription(
        @JsonProperty(value = "description", required = true)
        String description
) implements ApiResult {
}
