# Instructions to test

## Testing /search route

1. You need to install Scala in your PC. Sometimes you have to install sbt if during the next steps the command sbt appears that is not in your system

2. Go to the folder scala_api

3. Open another terminal and go to the folder of vectorize_api and follow the instruccions in the Readme there

4. For this step the API in Python must be running. Now in the directory from step 2 execute `sbt run`
   (Some folders like **project** and **target** will appear)

5. Using some API Client like Postman or Insomnia make a POST request to the following url http://localhost:9000/search make sure yourself that you put a Json in the body, like this:

```
{
	"search-criteria": "gatitos"
}
```

6. For now, if everything went ok you will see in the console a print of a json with a vector and a UUID

7. For stop the proccess just press `ctrl + c`

## Testing /result/save route

1. From `docker/scala_api` run `sbt run` or `sbt "run 9090"` to specify your own port.

2. You should send a JSON payload like this:

```
{
  "key": "123e4567-e89b-12d3-a456-426614174000",
  "results": [
    {
      "url": "https://www.youtube.com/watch?v=xzy4umDtA88",
      "title": "Top 10 Beautiful Places to Visit in Colombia - Colombia Travel Video",
      "tags": "Colombia, Colombia travel, Colombia travel guide, Colombia travel video, Colombia tourism, Colombia places to visit, top 10 beautiful places to visit in Colombia",
      "thumbnail": "https://i.ytimg.com/vi/xzy4umDtA88/hqdefault.jpg",
      "cosine_similarity": 0.5871344
    }
  ]
}
```

3. Successfull response looks like this (But you can also get 400 and 500 responses):

`{ "error": false, "message": "Response was saved successfully" }`

## Testing /result/obtain route

1. From `docker/scala_api` run `sbt run` or `sbt "run 9090"` to specify your own port.

2. You should send a JSON payload like this:

`{ "key": "123e4567-e89b-12d3-a456-426614174000" }`

3. If the provided key exists on the map, you'll get a 200 response like this: (But you can also get 400, 404 and 500 responses)

`{ "error": false, "message": "OK", "response": [ { "url": "https://www.youtube.com/watch?v=xzy4umDtA88", "title": "Top 10 Beautiful Places to Visit in Colombia - Colombia Travel Video", "tags": "Colombia, Colombia travel, Colombia travel guide, Colombia travel video, Colombia tourism, Colombia places to visit, top 10 beautiful places to visit in Colombia", "thumbnail": "https://i.ytimg.com/vi/xzy4umDtA88/hqdefault.jpg", "cosine_similarity": 0.5871344 } ] }`
