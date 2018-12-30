package com.learn.mongobee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseMigration {

    protected static Logger log = LoggerFactory.getLogger(BaseMigration.class.getName());

    protected List<Document> readInputJsonFiles(String filePath) {
        try {
            InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
            String json = IOUtils.toString(jsonInputStream, StandardCharsets.UTF_8);


            ObjectMapper objectMapper = new ObjectMapper();

            List<Map<String,Object>> listOfObjects = objectMapper.readValue(json, new TypeReference<List<Map<String,Object>>>(){});

            return listOfObjects.stream().map(m -> new Document(m)).collect(Collectors.toList());

        } catch (IOException e) {
            log.debug("Error reading json input file - " + filePath, e);
            throw new RuntimeException("Error running mongobee migration.", e);
        }
    }
}
