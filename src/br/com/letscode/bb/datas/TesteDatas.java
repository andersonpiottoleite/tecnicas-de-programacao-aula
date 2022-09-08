package br.com.letscode.bb.datas;

import java.io.ByteArrayInputStream;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.*;

public class TesteDatas {
    public static void main(String[] args) {
        usandoDate();

        usandoCalendar();

        System.out.println("-- API de Datas do Java 8");
        // dd/MM/yyyy ou MM/dd/yyyy ou yyyy/MM/dd ? Segue o padrão ISO yyyy-MM-dd
        LocalDate agora = usandoLocalDate();

        LocalTime hora = usandoLocalTime();

        usandoLocalDateTime(agora, hora);

        usandoInstant();

        usandoZonedDateTime();

        usandoZoneOffSet();

        conversoes();

        formatacoes();

        comparacoes();

        usandoChronoUnit();

        usandoPeriod();

        usandoDuration();
    }

    private static void usandoChronoUnit() {
        System.out.println(YEARS.between(LocalDate.of(1985,03,15), LocalDate.now()));
        System.out.println(MONTHS.between(LocalDate.of(1985,03,15), LocalDate.now()));
        System.out.println(WEEKS.between(LocalDate.of(1985,03,15), LocalDate.now()));
    }

    private static void usandoDuration() {
        // Duration - para trabalhar com periodos dentro de horas hh:mm:ss

        Duration duration = Duration.parse("PT1H30M5S");
        System.out.println(duration);

        duration = duration.plusHours(2);
        System.out.println(duration);

        duration = duration.plusSeconds(1);
        System.out.println(duration);

        duration = Duration.ofDays(2);
        System.out.println(duration);

        duration = Duration.ofHours(2);
        System.out.println(duration);

        duration = Duration.ofMinutes(2);
        System.out.println(duration);

        duration = Duration.ofSeconds(2);
        System.out.println(duration);

        LocalTime horaInicial = LocalTime.of(12, 30, 30);
        LocalTime horaFinal = LocalTime.of(14, 31, 31);

        long intervaloMinutos = MINUTES.between(horaInicial, horaFinal);
        System.out.println(intervaloMinutos);

        Duration intervaloMinutosComDuration = Duration.ofMinutes(intervaloMinutos);
        System.out.println(intervaloMinutosComDuration);

        long intervaloSegundos = SECONDS.between(horaInicial, horaFinal);
        System.out.println(intervaloSegundos);

        Duration intervaloSegundosComDuration = Duration.ofSeconds(intervaloMinutos);
        System.out.println(intervaloSegundosComDuration);

        LocalTime horaDeIrParaCasa = LocalTime.now().plus(Duration.ofHours(3));
        System.out.println(horaDeIrParaCasa);
    }

    private static void usandoPeriod() {
        // Period - para trabalhar com periodos dentro de datas yyyy-mm-dd

        Period periodo = Period.between(LocalDate.of(1985,03,15), LocalDate.now());
        System.out.println(periodo.getYears());

        Period period = Period.of(2022, 10, 05);
        System.out.println(period);

        period = Period.ofDays(2);
        System.out.println(period);

        period = Period.ofMonths(2);
        System.out.println(period);

        period = Period.ofYears(2);
        System.out.println(period);

        period = Period.ofWeeks(1);
        System.out.println(period);

        LocalDate dataComPeriodoAdicionado = LocalDate.now().plus(Period.ofMonths(4));
        System.out.println(dataComPeriodoAdicionado);
    }

    private static void comparacoes() {
        System.out.println("#### Comparações");

        LocalDate dataPentaCampeonato = LocalDate.of(2002, Month.JUNE, 30);
        LocalDate dataDeNascimentoDoJovem = LocalDate.of(2003, Month.JANUARY, 20);

        boolean nasceuDepoisDoPenta = dataDeNascimentoDoJovem.isAfter(dataPentaCampeonato);

        if (nasceuDepoisDoPenta){
            System.out.println("Será dificil ver levantar a taça denovo");
        } else {
            System.out.println("Viu o show");
        }
    }

    private static void formatacoes() {
        System.out.println("#### Formatacões");

        LocalDate finalDeAnoISO = LocalDate.parse("2022-12-30");
        System.out.println(finalDeAnoISO);

        DateTimeFormatter meuFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter meuFormatterISO = DateTimeFormatter.ISO_DATE;

        LocalDate meuFinalDeAno = LocalDate.parse("30/12/2022",meuFormatter);
        System.out.println(meuFinalDeAno);

        LocalDate novaDataParaformatar = LocalDate.now();
        System.out.println(novaDataParaformatar);
        System.out.println(novaDataParaformatar.format(meuFormatter));
    }

