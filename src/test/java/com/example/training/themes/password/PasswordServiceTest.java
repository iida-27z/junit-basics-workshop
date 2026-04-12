package com.example.training.themes.password;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_パスワードが空文字の場合は例外が投げられる() {
        // TODO: 空文字のパスワードで IllegalArgumentException を検証
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_パスワードがスペースを含む場合は例外が投げられる() {
        // TODO: スペースを含むパスワードで IllegalArgumentException を検証
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_パスワードがnullの場合は例外が投げられる() {
        // TODO: null を渡して IllegalArgumentException を検証
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_パスワードが制御文字を含む場合は例外が投げられる() {
        // TODO: タブ文字 "\b" を含むパスワードで IllegalArgumentException を検証
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_パスワードが7文字以下なら弱い() {
        // TODO: 境界値テスト。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_英小文字が含まれないパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_英大文字が含まれないパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_数字が含まれないパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_記号が含まれないパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_英小文字のみのパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_英大文字のみのパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_数字のみのパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

    @Test
    @Disabled("このアノテーションはテストを無効化します。実装後は削除かコメントアウトしてください。")
    void 異常系_記号のみのパスワードは弱い() {
        // TODO: ここにテストを追加。
    }

}
