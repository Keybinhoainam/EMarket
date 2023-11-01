package DoAn.B19DCCN445.EMarket.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import DoAn.B19DCCN445.EMarket.exception.ProductNotFoundException;
import DoAn.B19DCCN445.EMarket.exception.UserAlreadyExistException;
import DoAn.B19DCCN445.EMarket.exception.UserNotFoundException;



@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleUnexpectedTypeException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({DataIntegrityViolationException.class,HttpMessageNotWritableException.class})
	public Map<String, String> handle505(Exception ex){
		Map<String, String> errorMap=new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({UserAlreadyExistException.class,UserNotFoundException.class,
		HttpMessageNotReadableException.class, ProductNotFoundException.class
		})
	public Map<String, String> handleBadRequest(Exception ex){
		Map<String, String> errorMap=new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler({BadCredentialsException.class,NoSuchElementException.class})
	public Map<String, String> handleForbidden(Exception ex){
		Map<String, String> errorMap=new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
}
