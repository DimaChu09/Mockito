package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class MessageSenderImplTest {

    /**
     * Т.к. классы реализованы, заглушки для них нет смысла создавать.
     * Будем работать с интерфейсами.
     */
    @Mock
    GeoService geoService;
    @Mock
    LocalizationService localizationService;

    String testIPMosFull = "172.123.12.19";
    String testIPMosPart = "172.";
    String testIPNYFull = "96.44.183.149";
    String testIPNYPart = "96.";

    @Test
    @DisplayName("Проверяем полный Московский IP")
    void  MassageSenderTestMosFull () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPMosFull))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPMosFull);
        Assertions.assertEquals("Добро пожаловать", sender.send(headers));
    }

    @Test
    @DisplayName("Проверяем частичный Московский IP")
    void  MassageSenderTestMosPart () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPMosPart))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPMosPart);
        Assertions.assertEquals("Добро пожаловать", sender.send(headers));
    }

    @Test
    @DisplayName("Проверяем полный IP New York")
    void  MassageSenderTestNYFull () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPNYFull))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPNYFull);
        Assertions.assertEquals("Welcome", sender.send(headers));
    }

    @Test
    @DisplayName("Проверяем частичный IP New York")
    void  MassageSenderTestNYPart () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPNYPart))
                .thenReturn(new Location("New York", Country.USA, null,  0));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPNYPart);
        Assertions.assertEquals("Welcome", sender.send(headers));
    }
}
