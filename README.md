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

Los siguientes datos a modificar se encuentran en ***src/test/java/io/vantage/simulacion/mx/client/api/VantAgeApiTest.java***

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

```java
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
```

### Paso 3. Ejecutar la prueba unitaria

Teniendo los pasos anteriores ya solo falta ejecutar la prueba unitaria, con el siguiente comando:

```shell
mvn test -Dmaven.install.skip=true
```
