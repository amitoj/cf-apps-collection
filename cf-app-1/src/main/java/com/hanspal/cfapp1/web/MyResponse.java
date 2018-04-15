package com.hanspal.cfapp1.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MyResponse {
    private String id;
    private Date timestamp;
}
