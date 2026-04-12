package com.example.training.themes.era;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * EraService のテストクラス
 *
 * 【学習目標】
 * - @ParameterizedTest と @CsvSource を使った効率的なテスト
 * - テストコードを介して仕様や実装と対話することで、品質の高いプログラムを作る
 */
class EraServiceTest {

    private final EraService service = new EraService();

    // ================================================================
    // ✅ 実装例
    // ================================================================

    @ParameterizedTest
    @ValueSource(strings = { "1868-01-24", "1000-01-01" })
    void 明治以前の日付は例外が投げられる(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        assertThrows(IllegalArgumentException.class, () -> {
            service.toJapaneseEra(date);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "2015-04-01, 平成27年04月01日",
            "2025-04-01, 令和7年04月01日",
    })
    void 和暦に正しく変換される(String dateStr, String expected) {
        LocalDate date = LocalDate.parse(dateStr);
        assertEquals(expected, service.toJapaneseEra(date));
    }

    // ================================================================
    // 📝 以下のテストを自分で実装してください
    // ================================================================

    // もしも他に追加すべきテストケースや考慮漏れの仕様があれば、
    // 適宜にテストの追加やテスト対象の修正を行ってください。

    @ParameterizedTest
    @CsvSource({
            // TODO: 各元号の境界値を追加してください
            // ヒント:
            // 明治開始: 1868-01-25
            // 大正開始: 1912-07-30
            // 昭和開始: 1926-12-25
            // 平成開始: 1989-01-08
            // 令和開始: 2019-05-01
            //
            // 例:
            "9999-12-31, XX99年12月31日"
    })
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 各元号の開始日が正しく変換される(String dateStr, String expected) {
        // TODO: 引数で渡された各元号の開始日をテストしてください。
    }

    // @ParameterizedTest
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 各元号の終了日が正しく変換される() {
        // TODO: @ParameterizedTestのコメントアウトを外して、テストを追加。
    }

    // @ParameterizedTest
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 未来の日付は最新の元号で変換される() {
        // TODO: @ParameterizedTestのコメントアウトを外して、テストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void nullの場合は例外が投げられる() {
        // TODO: ここにテストを追加。
    }

}
