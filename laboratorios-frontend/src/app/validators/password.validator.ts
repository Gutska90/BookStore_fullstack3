import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

/**
 * Validador personalizado para contraseñas con 6 validaciones:
 * 1. Longitud mínima: 8 caracteres
 * 2. Longitud máxima: 50 caracteres
 * 3. Al menos una letra mayúscula
 * 4. Al menos una letra minúscula
 * 5. Al menos un número
 * 6. Al menos un carácter especial (!@#$%^&*)
 */
export function passwordValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    
    if (!value) {
      return null; // Dejar que required maneje esto
    }
    
    const errors: ValidationErrors = {};
    
    // Validación 1: Longitud mínima
    if (value.length < 8) {
      errors['minLength'] = { message: 'La contraseña debe tener al menos 8 caracteres' };
    }
    
    // Validación 2: Longitud máxima
    if (value.length > 50) {
      errors['maxLength'] = { message: 'La contraseña no puede tener más de 50 caracteres' };
    }
    
    // Validación 3: Al menos una mayúscula
    if (!/[A-Z]/.test(value)) {
      errors['uppercase'] = { message: 'La contraseña debe contener al menos una letra mayúscula' };
    }
    
    // Validación 4: Al menos una minúscula
    if (!/[a-z]/.test(value)) {
      errors['lowercase'] = { message: 'La contraseña debe contener al menos una letra minúscula' };
    }
    
    // Validación 5: Al menos un número
    if (!/\d/.test(value)) {
      errors['digit'] = { message: 'La contraseña debe contener al menos un número' };
    }
    
    // Validación 6: Al menos un carácter especial
    if (!/[!@#$%^&*]/.test(value)) {
      errors['specialChar'] = { message: 'La contraseña debe contener al menos un carácter especial (!@#$%^&*)' };
    }
    
    return Object.keys(errors).length > 0 ? errors : null;
  };
}

