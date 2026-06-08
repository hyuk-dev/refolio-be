package web.mvc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends  RuntimeException{
    private ErrorCode errorCode;
    public CommonException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
