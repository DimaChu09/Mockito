package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import java.util.List;

public class GeoServiceImplTest {

    String ip;
    private GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    @DisplayName("Тест локального IP")
    void byIPLoc () {
        ip = "127.0.0.1";
        Assertions.assertEquals(null, geoService.byIp(ip).getCity());
        Assertions.assertEquals(null, geoService.byIp(ip).getCountry());
        Assertions.assertEquals(null, geoService.byIp(ip).getStreet());
        Assertions.assertEquals(0, geoService.byIp(ip).getBuiling());
    }

    @Test
    @DisplayName("Тест Московскиго IP")
    void byIPMos () {
        ip = "172.0.32.11";
        Assertions.assertEquals("Moscow", geoService.byIp(ip).getCity());
        Assertions.assertEquals(Country.RUSSIA, geoService.byIp(ip).getCountry());
        Assertions.assertEquals("Lenina", geoService.byIp(ip).getStreet());
        Assertions.assertEquals(15, geoService.byIp(ip).getBuiling());
    }

    @Test
    @DisplayName("Тест IP NY")
    void byIPNY () {
        ip = "96.44.183.149";
        Assertions.assertEquals("New York", geoService.byIp(ip).getCity());
        Assertions.assertEquals(Country.USA, geoService.byIp(ip).getCountry());
        Assertions.assertEquals("10th Avenue", geoService.byIp(ip).getStreet());
        Assertions.assertEquals(32, geoService.byIp(ip).getBuiling());
    }
}
