package ems_project_2023.TestController;


import ems_project_2023.config.SecurityConfig;
import ems_project_2023.controller.TeacherController;
import ems_project_2023.service.GroupService;
import ems_project_2023.service.TaskResponseService;
import ems_project_2023.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(TeacherController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityConfig.class)
public class TaskControllerTest {

        @Autowired
        MockMvc mocMvc;

        @MockBean
        TaskService taskService;

        @MockBean
        GroupService groupService;

        @MockBean
        TaskResponseService taskResponseService;

        @BeforeEach
        void setup() {
                this.mocMvc = standaloneSetup(new TeacherController()).build();
        }

        @Test
        @DisplayName("checking task page")
        public void taskPanel() {
                MockMvc mockMvc = standaloneSetup(new TeacherController())
                        .defaultRequest(get("/teacher/taskPanel").accept(MediaType.APPLICATION_JSON))
                        .alwaysExpect(view().name("taskPanel"))
                        .alwaysExpect(status().isOk())
                        .alwaysExpect(content().string(containsString("Task")))
                        .alwaysExpect(content().contentType("application/json;charset=UTF-8"))
                        .build();
        }

}
