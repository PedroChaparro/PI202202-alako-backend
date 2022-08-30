FROM python:latest

RUN mkdir -p /usr/src/api
WORKDIR /usr/src/api

COPY . .

RUN pip install --upgrade pip
RUN pip install -r requirements.txt

EXPOSE 5050

CMD ["python", "./src/api.py"]