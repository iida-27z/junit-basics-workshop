package com.example.training.themes.bmi;

/**
 * テーマ2: BMI計算サービス
 *
 * 身長(cm)と体重(kg)から BMI を計算する純粋なロジッククラス。
 * BMI = 体重(kg) / (身長(m))^2
 */
public class BmiService {

    /**
     * BMI を計算する。
     *
     * @param heightCm 身長（cm）。0以下の場合は例外。
     * @param weightKg 体重（kg）。0以下の場合は例外。
     * @return 計算された BMI 値
     * @throws IllegalArgumentException 身長または体重が 0 以下の場合
     */
    public double calculate(double heightCm, double weightKg) {
        if (heightCm <= 0) {
            throw new IllegalArgumentException("身長は0より大きい値を入力してください");
        }
        if (weightKg <= 0) {
            throw new IllegalArgumentException("体重は0より大きい値を入力してください");
        }

        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }
}
