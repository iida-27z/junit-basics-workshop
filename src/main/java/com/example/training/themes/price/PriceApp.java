package com.example.training.themes.price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * テーマ5: 金額計算 — CLI実行クラス
 *
 * ユーザーから金額と操作を受け取り、MoneyService で計算して表示する。
 */
public class PriceApp {

    public static void run() {
        IO.println("=== 金額計算 ===");
        IO.println("1. 加算");
        IO.println("2. 減算");
        IO.println("3. 乗算（税率計算など）");
        IO.println("4. 除算（割り勘など）");
        IO.println("5. 分配");

        String choice = IO.readln("操作を選択してください: ");
        MoneyService service = new MoneyService();

        try {
            switch (choice) {
                case "1" -> {
                    Money a = readJpy("金額1");
                    Money b = readJpy("金額2");
                    IO.println("結果: " + service.add(a, b));
                }
                case "2" -> {
                    Money a = readJpy("金額1");
                    Money b = readJpy("金額2");
                    IO.println("結果: " + service.subtract(a, b));
                }
                case "3" -> {
                    Money m = readJpy("金額");
                    String factorStr = IO.readln("倍率を入力してください（例: 1.10 で消費税10%込み）: ");
                    BigDecimal factor = new BigDecimal(factorStr);
                    Money multiplied = service.multiply(m, factor);
                    IO.println("結果: " + service.round(multiplied, RoundingMode.HALF_DOWN));
                }
                case "4" -> {
                    Money m = readJpy("金額");
                    String divisorStr = IO.readln("除数を入力してください: ");
                    BigDecimal divisor = new BigDecimal(divisorStr);
                    Money divided = service.divide(m, divisor);
                    IO.println("結果: " + service.round(divided, RoundingMode.HALF_DOWN));
                }
                case "5" -> {
                    Money m = readJpy("合計金額");
                    String partsStr = IO.readln("分配人数を入力してください: ");
                    int parts = Integer.parseInt(partsStr);
                    List<Money> distributed = service.distribute(m, parts);
                    for (int i = 0; i < distributed.size(); i++) {
                        IO.println("  " + (i + 1) + "人目: " + distributed.get(i));
                    }
                }
                default -> IO.println("無効な操作です。");
            }
        } catch (NumberFormatException e) {
            IO.println("エラー: 数値の形式が正しくありません。");
        } catch (ArithmeticException e) {
            IO.println("エラー: 不正な計算が発生しました。");
        } catch (IllegalArgumentException | NullPointerException e) {
            IO.println("エラー: 不正な入力がありました。");
        }
    }

    private static Money readJpy(String label) {
        String input = IO.readln(label + "(円)を入力してください: ");
        return Money.jpy(input.trim());
    }
}
