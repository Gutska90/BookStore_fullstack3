package com.laboratorios.usuarios.service;

import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

/**
 * Servicio para validar contraseñas según los requisitos:
 * 1. Longitud mínima: 8 caracteres
 * 2. Longitud máxima: 50 caracteres
 * 3. Al menos una letra mayúscula
 * 4. Al menos una letra minúscula
 * 5. Al menos un número
 * 6. Al menos un carácter especial (!@#$%^&*)
 */
@Service
public class PasswordValidatorService {
    
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 50;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[!@#$%^&*].*");
    
    public void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        
        if (password.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("La contraseña debe tener al menos " + MIN_LENGTH + " caracteres");
        }
        
        if (password.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("La contraseña no puede tener más de " + MAX_LENGTH + " caracteres");
        }
        
        if (!UPPERCASE_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una letra mayúscula");
        }
        
        if (!LOWERCASE_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una letra minúscula");
        }
        
        if (!DIGIT_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La contraseña debe contener al menos un número");
        }
        
        if (!SPECIAL_CHAR_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La contraseña debe contener al menos un carácter especial (!@#$%^&*)");
        }
    }
}

