package com.example.training.themes.price;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * テーマ5: 金額計算サービス
 *
 * Money オブジェクトに対する四則演算・集計処理・端数処理・分配処理を提供する。
 */
public class MoneyService {

    // ─── 通貨の既定小数桁を取得 ───

    private int resolveCurrencyScale(Currency currency) {
        int scale = currency.getDefaultFractionDigits();
        if (scale < 0) {
            throw new IllegalArgumentException("通貨の既定小数桁を取得できません: " + currency);
        }
        return scale;
    }

    // ─── 通貨一致チェック ───

    private void requireSameCurrency(Money a, Money b) {
        if (!a.currency().equals(b.currency())) {
            throw new IllegalArgumentException(
                    "通貨が異なります: " + a.currency() + " と " + b.currency());
        }
    }

    // ─── 四則演算 ───

    /**
     * 2つの金額を加算する。
     *
     * <p>
     * 2つの金額は同一通貨である必要がある。
     * </p>
     *
     * @param a 加算対象1つ目の金額
     * @param b 加算対象2つ目の金額
     * @return 加算結果の金額
     * @throws IllegalArgumentException 通貨が異なる場合
     */
    public Money add(Money a, Money b) {
        requireSameCurrency(a, b);
        return new Money(a.amount().add(b.amount()), a.currency());
    }

    /**
     * 1つ目の金額から2つ目の金額を減算する。
     *
     * <p>
     * 2つの金額は同一通貨である必要がある。
     * </p>
     *
     * @param a 被減算の金額
     * @param b 減算する金額
     * @return 減算結果の金額
     * @throws IllegalArgumentException 通貨が異なる場合
     */
    public Money subtract(Money a, Money b) {
        requireSameCurrency(a, b);
        return new Money(a.amount().subtract(b.amount()), a.currency());
    }

    /**
     * 金額を指定した値で乗算する。
     *
     * <p>
     * このメソッドでは端数処理を行わない。必要に応じて {@link #round(Money, int, RoundingMode)}
     * を組み合わせて使用する。
     * </p>
     *
     * @param money  乗算対象の金額
     * @param factor 掛ける係数
     * @return 乗算結果の金額
     * @throws NullPointerException factor が null の場合
     */
    public Money multiply(Money money, BigDecimal factor) {
        Objects.requireNonNull(factor, "係数がnullです");
        BigDecimal result = money.amount().multiply(factor);
        return new Money(result, money.currency());
    }

