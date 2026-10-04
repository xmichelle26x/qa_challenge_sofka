Feature: PetStore - Consultar mascota por ID

  Background:
    * url baseUrl

  @smoke @get
  Scenario: Consultar la mascota ingresada previamente
    * def petId = 987654
    * def petName = 'Firulais'
    
    # 1. Asegurar que la mascota existe con el estado esperado
    Given path 'pet'
    And request
    """
    {
      "id": #(petId),
      "name": "#(petName)",
      "status": "available",
      "photoUrls": ["https://example.com/firulais.jpg"]
    }
    """
    When method post
    Then status 200
    
    # 2. Consultar la mascota por ID
    Given path 'pet', petId
    When method get
    Then status 200
    
    # 3. Validación del contrato completo del response
    And match response ==
    """
    {
      "id": #(petId),
      "name": "#(petName)",
      "status": "available",
      "photoUrls": ["https://example.com/firulais.jpg"],
      "tags": [],
      "category": ##null
    }
    """
    
    And print 'Mascota consultada:', response.name, '- Status:', response.status