package com.ll.wiseSaying.repository;

import com.ll.wiseSaying.entity.WiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// 조수 : 요리사가 재료 갖고 오라하면 재료 줘야함
public class WiseSayingRepository  {

    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying findById(int id) {

        int foundedIndex = findIndexById(id);

        if (foundedIndex == -1) {
            return null;
        }

        return wiseSayings.get(foundedIndex);
    }

    private int findIndexById(int id) {
        return IntStream
                .range(0, wiseSayings.size())
                .filter((i) -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public List<WiseSaying> findList() {

        List<WiseSaying> foundedWiseSayings = new ArrayList<>();

        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            foundedWiseSayings.add(wiseSaying);
        }

        return foundedWiseSayings;
    }

    public boolean delete(int id) {
        return wiseSayings.removeIf((wiseSaying) -> wiseSaying.getId() == id);
    }

    public WiseSaying save(WiseSaying wiseSaying) {

        if(wiseSaying.getId() == 0) {
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
            wiseSaying.setCreateDate(LocalDateTime.now());
            wiseSaying.setModifyDate(LocalDateTime.now());
        }
        else {
            wiseSaying.setModifyDate(LocalDateTime.now());
        }

        return wiseSaying;
    }
}