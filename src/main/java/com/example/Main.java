package com.example;

import java.util.Optional;

import com.example.training.themes.bmi.BmiApp;
import com.example.training.themes.era.EraApp;
import com.example.training.themes.greeting.GreetingApp;
import com.example.training.themes.password.PasswordApp;
import com.example.training.themes.price.PriceApp;

/**
 * JUnit ハンズオン研修 — 統合CLIメニュー
 *
 * 各テーマの App クラスをメニューから呼び出します。
 * 実行: mvn exec:java
 */
public class Main {

    public static void main(String[] args) {
        while (true) {
            IO.println("");
            IO.println("===== JUnit ハンズオン研修 =====");
            IO.println("1. 挨拶生成 (Greeting)");
            IO.println("2. BMI計算 (BMI)");
            IO.println("3. パスワード強度判定 (Password)");
            IO.println("4. 元号変換 (Era)");
            IO.println("5. 金額計算 (Price)");
            IO.println("0. 終了");

            String choice = Optional.ofNullable(IO.readln("番号を入力してください: ")).orElse("");

            switch (choice) {
                case "1" -> GreetingApp.run();
                case "2" -> BmiApp.run();
                case "3" -> PasswordApp.run();
                case "4" -> EraApp.run();
                case "5" -> PriceApp.run();
                case "0" -> {
                    IO.println("終了します。お疲れさまでした！");
                    return;
                }
                default -> IO.println("無効な入力です。もう一度お試しください。");
            }
        }
    }
}
