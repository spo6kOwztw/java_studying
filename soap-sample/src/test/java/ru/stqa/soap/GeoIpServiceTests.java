package ru.stqa.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {

        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("188.234.216.134");
        Assert.assertEquals(geoIP,"<GeoIP><Country>RU</Country><State>71</State></GeoIP>");


    }
}
