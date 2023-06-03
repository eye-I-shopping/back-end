package com.example.TT.item.controller;

import com.example.TT.Gpt.Service.GPT3Service;
import com.example.TT.item.dto.itemDto3;
import com.example.TT.item.entity.test1;
import com.example.TT.item.service.Test1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@Slf4j
@RestController
public class itemcontroller11 {
    private final Test1Service test1Service;
    private final GPT3Service gpt3Service;

    public itemcontroller11(Test1Service test1Service, GPT3Service gpt3Service) {
        this.test1Service = test1Service;
        this.gpt3Service = gpt3Service;
    }

    @PostMapping(value = "/api/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleJSONData(@RequestBody(required = false) itemDto3[] itemsList) {
        if (itemsList == null || itemsList.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        

        double maxConfidence = 0.0;
        itemDto3 maxConfidenceItem = null;

        for (itemDto3 item : itemsList) {
            log.info("Item Info:");
            log.info("xmin: {}", item.getXmin());
            log.info("ymin: {}", item.getYmin());
            log.info("xmax: {}", item.getXmax());
            log.info("ymax: {}", item.getYmax());
            log.info("confidence: {}", item.getConfidence());
            log.info("class: {}", item.getClazz());
            log.info("name: {}", item.getName());
            log.info("---");

            if (item.getConfidence() > maxConfidence) {
                maxConfidence = item.getConfidence();
                maxConfidenceItem = item;
            }
        }

        if (maxConfidenceItem == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<test1> dbItemOpt = test1Service.findByName(maxConfidenceItem.getName());
        if (dbItemOpt.isEmpty()) {
            return new ResponseEntity<>(maxConfidenceItem.getName(), HttpStatus.OK);
        }

        String generatedSentence = ToGPT3(dbItemOpt.get());
        return new ResponseEntity<>(generatedSentence, HttpStatus.OK);
    }

    public String ToGPT3(test1 test1) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        GPT3Prompt prompt = new GPT3Prompt();
        prompt.setModel("text-davinci-003");
        prompt.setPrompt(String.format("Please generate a sentence using the following information." +
                "Name: %s, Category: %s, %s, Allergens: %s, %s, %s ",
                test1.getName(),
                test1.getCategori(),
                test1.getItemDetail(),
                test1.getAllegori(),
                test1.getShape(),
                test1.getMake()
        ));
        prompt.setMax_tokens(500);
        prompt.setTemperature(0.0);
        prompt.setTop_p(1.0);
        prompt.setN(1);
        prompt.setStream(false);
        prompt.setLogprobs(null);
        prompt.setStop("<|endoftext|>");
        String jsonInput="";
        jsonInput = gson.toJson(prompt);
        System.out.println("jsonInput: " + jsonInput);

        try {
            String responseJson = gpt3Service.processRequest(jsonInput);
            System.out.println("responseJson: " + responseJson);

            JsonObject responseObj = new Gson().fromJson(responseJson, JsonObject.class);
            System.out.println("responseObj: " + responseObj);
            
            if (responseObj.has("choices") && responseObj.get("choices").isJsonArray()) {
                JsonArray choicesArray = responseObj.getAsJsonArray("choices");
                if (!choicesArray.isJsonNull() && choicesArray.size() > 0) {
                    JsonObject choiceObject = choicesArray.get(0).getAsJsonObject();
                    if (choiceObject.has("message") && choiceObject.get("message").isJsonObject()) {
                        JsonObject messageObject = choiceObject.getAsJsonObject("message");
                        if (messageObject.has("content")) {
                            String generatedText = messageObject.get("content").getAsString();
                            return generatedText;
                        }
                    }
                }
            }


            return responseJson;

        } catch (IOException e) {
            String noinput = String.format("The name of this product is " +
                    "item_nm: %s, category: %s, item_detail: %s, allergens: %s, shape: %s, make: %s",
                    test1.getName(),
                    test1.getCategori(),
                    test1.getItemDetail(),
                    test1.getAllegori(),
                    test1.getShape(),
                    test1.getMake());
            return noinput;

        } catch (JsonSyntaxException e) {
            log.error("Error processing GPT-3 response", e);
            return "Error processing GPT-3 response";
        }
    }

    class GPT3Prompt {
        String model;
        String prompt;
        int max_tokens;
        double temperature;
        double top_p;
        int n;
        boolean stream;
        String logprobs;
        String stop;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public int getMax_tokens() {
            return max_tokens;
        }

        public void setMax_tokens(int max_tokens) {
            this.max_tokens = max_tokens;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getTop_p() {
            return top_p;
        }

        public void setTop_p(double top_p) {
            this.top_p = top_p;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public boolean isStream() {
            return stream;
        }

        public void setStream(boolean stream) {
            this.stream = stream;
        }

        public String getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(String logprobs) {
            this.logprobs = logprobs;
        }

        public String getStop() {
            return stop;
        }

        public void setStop(String stop) {
            this.stop = stop;
        }
    }
}
