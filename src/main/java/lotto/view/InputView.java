package lotto.view;

import lotto.domain.Money;
import lotto.model.LottoInformation;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern NON_NUMERIC_PATTERN = Pattern.compile(".*[ㄱ-ㅎㅏ-ㅣ가-힣|a-z|A-Z]+.*");

    public static final Scanner scanner = new Scanner(System.in);

    public static Money inputPrice() {
        System.out.println("구입금액을 입력해 주세요 :)");
        int price = scanner.nextInt();
        validateNegativeNumberOrZero(price);

        return Money.wons(price);
    }

    private static void validateNegativeNumberOrZero(long amount) {
        if (amount < LottoInformation.LOTTO_UNIT_PRICE) {
            throw new IllegalArgumentException("1000원 이상 입력해주세 :)");
        }
    }

    public static List<String> inputWinningNumbers() {
        scanner.nextLine();
        System.out.println("지난 주 당첨 번호를 압력해주세요 :)");
        String inputWinnerLottoNumber = scanner.nextLine();
        validateEmptyString(inputWinnerLottoNumber);
        validateNonNumeric(inputWinnerLottoNumber);

        return Collections.singletonList(inputWinnerLottoNumber);
    }

    public static int inputBonusNumber(List<String> winnerLottoNumbers) {
        System.out.println("보너스 볼을 입력해주세요 :)");
        String bonusNumber = scanner.next();
        validateEmptyString(bonusNumber);
        validateNonNumeric(bonusNumber);
        validateExistSameNumber(winnerLottoNumbers,bonusNumber);

        return Integer.parseInt(bonusNumber);
    }

    private static void validateEmptyString(String inputWinnerLottoNumber) {
        if (inputWinnerLottoNumber == null) {
            throw new IllegalArgumentException("입력 값이 비었어요  :(");
        }
        if (inputWinnerLottoNumber.length() == 0) {
            throw new IllegalArgumentException("입력 값이 비었어요  :(");
        }
    }

    private static void validateNonNumeric(String inputWinnerLottoNumber) {
        if (NON_NUMERIC_PATTERN.matcher(inputWinnerLottoNumber).find()) {
            throw new IllegalArgumentException("숫자만 입력 가능하세요 :(");
        }
    }

    private static void validateExistSameNumber(List<String> lottoNumber,String bonusNumber){
        if(lottoNumber.contains(bonusNumber)){
            throw new IllegalArgumentException("로또번호에 존재해요 :(");
        }
    }
}
