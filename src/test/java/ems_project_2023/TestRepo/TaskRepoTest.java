
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.jdbc.Sql;
//import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
//import ru.sberbank.jd.java_reboot_ems_project_2023.dao.repository.TaskRepository;
//
//
//@DataJpaTest
//public class TaskRepoTest {
//
//        @Autowired
//        private TaskRepository taskRepository;
//
//        @Test
////        @Sql("classpath:addTableTest.sql")
//        void findTaskById() {
//            Long idTaskFirst = 1L;
//            Long idTaskSecond = 2L;
//
//            Task taskFirst = taskRepository.findTaskById(idTaskFirst);
//            Assertions.assertThat(taskFirst.getId()).isEqualTo(idTaskFirst);
//
//            Task taskSecond = taskRepository.findTaskById(idTaskSecond);
//            Assertions.assertThat(taskSecond).isNotEqualTo(taskFirst);
//        }
//
//}
