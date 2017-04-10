package com.devplant.introduction.configuration;

import com.devplant.introduction.properties.AdminUserProperties;
import com.devplant.introduction.properties.UserRegistrationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableConfigurationProperties({ AdminUserProperties.class, UserRegistrationProperties.class })
public class LibraryConfiguration {

	public ObjectMapper objectMapper(){
		ObjectMapper mapper = new ObjectMapper();
		JavaTimeModule javaTimeModule=new JavaTimeModule();
		// Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
		mapper.registerModule(javaTimeModule);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return mapper;
	}
}
