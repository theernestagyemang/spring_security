package com.project.software.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;
    @Builder.Default
    private Map<String, Object> model = new HashMap<>();
}

