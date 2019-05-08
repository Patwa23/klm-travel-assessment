package com.afklm.exercises.authclient.airport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AirportServiceUnitTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AirportService airportService;

    @Test
    public void getAirportList() {
        String body = this.restTemplate.getForObject("/", String.class);
      //  assertThat(body).isEqualTo("Hello World");
    }
}