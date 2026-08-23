package com.example.training.themes.greeting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * GreetingService のテストクラス
 *
 * 【学習目標】
 * - @Test アノテーションの基本的な使い方
 * - assertEquals による戻り値の検証
 * - テストの基本構造: Arrange（準備）→ Act（実行）→ Assert（検証）
 */
class GreetingServiceTest {

    // =================================================================
    //  ✅ 実装例:以下の2つのテストからテストの基本構造を理解してください
    // =================================================================

    @Test
    void 名前を指定すると挨拶が返される() {
        // Arrange（準備）
        GreetingService service = new GreetingService();
        String name = "太郎";
        
        // Act（実行）
        String result = service.greet(name);
        
        // Assert（検証）
        String expected = "こんにちは、太郎さん！";
        assertEquals(expected, result);
    }
    
    @Test
    void nullの場合はゲストと挨拶される() {
        // Arrange（準備）
        GreetingService service = new GreetingService();
        String name = null;

        // Act（実行）
        String result = service.greet(name);

        // Assert（検証）
        String expected = "こんにちは、ゲストさん！";
        assertEquals(expected, result);
    }

    // =================================================================
    //  📝 以下のテストを自分で実装してください
    // =================================================================

    @Test
    void 空文字の場合はゲストと挨拶される() {
        // Arrange（準備）
        GreetingService service = new GreetingService();
        String name = "";

        // Act（実行）
        String result = service.greet(name);

        // Assert（検証）
        String expected = "こんにちは、ゲストさん！";
        assertEquals(expected, result);
    }

    @Test
    void スペースのみの場合はゲストと挨拶される() {
        // Arrange（準備）
        GreetingService service = new GreetingService();
        String name = "   ";

        // Act（実行）
        String result = service.greet(name);

        // Assert（検証）
        String expected = "こんにちは、ゲストさん！";
        assertEquals(expected, result);
    }

    @Test
    void 前後にスペースがある名前は前後のスペースが取り除かれる() {
        // Arrange（準備）
        GreetingService service = new GreetingService();
        String name = " 太郎 ";

        // Act（実行）
        String result = service.greet(name);

        // Assert（検証）
        String expected = "こんにちは、太郎さん！";
        assertEquals(expected, result);
    }
}
