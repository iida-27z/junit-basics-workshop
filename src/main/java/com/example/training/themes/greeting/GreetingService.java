package com.example.training.themes.greeting;

/**
 * テーマ1: 挨拶生成サービス
 *
 * 名前を受け取り、挨拶文を返す純粋なロジッククラス。
 */
public class GreetingService {

    /**
     * 指定された名前に対する挨拶を生成する。
     *
     * @param name 挨拶対象の名前（null・空文字・スペースのみの場合は「ゲスト」扱い）
     * @return 挨拶文字列（例: 「こんにちは、太郎さん！」）
     */
    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "こんにちは、ゲストさん！";
        }
        return "こんにちは、" + name.trim() + "さん！";
    }
}
