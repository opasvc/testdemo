package com.tencent.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
@Slf4j
public class JsonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        log.info("\n Jackson Config init start...");
        ObjectMapper objectMapper = new ObjectMapper();

        // 序列化时不包含值为null的属性，避免序列化后出现"key":null的情况
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 在反序列化时，如果JSON中存在Java对象中不存在的属性，则忽略该属性，不会抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 设置全局的日期格式化，将日期类型序列化为指定格式的字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(simpleDateFormat);

        // 设置时区为东八区，确保日期时间的正确性，尤其是在不同服务器时区的情况下
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // 添加Java 8时间模块，支持对Java 8时间类型（如LocalDateTime）的序列化和反序列化
        JavaTimeModule timeModule = new JavaTimeModule();

        // 为LocalDateTime类型添加自定义序列化器，将其序列化为指定格式的字符串
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 注册JavaTimeModule到ObjectMapper，使其生效
        objectMapper.registerModule(timeModule);

        // 可选配置：在反序列化时，如果JSON中的值为单个值，允许将其反序列化为数组类型
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        // 可选配置：在序列化时，禁止将空的集合（如Map）序列化为对象，避免出现{}的情况
        objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);

        // 可选配置：在序列化时，将枚举类型序列化为它的name()值，而不是ordinal()值
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        // 可选配置：在反序列化时，允许将JSON中的字符串值反序列化为枚举类型，基于枚举的name()匹配
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        // 可选配置：在序列化时，将Java对象中的大数字类型（如BigInteger、BigDecimal）序列化为字符串，避免精度丢失
        objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);

        // 可选配置：在反序列化时，允许JSON中的数字值被反序列化为Java的原始类型（如int、long等）
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, false);

        log.info("\n Jackson Config init end...");
        return objectMapper;
    }
//    @Bean
//    public ObjectMapper objectMapper() {
//        log.info("Jackson Config init start...");
//        ObjectMapper objectMapper = new ObjectMapper();
////        序列化时不包含为null的属性
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
////      忽略不存在的属性
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//
////      设置全局时间转化
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        objectMapper.setDateFormat(simpleDateFormat);
////        设置时区
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//
//        JavaTimeModule timeModule = new JavaTimeModule();
//        timeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//
//        return objectMapper;
//    }
}