    private static void conversoes() {
        System.out.println("##### Conversoes");

        System.out.println("Primeira conversão de Date para LocalDateTime");
        Date minhaDataDeprecated = new Date();
        System.out.println(minhaDataDeprecated);

        LocalDateTime minhaDataApiJava8 = LocalDateTime.ofInstant(minhaDataDeprecated.toInstant(), ZoneId.systemDefault());
        System.out.println(minhaDataApiJava8);

        System.out.println("Segunda conversão de LocalDate para Date");
        LocalDate meuLocalDate = LocalDate.now();
        System.out.println(meuLocalDate);
        LocalDateTime meuLocalDateTime = meuLocalDate.atStartOfDay();
        ZonedDateTime meuZonedDateTime = meuLocalDateTime.atZone(ZoneId.systemDefault());
        Instant meuInstant = meuZonedDateTime.toInstant();
        Date date = Date.from(meuInstant);
        System.out.println(date);
    }

    private static void usandoZoneOffSet() {
        System.out.println("##### ZonedOffSet:");
        ZoneOffset zoneOffset = ZoneOffset.of("+01:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.now(), zoneOffset);
        System.out.println(offsetDateTime);
        LocalDateTime dataTimeComOffset = LocalDateTime.now(zoneOffset);
        System.out.println(dataTimeComOffset);
    }

    private static void usandoZonedDateTime() {
        System.out.println("##### ZonedDateTime:");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        System.out.println(ZoneId.systemDefault());
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId fusoHorarioParis = ZoneId.of("Europe/Paris");
        System.out.println(fusoHorarioParis);
        System.out.println(fusoHorarioParis.getRules());

        ZoneId fusoHorarioSaoPaulo = ZoneId.of("America/Sao_Paulo");
        System.out.println(fusoHorarioSaoPaulo);
        System.out.println(fusoHorarioSaoPaulo.getRules());

        ZoneId fusoHorarioLisboa = ZoneId.of("Europe/Lisbon");
        System.out.println(fusoHorarioLisboa);
        System.out.println(fusoHorarioLisboa.getRules());

        ZonedDateTime horaDeParis = ZonedDateTime.now(fusoHorarioParis);
        System.out.println(horaDeParis);

        ZonedDateTime horaDeSaoPaulo = ZonedDateTime.now(fusoHorarioSaoPaulo);
        System.out.println(horaDeSaoPaulo);

        ZonedDateTime horaDeLisboa = ZonedDateTime.now(fusoHorarioLisboa);
        System.out.println(horaDeLisboa);
    }

    private static void usandoInstant() {
        // basicamente traz um LocalDateTime, considerando o GMT/Fuso horário (0)- Londres
        // trabalha com epoca, a partir de 1970-01-01
        System.out.println("##### Instant:");
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(Instant.ofEpochSecond(5600000));
        System.out.println(Instant.ofEpochMilli(3000));
    }

    private static void usandoLocalDateTime(LocalDate agora, LocalTime hora) {
        //LocalDateTime ela representa data e hora no formato yyyy-MM-ddThh:mm:ss
        System.out.println("##### LocalDateTime:");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = LocalDateTime.parse("2050-08-22T15:55:39");
        System.out.println(localDateTime1);
        System.out.println(LocalDateTime.of(agora, hora));
    }

    private static LocalTime usandoLocalTime() {
        // LocalTime representa uma hora - HH:mm:ss
        System.out.println("##### LocalTime:");
        LocalTime hora = LocalTime.now();
        System.out.println(hora);
        LocalTime novaHora = LocalTime.of(16, 30, 25);
        System.out.println(novaHora);
        LocalTime outraHora = LocalTime.parse("17:10:15");
        System.out.println(outraHora);
        return hora;
    }

    private static LocalDate usandoLocalDate() {
        //LocalDate é a repreentação de uma Data - yyyy/MM/dd
        System.out.println("##### LocalDate:");
        LocalDate agora = LocalDate.now();
        System.out.println(agora);
        System.out.println(agora.getYear());
        System.out.println(agora.getMonth());
        System.out.println(agora.getMonth().getValue());
        System.out.println(agora.getMonthValue());
        LocalDate novaData = LocalDate.of(2022, Month.FEBRUARY, 15);
        System.out.println(novaData);
        novaData = novaData.plusDays(2);
        System.out.println(novaData);
        System.out.println(novaData.minusDays(3));
        System.out.println(novaData.plus(6, ChronoUnit.DAYS));
        System.out.println(novaData.plusYears(2).isLeapYear());
        System.out.println(novaData.getDayOfWeek());
        System.out.println(novaData.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate parse = LocalDate.parse("2010-06-04");
        System.out.println(parse);
        return agora;
    }

    private static void usandoCalendar() {
        System.out.println("--Calendar--");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        System.out.println(calendar.getTime());
        System.out.println(calendar.getWeekYear());
        System.out.println(calendar.getWeeksInWeekYear());
        System.out.println(calendar.getCalendarType());
    }

    private static void usandoDate() {
        System.out.println("--DATE--");
        Date data = new Date();
        System.out.println(data);
        data.setMonth(3); // falta de intuitividade
        System.out.println(data);
        System.out.println(data.getYear() + 1900);
    }
}