    /**
     * 金額を指定した値で除算する。
     *
     * <p>
     * このメソッドでは端数処理を行わない。必要に応じて {@link #round(Money, int, RoundingMode)}
     * を組み合わせて使用する。
     * </p>
     *
     * @param money   除算対象の金額
     * @param divisor 除数
     * @return 除算結果の金額
     * @throws NullPointerException divisor が null の場合
     * @throws ArithmeticException  divisor が 0 の場合
     */
    public Money divide(Money money, BigDecimal divisor) {
        Objects.requireNonNull(divisor, "除数がnullです");
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("ゼロで除算することはできません");
        }
        BigDecimal result = money.amount().divide(divisor, MathContext.DECIMAL128);
        return new Money(result, money.currency());
    }

    // ─── 集計 ───

    /**
     * 金額リストを指定通貨で合計する。
     *
     * <p>
     * リストが空または null の場合は、指定通貨で金額 0 を返す。
     * リスト内の要素はすべて指定通貨と一致する必要がある。
     * </p>
     *
     * @param moneyList 集計対象の金額リスト
     * @param currency  集計に使用する通貨
     * @return 合計金額
     * @throws NullPointerException     currency が null の場合
     * @throws IllegalArgumentException リスト内に異なる通貨の金額が含まれる場合
     */
    public Money sum(List<Money> moneyList, Currency currency) {
        Objects.requireNonNull(currency, "currency is null");
        if (moneyList == null || moneyList.isEmpty()) {
            return new Money(BigDecimal.ZERO, currency);
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Money m : moneyList) {
            if (!m.currency().equals(currency)) {
                throw new IllegalArgumentException(
                        "通貨が異なります: 期待 " + currency + " が見つかりました " + m.currency());
            }
            total = total.add(m.amount());
        }
        return new Money(total, currency);
    }

    /**
     * 金額リストの平均を計算する。
     *
     * <p>
     * このメソッドでは端数処理を行わない。必要に応じて {@link #round(Money, int, RoundingMode)}
     * を組み合わせて使用する。リストが空または null の場合は、指定通貨で金額 0 を返す。
     * </p>
     *
     * @param moneyList 集計対象の金額リスト
     * @param currency  集計に使用する通貨
     * @return 平均金額
     * @throws NullPointerException     currency が null の場合
     * @throws IllegalArgumentException リスト内に異なる通貨の金額が含まれる場合
     */
    public Money average(List<Money> moneyList, Currency currency) {
        Money total = sum(moneyList, currency);
        int size = (moneyList == null) ? 0 : moneyList.size();
        if (size == 0) {
            return new Money(BigDecimal.ZERO, currency);
        }
        return divide(total, BigDecimal.valueOf(size));
    }

    // ─── 端数処理 ───

    /**
     * 金額を通貨の既定小数桁と丸めモードで丸める。
     *
     * @param money        丸め対象の金額
     * @param roundingMode 丸めモード
     * @return 丸め後の金額
     * @throws NullPointerException     roundingMode が null の場合
     * @throws IllegalArgumentException 通貨の既定小数桁を取得できない場合
     */
    public Money round(Money money, RoundingMode roundingMode) {
        return round(money, resolveCurrencyScale(money.currency()), roundingMode);
    }

    /**
     * 金額を指定した小数桁と丸めモードで丸める。
     *
     * @param money        丸め対象の金額
     * @param scale        小数桁数
     * @param roundingMode 丸めモード
     * @return 丸め後の金額
     * @throws IllegalArgumentException scale が 0 未満の場合
     * @throws NullPointerException     roundingMode が null の場合
     */
    public Money round(Money money, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException("scale は 0 以上にしてください");
        }
        return new Money(
                money.amount().setScale(scale, roundingMode),
                money.currency());
    }

    // ─── 分配 ───

    /**
     * 金額を均等に分配する。端数は先頭の要素から 1単位ずつ加算する。
     *
     * <p>
     * 例: ¥1000 を 3分割 → [¥334, ¥333, ¥333]
     * </p>
     *
     * @param total 分配元の金額
     * @param parts 分配数（1以上）
     * @return 分配結果のリスト
     * @implSpec 分配は通貨の既定小数桁単位に基づいて行われ、端数はリストの先頭から順に加算されます。
     * @throws IllegalArgumentException parts が 1 未満の場合
     */
    public List<Money> distribute(Money total, int parts) {
        if (parts <= 0) {
            throw new IllegalArgumentException("分配数は1以上にしてください");
        }

        int scale = resolveCurrencyScale(total.currency());
        BigDecimal base = total.amount()
                .divide(BigDecimal.valueOf(parts), scale, RoundingMode.DOWN);
        BigDecimal distributed = base.multiply(BigDecimal.valueOf(parts));
        BigDecimal remainder = total.amount().subtract(distributed);

        // 通貨の最小単位（JPY=1, USD=0.01）
        BigDecimal unit = (scale > 0)
                ? BigDecimal.ONE.movePointLeft(scale)
                : BigDecimal.ONE;

        int extraUnits = remainder
                .divide(unit, 0, RoundingMode.DOWN)
                .intValue();

        List<Money> result = new ArrayList<>();
        for (int i = 0; i < parts; i++) {
            BigDecimal amount = (i < extraUnits) ? base.add(unit) : base;
            result.add(new Money(amount, total.currency()));
        }
        return result;
    }

}
