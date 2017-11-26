package ru.springws.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackages = "ru.springws.client")
public class ApplicationTest {
    @Test
    public void contextLoads() {

    }
}
