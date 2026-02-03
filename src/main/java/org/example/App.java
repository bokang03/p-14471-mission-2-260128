package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc = new Scanner(System.in);
    private int lastId = 0;

    private WiseSaying[] wiseSayings = new WiseSaying[10];
    private int lastWiseSayingIndex = -1;

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
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
            }
        }
    }

    private void actionModify(String cmd) {
        String idStr = cmd.split("=")[1];
        int id = Integer.parseInt(idStr);

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("명언(기존) : " + wiseSaying.getContent());
        System.out.print("명언 : ");
        wiseSaying.setContent(sc.nextLine());
        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        wiseSaying.setAuthor(sc.nextLine());
    }

    private WiseSaying findById(int modifyTarget) {
        int foundIndex = -1;

        foundIndex = findIndexById(modifyTarget);
        WiseSaying wiseSaying = wiseSayings[foundIndex];
        return wiseSaying;
    }


    private int findIndexById(int id){
        for (int i = 0; i <= lastWiseSayingIndex; i++){
            WiseSaying foundWisaying = wiseSayings[i];
            if(id == foundWisaying.getId()) {
                return i;
            }
        }
        return -1;
    }

    private void actionDelete(String cmd) {
        String idStr = cmd.split("=")[1];
        int id = Integer.parseInt(idStr);

        boolean rst = delete(id);
        if (rst == false) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private boolean delete(int deleteTarget) {

        int foundIndex = findIndexById(deleteTarget);

        if (foundIndex == -1) return false;

        for (int i = foundIndex; i < lastWiseSayingIndex; i++) {
            wiseSayings[i] = wiseSayings[i + 1];
        }

        lastWiseSayingIndex--;
        return true;
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        List<WiseSaying> wiseSayingList = findList();

        for(WiseSaying wiseSaying : wiseSayingList) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
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
        System.out.println("%d번 명언이 등록되었습니다.".formatted(lastId));
    }

    private void write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);

        wiseSayings[++lastWiseSayingIndex] = wiseSaying;
    }
}