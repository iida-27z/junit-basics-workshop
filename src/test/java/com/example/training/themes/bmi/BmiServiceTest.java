package com.example.training.themes.bmi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * BmiService のテストクラス
 *
 * 【学習目標】
 * - 浮動小数点の誤差を考慮した assertEquals(expected, actual, delta)
 * - assertThrows を用いた例外テスト
 */
class BmiServiceTest {

    // ================================================================
    // ✅ 実装例
    // ================================================================

    @Test
    void 正常な身長と体重でBMIが計算される() {
        // Arrange
        BmiService service = new BmiService();
        double height = 170.0;
        double weight = 65.0;

        // Act
        double bmi = service.calculate(height, weight);
        
        // Assert
        assertEquals(22.49, bmi, 0.01); // delta（許容誤差）を指定して浮動小数点を比較する
    }

    @Test
    void 身長が0の場合は例外が投げられる() {
        // Arrange
        BmiService service = new BmiService();
        double height = 0.0;
        double weight = 65.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(height, weight);
        }); // assertThrowsは第1引数に想定される例外クラス、第2引数に実行させたい処理をラムダ式で渡す
    }

    // ================================================================
    // 📝 以下のテストを自分で実装してください
    // ================================================================

    @Test
    void 体重が0以下の場合は例外が投げられる() {
        // Arrange
        BmiService service = new BmiService();
        double height = 170.0;
        double weight = 0.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(height, weight);
        });
    }

    @Test
    void 身長が負の値の場合は例外が投げられる() {
        // Arrange
        BmiService service = new BmiService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(-170.0, 65.0);
        });
    }

    @Test
    void 体重が負の値の場合は例外が投げられる() {
        // Arrange
        BmiService service = new BmiService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(170.0, -65.0);
        });
    }

    @Test
    void 身長150cm体重50kgの場合() {
        // Arrange
        BmiService service = new BmiService();
        double height = 150.0;
        double weight = 50.0;

        // Act
        double bmi = service.calculate(height, weight);

        // Assert
        assertEquals(22.22, bmi, 0.01);
    }

    @Test
    void 身長180cm体重100kgの場合() {
        // Arrange
        BmiService service = new BmiService();
        double height = 180.0;
        double weight = 100.0;

        // Act
        double bmi = service.calculate(height, weight);

        // Assert
        assertEquals(30.86, bmi, 0.01);
    }
}
