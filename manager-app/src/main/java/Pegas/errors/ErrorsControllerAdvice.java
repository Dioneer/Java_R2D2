package Pegas.errors;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorsControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(NoSuchElementException.class)
    private String handleNoSuchElementException(NoSuchElementException exception, Model model, Locale locale) {
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(), new Object[0],
                exception.getMessage(), locale));
        return "errors/404";
    }
    @ExceptionHandler(BadRequestException.class)
    private String handleBadRequestException(BadRequestException exception, Model model, Locale locale) {
        model.addAttribute("error", exception.getErrors().stream()
                .map(i->messageSource.getMessage(i, new Object[0], locale))
                .toList());
        return "errors/404";
    }
}
