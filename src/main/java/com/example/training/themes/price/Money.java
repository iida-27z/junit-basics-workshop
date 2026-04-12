package com.example.training.themes.price;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * テーマ5: 金額を表す不変バリューオブジェクト
 *
 * BigDecimal で金額を、Currency で通貨を保持する。
 * record を使用して不変性とコンパクトなコードを両立する。
 */
public record Money(BigDecimal amount, Currency currency) {

    /** コンパクトコンストラクタでバリデーション */
    public Money {
        Objects.requireNonNull(amount, "金額がnullです");
        Objects.requireNonNull(currency, "通貨がnullです");
    }

    // ─── ファクトリメソッド ───

    /** 汎用ファクトリ */
    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    /** JPY のファクトリ（整数） */
    public static Money jpy(long amount) {
        return new Money(BigDecimal.valueOf(amount), Currency.getInstance("JPY"));
    }

    /** JPY のファクトリ（文字列） */
    public static Money jpy(String amount) {
        return new Money(new BigDecimal(amount), Currency.getInstance("JPY"));
    }

    /** USD のファクトリ（文字列） */
    public static Money usd(String amount) {
        return new Money(new BigDecimal(amount), Currency.getInstance("USD"));
    }

    // ─── 表示 ───

    @Override
    public String toString() {
        return currency.getCurrencyCode() + " " + amount.toPlainString();
    }
}
