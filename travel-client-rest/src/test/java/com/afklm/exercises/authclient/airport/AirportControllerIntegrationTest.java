package com.afklm.exercises.authclient.airport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirportControllerIntegrationTest {

    @Autowired
    AirportController airportController;

    @Test
    public void testGetAirportListHappyPath() {

        //String outcome= airportController.getAirportList()
        // Assert THAT the outcome is as expected
        //assertThat(outcome,is(equalTo("success")));
    }

    @Test
    public void getAirportListByCode() {
    }

    @Test
    public void findAirport() {
    }
}