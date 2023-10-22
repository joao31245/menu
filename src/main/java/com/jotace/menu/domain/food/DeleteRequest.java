package com.jotace.menu.domain.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeleteRequest(@NotBlank String title, @NotNull Integer price) {

}
