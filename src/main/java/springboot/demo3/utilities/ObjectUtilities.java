package springboot.demo3.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ObjectUtilities {
    public static boolean hasNullOrEmptyOrBlankAttributes(Object object) throws IllegalAccessException {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(object);
            if (value == null || (value instanceof String && ((String) value).trim().isEmpty()) ||
                    (value instanceof Object[] && ((Object[]) value).length == 0)) {
                log.info("{} is null", field.getName());
                return true;
            }
        }
        return false;
    }

    public static String convertToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
