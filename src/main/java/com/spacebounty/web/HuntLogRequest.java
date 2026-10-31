package com.spacebounty.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

public record HuntLogRequest(
        @JsonProperty("criminal_id")
        @NotNull Long criminalId,
        @NotNull Boolean success,
        @Size(max = 2000) String notes
) {
}
