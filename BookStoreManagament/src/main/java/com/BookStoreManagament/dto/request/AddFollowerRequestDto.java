package com.BookStoreManagament.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddFollowerRequestDto {

    @NotBlank(message = "followedAccountId is blank")
    String fkFollowedAccountId;
    @NotBlank(message = "followerAccountId is blank")
    String fkFollowerAccountId;

}
