package com.iq.ema.dao;

import com.iq.ema.EmaApplication;
import com.iq.ema.model.Project;
import com.iq.ema.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
//@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql","classpath:data.sql"}),
//        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts ="classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest {
    @Autowired
    ProjectRepository proRepo;

    @Test
    public void ifNewProjectSaved_thenSuccess(){

        Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
        proRepo.save(newProject);

        assertEquals(1,proRepo.findAll().size());

    }
}
