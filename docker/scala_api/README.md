# Instructions to test

## Testing /search route

1. You need to install Scala in your PC. Sometimes you have to install sbt if during the next steps the command sbt appears that is not in your system
2. Go to the folder scala_api
3. Open another terminal and go to the folder of vectorize_api and follow the instruccions in the Readme there
4. For this step the API in Python must be running. Now in the directory from step 2 execute ```sbt run```
(Some folders like **project** and **target** will appear)
5. Using some API Client like Postman or Insomnia make a POST request to the following url http://localhost:9000/search make sure yourself that you put a Json in the body, like this:
```
{
	"search-criteria": "gatitos"
}
```
6. For now, if everything went ok you will see in the console a print of a json with a vector and a UUID
7. For stop the proccess just press ```ctrl + c```
