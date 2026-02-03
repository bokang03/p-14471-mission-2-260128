package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner sc = new Scanner(System.in);
    int lastId = 0;

    WiseSaying[] wiseSayings = new WiseSaying[10];
    int lastWiseSayingIndex = -1;

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            }
        }
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        List<WiseSaying> wiseSayingList = findList();

        for(WiseSaying wiseSaying : wiseSayingList) {
            System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    private List<WiseSaying> findList() {

        List<WiseSaying> wiseSayingList = new ArrayList<>();

        for (int i = lastWiseSayingIndex; i >= 0; i--) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            wiseSayingList.add(foundedWiseSaying);
        }

        return wiseSayingList;
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        write(content, author);
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

    private void write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);

        wiseSayings[++lastWiseSayingIndex] = wiseSaying;
    }
}