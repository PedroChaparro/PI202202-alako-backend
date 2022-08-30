## Instalation

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
