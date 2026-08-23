package com.example.training.themes.era;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

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
            "1868-01-25, 明治元年01月25日",
            "1912-07-30, 大正元年07月30日",
            "1926-12-25, 昭和元年12月25日",
            "1989-01-08, 平成元年01月08日",
            "2019-05-01, 令和元年05月01日"
    })
    void 各元号の開始日が正しく変換される(String dateStr, String expected) {
        LocalDate date = LocalDate.parse(dateStr);
        assertEquals(expected, service.toJapaneseEra(date));
    }

    @ParameterizedTest
    @CsvSource({
            "1912-07-29, 明治45年07月29日",
            "1926-12-24, 大正15年12月24日",
            "1989-01-07, 昭和64年01月07日",
            "2019-04-30, 平成31年04月30日"
    })
    void 各元号の終了日が正しく変換される(String dateStr, String expected) {
        LocalDate date = LocalDate.parse(dateStr);
        assertEquals(expected, service.toJapaneseEra(date));
    }

    @ParameterizedTest
    @CsvSource({
            "1868-12-31, 明治元年12月31日",
            "1912-12-31, 大正元年12月31日",
            "1926-12-31, 昭和元年12月31日",
            "1989-12-31, 平成元年12月31日",
            "2019-12-31, 令和元年12月31日"
    })
    void 各元号の元年の最終日が正しく変換される(String dateStr, String expected) {
        LocalDate date = LocalDate.parse(dateStr);
        assertEquals(expected, service.toJapaneseEra(date));
    }

    @ParameterizedTest
    @CsvSource({
            "1869-01-01, 明治2年01月01日",
            "1913-01-01, 大正2年01月01日",
            "1927-01-01, 昭和2年01月01日",
            "1990-01-01, 平成2年01月01日",
            "2020-01-01, 令和2年01月01日"
    })
    void 各元号の元年の最終日の翌日が正しく変換される(String dateStr, String expected) {
        LocalDate date = LocalDate.parse(dateStr);
        assertEquals(expected, service.toJapaneseEra(date));
    }

    @ParameterizedTest
    @CsvSource({
            "2050-01-01, 令和32年01月01日",
            "2100-12-31, 令和82年12月31日"
    })
    void 未来の日付は最新の元号で変換される(String dateStr, String expected) {
        LocalDate date = LocalDate.parse(dateStr);
        assertEquals(expected, service.toJapaneseEra(date));
    }

    @ParameterizedTest
    @CsvSource({
            "1896-02-29, 明治29年02月29日",
            "2000-02-29, 平成12年02月29日",
            "2024-02-29, 令和6年02月29日"
    })
    void うるう日も正しく変換される(String dateStr, String expected) {
        LocalDate date = LocalDate.parse(dateStr);
        assertEquals(expected, service.toJapaneseEra(date));
    }

    @Test
    void nullの場合は例外が投げられる() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.toJapaneseEra(null);
        });
    }

}
