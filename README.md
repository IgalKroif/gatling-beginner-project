# Gatling Stress Testing API Project

This project is designed for stress testing the [FakeStoreAPI](https://fakestoreapi.com) using Gatling. The project includes models for Products and Carts, checks, and generates detailed reports. Additionally, it incorporates CSV feeders and files to generate dynamic IDs for testing purposes.

## In the 'master' branch you will find : 

## Project Structure

- `src/test/scala`: Contains the Gatling simulation files.
  - `CartStressTest.java`
  - `ProductStressTest.java`
  - `ProductStressHighUserCountTests.java`
- `src/test/resources`: Includes necessary resources, such as a OneNote notebook and CSV files for dynamic ID generation.
- `target`: Stores the generated reports after test execution.

## Prerequisites

- Java 11, 17 ,21
- Maven

## Setup Instructions

1. **Clone the repository**:
   ```sh
   git clone https://github.com/your-username/gatling-stress-test.git
   cd gatling-stress-test
   ```

2. **Add your OneNote notebook to the `src/test/resources` folder**.

3. **Add CSV files** for dynamic ID generation to the `src/test/resources` folder.

## Running the Tests

To execute the stress tests, use the following Maven command:

```sh
mvn gatling:test
mvn gatling:test -Dgatling.simulationClass=path.to.your.SimulationClass
mvn gatling:test -Dgatling.simulationClass=path.to.your.CartStressTest
```

## Test Classes

The project includes the following test classes:

### 1. `CartStressTest.java`

This class tests the `/carts` endpoint for performance under various load conditions.

### 2. `ProductStressTest.java`

This class tests the `/products` endpoint for performance and response validation.

### 3. `ProductStressHighUserCountTests.java`

This class performs high user count stress tests on the `/products` endpoint to evaluate system behavior under extreme conditions.

## Reports

After running the tests, detailed reports will be generated and stored in the `target/gatling` directory. These reports include various metrics and visualizations to help you analyze the performance of the API under stress.

## CSV Feeders

The project uses CSV feeders to dynamically generate IDs for products and carts during testing. Ensure the CSV files are correctly placed in the `src/test/resources` folder.

## Additional Resources

The `src/test/resources` folder includes a personal OneNote notebook that contains notes and insights about the testing process and methodology used in this project.

## Contributing

Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.
