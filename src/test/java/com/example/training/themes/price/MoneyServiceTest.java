package com.example.training.themes.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * MoneyService のテストクラス
 *
 * 【学習目標】
 * - 複雑なビジネスルールのテストケースを自分で考え、実装できるようになること
 * 
 * 【注意点】
 * - このテーマは難易度の高いエクストラステージです。無理に進めずこれまでのテーマの復習を進める形でも大丈夫です。
 */
class MoneyServiceTest {

    private MoneyService service;

    @BeforeEach
    void setUp() {
        service = new MoneyService();
    }

    @Test
    void 同じ通貨同士は加算できる() {
        // arrange
        Money price = Money.jpy(1200);
        Money tax = Money.jpy(120);

        // act
        Money result = service.add(price, tax);

        // assert
        assertEquals(Money.jpy(1320), result);
    }

    @Test
    void 異なる通貨同士は加算できず例外が投げられる() {
        // arrange
        Money yen = Money.jpy(1000);
        Money dollar = Money.usd("10.00");

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.add(yen, dollar));
    }

    @Test
    void 同じ通貨同士は減算できる() {
        // arrange
        Money subtotal = Money.jpy(1500);
        Money discount = Money.jpy(200);

        // act
        Money result = service.subtract(subtotal, discount);

        // assert
        assertEquals(Money.jpy(1300), result);
    }

    @Test
    void 異なる通貨同士は減算できず例外が投げられる() {
        // arrange
        Money yen = Money.jpy(1000);
        Money dollar = Money.usd("10.00");

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.subtract(yen, dollar));
    }

    @Test
    void 乗算すると係数を掛けた金額になる() {
        // arrange
        Money unitPrice = Money.jpy(980);
        BigDecimal quantity = BigDecimal.valueOf(3);

        // act
        Money result = service.multiply(unitPrice, quantity);

        // assert
        assertEquals(Money.jpy(2940), result);
    }

    @Test
    void ゼロで除算すると例外が投げられる() {
        // arrange
        Money total = Money.jpy(1000);
        BigDecimal divisor = BigDecimal.ZERO;

        // act & assert
        assertThrows(ArithmeticException.class, () -> service.divide(total, divisor));
    }

    @Test
    void 合計は同じ通貨のリストを集計できる() {
        // arrange
        List<Money> moneyList = List.of(Money.jpy(100), Money.jpy(250), Money.jpy(50));

        // act
        Money result = service.sum(moneyList, Currency.getInstance("JPY"));

        // assert
        assertEquals(Money.jpy(400), result);
    }

    @Test
    void 合計は空リストなら指定通貨のゼロを返す() {
        // arrange
        List<Money> moneyList = List.of();

        // act
        Money result = service.sum(moneyList, Currency.getInstance("JPY"));

        // assert
        assertEquals(Money.jpy(0), result);
    }

    @Test
    void 合計は異なる通貨が含まれると例外が投げられる() {
        // arrange
        List<Money> moneyList = List.of(Money.jpy(100), Money.usd("1.00"));

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.sum(moneyList, Currency.getInstance("JPY"));
        });
    }

    @Test
    void 平均は件数で割った金額になる() {
        // arrange
        List<Money> moneyList = List.of(Money.jpy(100), Money.jpy(200), Money.jpy(300));

        // act
        Money result = service.average(moneyList, Currency.getInstance("JPY"));

        // assert
        assertEquals(Money.jpy(200), result);
    }

    @Test
    void 通貨の既定小数桁で丸められる() {
        // arrange
        Money money = Money.usd("10.125");

        // act
        Money result = service.round(money, RoundingMode.HALF_UP);

        // assert
        assertEquals(Money.usd("10.13"), result);
    }

    @Test
    void 小数桁を指定して丸められる() {
        // arrange
        Money money = Money.usd("10.156");

        // act
        Money result = service.round(money, 1, RoundingMode.DOWN);

        // assert
        assertEquals(Money.of(new BigDecimal("10.1"), Currency.getInstance("USD")), result);
    }

    @Test
    void 負の小数桁を指定すると丸めで例外が投げられる() {
        // arrange
        Money money = Money.jpy(100);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.round(money, -1, RoundingMode.HALF_UP);
        });
    }

    @Test
    void 端数が出る分配は先頭から一単位ずつ配られる() {
        // arrange
        Money total = Money.jpy(1000);

        // act
        List<Money> result = service.distribute(total, 3);

        // assert
        assertEquals(List.of(Money.jpy(334), Money.jpy(333), Money.jpy(333)), result);
    }

    @Test
    void 小数通貨の分配も最小単位で端数が配られる() {
        // arrange
        Money total = Money.usd("10.00");

        // act
        List<Money> result = service.distribute(total, 3);

        // assert
        assertEquals(List.of(Money.usd("3.34"), Money.usd("3.33"), Money.usd("3.33")), result);
    }

    @Test
    void 分配数が0以下なら例外が投げられる() {
        // arrange
        Money total = Money.jpy(1000);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.distribute(total, 0));
    }

    @Test
    void 乗算で係数がnullなら例外が投げられる() {
        // arrange
        Money money = Money.jpy(100);

        // act & assert
        assertThrows(NullPointerException.class, () -> service.multiply(money, null));
    }

    @Test
    void 除算で除数がnullなら例外が投げられる() {
        // arrange
        Money total = Money.jpy(1000);

        // act & assert
        assertThrows(NullPointerException.class, () -> service.divide(total, null));
    }

    @Test
    void 合計で通貨がnullなら例外が投げられる() {
        // arrange
        List<Money> moneyList = List.of(Money.jpy(100));

        // act & assert
        assertThrows(NullPointerException.class, () -> service.sum(moneyList, null));
    }

    @Test
    void 合計はnullリストなら指定通貨のゼロを返す() {
        // act
        Money result = service.sum(null, Currency.getInstance("JPY"));

        // assert
        assertEquals(Money.jpy(0), result);
    }

    @Test
    void 平均はnullリストなら指定通貨のゼロを返す() {
        // act
        Money result = service.average(null, Currency.getInstance("JPY"));

        // assert
        assertEquals(Money.jpy(0), result);
    }

    @Test
    void 丸めで丸めモードがnullなら例外が投げられる() {
        // arrange
        Money money = Money.usd("10.00");

        // act & assert
        assertThrows(NullPointerException.class, () -> service.round(money, (RoundingMode) null));
    }

    @Test
    void 小数桁指定で丸めモードがnullなら例外が投げられる() {
        // arrange
        Money money = Money.usd("10.00");

        // act & assert
        assertThrows(NullPointerException.class, () -> service.round(money, 2, null));
    }

    @Test
    void 分配数が負なら例外が投げられる() {
        // arrange
        Money total = Money.jpy(1000);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> service.distribute(total, -1));
    }

}
