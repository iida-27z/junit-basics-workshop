# JUnit ハンズオン

![GitHub Repo](https://img.shields.io/badge/github-repo-blue?logo=github)
![Java Version](https://img.shields.io/badge/Java-25-orange)
![JUnit](https://img.shields.io/badge/Test-JUnit6-green)
![CI](https://github.com/iida-27z/junit-basics-workshop/actions/workflows/ci.yml/badge.svg?branch=main)

## 概要
本プロジェクトは、テストコードの基礎を学びたい人を対象とした「JUnit」のハンズオン研修リポジトリです。
テストコードの基本から、例外テストやパラメータ化テストなど、5つのテーマを通じて段階的に学習していきます。バグのない堅牢なソフトウェア開発において、テストコードは欠かせないスキルです。このハンズオンを通じて、自信を持ってテストコードを書けるようになりましょう！

## 学習目標
- JUnitの基本的なアノテーション（`@Test`, `@ParameterizedTest` など）の使い方を理解する
- Arrange-Act-Assert (準備・実行・検証) パターンに基づき、読みやすいテストコードを書く
- 各種アサーション（`assertEquals`, `assertTrue`, `assertThrows` など）を適切に選択・使用できる
- 正常系・異常系のテスト、同値分割、境界値分析などの基本的なテスト概念を理解する

## 動作環境
- **Java**: 25
- **ビルドツール**: Maven
- **推奨エディタ**: VSCode + DevContainer (GitHub Codespaces)

## ハンズオンの進め方
本プロジェクトの `src/main/java/` には、実装済みのビジネスロジック（`*Service.java`）と、動作確認用のCLIアプリ（`*App.java`）が用意されています。
みなさんのタスクは、**`src/test/java/` にある各テーマのビジネスロジックに対するテストクラス（`*ServiceTest.java`）を完成させること**です。

テーマ1～4の各テストクラスには、最初の1〜2ケースの実装例と、実装すべきテストの「TODOコメント」が用意されています。
実装例を参考にしながら、`@Disabled`を外し、TODOを一つずつテストコードに置き換えていきましょう。

テーマ5は、実装例やTODOが一切ない完全な白紙状態になっています。
難易度も高めに設定されているので、テーマ4を終えて時間に余裕がある方や、テストコードの設計に興味が出てきた方は挑戦してみてください。

## テーマ一覧と学習内容

### 1. 挨拶生成 (Greeting)
- **対象ファイル**: `GreetingServiceTest.java`
- **学習テーマ**: テストクラスの基本構造構築、`assertEquals` の使い方、Arrange-Act-Assertパターンの習得。

### 2. BMI計算 (BMI)
- **対象ファイル**: `BmiServiceTest.java`
- **学習テーマ**: `assertThrows` を用いた例外処理のテスト、浮動小数点の誤差を考慮したテスト方法。

### 3. パスワード強度判定(Password)
- **対象ファイル**: `PasswordServiceTest.java`
- **学習テーマ**: 複雑な条件分岐に対するテストケースの網羅。

### 4. 元号サービス (Era)
- **対象ファイル**: `EraServiceTest.java`
- **学習テーマ**: `@ParameterizedTest` と `@CsvSource` を活用した効率的なデータ駆動テスト。

### 5. 金額計算 (Price)
- **対象ファイル**: `MoneyServiceTest.java`
- **学習テーマ**: 複雑なドメインロジックが詰め込み気味なクラスに対するテスト。
- **補足**: 難易度を高めに設定しています。取り組みは任意です。


---

### 実行方法

#### メインアプリの起動
動作確認用のCLIアプリを実行したい場合は、F5キーからデバッグを開始するか、以下のコマンドから起動できます。
```bash
mvn exec:java
```

#### テストを実行
VSCodeの左側のアクティビティバーからテストアイコンを選択し、特定のテストやすべてのテストを実行することができます。または、以下のコマンドを使用してターミナルからすべてのテストを実行することも可能です。
```bash
mvn test
```

### ファイル一覧（リンク）
以下は本リポジトリ内の主要なテストおよびテスト対象ファイルへのリンクです。演習で参照してください。

- [Main.java](src/main/java/com/example/Main.java)

- Greeting:
	- [src/main/java/com/example/training/themes/greeting/GreetingService.java](src/main/java/com/example/training/themes/greeting/GreetingService.java)
	- [src/main/java/com/example/training/themes/greeting/GreetingApp.java](src/main/java/com/example/training/themes/greeting/GreetingApp.java)
	- [src/test/java/com/example/training/themes/greeting/GreetingServiceTest.java](src/test/java/com/example/training/themes/greeting/GreetingServiceTest.java)

- BMI:
	- [src/main/java/com/example/training/themes/bmi/BmiService.java](src/main/java/com/example/training/themes/bmi/BmiService.java)
	- [src/main/java/com/example/training/themes/bmi/BmiApp.java](src/main/java/com/example/training/themes/bmi/BmiApp.java)
	- [src/test/java/com/example/training/themes/bmi/BmiServiceTest.java](src/test/java/com/example/training/themes/bmi/BmiServiceTest.java)

- Password:
	- [src/main/java/com/example/training/themes/password/PasswordService.java](src/main/java/com/example/training/themes/password/PasswordService.java)
	- [src/main/java/com/example/training/themes/password/PasswordApp.java](src/main/java/com/example/training/themes/password/PasswordApp.java)
	- [src/test/java/com/example/training/themes/password/PasswordServiceTest.java](src/test/java/com/example/training/themes/password/PasswordServiceTest.java)

- Era:
	- [src/main/java/com/example/training/themes/era/EraService.java](src/main/java/com/example/training/themes/era/EraService.java)
	- [src/main/java/com/example/training/themes/era/EraApp.java](src/main/java/com/example/training/themes/era/EraApp.java)
	- [src/test/java/com/example/training/themes/era/EraServiceTest.java](src/test/java/com/example/training/themes/era/EraServiceTest.java)

- Price:
	- [src/main/java/com/example/training/themes/price/Money.java](src/main/java/com/example/training/themes/price/Money.java)
	- [src/main/java/com/example/training/themes/price/MoneyService.java](src/main/java/com/example/training/themes/price/MoneyService.java)
	- [src/main/java/com/example/training/themes/price/PriceApp.java](src/main/java/com/example/training/themes/price/PriceApp.java)
	- [src/test/java/com/example/training/themes/price/MoneyServiceTest.java](src/test/java/com/example/training/themes/price/MoneyServiceTest.java)
