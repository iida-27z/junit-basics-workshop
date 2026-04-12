package com.example.training.themes.greeting;

/**
 * テーマ1: 挨拶生成 — CLI実行クラス
 *
 * ユーザーから名前を受け取り、GreetingService を呼び出して結果を表示する。
 */
public class GreetingApp {

    public static void run() {
        IO.println("=== 挨拶生成 ===");

        String name = IO.readln("名前を入力してください（空欄で「ゲスト」）: ");

        GreetingService service = new GreetingService();
        String result = service.greet(name);

        IO.println(result);
    }
}
