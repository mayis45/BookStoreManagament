package com.BookStoreManagament.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProfilePhotoPublisherAccountRequestDto {

    @NotBlank(message = "publisherId is blank")
    @NotNull(message = "publisherId is null")
    String publisherId;
    @NotBlank(message = "URL is blank")
    @NotNull(message = "URL is null")
    String newProfilePhotoURL;

}
