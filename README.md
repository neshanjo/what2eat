# what2eat

Sample app for demonstrating architecture design in SWAR lecture (HTWG WIN)

## Getting started

- Create an account at [WeatherBit](https://www.weatherbit.io/) and note the API key
- In `src/main/resources`, **copy** (not rename) the file `application.sample.properties` to `application.properties`
  and enter the API key as `weatherBitApiKey`.
- Start the application by running `src/main/java/de/schneider21/what2eat/Application.java`

## TODOs

1. Spring comes with its own REST client. Hence

- remove okhttp dependency
- In `MensaKlService` and `WeatherBitService`, use [RestTemplate](https://www.baeldung.com/rest-template) or (
  preferably) [WebClient](https://www.baeldung.com/spring-5-webclient) instead of OkHttpClient. Note that the mensa-kl
  API returns the wrong application-type in the response. That's why it might be necessary to get the response first as
  a string and do the JSON-to-Object mapping manually. Better start first with the WeatherBit API.