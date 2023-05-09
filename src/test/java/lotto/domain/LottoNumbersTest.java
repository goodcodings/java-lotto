package lotto.domain;

import lotto.model.request.ReqManualLotto;
import lotto.service.gernerator.ManualLottoNumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoNumbersTest {

    private final ManualLottoNumbersGenerator manualLottoNumbersGenerator = new ManualLottoNumbersGenerator();

    @Test
    @DisplayName("로또번호 그룹 생성 테스트")
    void generateLottoNumbers() {
        LottoNumbers lottoNumbers = manualLottoNumbersGenerator.generateLottoNumbers(new ReqManualLotto(List.of("1, 2, 3, 4, 5, 6")));
        assertThat(lottoNumbers).isNotNull();

    }


    @Test
    @DisplayName("로또번호가 없으면 예외를 던진다")
    void throwExceptionLottoNumbers() {
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new LottoNumbers(null))
                        .withMessage("로또번호가 입력되지 않았어요 :("),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new LottoNumbers(List.of()))
                        .withMessage("로또번호가 입력되지 않았어요 :(")
        );
    }

}