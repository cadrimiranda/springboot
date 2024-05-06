
package com.dietreino.backend.controller;

import com.dietreino.backend.controllers.MuscularGroupController;
import com.dietreino.backend.domain.MuscularGroup;
import com.dietreino.backend.repositories.UserRepository;
import com.dietreino.backend.services.MuscularGroupService;
import com.dietreino.backend.services.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MuscularGroupController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MuscularGroupControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MuscularGroupService muscularGroupService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldgetmusculargroup() throws Exception {
        UUID id = UUID.randomUUID();

        when(muscularGroupService.findById(id)).thenReturn(new MuscularGroup(id, "group1"));
        mockMvc.perform(MockMvcRequestBuilders.get("/musculargroup/getone/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("group1"));
    }
}
