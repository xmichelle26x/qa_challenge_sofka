Feature: PetStore - Actualizar mascota

  Background:
    * url baseUrl

  @smoke @update
  Scenario: Actualizar el nombre y estatus de la mascota a "sold"
    * def petId = 987654
    * def petName = 'Firulais'
    * def newName = 'Firulais Vendido'
    
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
    
    # 2. Actualizar el nombre y status
    Given path 'pet', petId
    And header Content-Type = 'application/x-www-form-urlencoded'
    And form field name = newName
    And form field status = 'sold'
    When method post
    Then status 200
    
    # 3. Verificar la actualización con un GET
    Given path 'pet', petId
    When method get
    Then status 200
    
    # Validación del contrato completo post-actualización
    And match response ==
    """
    {
      "id": #(petId),
      "name": "#(newName)",
      "status": "sold",
      "photoUrls": ["https://example.com/firulais.jpg"],
      "tags": [],
      "category": ##null
    }
    """
    
    And print 'Mascota actualizada:', response.name, '- Status:', response.status