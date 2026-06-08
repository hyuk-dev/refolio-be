package web.mvc.advice;


import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import web.mvc.exception.CommonException;
import web.mvc.exception.ErrorCode;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DefaultExceptionAdvice {
//    @ExceptionHandler({CommonException.class})
//    public ProblemDetail signInExceptionHandle(MemberAuthenticationException e){
//        ProblemDetail problemDetail = ProblemDetail.forStatus(e.getHttpStatus().value());
//
//        problemDetail.setTitle(e.getTitle());
//        problemDetail.setDetail(e.getMessage());
//        problemDetail.setProperty("timestamp", LocalDateTime.now());
//
//        return  problemDetail;
//    }
//    public ResponseEntity<?> signInExceptionHandle( e){
//        ProblemDetail problemDetail = ProblemDetail.forStatus(e.getHttpStatus().value());
//
//        problemDetail.setTitle(e.getTitle());
//        problemDetail.setDetail(e.getMessage());
//        problemDetail.setProperty("timestamp", LocalDateTime.now());
//
//        return  ResponseEntity.status(700).body("오류");
//    }

    @ExceptionHandler({CommonException.class})
    public ResponseEntity<?> commonExceptionHandle(CommonException e){
        ErrorCode errorCode = e.getErrorCode();

        ProblemDetail problemDetail = ProblemDetail.forStatus(errorCode.getStatus().value());
        problemDetail.setTitle(errorCode.getStatus().getReasonPhrase());
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return ResponseEntity
                .status(errorCode.getStatus().value())
                .body(problemDetail);
    }






}