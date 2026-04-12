package com.example.training.themes.bmi;

import java.util.Optional;

/**
 * テーマ2: BMI計算 — CLI実行クラス
 *
 * ユーザーから身長・体重を受け取り、BmiService で BMI を計算して表示する。
 */
public class BmiApp {

    public static void run() {
        IO.println("=== BMI計算 ===");

        try {
            String heightStr = Optional.ofNullable(IO.readln("身長(cm)を入力してください: ")).orElse("");
            String weightStr = Optional.ofNullable(IO.readln("体重(kg)を入力してください: ")).orElse("");

            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            BmiService service = new BmiService();
            double bmi = service.calculate(height, weight);

            IO.println(String.format("あなたのBMI: %.2f", bmi));

            // 判定結果の表示
            if (bmi < 18.5) {
                IO.println("判定: 低体重（やせ型）");
            } else if (bmi < 25.0) {
                IO.println("判定: 普通体重");
            } else if (bmi < 30.0) {
                IO.println("判定: 肥満（1度）");
            } else {
                IO.println("判定: 肥満（2度以上）");
            }
        } catch (NumberFormatException e) {
            IO.println("エラー: 数値を入力してください。");
        } catch (IllegalArgumentException e) {
            IO.println("エラー: " + e.getMessage());
        }
    }
}
