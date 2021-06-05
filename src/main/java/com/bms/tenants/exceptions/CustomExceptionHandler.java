package com.bms.tenants.exceptions;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shubham
 *
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TenantBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	protected ResponseEntity<Object> handleTenantBadRequestException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return handleExceptionInternal(ex, null, headers, status, request);
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public void handleBookMyShowBadRequestException(HttpServletResponse response, MethodArgumentNotValidException ex)
//			throws IOException {
//		List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//		response.sendError(0);
//	}

//	@ExceptionHandler(NoHandlerFoundException.class)
//	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
//
//		return new ResponseEntity<>("test", HttpStatus.OK);
//	}

//	@Override
//	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		return handleExceptionInternal(ex, "test", headers, status, request).status(status)
//				.contentType(MediaType.APPLICATION_JSON).build();
////		 return new ResponseEntity<>("test", status);
//	}
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error(ex.getLocalizedMessage(), ex);
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
//		BaggageField.create("user-id").updateValue("test-user");
//		MDC.put("TRANS_ID", "user-id");
//		tracer.currentSpan().context().traceIdString()
//		tracer.activeSpan().context().toTraceId();
		body = new Error(status, ex.getMessage(), ex, servletWebRequest.getRequest().getRequestURI(),
				servletWebRequest.getRequest().getMethod(), MDC.get("trace-id"));
		return new ResponseEntity<>(body, headers, status);
	}

}
