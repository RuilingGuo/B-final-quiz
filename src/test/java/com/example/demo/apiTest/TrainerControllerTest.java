package com.example.demo.apiTest;

import com.example.demo.domain.Trainer;
import com.example.demo.dto.TrainerDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll()
    public static void init(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void should_create_trainer_and_return_trainer_with_id() throws Exception {

        //language=JSON
        String requestJson = "{\n" +
                "  \"name\": \"Foo\"\n" +
                "}";

        mockMvc.perform(post("/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    void should_not_create_trainer_with_blank_value() throws Exception {

        //language=JSON
        String requestJson = "{\n" +
                "  \"name\": \"\"\n" +
                "}";

        mockMvc.perform(post("/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("名字不能为空")));

    }

    @Test
    void should_return_trainer_list_if_grouped_is_false() throws Exception {

        //language=JSON
        String requestJson = "{\n" +
                "  \"name\": \"Foo\"\n" +
                "}";

        mockMvc.perform(post("/trainers")
                .param("grouped","false")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());


        mockMvc.perform(get("/trainers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_delete_trainer_if_id_is_existed() throws Exception {

        //language=JSON
        String requestJson = "{\n" +
                "  \"name\": \"Foo\"\n" +
                "}";

        mockMvc.perform(post("/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/trainers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        String result = mockMvc.perform(get("/trainers")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        List<TrainerDto> trainerDtoList = objectMapper.readValue(result, new TypeReference<List<TrainerDto>>() {});


        mockMvc.perform(delete("/trainers/{id}",trainerDtoList.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/trainers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void should_not_delete_and_return_err_when_id_is_not_existed() throws Exception {
        mockMvc.perform(delete("/trainers/9999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
