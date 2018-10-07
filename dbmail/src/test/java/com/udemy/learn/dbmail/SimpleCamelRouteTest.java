package com.udemy.learn.dbmail;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SimpleCamelRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    Environment environment;


    @Before
    public void cleanUp() throws IOException {
        FileUtils.cleanDirectory(new File("data/output"));
        FileUtils.deleteDirectory(new File("data/output"));
    }

    @Test
    public void testMoveFile() throws InterruptedException {
        String message = "test";
        String fileName = "test.txt";
        producerTemplate.sendBodyAndHeader(environment.getProperty("fromRoute"),
                message,
                Exchange.FILE_NAME,
                fileName);

        Thread.sleep(3000);
        File outFile = new File("data/output/"+fileName);
        assertTrue(outFile.exists());
    }
}
