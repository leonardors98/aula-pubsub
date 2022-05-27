package br.edu.unicesumar.pubsub;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.unicesumar.pubsub.domain.Aluno;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class AlunoControllerIntTest {

    @Autowired
    private MockMvc mockMvc;
   
    @Autowired
    private ObjectMapper objectMapper;
   
    @Test
    public void criarUmAlunoTest() throws Exception {
       
        Aluno aluno = new Aluno(null, "123", "Teste", LocalDate.of(2000, 1, 1), new ArrayList<>());
       
        String jsonAluno = objectMapper.writeValueAsString(aluno);

         MvcResult andReturn = mockMvc.perform(post("/aluno")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAluno))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
       
         String corpoDaResposta = andReturn.getResponse().getContentAsString();
         
         Aluno alunoReposta = objectMapper.readValue(corpoDaResposta, Aluno.class);

         assertNotNull(alunoReposta.getId());
         
         mockMvc.perform(get("/aluno/" + alunoReposta.getId()))
            .andExpect(status().is2xxSuccessful());
                 
    }
   
    @Test
    public void criarUmOutroAlunoTest() throws Exception {
       
        Aluno aluno = new Aluno(null, "123", "Teste", LocalDate.of(2000, 1, 1), new ArrayList<>());
       
        String jsonAluno = objectMapper.writeValueAsString(aluno);

         MvcResult andReturn = mockMvc.perform(post("/aluno")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAluno))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
       
         String corpoDaResposta = andReturn.getResponse().getContentAsString();
         
         Aluno alunoReposta = objectMapper.readValue(corpoDaResposta, Aluno.class);

         assertNotNull(alunoReposta.getId());
       
    }
}