package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");

        baseball();

    }

    public static void baseball() {
        boolean flag = true;
        String answer = selectRandomNumber();
        while (flag) {
            System.out.print("숫자를 입력해주세요 : ");
            String input = Console.readLine();

            if (!input.matches("^[1-9]{3}$")) {
                throw new IllegalArgumentException();
            }

            String analyzeResult = analyzeInput(input, answer);
        }
    }

    public static String selectRandomNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            while (sb.toString().contains(Integer.toString(randomNumber))) {
                randomNumber = Randoms.pickNumberInRange(1, 9);
            }
            sb.append(randomNumber);
        }

        return sb.toString();
    }

    public static String analyzeInput(String input, String answer) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == answer.charAt(i)) {
                strike++;
            } else if (answer.contains(String.valueOf(input.charAt(i)))) {
                ball++;
            }
        }
        if (strike == 0 && ball == 0) {
            return "낫싱";
        } else if (strike == 0 && ball != 0) {
            return String.format("%d볼", ball);
        } else if (ball == 0 && strike != 0) {
            return String.format("%d스트라이크", strike);
        } else {
            return String.format("%d볼 %d스트라이크", ball, strike);
        }
    }

}
