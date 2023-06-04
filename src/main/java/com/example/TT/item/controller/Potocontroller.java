package com.example.TT.item.controller;

import com.example.TT.Gpt.Service.GPT3Service;
import com.example.TT.item.dto.itemDto3;
import com.example.TT.item.entity.itementity;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@Slf4j
@RestController
public class Potocontroller {
	private final Test1Service test1Service;
	private final GPT3Service gpt3Service;

	public Potocontroller(Test1Service test1Service, GPT3Service gpt3Service) {
		this.test1Service = test1Service;
		this.gpt3Service = gpt3Service;
	}

	@PostMapping(value = "/api/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> handleJSONData(@RequestBody(required = false) itemDto3[] itemsList) {
		System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
		if (itemsList == null || itemsList.length == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

		if (itemsList.length >= 10) {
			Map<String, Integer> categoryCountMap = new HashMap<>();
			for (itemDto3 item : itemsList) {
				String category = test1Service.getCategoryByName(item.getName());
				categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
			}
			String mostCommonCategory = Collections.max(categoryCountMap.entrySet(), Map.Entry.comparingByValue())
					.getKey();

			return new ResponseEntity<>(mostCommonCategory + " 매대 입니다", HttpStatus.OK);
		} else {

			List<itemDto3> top3Items = getTop3ItemsByConfidence(itemsList);

//            String itemDetail = test1Service.getItemDetailByName(top3Items.get(0).getName());
			itemDto3 highestConfidenceItem = top3Items.get(0);

			if (highestConfidenceItem == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Optional<itementity> dbItemOpt = test1Service.findByName(highestConfidenceItem.getName());
			if (!dbItemOpt.isPresent()) {
				dbItemOpt = test1Service.findByName(top3Items.get(0).getName());
			}

			if (dbItemOpt.isEmpty()) {
				return new ResponseEntity<>(highestConfidenceItem.getName(), HttpStatus.OK);
			}
			String generatedSentence = "";
			if (highestConfidenceItem.getFilter() == 15) {
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 14) {
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 13) {
				dbItemOpt.get().setAllegori(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 12) {
				dbItemOpt.get().setAllegori(null);
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 11) {
				dbItemOpt.get().setShape(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 10) {
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 9) {
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setAllegori(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 8) {
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setAllegori(null);
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 7) {
				dbItemOpt.get().setMake(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 6) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setItemDetail(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 5) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setAllegori(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 4) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setItemDetail(null);
				dbItemOpt.get().setAllegori(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 3) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setShape(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 2) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setItemDetail(null);
				dbItemOpt.get().setShape(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter() == 1) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setAllegori(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			}else if(highestConfidenceItem.getFilter() == 0) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setItemDetail(null);
				dbItemOpt.get().setAllegori(null);
				dbItemOpt.get().setShape(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
	        }

			List<String> itemLocations = new ArrayList<>();

			double xReference = highestConfidenceItem.getXmax();
			double yReference = highestConfidenceItem.getYmax();

			if (!top3Items.isEmpty()) {
				top3Items.remove(0);
			}

			for (itemDto3 item : top3Items) {
				double x = item.getXmax();
				double y = item.getYmax();
				String itemName = item.getName();

				if (x > xReference && y < yReference) {
					itemLocations.add("오른쪽 아래쪽 " + itemName + "");
				} else if (x > xReference && y > yReference) {
					itemLocations.add("오른쪽 위쪽 " + itemName + "");
				} else if (x < xReference && y < yReference) {
					itemLocations.add("왼쪽 아래쪽 " + itemName + "");
				} else if (x < xReference && y > yReference) {
					itemLocations.add("왼쪽 위쪽 " + itemName + "");
				} else if (x < xReference && y == yReference) {
					itemLocations.add("수평에서 왼쪽에있습니다. " + itemName + "");
				} else if (x > xReference && y == yReference) {
					itemLocations.add("수평에서 오른쪽에있습니다. " + itemName + "");
				} else if (x == xReference && y < yReference) {
					itemLocations.add("바로 아래쪽에있습니다. " + itemName + "");
				} else if (x == xReference && y > yReference) {
					itemLocations.add("바로 위쪽에있습니다. " + itemName + "");
				}
			}

			return new ResponseEntity<>(generatedSentence + itemLocations, HttpStatus.OK);
		}
	}

	public String ToGPT3(itementity test1) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		GPT3Prompt prompt = new GPT3Prompt();
		prompt.setModel("text-davinci-003");
		prompt.setPrompt(String.format(
				"다음 정보를 이용하여 존댓말로 문장을 완성해주세요. 맛에 대한 정보가 중복될 경우 한번만 언급하세요."
						+ "이름: %s, 카테고리: %s, 맛:%s, 알레르기: %s, 모양:%s, %s",
				test1.getName(), test1.getCategory(), test1.getItemDetail(), test1.getAllegori(), test1.getShape(),
				test1.getMake()));
		prompt.setMax_tokens(500);
		prompt.setTemperature(0.0);
		prompt.setTop_p(1.0);
		prompt.setN(1);
		prompt.setStream(false);
		prompt.setLogprobs(null);
		prompt.setStop("<|endoftext|>");
		String jsonInput = "";
		jsonInput = gson.toJson(prompt);
		System.out.println("jsonInput: " + jsonInput);

		try {
			String generatedText = gpt3Service.processRequest(jsonInput);
			return generatedText;
		} catch (Exception e) {
			String noinput = String.format("제품명" + "%s, %s, %s, %s, %s, %s", test1.getName(), test1.getCategory(),
					test1.getItemDetail(), test1.getAllegori(), test1.getShape(), test1.getMake());
			return noinput;
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

	private List<itemDto3> getTop3ItemsByConfidence(itemDto3[] itemsList) {
		List<itemDto3> top3Items = new ArrayList<>();
		Set<itemDto3> selectedItems = new HashSet<>();

		for (int i = 0; i < 3 && i < itemsList.length; i++) {
			itemDto3 maxConfidenceItem = null;

			for (itemDto3 item : itemsList) {
				if (!selectedItems.contains(item)
						&& (maxConfidenceItem == null || item.getConfidence() > maxConfidenceItem.getConfidence())) {
					maxConfidenceItem = item;
				}
			}

			if (maxConfidenceItem != null) {
				top3Items.add(maxConfidenceItem);
				selectedItems.add(maxConfidenceItem);
			}
		}
		System.out.println(top3Items);
		return top3Items;
	}

}
