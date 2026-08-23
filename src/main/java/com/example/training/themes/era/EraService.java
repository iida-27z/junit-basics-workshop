package com.example.training.themes.era;

import java.time.LocalDate;

/**
 * テーマ4: 元号変換サービス
 *
 * 西暦の LocalDate を受け取り、和暦（明治〜令和）の
 * 「XX yy年mm月dd日」形式の文字列を返す。
 *
 * <ul>
 *   <li>未来の日付 → 最新元号（令和）で表示</li>
 *   <li>明治開始日（1868-01-25）より前 → IllegalArgumentException</li>
 * </ul>
 */
public class EraService {

    /** 明治の開始日（グレゴリオ暦 1868-01-25） */
    private static final LocalDate MEIJI_START = LocalDate.of(1868, 1, 25);
    /** 大正の開始日（グレゴリオ暦 1912-07-30） */
    private static final LocalDate TAISHO_START = LocalDate.of(1912, 7, 30);
    /** 昭和の開始日（グレゴリオ暦 1926-12-25） */
    private static final LocalDate SHOWA_START = LocalDate.of(1926, 12, 25);
    /** 平成の開始日（グレゴリオ暦 1989-01-08） */
    private static final LocalDate HEISEI_START = LocalDate.of(1989, 1, 8);
    /** 令和の開始日（グレゴリオ暦 2019-05-01） */
    private static final LocalDate REIWA_START = LocalDate.of(2019, 5, 1);

    /**
     * 西暦日付を和暦文字列に変換する。
     *
     * @param date 変換対象の日付
     * @return 和暦文字列（例: 「令和7年04月12日」）
     * @throws IllegalArgumentException date が null または明治開始日より前の場合
     */
    public String toJapaneseEra(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("日付がnullです");
        }
        if (date.isBefore(MEIJI_START)) {
            throw new IllegalArgumentException(
                    "明治開始日より前の日付には対応していません: " + date);
        }

        EraInfo era = findEra(date);
        int year = date.getYear() - era.startDate().getYear() + 1;
        String yearLabel = (year == 1) ? "元" : String.valueOf(year);

        return String.format("%s%s年%02d月%02d日",
            era.name(), yearLabel,
                date.getMonthValue(), date.getDayOfMonth());
    }

    private EraInfo findEra(LocalDate date) {
        if (!date.isBefore(REIWA_START)) {
            return new EraInfo("令和", REIWA_START);
        }
        if (!date.isBefore(HEISEI_START)) {
            return new EraInfo("平成", HEISEI_START);
        }
        if (!date.isBefore(SHOWA_START)) {
            return new EraInfo("昭和", SHOWA_START);
        }
        if (!date.isBefore(TAISHO_START)) {
            return new EraInfo("大正", TAISHO_START);
        }
        return new EraInfo("明治", MEIJI_START);
    }

    private record EraInfo(String name, LocalDate startDate) {
    }
}
