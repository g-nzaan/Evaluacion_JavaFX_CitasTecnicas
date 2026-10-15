package com.tecnoasistencia.utilidad;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.regex.Pattern;

public final class Validaciones {
    public static final List<String> ESTADOS = List.of("Pendiente", "En proceso", "Finalizada", "Cancelada");
    public static final DateTimeFormatter HORA = DateTimeFormatter.ofPattern("HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final Pattern CORREO = Pattern.compile(
            "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                    + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z]{2,}$");
    private Validaciones() { }
    public static boolean correoValido(String correo) {
        return correo != null && correo.trim().length() <= 120 && CORREO.matcher(correo.trim()).matches();
    }
    public static LocalTime leerHora(String texto) {
        if (texto == null || !texto.trim().matches("\\d{2}:\\d{2}"))
            throw new IllegalArgumentException("Escriba la hora con formato HH:mm. Ejemplo: 09:30.");
        try { return LocalTime.parse(texto.trim(), HORA); }
        catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException("La hora debe estar entre 00:00 y 23:59.");
        }
    }
    public static String textoObligatorio(String texto, String campo, int maximo) {
        if (texto == null || texto.isBlank()) throw new IllegalArgumentException(campo + " es obligatorio.");
        String valor = texto.trim();
        if (valor.length() > maximo) throw new IllegalArgumentException(campo + " admite hasta " + maximo + " caracteres.");
        return valor;
    }
}