package com.matt.feng.user.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.matt.feng.user.entity.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserDetailControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user")
    public void whenFoundThenReturnFoundUserDetail() throws Exception {

        final MvcResult mvcResult = mvc.perform(get("/api/userdetails/1000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString();
        final String expectedResponse
                = "{\"title\":\"Ms\",\"firstn\":\"Gates\",\"lastname\":\"Clinton\",\"gender\":\"Male\",\"address\":{\"street\":\"any Street\",\"city\":\"Beijing\",\"state\":\"Chaoyang\",\"postcode\":\"1000\"}}";

        assertEquals(expectedResponse, response);

        final ObjectMapper mapper = new ObjectMapper();
        final UserDetail returnedUserDetail = mapper.readValue(response, UserDetail.class);
        assertNotNull(returnedUserDetail);
        assertEquals("Gates", returnedUserDetail.getFirstName());
        assertEquals("Clinton", returnedUserDetail.getLastName());
        assertNotNull(returnedUserDetail.getAddress());
        assertEquals("any Street", returnedUserDetail.getAddress().getStreet());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void updateUserDetailWorkCorrectly() throws Exception {
        final String content
                = "{\"title\":\"Ms\",\"firstn\":\"Gates\",\"lastname\":\"Bill\",\"gender\":\"Male\",\"address\":{\"street\":\"fff street\",\"city\":\"Beijing\",\"state\":\"Chaoyang\",\"postcode\":\"1000\"}}";

        final MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.patch("/api/userdetails/1000000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content);

        MvcResult mvcResult = mvc.perform(builder)
                .andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString();
        assertEquals(content, response);

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void whenNotFoundThenShouldRaiseException() throws Exception {
        final String content
                = "{\"title\":\"Ms\",\"firstn\":\"Gates\",\"lastname\":\"Clinton\",\"gender\":\"Male\",\"address\":{\"street\":\"fff street\",\"city\":\"Beijing\",\"state\":\"Chaoyang\",\"postcode\":\"1000\"}}";

        final MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.patch("/api/userdetails/200")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content);

        mvc.perform(builder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "user")
    public void whenWithUserRole_TryUpdate_thenReturnStatusForbidden() throws Exception {

        final String content
                = "{\"title\":\"mr\",\"address\":{\"street\":\"12345 any rd\",\"city\":\"Eastwood\",\"state\":\"NSW\",\"postcode\":\"2000\"},\"firstn\":\"updatedTest\",\"lastName\":\"last\",\"gender\":\"male\"}";

        final MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.patch("/api/userdetails/1000000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content);

        final MvcResult mvcResult = mvc.perform(builder)
                .andExpect(status().isForbidden())
                .andReturn();
    }

}