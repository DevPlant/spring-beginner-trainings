package com.devplant.introduction.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("grettings")
public class SimpleProperties {

	private String goodbyeMessage;

}
