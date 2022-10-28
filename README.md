# PI202202-alako-backend

This repository contains all the files related to project's data collection, data normalization / cleansing and database management. 

- 🎨 You can find front-end repository [here](https://github.com/PedroChaparro/PI202202-alako-frontend)
- 📜 You can find data repository [here](https://github.com/PedroChaparro/PI202202-alako-data)

## Subjects / topics

This project includes the following college subjects: Web development, TI design and management, Artificial Intelligence.

## Tech Stack

- Docker, docker-compose.
- Python: [sentence-transformers](https://www.sbert.net/docs/package_reference/models.html), Bottle Web Framework.
- Scala: Play Web Framework, org.apache.spark.sql, org.apache.spark.launcher.SparkLauncher. 
- Apache Spark.
- Apache Hadoop.

## Results

- Docker images and docker-compose files (See [this](https://github.com/PedroChaparro/PI202202-alako-backend/tree/main/docker) folder for more details): 

<p align="center">
  <img src="https://user-images.githubusercontent.com/62714297/198424196-7361624e-8f5b-4c1d-a26d-7c9f4417f59a.jpg" width="680px"/>
</p>

- Vectorize api: Get user query's embeddings read [this](https://arxiv.org/abs/1908.10084) for theorical context and view the used code [here](https://github.com/PedroChaparro/PI202202-alako-backend/tree/main/docker/vectorize_api).

<p align="center">
  <img src="https://user-images.githubusercontent.com/62714297/198428042-e1f5de41-217b-448f-b92d-756d610e6c55.jpg" width="680px"/>
</p>

- Search algorithm (See [this](https://github.com/PedroChaparro/PI202202-alako-backend/tree/main/docker/cosine) folder for more details):

The following is an example of the results obtained after executing the cosine-similarity algorithm on Apache Spark cluster:

<p align="center">
  <img src="https://user-images.githubusercontent.com/62714297/198425156-a3d0fab8-a92c-4fc2-b948-f49e1bbd0dc3.jpg" width="680px"/>
</p>
