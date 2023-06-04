//package com.example.TT.item.controller;
//
//import com.example.TT.item.dto.itemDto3;
//import com.example.TT.item.entity.itementity;
//import com.example.TT.item.service.Test1Service;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.Console;
//import java.io.PrintStream;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//@RestController
//@RequestMapping("/api/items")
//@Slf4j
//public class itemcontroller10 {
//
//    private final Test1Service itemService;
//
//   
//    public itemcontroller10(Test1Service itemService) {
//        this.itemService = itemService;
//    }
//    
//    
//  @PostMapping("/process-items")
//    public ResponseEntity<Object> handleJSONData(@RequestBody(required = false) itemDto3[] itemsList) {   	
//    	if (itemsList == null || itemsList.length == 0) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);        
//        }
//    	
//    	System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
//
//        if(itemsList.length >= 10) {
//            Map<String, Integer> categoryCountMap = new HashMap<>();
//            for (itemDto3 item : itemsList) {
//                String category = itemService.getCategoryByName(item.getName());
//                categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
//            }
//            String mostCommonCategory = Collections.max(categoryCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
//            return new ResponseEntity<>(mostCommonCategory, HttpStatus.OK);
//  
//        
//        }     
//        else {
//        		
////       최대 상세정보?
//        	
//        	
//// 매대인식 끝 10 미만일때 시작      	
//            List<itemDto3> top3Items = getTop3ItemsByConfidence(itemsList);
////            [itemDto3(name=라게보드 아일랜드 라거캔1), itemDto3( name=라게보드 아일랜드 라거캔2), itemDto3(name=아사히 캔3)]
//
//            
//            
////            System.out.println(top3Items);
//            List<String> itemLocations = new ArrayList<>();
//
//            
////           정확도 0번째 디테일가져오기
//            String itemDetail = itemService.getItemDetailByName(top3Items.get(0).getName());
//  
//            System.out.println(itemDetail);
////  환타다          
//            if (itemDetail == "") {
//                itemDetail = top3Items.get(0).getName();
//            }
//            
//            
//            itemDto3 highestConfidenceItem = top3Items.get(0);
//            
//            double xReference = highestConfidenceItem.getXmax();
//            double yReference = highestConfidenceItem.getYmax();
//            if (!top3Items.isEmpty()) {
//                top3Items.remove(0);
//            }
//            //            {"itemLocations":["오른쪽 아래쪽 (아사히 캔3)","오른쪽 위쪽 (라게보드 아일랜드 라거캔1)","왼 쪽 아래쪽 (라게보드 아일랜드 라거캔2)"],"itemDetail":"아사히 캔3"}
//
//            for (itemDto3 item : top3Items) {
//                double x = item.getXmax();
//                double y = item.getYmax();
//                String itemName = item.getName();
//
//                if (x > xReference && y < yReference) {
//                    itemLocations.add("오른쪽 아래쪽 (" + itemName + ")");
//                } else if (x > xReference && y > yReference) {
//                    itemLocations.add("오른쪽 위쪽 (" + itemName + ")");
//                } else if (x < xReference && y < yReference) {
//                    itemLocations.add("왼쪽 아래쪽 (" + itemName + ")");
//                } else if (x < xReference && y > yReference) {
//                    itemLocations.add("왼쪽 위쪽 (" + itemName + ")");
//                }
//            }
//
//            Map<Object, Object> result = new HashMap<>();
//            result.put("itemDetail", itemDetail);
//            result.put("itemLocations", itemLocations);
//
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//    }
//
//
//    private List<itemDto3> getTop3ItemsByConfidence(itemDto3[] itemsList) {
//        List<itemDto3> top3Items = new ArrayList<>();
//        Set<itemDto3> selectedItems = new HashSet<>();
//
//        for (int i = 0; i < 3 && i < itemsList.length; i++) {
//            itemDto3 maxConfidenceItem = null;
//
//            for (itemDto3 item : itemsList) {
//                if (!selectedItems.contains(item) && (maxConfidenceItem == null || item.getConfidence() > maxConfidenceItem.getConfidence())) {
//                    maxConfidenceItem = item;
//                }
//            }
//
//            if (maxConfidenceItem != null) {
//                top3Items.add(maxConfidenceItem);
//                selectedItems.add(maxConfidenceItem);
//            }
//        }
//        System.out.println(top3Items);
//        return top3Items;
//    }
//
//
//
//    
//    
////    private itemDto3[] removeItem(itemDto3[] itemsList, itemDto3 itemToRemove) {
////        List<itemDto3> itemList = new ArrayList<>();
////        for (itemDto3 item : itemsList) {
////            if (!item.equals(itemToRemove)) {
////                itemList.add(item);
////            }
////        }       
////        return itemList.toArray(new itemDto3[0]);
////    }
//}
