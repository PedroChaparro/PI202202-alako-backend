FROM python:3.10.6

RUN mkdir -p /usr/src/api
WORKDIR /usr/src/api

COPY ./vectorize_api .

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
RUN python src/model.py
RUN rm src/model.py

EXPOSE 5050

CMD ["python", "./src/api.py"]