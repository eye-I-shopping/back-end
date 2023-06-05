package com.example.TT.item.controller;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TT.Gpt.Service.GPT3Service;
import com.example.TT.item.dto.itemDto3;
import com.example.TT.item.dto.GPT3PromptDto;

import com.example.TT.item.entity.itementity;
import com.example.TT.item.service.Test1Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

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
	
	System.out.println("1번쟤"+itemsList);	
		
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
			if (highestConfidenceItem.getFilter().equals("15") ) {
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("14")) {
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("13")) {
				dbItemOpt.get().setAllegori(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("12")) {
				dbItemOpt.get().setAllegori(null);
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("11")) {
				dbItemOpt.get().setShape(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("10")) {
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("9")) {
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setAllegori(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("8")) {
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setAllegori(null);
				dbItemOpt.get().setItemDetail(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("7")) {
				dbItemOpt.get().setMake(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("6")) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setItemDetail(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("5")) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setAllegori(null);
				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("4")) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setItemDetail(null);
				dbItemOpt.get().setAllegori(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("3")) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setShape(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("2")) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setItemDetail(null);
				dbItemOpt.get().setShape(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			} else if (highestConfidenceItem.getFilter().equals("1")) {
				dbItemOpt.get().setMake(null);
				dbItemOpt.get().setShape(null);
				dbItemOpt.get().setAllegori(null);

				generatedSentence = ToGPT3(dbItemOpt.get());
			}else if(highestConfidenceItem.getFilter().equals("0")) {
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
					itemLocations.add("오른쪽 영역 하단 에  " + itemName + "이 있습니다.");
				} else if (x > xReference && y > yReference) {
					itemLocations.add("오른쪽 영역 상단 에 " + itemName + "이 있습니다.");
				} else if (x < xReference && y < yReference) {
					itemLocations.add("왼쪽 영역 하단 에 " + itemName + "이 있습니다.");
				} else if (x < xReference && y > yReference) {
					itemLocations.add("왼쪽 영역 상단 에 " + itemName + "");
				} else if (x < xReference && y == yReference) {
					itemLocations.add(itemName+"이 수평에서 왼쪽 영역에 있습니다. " );
				} else if (x > xReference && y == yReference) {
					itemLocations.add(itemName+ "이 수평에서 오른쪽 영역에 있습니다. ");
				} else if (x == xReference && y < yReference) {
					itemLocations.add(itemName +"바로 아래쪽에있습니다. ");
				} else if (x == xReference && y > yReference) {
					itemLocations.add( itemName + "은 바로 위쪽에있습니다. ");
				}
				else if (x == xReference && y == yReference) {
					itemLocations.add(itemName + "은 같은위치에있습니다. ");
				}
				
			}
			
//				String p ="";
//				String s = "";
//				if(itemLocations!=null) {
//				s = itemLocations.get(0).concat(itemLocations.get(1));
//				}
//				if(s!=null) {
//				p = generatedSentence.concat(s);
//				}else {
//					p = generatedSentence;
//				}
//				
//				System.out.println(p);
			return new ResponseEntity<>(generatedSentence , HttpStatus.OK);
		}
	}

	public String ToGPT3(itementity test1) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

        GPT3PromptDto prompt = new GPT3PromptDto();
		prompt.setModel("text-davinci-003");
		prompt.setPrompt(String.format(
				"다음 정보를 순서대로 이용하여 존댓말로 설명문을 완성해주세요. 맛에 대한 정보가 중복될 경우 한번만 언급하세요."
						+ "이름: %s, 카테고리: %s, 맛:%s, 알레르기: %s, %s, %s",
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
