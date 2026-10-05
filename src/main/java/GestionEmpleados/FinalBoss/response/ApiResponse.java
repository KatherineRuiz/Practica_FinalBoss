package GestionEmpleados.FinalBoss.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T dta) {
        this.success = success;
        this.message = message;
        this.data = dta;
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
