package com.example.training.themes.password;

/**
 * テーマ3: パスワード強度判定
 *
 * パスワード文字列を受け取り、以下のルールで強度を判定する。
 *
 * <ul>
 *   <li>使用できない文字（スペース・制御文字）が含まれる → IllegalArgumentException</li>
 *   <li>長さが 8文字以上かつ英大文字・英小文字・数字・記号の 4種すべてを含む → true（強い）</li>
 *   <li>上記を満たさない → false（弱い）</li>
 * </ul>
 */
public class PasswordService {

    /**
     * パスワードの強度を判定する。
     *
     * @param password 判定対象のパスワード
     * @return true: 強い / false: 弱い
     * @throws IllegalArgumentException パスワードが null、
     *         またはスペース・制御文字を含む場合
     */
    public boolean isStrong(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("パスワードがnullまたは空です");
        }

        // スペースや制御文字のチェック
        for (char c : password.toCharArray()) {
            if (Character.isWhitespace(c) || Character.isISOControl(c)) {
                throw new IllegalArgumentException(
                        "使用できない文字が含まれています");
            }
        }

        // 長さチェック: 8文字以上
        if (password.length() < 8) {
            return false;
        }

        // 文字種チェック
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSymbol = true;
            }
        }

        // 4種すべてを含めば「強い」
        return hasUpper && hasLower && hasDigit && hasSymbol;
    }
}
