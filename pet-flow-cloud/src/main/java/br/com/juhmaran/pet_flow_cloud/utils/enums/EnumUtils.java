package br.com.juhmaran.pet_flow_cloud.utils.enums;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@AllArgsConstructor
public class EnumUtils {

    public static String getDescription(Enum<?> enumValue, MessageSource messageSource) {
        String messageKey = ((HasMessageKey) enumValue).getMessageKey();
        return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }

    public interface HasMessageKey {
        String getMessageKey();
    }

}
