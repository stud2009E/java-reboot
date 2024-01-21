package ru.edu.module12;

import ru.edu.module12.controller.UserController;
import ru.edu.module12.dao.entity.UserEntity;
import ru.edu.module12.dao.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders ;

@WebMvcTest({UserController.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("userAll"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    void createUserFormTest() throws Exception {
        UserEntity user = new UserEntity("Test", 0);
        Mockito.when(userService.getEmptyUserEntity()).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("userCreate"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    void editUserTest() throws Exception {
        UserEntity user = new UserEntity("Test", 0);
        Mockito.when(userService.findById(Mockito.any())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("userEdit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    void editUserPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/all"));
    }

    @Test
    void editUserNegativeTest() throws Exception {
        UserEntity user = new UserEntity("Test", 0);
        Mockito.when(userService.edit(Mockito.any())).thenThrow(new IllegalArgumentException("Age < 0"));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/edit", user, new ExtendedModelMap()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("userEdit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
    }

    @Test
    void createUserNegativeTest() throws Exception {
        UserEntity user = new UserEntity("Test", 0);
        Mockito.when(userService.create(Mockito.any())).thenThrow(new IllegalArgumentException("Age < 0"));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/create", user, new ExtendedModelMap()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("userCreate"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
    }

    @Test
    void createUserPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/create"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/all"));
    }

    @Test
    void deleteUserTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/user/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/all"));
    }
}
