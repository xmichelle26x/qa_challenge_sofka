Feature: PetStore - Añadir mascota

  Background:
    * url baseUrl

  @smoke @add
  Scenario: Añadir una mascota a la tienda
    * def petId = 987654
    * def petName = 'Firulais'
    
    Given path 'pet'
    And request
    """
    {
      "id": #(petId),
      "name": "#(petName)",
      "status": "available",
      "photoUrls": ["https://example.com/firulais.jpg"],
      "category": {
        "id": 1,
        "name": "Perros"
      },
      "tags": [
        { "id": 1, "name": "amigable" }
      ]
    }
    """
    When method post
    Then status 200
    
    # Validación del contrato completo del response
    And match response ==
    """
    {
      "id": #(petId),
      "name": "#(petName)",
      "status": "available",
      "photoUrls": ["https://example.com/firulais.jpg"],
      "category": {
        "id": 1,
        "name": "Perros"
      },
      "tags": [
        { "id": 1, "name": "amigable" }
      ]
    }
    """
    
    And print 'Mascota creada con ID:', response.id