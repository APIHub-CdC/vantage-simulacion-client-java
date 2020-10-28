# vantage-simulacion-client-java
Es un modelo que segmenta a los clientes morosos en 6 calificaciones, de acuerdo al avance esperado de mora en los siguientes 30 días. Reduce costos de gestión y administración de cartera morosa al segmentar a la población.

## Requisitos

1. Java >= 1.7
2. Maven >= 3.3

## Instalación

Para la instalación de las dependencias se deberá ejecutar el siguiente comando:

```shell
mvn install -Dmaven.test.skip=true
```

> **NOTA:** Este fragmento del comando *-Dmaven.test.skip=true* evitará que se lance la prueba unitaria.


## Guía de inicio

### Paso 1. Agregar el producto a la aplicación

Al iniciar sesión seguir los siguientes pasos:

 1. Dar clic en la sección "**Mis aplicaciones**".
 2. Seleccionar la aplicación.
 3. Ir a la pestaña de "**Editar '@tuApp**' ".
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/edit_applications.jpg" width="900">
    </p>
 4. Al abrirse la ventana emergente, seleccionar el producto.
 5. Dar clic en el botón "**Guardar App**":
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/selected_product.jpg" width="400">
    </p>

### Paso 2. Capturar los datos de la petición

Los siguientes datos a modificar se encuentran en ***src/test/java/com/cdc/apihub/mx/vantage/test/ApiTest.java***

Es importante contar con el setUp() que se encargará de inicializar la petición. Por tanto, se debe modificar la URL (**urlApi**); y la API KEY (**xApiKey**), como se muestra en el siguiente fragmento de código:

```java
@Before()
public void setUp() {
    this.xApiKey = "your_api_key";
    this.apiClient = api.getApiClient();
    this.apiClient.setBasePath("the_url");
}
```

La petición se deberá modificar el siguiente fragmento de código con los datos correspondientes:

> **NOTA:** Para más ejemplos de simulación, consulte la colección de Postman.

```java
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
```

### Paso 3. Ejecutar la prueba unitaria

Teniendo los pasos anteriores ya solo falta ejecutar la prueba unitaria, con el siguiente comando:

```shell
mvn test -Dmaven.install.skip=true
```
