package io.vantage.simulacion.mx.client.api;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vantage.simulacion.mx.client.ApiClient;
import io.vantage.simulacion.mx.client.ApiException;
import io.vantage.simulacion.mx.client.model.AportantesPeticion;
import io.vantage.simulacion.mx.client.model.CatalogoContrato;
import io.vantage.simulacion.mx.client.model.CatalogoFrecuenciaPago;
import io.vantage.simulacion.mx.client.model.NoAportantesPeticion;
import io.vantage.simulacion.mx.client.model.PersonaPeticion;
import io.vantage.simulacion.mx.client.model.Respuesta;

public class VantAgeApiTest {
	private Logger logger = LoggerFactory.getLogger(VantAgeApiTest.class.getName());
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
        AportantesPeticion body = new AportantesPeticion();
        body.setFolio("123456");
        body.setFechaProceso("dd/MM/yyyy");
        body.setNumeroCuenta("00000000");
        body.setDiasAtraso(10);
        Respuesta response = api.getVantageAportantes(this.xApiKey, body);
        this.logger.info(response.toString());
    }
    
    
    @Test
    public void getVantageNoAportantesTest() throws ApiException {
    	PersonaPeticion persona = new PersonaPeticion();
    	NoAportantesPeticion body = new NoAportantesPeticion();
    	
    	persona.setPrimerNombre("NOMBRE");
        persona.setApellidoPaterno("PATERNO");
        persona.setApellidoMaterno("MATERNO");
        persona.setFechaNacimiento("1986-06-27");
        body.setFolio("123456");
        body.setFechaProceso("dd/MM/yyyy");
        body.setTipoContrato(CatalogoContrato.AA);
        body.setFrecuenciaPago(CatalogoFrecuenciaPago.N);
        body.setDiasAtraso(10);
        body.setPersona(persona);
        
        Respuesta response = api.getVantageNoAportantes(this.xApiKey, body);
        this.logger.info(response.toString());
    }
    
}
