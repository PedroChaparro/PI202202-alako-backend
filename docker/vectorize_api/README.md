## Local Instalation

### (Optional) Create a python virtualenv

Install virtualenv

```
pip install virtualenv
```

Create a new environment

```
virtualenv -p python3 name
```

Activate the environment (Linux)

```
source ./name/bin/activate
```

Activate the environment (Windows)

```
./name/Scripts/activate
```

### Install dependencies

Bottle package has no dependencies other than the Python Standart Library. [See bottlepy here](https://bottlepy.org/docs/dev/). But sentence-transformers module has numerous dependencies, such numpy or scikit-learn, so, you should install these dependencies by using:

```
pip install -r requirements.txt
```

### Executing

To start the API run:

```
python ./src/api.py
```

## Docker installation 🐋

### Build the image

**Note:** If you want the API be only accessible inside the container change `0.0.0.0` to `localhost` as host in `./src/api.py` file.

From `./vectorize_api` folder:

```
docker build -t alako/vectorize_api .
```

### Run the image

```
docker run -p 5050:5050 alako/vectorize_api
```

### Access through localhost

You can access the api endpoing through `localhost:5050`
