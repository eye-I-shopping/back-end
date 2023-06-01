package com.example.TT.item.controller;

import com.example.TT.item.dto.itemDto3;
import com.example.TT.item.entity.itementity;
import com.example.TT.item.service.Test1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/items")
@Slf4j
public class itemcontroller10 {

    private final Test1Service itemService;

    public itemcontroller10(Test1Service itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/process-items")
    public ResponseEntity<Object> handleJSONData(@RequestBody(required = false) itemDto3[] itemsList) {
        if (itemsList == null || itemsList.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(itemsList.length >= 10) {
            Map<String, Integer> categoryCountMap = new HashMap<>();
            for (itemDto3 item : itemsList) {
                String category = itemService.getCategoryByName(item.getName());
                categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
            }
            String mostCommonCategory = Collections.max(categoryCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
            return new ResponseEntity<>(mostCommonCategory, HttpStatus.OK);
        } else {
            List<itemDto3> top3Items = getTop3ItemsByConfidence(itemsList);
            double xMax = top3Items.get(0).getXmax();
            double yMax = top3Items.get(0).getYmax();
            List<String> itemLocations = new ArrayList<>();

            String itemDetail = itemService.getItemDetailByName(top3Items.get(0).getName());
            if (itemDetail == null) {
                itemDetail = top3Items.get(0).getName();
            }

            double xSum = 0.0, ySum = 0.0;
            for (itemDto3 item : top3Items) {
                xSum += item.getXmax();
                ySum += item.getYmax();
            }
            double xAvg = xSum / top3Items.size();
            double yAvg = ySum / top3Items.size();

            for (itemDto3 item : top3Items) {
                double x = item.getXmax();
                double y = item.getYmax();

                String itemName = item.getName();

                if (x > xAvg && y < yAvg) {
                    itemLocations.add("오른쪽 아래쪽 (" + itemName + ")");
                } else if (x > xAvg && y > yAvg) {
                    itemLocations.add("오른쪽 위쪽 (" + itemName + ")");
                } else if (x < xAvg && y < yAvg) {
                    itemLocations.add("왼쪽 아래쪽 (" + itemName + ")");
                } else if (x < xAvg && y > yAvg) {
                    itemLocations.add("왼쪽 위쪽 (" + itemName + ")");
                }
            }
            Map<String, Object> result = new HashMap<>();
            result.put("itemDetail", itemDetail);
            result.put("itemLocations", itemLocations);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }


    private List<itemDto3> getTop3ItemsByConfidence(itemDto3[] itemsList) {
        List<itemDto3> top3Items = new ArrayList<>();

        for (int i = 0; i < 3 && i < itemsList.length; i++) {
            itemDto3 maxConfidenceItem = itemsList[0];

            for (int j = 1; j < itemsList.length; j++) {
                if (itemsList[j].getConfidence() > maxConfidenceItem.getConfidence()) {
                    maxConfidenceItem = itemsList[j];
                }
            }

            top3Items.add(maxConfidenceItem);
            itemsList = removeItem(itemsList, maxConfidenceItem);
        }

        return top3Items;
    }

    private itemDto3[] removeItem(itemDto3[] itemsList, itemDto3 itemToRemove) {
        List<itemDto3> itemList = new ArrayList<>();
        for (itemDto3 item : itemsList) {
            if (!item.equals(itemToRemove)) {
                itemList.add(item);
            }
        }
        return itemList.toArray(new itemDto3[0]);
    }
}
