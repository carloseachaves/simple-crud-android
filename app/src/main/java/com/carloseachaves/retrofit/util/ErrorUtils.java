package com.carloseachaves.retrofit.util;

import com.carloseachaves.retrofit.api.ApiClient;
import com.carloseachaves.retrofit.model.APIgeeResponseError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;


public class ErrorUtils {

    public static APIgeeResponseError parseError(Response<?> response) {

        Converter<ResponseBody, APIgeeResponseError> converter =
                ApiClient.getClientAPIgee().responseBodyConverter(APIgeeResponseError.class, new Annotation[0]);

        APIgeeResponseError error;

        try {
            error = converter.convert(response.errorBody());
            error.setCode(response.code());
            error.setMessage(response.message());
        } catch (IOException e) {
            return new APIgeeResponseError();
        }

        return error;
    }
}
