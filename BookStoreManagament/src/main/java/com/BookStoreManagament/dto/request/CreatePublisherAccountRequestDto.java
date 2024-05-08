package com.BookStoreManagament.dto.request;

import jakarta.validation.constraints.Email;
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
public class CreatePublisherAccountRequestDto {

    @NotBlank(message = "publisherAccountName is blank")
    @NotNull(message = "publisherAccountName is null")
    String publisherAccountName;
    @Email
    @NotNull(message = "publisherAccountEmail is null")
    String publisherAccountEmail;
    @NotBlank(message = "username is blank")
    @NotNull(message = "username is null")
    String username;
    @NotBlank(message = "password is blank")
    @NotNull(message = "password is null")
    String password;
    String roleId;

}
