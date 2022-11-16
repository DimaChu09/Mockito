package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.Set;

public class LocalizationServiceImplTest {

    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    @DisplayName("Проверка на ввод RUSSIA")
    void localeRus () {
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    @DisplayName("Проверка на ввод кроме RUSSIA")
    void localeNotRus () {
        Set<Country> countriesNotRus = Set.of(Country.BRAZIL, Country.USA, Country.GERMANY);
        countriesNotRus.forEach(c -> Assertions.assertEquals("Welcome", localizationService.locale(c)));
        // Автогенерация foreach из
        //        for (Country c : countriesNotRus) {
        //            Assertions.assertEquals("Welcome", localizationService.locale(c));
        //        }
    }
}
