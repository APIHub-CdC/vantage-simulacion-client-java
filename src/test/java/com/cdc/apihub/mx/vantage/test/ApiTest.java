package com.cdc.apihub.mx.vantage.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdc.apihub.mx.vantage.client.ApiClient;
import com.cdc.apihub.mx.vantage.client.ApiException;
import com.cdc.apihub.mx.vantage.client.ApiResponse;
import com.cdc.apihub.mx.vantage.client.api.VantAgeApi;
import com.cdc.apihub.mx.vantage.client.model.AportantesPeticion;
import com.cdc.apihub.mx.vantage.client.model.CatalogoContrato;
import com.cdc.apihub.mx.vantage.client.model.CatalogoEstados;
import com.cdc.apihub.mx.vantage.client.model.CatalogoFrecuenciaPago;
import com.cdc.apihub.mx.vantage.client.model.CatalogoProducto;
import com.cdc.apihub.mx.vantage.client.model.DomicilioPeticion;
import com.cdc.apihub.mx.vantage.client.model.NoAportantesPeticion;
import com.cdc.apihub.mx.vantage.client.model.PersonaPeticion;
import com.cdc.apihub.mx.vantage.client.model.Respuesta;


public class ApiTest {
	private Logger logger = LoggerFactory.getLogger(ApiTest.class.getName());
	private ApiClient apiClient = null;
    private final VantAgeApi api = new VantAgeApi();
    private String xApiKey = null;
    
    @Before()
    public void setUp() {
        this.xApiKey = "your_api_key";
        this.apiClient = api.getApiClient();
        this.apiClient.setBasePath("the_url");
    }

	@Test
    public void getVantageAportantesTest() throws ApiException {
        
		AportantesPeticion peticion = new AportantesPeticion();
        
        Integer estatusOK = 200;
        Integer estatusNoContent = 204;
        
        try {
            
            peticion.setFolio("1500001");
            peticion.setTipoContrato(CatalogoContrato.CA);
            peticion.setNumeroCuenta("2000001");
            peticion.setDiasAtraso(21);
            
            ApiResponse<?> response = api.getGenericVantageAportantes(xApiKey, peticion);
  
            Assert.assertTrue(estatusOK.equals(response.getStatusCode()));
            
            if(estatusOK.equals(response.getStatusCode())) {
                Respuesta responseOK = (Respuesta) response.getData();
                logger.info("Vantage Aportantes Test: "+responseOK.toString());
            }
            
        }catch (ApiException e) {
            if(!estatusNoContent.equals(e.getCode())) {
            	logger.info("Error getVantageAportantesTest: "+e.getResponseBody());
            }
            Assert.assertTrue(estatusOK.equals(e.getCode()));
        }        
    }
    
    
    @Test
    public void getVantageNoAportantesTest() throws ApiException {

        PersonaPeticion persona = new PersonaPeticion();
        DomicilioPeticion domicilio = new DomicilioPeticion();
        NoAportantesPeticion request = new NoAportantesPeticion();
        
        Integer estatusOK = 200;
        Integer estatusNoContent = 204;
        
        try {
            
            domicilio.setDireccion("PASADISO ENCONTRADO 1");
            domicilio.setColoniaPoblacion("MONTEVIDEO");
            domicilio.setDelegacionMunicipio("GUSTAVO A MADERO");
            domicilio.setCiudad("CIUDAD DE MÉXICO");
            domicilio.setEstado(CatalogoEstados.CDMX);
            domicilio.setCP("07730");

            persona.setPrimerNombre("JUAN01");
            persona.setApellidoPaterno("PRUEBAP01");
            persona.setApellidoMaterno("PRUEBAM01");
            persona.setFechaNacimiento("1980-01-01");
            persona.setDomicilio(domicilio);

            request.setFolio("1600001");
            request.setTipoProducto(CatalogoProducto.R);
            request.setTipoContrato(CatalogoContrato.TC);
            request.setFrecuenciaPago(CatalogoFrecuenciaPago.M);
            request.setDiasAtraso(21);
            request.setNumeroCuenta("3000001");
            request.setFechaApertura("2019-01-01");
            request.setSaldoActual(15301F);
            request.setPersona(persona);
            
            ApiResponse<?> response = api.getGenericVantageNoAportantes(xApiKey, request);
  
            Assert.assertTrue(estatusOK.equals(response.getStatusCode()));
            
            if(estatusOK.equals(response.getStatusCode())) {
                Respuesta responseOK = (Respuesta) response.getData();
                logger.info("Vantage NO Aportantes Test: "+responseOK.toString());
            }
            
        }catch (ApiException e) {
            if(!estatusNoContent.equals(e.getCode())) {
                logger.info("Error getVantageNoAportantesTest: "+e.getResponseBody());
            }
            Assert.assertTrue(estatusOK.equals(e.getCode()));
        }        
    }
    
}
