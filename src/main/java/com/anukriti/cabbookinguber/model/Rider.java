package com.anukriti.cabbookinguber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.pattern.PathPattern;

@Getter
@ToString
@AllArgsConstructor
public class Rider {
    private final String riderId;
    private final String riderName;
}
