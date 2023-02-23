package com.endava.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePersonResponse {

    private String name;

    private String job;

    private String id;

    private String createdAt;


}
