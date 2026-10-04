Feature: PetStore - Consultar mascotas por status

  Background:
    * url baseUrl

  @smoke @find
  Scenario: Consultar la mascota modificada por status "sold"
    * def petId = 987654
    * def petName = 'Firulais'
    
    # 1. Asegurar que la mascota existe
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
    
    # 2. Actualizar a "sold"
    Given path 'pet', petId
    And header Content-Type = 'application/x-www-form-urlencoded'
    And form field name = petName
    And form field status = 'sold'
    When method post
    Then status 200
    
    # 3. Buscar por status "sold"
    Given path 'pet', 'findByStatus'
    And param status = 'sold'
    When method get
    Then status 200
    And match response == '#[]'
    
    # 4. Filtrar por ID y verificar
    * def found = karate.filter(response, function(p){ return p.id == petId })
    * match found[0].id == petId
    * match found[0].name == petName
    * match found[0].status == 'sold'
    And print 'Mascota encontrada con status sold:', found[0].name