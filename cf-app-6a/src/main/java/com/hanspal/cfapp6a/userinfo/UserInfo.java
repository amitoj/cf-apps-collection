package com.hanspal.cfapp6a.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private Integer requestId;
    private String firstName;
    private String lastName;
}
