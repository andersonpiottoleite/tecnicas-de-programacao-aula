package br.com.letscode.bb.datas;

/*
    Crie um metodo que receba uma data no formato dd/MM/yyyy e imprima:
    - qual dia da semana a pessoa nasceu.
    - qual do ano que a pessoa nasceu.
    - se nasceu em ano bissexto.
    - se nasceu antes do ano 2000.
    - quantos dias viveu até aqui.
    - quantos meses viveu até aqui.
    - quantos anos  viveu até aqui.
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.*;

public class ExercicioData {

    public static void main(String[] args) {
        DateTimeFormatter meuFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        DateTimeFormatter meuFormatterISO = DateTimeFormatter.ISO_DATE;

        LocalDate meuFinalDeAno = LocalDate.parse("30-12-99",meuFormatter);
        System.out.println(meuFinalDeAno);
        //imprimeInformacoesDaVida("12/03/1985");

        /*Date date1 = new Date();
        date1.setYear(2022-1900);
        date1.setMonth(01);
        date1.setDate(10);
        date1.setHours(05);
        date1.setMinutes(30);

        Date date2 = new Date();
        date2.setYear(2022-1900);
        date2.setMonth(01);
        date2.setDate(10);
        date2.setHours(05);
        date2.setMinutes(30);

        Date somaDate = somaComDate(date1, date2);
        System.out.println("Soma com Date: " + somaDate);

        Calendar c1 = Calendar.getInstance();
        c1.set(2022, 01, 10, 05, 30);

        Calendar c2 = Calendar.getInstance();
        c2.set(2022, 01, 10, 05, 30);

        Date resultSomaCalendar = somaComCalendar(c1, c2);
        System.out.println("Soma com Calendar: " + resultSomaCalendar);

        LocalDateTime ldt1 = LocalDateTime.of(2022, 07, 10, 05, 30);
        LocalDateTime ldt2 = LocalDateTime.of(2022, 07, 10, 05, 30);

        LocalDateTime resultSomaLocalDateTime = somaComApiTime(ldt1, ldt2);
        System.out.println("Soma com ApiTime: " + resultSomaLocalDateTime);*/

    }

    private static void imprimeInformacoesDaVida(String dataNascimento) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate minhaDataNascimento = LocalDate.parse(dataNascimento, formatter);
        System.out.println(minhaDataNascimento.getDayOfWeek());
        System.out.println(minhaDataNascimento.getYear());
        System.out.println(minhaDataNascimento.isLeapYear());
        System.out.println(minhaDataNascimento.isBefore(LocalDate.of(2000, 01, 01)));
        System.out.println(DAYS.between(minhaDataNascimento, LocalDate.now()));
        System.out.println(MONTHS.between(minhaDataNascimento, LocalDate.now()));
        System.out.println(YEARS.between(minhaDataNascimento, LocalDate.now()));
    }

    /*
    Crie um metodo que receba uma data no formato dd/MM/yyyy e imprima:
    - qual dia da semana a pessoa nasceu.
    - qual do ano que a pessoa nasceu.
    - se nasceu em ano bissexto.
    - se nasceu antes do ano 2000.
    - quantos dias viveu até aqui.
    - quantos meses viveu até aqui.
    - quantos anos  viveu até aqui.
 */

    private static Date somaComDate(Date d1 , Date d2) {
        /*long sum = d1.getTime() + d2.getTime();
        Date sumDate = new Date(sum);
        return sumDate; // Date é realmente um problema... :(*/
        Date date3 = new Date();
        date3.setMonth(d1.getMonth() + d2.getMonth());
        date3.setYear((d1.getYear()+ 1900) + (d2.getYear()+ 1900));
        date3.setDate(d1.getDate() + d2.getDate());
        date3.setHours(d1.getHours() + d2.getHours());
        date3.setMinutes(d1.getMinutes() + d2.getMinutes());
        return date3;
    }

    private static Date somaComCalendar(Calendar c1, Calendar c2 ) {
        c1.add(Calendar.YEAR, c2.get(Calendar.YEAR));
        c1.add(Calendar.MONTH, c2.get(Calendar.MONTH));
        c1.add(Calendar.DATE, c2.get(Calendar.DATE));
        c1.add(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
        c1.add(Calendar.MINUTE, c2.get(Calendar.MINUTE));
        c1.add(Calendar.SECOND, c2.get(Calendar.SECOND));
        c1.add(Calendar.MILLISECOND, c2.get(Calendar.MILLISECOND));

        return c1.getTime();
    }

    private static LocalDateTime somaComApiTime(LocalDateTime ldt1, LocalDateTime ldt2) {
        ldt1 = ldt1.plus(ldt2.getYear(), YEARS);
        ldt1 = ldt1.plus(ldt2.getMonthValue(), MONTHS);
        ldt1 = ldt1.plus(ldt2.getDayOfMonth(), DAYS);
        ldt1 = ldt1.plus(ldt2.getHour(), HOURS);
        ldt1 = ldt1.plus(ldt2.getMinute(), MINUTES);
        ldt1 = ldt1.plus(ldt2.getSecond(), SECONDS);
        ldt1 = ldt1.plus(ldt2.getNano(), NANOS);

        return ldt1;
    }
}


