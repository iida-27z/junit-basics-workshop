package com.example.training.themes.password;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * PasswordService のテストクラス
 *
 * 【学習目標】
 * - BeforeEach を用いたテスト前の共通処理
 * - 正常系と異常系のテストケース設計
 * - 同値分割と境界値分析を用いたテストケース設計
 * - 複数の条件分岐を網羅するテストケース設計
 */
class PasswordServiceTest {

    private PasswordService service;

    @BeforeEach
    void setUp() {
        service = new PasswordService();
    }

    // ================================================================
    // ✅ 実装例
    // ================================================================

    @Test
    void 正常系_ちょうど八文字で全文字種を含むパスワードは強い() {
        // arrange
        String password = "Ab1!xyzC"; // BeforeEach で service を初期化しているので、ここでは password のみ準備

        // act
        boolean result = service.isStrong(password);

        // assert
        assertTrue(result);
    }

    // ================================================================
    // 📝 以下のテストを自分で実装してください
    // ================================================================

    @Test
    void 異常系_パスワードが空文字の場合は例外が投げられる() {
        // arrange
        String password = "";

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.isStrong(password));
    }

    @Test
    void 異常系_パスワードがスペースを含む場合は例外が投げられる() {
        // arrange
        String password = "Ab1! xyz";

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.isStrong(password));
    }

    @Test
    void 異常系_パスワードがnullの場合は例外が投げられる() {
        // arrange
        String password = null;

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.isStrong(password));
    }

    @Test
    void 異常系_パスワードが制御文字を含む場合は例外が投げられる() {
        // arrange
        String password = "Ab1!\bxyz";

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.isStrong(password));
    }

    @Test
    void 異常系_パスワードが7文字以下なら弱い() {
        // arrange
        String password = "Ab1!xyz";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_英小文字が含まれないパスワードは弱い() {
        // arrange
        String password = "AB1!XYZ@";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_英大文字が含まれないパスワードは弱い() {
        // arrange
        String password = "ab1!xyz@";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_数字が含まれないパスワードは弱い() {
        // arrange
        String password = "Abc!xyZ@";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_記号が含まれないパスワードは弱い() {
        // arrange
        String password = "Ab1xyzCD";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_英小文字のみのパスワードは弱い() {
        // arrange
        String password = "abcdefgh";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_英大文字のみのパスワードは弱い() {
        // arrange
        String password = "ABCDEFGH";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_数字のみのパスワードは弱い() {
        // arrange
        String password = "12345678";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

    @Test
    void 異常系_記号のみのパスワードは弱い() {
        // arrange
        String password = "!@#$%^&*";

        // act
        boolean result = service.isStrong(password);

        // assert
        assertFalse(result);
    }

}
