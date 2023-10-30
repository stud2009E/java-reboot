# Module 4. Java Collections Framework Part II

## Задание №1

1. Написать компаратор `CustomDigitComparator`, который реализует интерфейс `Comparator<Integer>`.
2. Класс `CustomDigitComparator` определяет следующий порядок:
- Сначала четные числа, затем нечетные
- На вход подаются числа, отличные от null.

## Задание 2

1. Реализовать класс `Person{name, city, age}`, определить метод `toString`.
2. Реализовать методы `equals` и `hashCode` (Условие равенства: все поля `name`, `city`, `age` должны совпадать, `name` и `city` без учета регистра).
3. Класс Person должен реализовывать интерфейс `Comparable<Person>`, который обеспечивает следующий порядок:
- Сортировка сначала по полю `city`, а затем по полю `name`.
- Поля `city`, `name` отличны от `null`.

## Критерии приемки

1. Проект должен собираться.
2. Класс должен быть протестирован с помощью JUnit на предмет возвращаемых значений.
3. Должны быть написаны java docs.
4. В репозитории не должно быть IDE-специфичных файлов.