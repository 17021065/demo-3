package vn.vnpay.demo.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ObjectUtilities {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static boolean hasNullOrEmptyOrBlankAttributes(Object object) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value == null || (value instanceof String && ((String) value).trim().isEmpty()) ||
                        (value instanceof Object[] && ((Object[]) value).length == 0)) {
                    log.info("Field name {} is null", field.getName());
                    return true;
                }
            }
            return false;
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            log.error("Checking attribute check failed: ", e);
            return false;
        }
    }

    public static String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("JSON conversion failed: ", e);
            return null;
        }
    }
}
