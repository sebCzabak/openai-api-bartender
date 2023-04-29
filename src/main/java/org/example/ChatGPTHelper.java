package org.example;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.List;

public class ChatGPTHelper
{
    OpenAiService service;
    public ChatGPTHelper(){
        service = new OpenAiService("token", Duration.ofSeconds(30));
    }
    public String getDrinkIdea(List<String> products){

        String allProducts = String.join(", ", products);
        String question  ="I have this in my fridge "+ allProducts+". What drinks can I make with this. Give me 3 ideas.";
        return askChatGPT(question);

    }
    public String getNonAlcoholicDrinkIdea(List<String> products){

    String allProducts = String.join(", ", products);
    String question  ="I have this in my fridge "+ allProducts+". What non-alcoholic drinks can I make with this. Give me 3 ideas.";
    return askChatGPT(question);
}
    public String specificDrinkIdea(String specificProduct){
        String question="Can you came up with 3 drink ideas with that specific liquor "+ specificProduct;
        return askChatGPT(question);

    }
    private String askChatGPT(String question){
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-3.5-turbo")
                .build();

        StringBuilder stringBuilder =new StringBuilder();

        List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
        choices.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }
}
