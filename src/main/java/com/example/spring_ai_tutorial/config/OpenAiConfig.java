package com.example.spring_ai_tutorial.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAI API 설정
 */
@Configuration
public class OpenAiConfig {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiConfig.class);

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    /**
     * OpenAI API 클라이언트 빈 등록
     */
    @Bean
    public OpenAiApi openAiApi() {
        logger.debug("OpenAI API 클라이언트 초기화");
        return OpenAiApi.builder()
                .apiKey(apiKey)
                .build();
    }

    /**
     * OpenAI 임베딩 모델 빈 등록
     * 커스텀 옵션(모델명, MetadataMode 등)을 적용한 EmbeddingModel을 제공한다.
     * Qdrant VectorStore 자동 구성이 이 빈을 사용한다.
     */
    @Bean
    public EmbeddingModel embeddingModel(
            OpenAiApi openAiApi,
            @Value("${spring.ai.openai.embedding.options.model}") String embeddingModelName) {
        logger.debug("OpenAI 임베딩 모델 초기화: {}", embeddingModelName);
        return new OpenAiEmbeddingModel(
                openAiApi,
                MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder().model(embeddingModelName).build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE
        );
    }
}
