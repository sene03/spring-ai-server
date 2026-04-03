package com.example.spring_ai_tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SpringAiTutorialApplicationTests {

    @MockBean
    VectorStore vectorStore;

    @Test
    void contextLoads() {
    }
}
