package com.example.training.themes.era;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * テーマ4: 元号変換 — CLI実行クラス
 *
 * ユーザーから日付（yyyy-MM-dd）を受け取り、
 * EraService で和暦に変換して表示する。
 */
public class EraApp {

    public static void run() {
        IO.println("=== 元号変換 ===");

        String input = IO.readln("日付を入力してください (yyyy-MM-dd 形式、空欄で今日): ");

        try {
            LocalDate date;
            if (input == null || input.isBlank()) {
                date = LocalDate.now();
            } else {
                date = LocalDate.parse(input.trim());
            }

            EraService service = new EraService();
            String result = service.toJapaneseEra(date);

            IO.println("西暦: " + date);
            IO.println("和暦: " + result);
        } catch (DateTimeParseException e) {
            IO.println("エラー: 日付の形式が正しくありません。yyyy-MM-dd で入力してください。");
        } catch (IllegalArgumentException e) {
            IO.println("エラー: " + e.getMessage());
        }
    }
}
