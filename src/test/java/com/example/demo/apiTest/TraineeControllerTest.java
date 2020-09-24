package com.example.demo.apiTest;

import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TraineeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll()
    public static void init(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void should_create_trainee_and_return_trainee_with_id() throws Exception {

        //language=JSON
        String requestJson = "{\n" +
                "  \"name\": \"Foo\",\n" +
                "  \"office\": \"西安\",\n" +
                "  \"email\": \"foo@thoughtworks.com\",\n" +
                "  \"zoomId\": \"foo\"\n" +
                "}";

        mockMvc.perform(post("/trainees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    void should_not_create_trainee_with_invalid_param() throws Exception {

        //language=JSON
        String requestJsonWithBlank = "{\n" +
                "  \"name\": \"\",\n" +
                "  \"office\": \"西安\",\n" +
                "  \"email\": \"foo@thoughtworks.com\",\n" +
                "  \"zoomId\": \"foo\"\n" +
                "}";

        mockMvc.perform(post("/trainees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonWithBlank))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("名字不能为空")));

        //language=JSON
        String requestJsonWithErrEmail = "{\n" +
                "  \"name\": \"foo\",\n" +
                "  \"office\": \"西安\",\n" +
                "  \"email\": \"foothoughtworks.com\",\n" +
                "  \"zoomId\": \"foo\"\n" +
                "}";

        mockMvc.perform(post("/trainees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonWithErrEmail))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("邮箱地址不正确")));

    }

    @Test
    void should_return_trainee_list_if_grouped_is_false() throws Exception {

        //language=JSON
        String requestJson = "{\n" +
                "  \"name\": \"Foo\",\n" +
                "  \"office\": \"西安\",\n" +
                "  \"email\": \"foo@thoughtworks.com\",\n" +
                "  \"zoomId\": \"foo\"\n" +
                "}";

        mockMvc.perform(post("/trainees")
                .param("grouped","false")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/trainees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());


        mockMvc.perform(get("/trainees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_delete_trainee_if_id_is_existed() throws Exception {

        //language=JSON
        String requestJson = "{\n" +
                "  \"name\": \"Foo\",\n" +
                "  \"office\": \"西安\",\n" +
                "  \"email\": \"foo@thoughtworks.com\",\n" +
                "  \"zoomId\": \"foo\"\n" +
                "}";

        mockMvc.perform(post("/trainees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/trainees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        String result = mockMvc.perform(get("/trainees")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        List<TraineeDto> traineeDtoList = objectMapper.readValue(result, new TypeReference<List<TraineeDto>>() {});


        mockMvc.perform(delete("/trainees/{id}",traineeDtoList.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/trainees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void should_not_delete_and_return_err_when_id_is_not_existed() throws Exception {
        mockMvc.perform(delete("/trainees/9999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
