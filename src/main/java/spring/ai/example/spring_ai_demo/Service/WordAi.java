package spring.ai.example.spring_ai_demo.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import spring.ai.example.spring_ai_demo.Entity.Word;

@Service
public class WordAi {

    private final ChatClient chatClient;

    public WordAi(OpenAiChatModel chatModel) {
        chatClient = ChatClient.create(chatModel);

    }

    //method to get word response from chatgpt's LLM API
    public Word getWordResponse(String name) {

        return chatClient.prompt()
                .user(u -> u.text("Get me the English meanings and dutch example sentences for the Dutch word {name}").param("name", name))
                .call()
                .entity(new BeanOutputConverter<Word>(Word.class));
    }

}
