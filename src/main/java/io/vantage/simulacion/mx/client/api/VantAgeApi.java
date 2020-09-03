package io.vantage.simulacion.mx.client.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import io.vantage.simulacion.mx.client.ApiClient;
import io.vantage.simulacion.mx.client.ApiException;
import io.vantage.simulacion.mx.client.ApiResponse;
import io.vantage.simulacion.mx.client.Configuration;
import io.vantage.simulacion.mx.client.Pair;
import io.vantage.simulacion.mx.client.ProgressRequestBody;
import io.vantage.simulacion.mx.client.ProgressResponseBody;
import io.vantage.simulacion.mx.client.model.AportantesPeticion;
import io.vantage.simulacion.mx.client.model.NoAportantesPeticion;
import io.vantage.simulacion.mx.client.model.Respuesta;

public class VantAgeApi {
	private ApiClient apiClient;

	public VantAgeApi() {
		this(Configuration.getDefaultApiClient());
	}

	public VantAgeApi(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public okhttp3.Call getVantageAportantesCall(String xApiKey, AportantesPeticion body,
			final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody = body;
		String localVarPath = "/aportantes";
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		if (xApiKey != null)
			localVarHeaderParams.put("x-api-key", apiClient.parameterToString(xApiKey));
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();
		final String[] localVarAccepts = { "application/json" };
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
		if (localVarAccept != null)
			localVarHeaderParams.put("Accept", localVarAccept);
		final String[] localVarContentTypes = { "application/json" };
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);
		if (progressListener != null) {
			apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
				@Override
				public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
					okhttp3.Response originalResponse = chain.proceed(chain.request());
					return originalResponse.newBuilder()
							.body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
				}
			});
		}
		String[] localVarAuthNames = new String[] {};
		return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
				localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

	private okhttp3.Call getVantageAportantesValidateBeforeCall(String xApiKey, AportantesPeticion body,
			final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		if (xApiKey == null) {
			throw new ApiException("Missing the required parameter 'xApiKey' when calling getVantageAportantes(Async)");
		}
		if (body == null) {
			throw new ApiException("Missing the required parameter 'body' when calling getVantageAportantes(Async)");
		}

		okhttp3.Call call = getVantageAportantesCall(xApiKey, body, progressListener, progressRequestListener);
		return call;
	}

	public Respuesta getVantageAportantes(String xApiKey, AportantesPeticion body) throws ApiException {
		ApiResponse<Respuesta> resp = getVantageAportantesWithHttpInfo(xApiKey, body);
		return resp.getData();
	}

	public ApiResponse<Respuesta> getVantageAportantesWithHttpInfo(String xApiKey, AportantesPeticion body)
			throws ApiException {
		okhttp3.Call call = getVantageAportantesValidateBeforeCall(xApiKey, body, null, null);
		Type localVarReturnType = new TypeToken<Respuesta>() {
		}.getType();
		return apiClient.execute(call, localVarReturnType);
	}

	public okhttp3.Call getVantageNoAportantesCall(String xApiKey, NoAportantesPeticion body,
			final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody = body;
		String localVarPath = "/noAportantes";
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		if (xApiKey != null)
			localVarHeaderParams.put("x-api-key", apiClient.parameterToString(xApiKey));
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();
		final String[] localVarAccepts = { "application/json" };
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
		if (localVarAccept != null)
			localVarHeaderParams.put("Accept", localVarAccept);
		final String[] localVarContentTypes = { "application/json" };
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);
		if (progressListener != null) {
			apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
				@Override
				public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
					okhttp3.Response originalResponse = chain.proceed(chain.request());
					return originalResponse.newBuilder()
							.body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
				}
			});
		}
		String[] localVarAuthNames = new String[] {};
		return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
				localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

	private okhttp3.Call getVantageNoAportantesValidateBeforeCall(String xApiKey, NoAportantesPeticion body,
			final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		if (xApiKey == null) {
			throw new ApiException(
					"Missing the required parameter 'xApiKey' when calling getVantageNoAportantes(Async)");
		}
		if (body == null) {
			throw new ApiException("Missing the required parameter 'body' when calling getVantageNoAportantes(Async)");
		}

		okhttp3.Call call = getVantageNoAportantesCall(xApiKey, body, progressListener, progressRequestListener);
		return call;
	}

	public Respuesta getVantageNoAportantes(String xApiKey, NoAportantesPeticion body) throws ApiException {
		ApiResponse<Respuesta> resp = getVantageNoAportantesWithHttpInfo(xApiKey, body);
		return resp.getData();
	}

	public ApiResponse<Respuesta> getVantageNoAportantesWithHttpInfo(String xApiKey, NoAportantesPeticion body)
			throws ApiException {
		okhttp3.Call call = getVantageNoAportantesValidateBeforeCall(xApiKey, body, null, null);
		Type localVarReturnType = new TypeToken<Respuesta>() {
		}.getType();
		return apiClient.execute(call, localVarReturnType);
	}
}
