package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

public class App {


    Scanner sc = new Scanner(System.in);
    ArrayList<WiseSaying> wiseSayings = new ArrayList<>();
    int id = 0;

    public void run(){;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if(cmd.equals("종료")) {
                break;
            }
            else if (cmd.equals("등록")) {
                actionWrite();
            }
            else if(cmd.equals("목록")){
                actionList();
            }
        }
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i=wiseSayings.size()-1; i >= 0; i--){
            System.out.println(wiseSayings.get(i).id + " / " + wiseSayings.get(i).author + " / " + wiseSayings.get(i).content);
        }
    }

    private void actionWrite() {

        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        write(content, author);

        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    private void write(String content, String author){
        ++id;
        wiseSayings.add(new WiseSaying(id, content, author));
    }
}
