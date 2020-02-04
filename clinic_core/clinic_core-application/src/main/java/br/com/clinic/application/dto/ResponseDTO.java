package br.com.clinic.application.dto;

import br.com.clinic.domain.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.List;

import static br.com.clinic.application.utils.Constants.NUMBER_ONE_HUNDRED;
import static br.com.clinic.application.utils.Constants.NUMBER_ZERO;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    @JsonProperty("status")
    private int status = HttpStatus.OK.value();

    @JsonProperty("message")
    private String message = HttpStatus.OK.getReasonPhrase();

    @JsonProperty("errors")
    private List<ErrorDTO> errors;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("responseData")
    private List<T> responseData;

    @JsonProperty("requestData")
    private T requestData;

    public ResponseDTO() { }

    public ResponseDTO(final String offset, final String pageSize) {
        validOffsetAndPageSize(offset, pageSize);
    }

    public ResponseDTO(final List<ErrorDTO> errors) {
        if (isNotEmpty(errors)) {
            this.errors = errors;
            this.setStatus(HttpStatus.BAD_REQUEST.value());
            this.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<T> responseData) {
        this.responseData = responseData;
    }

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }

    private void validOffsetAndPageSize(final String offset, final String pageSize) {
        try {
            this.offset = Integer.parseInt(offset);
            if (this.offset.compareTo(NUMBER_ZERO) < NUMBER_ZERO){
                this.offset = NUMBER_ZERO;
            }
        } catch (Exception e) {
            this.offset = NUMBER_ZERO;
        }

        try {
            this.pageSize = Integer.parseInt(pageSize);
            if (this.pageSize.compareTo(NUMBER_ZERO) < NUMBER_ZERO || this.pageSize.compareTo(NUMBER_ONE_HUNDRED) > NUMBER_ZERO) {
                this.pageSize = NUMBER_ONE_HUNDRED;
            }
        } catch (Exception e) {
            this.pageSize = NUMBER_ONE_HUNDRED;
        }
    }

    public ResponseDTO analyseExcetion(Exception ex) {

        final ResponseDTO responseDTO = new ResponseDTO();

        if (ex.getMessage().contains("error")) {
            responseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        } else {
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }

        return responseDTO;
    }

}
