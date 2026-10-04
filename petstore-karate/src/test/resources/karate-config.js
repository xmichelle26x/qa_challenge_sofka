function fn() {
  var env = karate.env || 'dev';
  karate.log('karate.env system property was:', env);

  var config = {
    baseUrl: 'https://petstore.swagger.io/v2'
  };

  if (env === 'qa') {
    config.baseUrl = 'https://petstore.swagger.io/v2';
  }

  return config;
}