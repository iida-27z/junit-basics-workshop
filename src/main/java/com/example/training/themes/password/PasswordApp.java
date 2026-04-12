package com.example.training.themes.password;

/**
 * テーマ3: パスワード強度判定 — CLI実行クラス
 *
 * ユーザーからパスワードを受け取り、PasswordService で強度を判定して表示する。
 */
public class PasswordApp {

    public static void run() {
        IO.println("=== パスワード強度判定 ===");

        String password = IO.readln("パスワードを入力してください: ");

        PasswordService service = new PasswordService();
        try {
            boolean strong = service.isStrong(password);

            if (strong) {
                IO.println("判定: 強い ✔ — 4種の文字種を含み、長さも十分です。");
            } else {
                IO.println("判定: 弱い ✘ — 8文字以上で、英大文字・小文字・数字・記号を混ぜてください。");
            }
        } catch (IllegalArgumentException e) {
            IO.println("エラー: " + e.getMessage());
        }
    }
}
