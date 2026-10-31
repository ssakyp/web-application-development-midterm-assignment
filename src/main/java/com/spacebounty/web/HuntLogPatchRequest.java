package com.spacebounty.web;

import jakarta.validation.constraints.Size;

public record HuntLogPatchRequest(
        Boolean success,
        @Size(max = 2000) String notes
) {
}
