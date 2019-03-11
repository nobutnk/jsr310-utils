package com.nobutnk;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.nobutnk.jsr310util.dto.Item;
import com.nobutnk.jsr310util.dto.Sample;

/**
 * Unit test for simple App.
 */
public class ConvertTest {
    

    /**
     * Java -> String, String -> Java
     * @throws IOException 
     */
    @Test
    public void testConvert() throws IOException {
        Sample sample = new Sample();
        sample.setName("sampleName");
        sample.setPrice(Integer.valueOf(1280));
        sample.setIsPositive(Boolean.TRUE);
        
        Item item1 = new Item();
        item1.setName("item1");
        item1.setPrice(Integer.valueOf(280));
        item1.setCreatedAt(LocalDateTime.now());
        item1.setUpdatedAt(LocalDateTime.now());
        item1.setTest("test");
        
        sample.setItems(Arrays.asList(item1));
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        objectMapper.setVisibilityChecker(
                objectMapper.getSerializationConfig().
                getDefaultVisibilityChecker().
                withFieldVisibility(JsonAutoDetect.Visibility.ANY).
                withGetterVisibility(JsonAutoDetect.Visibility.NONE));
        
        String jsonString = objectMapper.writeValueAsString(sample);
        
        assertThat(jsonString, is(notNullValue()));
        
        
        Sample actual = objectMapper.readValue(jsonString, Sample.class);

        assertThat(actual, is(notNullValue()));
        assertThat(actual.getName(), is(sample.getName()));
        assertThat(actual.getPrice(), is(sample.getPrice()));
        assertThat(actual.getIsPositive(), is(sample.getIsPositive()));
        
        Item actualItem = actual.getItems().get(0);
        assertThat(actualItem.getName(), is(item1.getName()));
        assertThat(actualItem.getPrice(), is(item1.getPrice()));
        assertThat(actualItem.getUpdatedAt(), is(item1.getUpdatedAt()));
        assertThat(actualItem.getCreatedAt(), is(item1.getCreatedAt()));
        assertThat(actualItem.getTest(), is(nullValue()));
        
    }
}
