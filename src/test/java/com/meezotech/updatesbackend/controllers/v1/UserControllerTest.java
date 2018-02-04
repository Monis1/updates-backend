package com.meezotech.updatesbackend.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.domain.Gender;
import com.meezotech.updatesbackend.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private static final Long ID = 1L;

    @Mock
    private UserService userService;

    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void createUserTest() throws Exception {
        // request user DTO
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Arsalan");
        userDTO.setGender(Gender.MALE.name());

        // mocking user service to return entered DTO
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        // mocking user controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(userDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByIdTest() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(ID);
        userDTO.setFirstName("Monis");
        userDTO.setLastName("khan");
        userDTO.setGender(Gender.MALE.name());
        userDTO.setEmail("monisahmed8@gmail.com");

        // mocking user service to return user with specified ID
        when(userService.getUserById(ID)).thenReturn(userDTO);

        // mocking user controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(get(UserController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Monis")));
    }
}